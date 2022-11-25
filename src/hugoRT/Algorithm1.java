package hugoRT;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * This class implements algorithm 1 of MBFair
 *
 * @author sfa
 * @since 05.2022
 *
 */
public class Algorithm1{

	Initializations ini;
	ProtectedCharacteristics prot;
	TransitionSystem trans;
	public List<String> ltlClauses = new ArrayList<>();;
	public long checktime = 0;
	public String valid = "invalid";
	public String error = null;
	Command cmd;

	/**
	 *
	 * @param ini Initialization
	 * @param prot Protected characteristics
	 * @param trans transition system
	 * @param cmd Command
	 */
	public Algorithm1(Initializations ini, ProtectedCharacteristics prot, TransitionSystem trans, Command cmd) {
		this.ini = ini;
		this.prot = prot;
		this.trans = trans;
		this.cmd = cmd;
	}

	/**
	 * Executes Algorithm 1
	 * @throws IOException
	 */
	public void run() throws IOException {

		List<String> changedList = new ArrayList<>();
		// Creating command object

		// end test
		for (int i = 0; i < ini.getInitialization().size(); i++) {


			int index = 0;

			// just for visualization purposes
			StringBuffer iniBuff = new StringBuffer();
			ini.getInitialization().get(i).forEach((key, value)-> iniBuff.append((String) key).append(": ").append((String) value).append(", "));

			while(index < prot.protectedCharsProxies.size()) {
				// getting ltl clause

				String ltlClause = this.cmd.generateLtlClause(prot.protectedCharsProxies.get(index),
						(String) ini.getInitialization().get(i).get(prot.protectedCharsProxies.get(index)), ini.dataTypes.get(prot.protectedCharsProxies.get(index)));
				if(!ltlClause.equals("")) {
					if(!ltlClauses.contains(ltlClause)) {
						this.ltlClauses.add(ltlClause);
					}
					this.cmd.deletePanFiles();
					// opening file with standard content
					String projectPath = new File("").getAbsolutePath();
					// creating directory for cached files

					// File path = new File(projectPath + "/cache/promella_initializations" + "/initialiazation_" + i + ".pml");

					String contents = null;
					Path path_to_standard_initializations = Paths.get(projectPath + "\\cache\\promella_standards" + "\\algorithm1\\initialiazation_" + (String) ini.getInitialization().get(i).get("__Ini_ID") + ".pml");
					contents =  new String(Files.readAllBytes(path_to_standard_initializations));

					// adding ltl clause

					contents = contents + "\n" + ltlClause;

					// System.out.println(contents);					

					String fileName = i + "_" + prot.protectedCharsProxies.get(index) + ".pml";
					trans.saveInitialization(contents, fileName);
//						String command0 = "cd " + projectPath;
					String command1 = "spin -a filename";
					String command2 = "gcc -DMEMLIM=1024 -O2 -DXUSAFE -w -o pan pan.c";
					String command3 = "./pan -m10000 -a -n -c1 -N formula1";

					command1 = command1.replace("filename", "cache\\promella_initializations\\algorithm1\\" + "initialiazation_" + fileName);
					//	cmd.executeShellCommand(command0);
//					System.out.println(cmd.executeShellCommand("ls -la"));
					long startCheckTime = System.nanoTime();
					System.out.println(command1);
					System.out.println(this.cmd.executeShellCommand(command1));
					System.out.println(this.cmd.executeShellCommand(command2));
//					cmd.executeShellCommand(command2);
					String resultString = this.cmd.executeShellCommand(command3);
					long endCheckTime = System.nanoTime();
					this.checktime += endCheckTime - startCheckTime;
					System.out.println("LTL Clause: " + ltlClause);
					System.out.println("Initialization: " + iniBuff.toString());
					System.out.println(resultString);

					if(!resultString.contains("errors: 0")) {
						changedList.add(ltlClause);
					}

				}
				index++;
			}
		}

		if(changedList.size() == 0) {
			this.valid = "valid";
		}

		// generate csv file 
		// in line 119, assume that we have an LTL clause. We have to append "valid" if there is no error

		StringBuffer sBuffer = new StringBuffer();

		for(String s : changedList) {
			sBuffer.append(s);
			sBuffer.append(" ");
		}

		this.error = "Precondition PC1 is not valid. The values of the following protected characteristics and proxies change while computing the decision" + sBuffer.toString();

	}
}