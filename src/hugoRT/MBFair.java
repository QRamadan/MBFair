package hugoRT;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "run",
        aliases = {"execute", "-r"},
        version = "1.0.0",
        mixinStandardHelpOptions = true,
        requiredOptionMarker = '*',
        description = "This is a Sub Command to 'execute' Software Fairness Analysis",
        header = "Run software fairness analysis tool.",
        optionListHeading = "%nOptions are:%n",
        footerHeading = "%nCopyright",
        footer = "%nDeveloped by SFAT"
)

public class MBFair implements Callable<Integer> {
    @CommandLine.Option(names = {"-f", "--filepath"}, required = true, description = "File Path for XML/UML model")
    /**
     * @var filepath refers to user inputed xml or uml file
     */
            String filepath;

    @CommandLine.Option(names = {"-p", "--pc"}, required = false, description = "File Path for CSV containing protected characteristics")
    /**
     * @var pc Path for CSV containing protected characteristics
     */
            String pc;

    final Integer SUCCESS = 0;
    final Integer FAILURE = 1;

    public static void main(String[] args) {
        int exitStatus = new CommandLine(new MBFair()).setCaseInsensitiveEnumValuesAllowed(true).execute(args);
        System.exit(exitStatus);
    }

    @Override
    public Integer call() throws Exception {
        try {

            long overAllStartTime = System.nanoTime();
            String projectPath = new File("").getAbsolutePath();
            String initializationsFileName = projectPath + "/initializations_table.csv";
            String attributeDataTypeFileName = projectPath + "/attributes_data_types.csv";
            String classNamesFileName = projectPath + "/class_names.csv";


            String decisionDataAttribsTypeFileName = projectPath + "\\decision_data_attribs_types.csv";
            String statisticsFromPytonFileName = projectPath + "\\statistics.csv";
            String decisionDataAttribsFileName = projectPath + "\\decision_data_attribs.csv";
            String protectedCharateristicsFileName = this.pc;
            if(this.pc == null){
                protectedCharateristicsFileName = projectPath + "\\protected_characteristics.csv";
            }
            String pathToModel = this.filepath;
            String pathToPml = pathToModel.substring(pathToModel.lastIndexOf("\\") + 1);
            if(pathToPml.contains(".uml")){
                pathToPml = projectPath + "\\" +  pathToPml.replace(".uml", ".pml");
            }
            else if(pathToPml.contains(".xml")){
                pathToPml = projectPath + "\\" + pathToPml.replace(".xml", ".pml");
            }
            else{
                throw new Exception("Unsupported file format");
            }
            String transitionSystem = pathToPml;

            System.out.println("Transition system file path: " + transitionSystem);

            Command cmd = new Command();
            long startTransTime = System.nanoTime();
            long endTransTime = System.nanoTime();
//            long totalTransTime = endTransTime - startTransTime;
            /**
             * @// TODO: 06.10.22 Calculate the time to translate model to PML instead of setting it to 1 second
             */
            long totalTransTime = 1000000000L;

            // Creating a new Initialization object
            Initializations ini = new Initializations(initializationsFileName, attributeDataTypeFileName, classNamesFileName);

            // Creating protected characteristics object
            ProtectedCharacteristics prot = new ProtectedCharacteristics(ini, protectedCharateristicsFileName);

            System.out.println("Protected chars : " + prot.protectedCharsProxies);
            TransitionSystem trans = null;
            try {
                trans = new TransitionSystem(transitionSystem, ini, "algorithm1", prot);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            // Creating standard files for each initialization
            trans.standardizeFile();

            // decision data attributes
            DecisionDataAttributes dda = new DecisionDataAttributes();
            dda.setDecisionDataAttributes(decisionDataAttribsTypeFileName);
            System.out.println(dda.decisionDataAttributes);

            Set columNames = ini.initializations.get(0).keySet();
            List protecedCharsAndProxiesList = prot.protecedCharsAndProxiesList;
            boolean atLeastOneProtIsPresentInIniTable = false;
            for (int i = 0; i < protecedCharsAndProxiesList.size(); i++) {
                if (columNames.contains(protecedCharsAndProxiesList.get(i))) {
                    atLeastOneProtIsPresentInIniTable = true;
                }
            }
            if (!atLeastOneProtIsPresentInIniTable) {
                throw new Exception("At least one protected characteristic must be a present in the initialization");
            }
            Algorithm1 a1 = new Algorithm1(ini, prot, trans, cmd);
            a1.run();
            if(a1.valid.equals("invalid")){
                throw new Exception("Precondition 1 invalid: Aborting MBFair... " );
            }
            TransitionSystem trans2 = null;
            try {
                trans2 = new TransitionSystem(transitionSystem, ini, "algorithm2", prot);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            trans2.standardizeFile();
            Algorithm2 a2 = new Algorithm2(ini, dda, trans2, cmd);
            a2.run();
            if(a2.valid.equals("invalid")){
                throw new Exception("Precondition 2 invalid: Aborting MBFair... " );
            }
            System.out.println("Algo 2 Result: in Main: " + a2.result);

            HashMap<String, String> iniConstructorParams = new HashMap<>();
            iniConstructorParams.put("initializationsFileName", initializationsFileName);
            iniConstructorParams.put("attributeDataTypeFileName", attributeDataTypeFileName);
            iniConstructorParams.put("classNamesFileName", classNamesFileName);

            Definition1 definition1 = new Definition1(a2.result, iniConstructorParams, prot, dda);
            definition1.run();
            long overAllEndTime = System.nanoTime();
            long overallTime = overAllEndTime - overAllStartTime;
            Statistics stat = new Statistics(statisticsFromPytonFileName, a1, a2, definition1, totalTransTime, overallTime, dda, prot, cmd);
            stat.printStats();
            return SUCCESS;
        } catch (IOException ex) {
            System.out.println(ex);
            return FAILURE;
        }
    }

}
