package hugoRT;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Algorithm 2 of MBFair
 *
 * @author sfa
 *
 */
public class Algorithm2 {

	public long checktime = 0;
	public List <String> ltlClauses = new ArrayList<>();
	private Initializations ini;
	private DecisionDataAttributes dda;
	private TransitionSystem trans;
	public List<HashMap> result = new ArrayList<>();
	public String error = null;
	public String valid = "invalid";
	Command cmd;

	/**
	 *
	 * @param ini initialization
	 * @param dda decision data attribute
	 * @param trans transition system
	 * @param cmd Command
	 */
	public Algorithm2(Initializations ini, DecisionDataAttributes dda, TransitionSystem trans, Command cmd) {
		this.ini = ini;
		this.dda = dda;
		this.trans = trans;
		this.cmd = cmd;
	}

	/**
	 * Executes Algorithm 2
	 * @throws IOException
	 */
	public void run () throws IOException {
		List<String> violationList = new ArrayList<>();
		List<HashMap> algorithm_2_results = new ArrayList<>();

		System.out.println("Decision data atribs are:  " + dda.decisionDataAttributes);
		for (int i = 0; i < ini.getInitialization().size(); i++) {
			StringBuffer iniBuff = new StringBuffer();
			for (String d : dda.decisionDataAttributes.keySet()) {
				String[] distictColumnNames = dda.getDistinctColumnVals(d);
				List<String> checkList = new ArrayList<>();
				for(int vd = 0; vd < distictColumnNames.length; vd ++) {
					String ltlClause = this.cmd.generateLtlClauseAlgo2(d, (String) distictColumnNames[vd]);
					if(!ltlClause.equals("")) {
						if(!ltlClauses.contains(ltlClause)) {
							this.ltlClauses.add(ltlClause);
						}
						this.cmd.deletePanFiles();
						// opening file with standard content
						String projectPath = new File("").getAbsolutePath();
						// creating directory for cached files

						String contents = null;

						Path path_to_standard_initializations = Paths.get(projectPath + "\\cache\\promella_standards" + "\\algorithm2\\initialiazation_" + (String) ini.getInitialization().get(i).get("__Ini_ID") + ".pml");
						contents = new String(Files.readAllBytes(path_to_standard_initializations));

						// adding ltl clause

						contents = contents + "\n" + ltlClause;
						String fileName = i + "_" + d + "_" + distictColumnNames[vd] + ".pml";
						trans.saveInitialization(contents, fileName);
						String command1 = "spin -a filename";
						String command2 = "gcc -DMEMLIM=1024 -O2 -DXUSAFE -w -o pan pan.c";
						String command3 = "./pan -m10000  -a -n -c1 -N formula1";

						command1 = command1.replace("filename", "cache\\promella_initializations\\algorithm2\\" + "initialiazation_" + fileName);

						long startCheckTime = System.nanoTime();
						this.cmd.executeShellCommand(command1);
						this.cmd.executeShellCommand(command2);
						String resultString = this.cmd.executeShellCommand(command3);
						long endCheckTime = System.nanoTime();
						this.checktime += endCheckTime -  startCheckTime;

						System.out.println("LTL Clause: " + ltlClause);
						System.out.println("Initialization: " + iniBuff.toString());
						System.out.println(resultString);
						HashMap<String, String> local_result = new HashMap<String, String>();
						local_result.put("__Ini_ID", (String) ini.getInitialization().get(i).get("__Ini_ID"));
						local_result.put("decision_data_attribute", d);
						local_result.put("value", (String) distictColumnNames[vd]);
						if(resultString.contains("errors: 0")) {
							checkList.add("valid");
							local_result.put("result", "valid");
						}
						else {
							local_result.put("result", "invalid");
						}

						algorithm_2_results.add(local_result);
						System.out.println("local results");
						System.out.println(local_result);
					}
				}

				if(!checkList.contains("valid")) {
					violationList.add(d);
				}
			}

		}

		System.out.println("Algorithm 2 results");
		System.out.println(algorithm_2_results);

		if(violationList.isEmpty()) {
			System.out.println("Precondition PC2 is valid.");
			this.result = algorithm_2_results;
			this.valid = "valid";
		}
		else {
			this.error = "Precondition PC2 is not valid. The following decision data attributes violate precondition PC2: " + violationList;
			System.out.println(this.error);
		}

	}

}
