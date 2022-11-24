package hugoRT;

import java.io.*;
import java.time.chrono.ChronoZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Statistics {

    /**
     * statistic variables
     * <p>
     * model
     * # elements (number of XML elements present in XML file)
     * # Annotated Elements (Number of Classes that are annotated in the XML File. Classes are represented using XML elements) NB: elements linked to UMLFairness:critical
     * # Data Attributes (Number of unique elements that are class attributes in the XML file)
     * Decision Data Attributes (The decision data attributes)
     * Protected Characteristics (The protected characteristics)
     * Proxy Information  (The proxy of the protected characteristics)
     * <p>
     * # initializations (Number of initializations)
     * Initialization generation time (Total time taken to generate initializations)
     * Transformation time (Time to transform model from XML to PML)
     * # Unique LTL formulas (Number of Unique LTL formulas)
     * # Total LTL formulas (Total number of LTL formulas)
     * Generation Time (Time to generate all the LTL formulas)
     * Check Time (Time to check all the LTL formulas)
     * Sensitive Decision (The same as Decision Data Attributes above)
     * Precondition 1 Result (The result of evaluating precondition 1 [valid, invalid]))
     * Precondition 2 Result (The result of evaluating precondition 2 [valid, invalid]))
     * # Violations (Number of violations of Definition 1)
     * Against (The protected characteristic violated)
     * Source (The proxy that caused the violation)
     */

    public HashMap pythonStats;

    Definition1 def1;
    String filename;
    String filepath;

    Algorithm2 a2;
    Algorithm1 a1;
    Command cmd;
    long transtime;
    long totalTime;
    DecisionDataAttributes dda;
    ProtectedCharacteristics prot;

    public Statistics(String pythonFileName, Algorithm1 a1, Algorithm2 a2, Definition1 def1, long transtime, long totalTime, DecisionDataAttributes dda, ProtectedCharacteristics prot, Command cmd) {
        this.filename = pythonFileName;
        this.a2 = a2;
        this.a1 = a1;
        this.def1 = def1;
        this.transtime = transtime;
        this.totalTime = totalTime;
        this.dda = dda;
        this.setPythonStats(pythonFileName);
        this.prot = prot;
        this.cmd = cmd;
    }


    public void setPythonStats(String filename) {
        String absoluteFilePath = filename;
        this.filepath = absoluteFilePath;

        // creating empty HashMap that will hold the form form key, value pairs
        HashMap<String, String> records = new HashMap<>();

        // reading the csv file
        try (BufferedReader br = new BufferedReader(
                new FileReader(absoluteFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.put(values[0], values[1]);
            }
        } catch (Exception E) {
            System.out.println("file not found");

        }

        this.pythonStats = records;
    }

    public void printStats() throws IOException {
        FileWriter writer = new FileWriter(this.filepath);
        BufferedWriter br = new BufferedWriter(writer);
        PrintWriter pr = new PrintWriter(br);
		pr.append("Model Name "+ "," + "" + "\n");
		pr.append("Number of Elements "+ "," + (String) pythonStats.get("number_of_xml_tags") + "\n");
		pr.append("Number of Annotated Elements "+ "," + (String) pythonStats.get("num_annot_elems") + "\n");
		pr.append("Number of Data Attributes"+ "," + (String) pythonStats.get("data_attributes") + "\n");

        System.out.println("|---------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("| Model Name                                   | " + "DeliverySystem                           ");
        System.out.println("| # of Elements                                | " + (String) pythonStats.get("number_of_xml_tags"));
        System.out.println("| # Annotated Elements                         | " + (String) pythonStats.get("num_annot_elems"));
        System.out.println("| # Data Attributes                            | " + (String) pythonStats.get("data_attributes"));
        System.out.print("\n| Protected Characteristics                   ");
        int longest = 0;
        Set<String> protCharsNames = this.prot.protectedCharsAndItsProxies.keySet();
        for (String key : protCharsNames) {
            System.out.print(" | " + this.prot.beautify(key));
			pr.append("Protected Characteristics "+ "," + this.prot.beautify(key) + "\n");
            if (this.prot.protectedCharsAndItsProxies.get(key).size() > longest) {
                longest = this.prot.protectedCharsAndItsProxies.get(key).size();
            }
        }


        System.out.print("\n| Proxy Information                            ");
		pr.append("Proxy Information "+ "," + "" + "\n");

        for (int i = 0; i < longest; i++) {
            System.out.print("\n                                              ");

            for (String key : protCharsNames) {
                try {
                    System.out.print(" | " + this.prot.beautify(this.prot.protectedCharsAndItsProxies.get(key).get(i)));
                } catch (Exception e) {
                    System.out.print(" | " + this.prot.beautify(" "));

                }
            }

        }
        System.out.println("\n| # Number of initialization                   | " + (String) pythonStats.get("num_of_initializations"));
        System.out.println("| Initialization Generation Time [s]           | " + (String) pythonStats.get("inni_gen_time"));

        System.out.println("| Time translate model to promella [s]         | " + ((long) (this.transtime / 1000000000L)));
        System.out.println("| Number of Unique LTL formulars               | " + (a1.ltlClauses.size() + a2.ltlClauses.size()));
        System.out.println("| Time to generate all LTL forumlars[microsecs]| " + (this.cmd.totTimeToGenLtlClauses / 1000L));
        System.out.println("| Time to check LTL clauses [s]                | " + (a2.checktime + a1.checktime) / 1000000000L);
        System.out.println("| Precondition 1 result                        | " + a1.valid);
        System.out.println("| Precondition 2 result                        | " + a2.valid);

		pr.append("Number of initialization"+ "," + (String) pythonStats.get("num_of_initializations") + "\n");
		pr.append("Initialization Generation Time [s]"+ "," + (String) pythonStats.get("inni_gen_time") + "\n");
		pr.append("Time translate model to promella [s]"+ "," + ((long) (this.transtime / 1000000000L)) + "\n");
		pr.append("Number of Unique LTL formulas"+ "," + (a1.ltlClauses.size() + a2.ltlClauses.size()) + "\n");
		pr.append("Time to generate all LTL forumlas[microsecs]"+ "," + (this.cmd.totTimeToGenLtlClauses / 1000L) + "\n");
		pr.append("Time to check Preconditions and Constraints [s]"+ "," + ((a2.checktime + a1.checktime) / 1000000000L) + "\n");
		pr.append("Precondition 1 result"+ "," + a1.valid + "\n");
		pr.append("Precondition 2 result"+ "," + a2.valid + "\n");

        System.out.print("| Sensitive Decisions or Decisiondata Attribute| ");
        for (String key : this.dda.decisionDataAttributes.keySet()) {
            System.out.print(key + ", ");
			pr.append("Sensitive Decisions"+ "," + key + "\n");
        }
        System.out.println("\n| # Violations                                 | " + this.def1.violations.size());
		pr.append("Violations"+ "," + this.def1.violations.size() + "\n");

        List<String> sourceList = new ArrayList<>();
        for (int i = 0; i < this.def1.violations.size(); i++) {
            if (!sourceList.contains(this.def1.violations.get(i).get("source"))) {
                sourceList.add(this.def1.violations.get(i).get("source"));
                System.out.println("\n| Against                                      | " + this.prot.beautify(this.def1.violations.get(i).get("against")) + "| source --> " + this.def1.violations.get(i).get("source"));
				pr.append("Against"+ "," + this.prot.beautify(this.def1.violations.get(i).get("against")) + "\n");
				pr.append("Source"+ "," + this.def1.violations.get(i).get("source") + "\n");
            }

        }
        System.out.print("\n| total time to run MBFair [s]                 | " + ((totalTime / 1000000000L) + Double.valueOf((String) pythonStats.get("inni_gen_time"))));
		pr.append("Total time to run MBFair [s]"+ "," + ((totalTime / 1000000000L) + Double.valueOf((String) pythonStats.get("inni_gen_time"))) + "\n");
        pr.close();
        br.close();
        writer.close();
    }
}