package mbFair;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TransitionSystem {

    // read the original file
    // delete the lines
    // add the run CustomerProfile(event_queues[24], 1, 1); for each class present
    // save the copy
    // read the new copy
    // add the lines at the top

    // add the ltl clause at the button

    private String hugoModelFilePath;
    private String cacheDirectory = this.projectPath + "/cache/promella_standards";
    public Initializations initializations;
    public String algorithmName;
    private String projectPath;
    private ProtectedCharacteristics protChars;

	/**
	 *
	 * @param filename The name of PML file containing the transition system
	 * @param initializations the initialization
	 * @param algorithmName the name of the algorithm (algorithm 1 or2)
	 * @param protChars the protected characteristics
	 * @throws IOException
	 */
    public TransitionSystem(String filename, Initializations initializations, String algorithmName, ProtectedCharacteristics protChars) throws IOException {

        this.projectPath = new File("").getAbsolutePath();
        this.hugoModelFilePath = filename;
        this.initializations = initializations;
        this.algorithmName = algorithmName;
        this.protChars = protChars;

        // creating directory for cached files
        this.cacheDirectory = this.projectPath + "/cache/promella_standards";
        File standardsFolder = new File(this.cacheDirectory + "/" + this.algorithmName);
        File initializationsFolderFile = new File(this.projectPath + "/cache/promella_initializations" + "/" + this.algorithmName);

        // deleting the cache if it already exists
        try {
            Path path = Paths.get(this.cacheDirectory);
            Files.walk(path)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);

            Path initalization_path = Paths.get(this.projectPath + "/cache/promella_initializations" + "/" + this.algorithmName);
            Files.walk(path)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);


        } catch (Exception e) {
//            e.printStackTrace();
        }
        // creating new cache
        standardsFolder.mkdirs();
        initializationsFolderFile.mkdirs();
    }

	/**
	 *
	 * @return
	 * @throws IOException
	 */
    public boolean standardizeFile() throws IOException {
        // Getting lines in hugo model file
        String contents = null;
        try {
            Path path = Paths.get(this.hugoModelFilePath);
            contents = Files.readString(path, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        // going through initializations
        for (int i = 0; i < this.initializations.getInitialization().size(); i++) {
            String contentReplacement = contents;
            // going through attributes and getting the name, data type and value
            for (String key : this.initializations.dataTypes.keySet()) {
                String attribute = key;
                String attributeValue = (String) this.initializations.getInitialization().get(i).get(key);
                String dataType = this.initializations.dataTypes.get(key);
                attributeValue = this.promelaData(dataType, attributeValue);
                dataType = this.promelaDataType(dataType);

                // sample promela initialization
                String samplePromellaInit = "datatype variablename;";
                String promellaAttributeDefault = samplePromellaInit.replace("datatype", dataType).replace("variablename", attribute);
                String promellaAttributeInit = promellaAttributeDefault.replace(";", "") + " = " + attributeValue + ";";
                try {
                    Pattern samplePromellaInit1 = Pattern.compile(dataType + "\\s" + attribute + ";", Pattern.CASE_INSENSITIVE);
                    if(samplePromellaInit1.matcher(contentReplacement).find()){
                        contentReplacement = samplePromellaInit1.matcher(contentReplacement).replaceAll(promellaAttributeInit);
                    }
                    else{
                        Pattern samplePromellaInit_2 = Pattern.compile(dataType + "\\s" + attribute + "\\s=\\s.*?;", Pattern.CASE_INSENSITIVE);
                        contentReplacement = samplePromellaInit_2.matcher(contentReplacement).replaceAll(promellaAttributeInit);
                    }

                } catch (Exception e) {
                   e.printStackTrace();
                }
            }

            // replacing atomic
            /**
             * only for classes with the protected characteristics. The assumption here is that only classes that have protected characteristics have to be initialized
             * @todo also have to initialize classes that have proxies of protected characteristics.
             *
             */
            String sampleAtomicString = "run ClassName(1, 1, 1)";
            String atomicString = "";
            List<String> classesToBeInitialized = new ArrayList<>();
            for (int k = 0; k < this.initializations.classNames.size(); k++) {
                for (int l = 0; l < this.protChars.protectedCharsProxies.size(); l++) {
                    if (this.protChars.protectedCharsProxies.get(l).startsWith(this.initializations.classNames.get(k))) {

                        if(!classesToBeInitialized.contains(this.initializations.classNames.get(k))){
                            classesToBeInitialized.add(this.initializations.classNames.get(k));
                        }

                    }
                }
            }

            for(int m=0; m<classesToBeInitialized.size(); m++){
                atomicString = atomicString + sampleAtomicString.replace("ClassName", classesToBeInitialized.get(m)) + "\n" + "    ";
            }

            try {
                Pattern p = Pattern.compile("atomic\\s\\{\\n\\s*skip", Pattern.CASE_INSENSITIVE);
                contentReplacement = p.matcher(contentReplacement).replaceAll("atomic { \n " + atomicString);

            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (contentReplacement.contains("#define number_objects 0")) {
                    /**
                     * @// TODO: 05.10.22   check what number_objects really means
                     */
                    //int count = countMatches(contentReplacement, "proctype");
                    contentReplacement = contentReplacement.replace("#define number_objects 0", "#define number_objects " + 15);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if (contentReplacement.contains("#define queue_size ")) {
                    Pattern p = Pattern.compile("#define queue_size \\d", Pattern.CASE_INSENSITIVE);
                    contentReplacement = p.matcher(contentReplacement).replaceAll("#define queue_size 25");

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            //creating the instance of file
            File path = new File(this.cacheDirectory + "/" + this.algorithmName + "/initialiazation_" + (String) this.initializations.getInitialization().get(i).get("__Ini_ID") + ".pml");

            //passing file instance in filewriter
            FileWriter wr = new FileWriter(path);

            //calling writer.write() method with the string
            wr.write(contentReplacement);

            //flushing the writer
            wr.flush();

            //closing the writer
            wr.close();
        }
        return true;
    }

    /**
     * saves initializations in files
     *
     * @param content
     * @param filename
     * @throws IOException
     */
    public void saveInitialization(String content, String filename) throws IOException {
        File path = new File(this.projectPath + "/cache/promella_initializations" + "/" + this.algorithmName + "/initialiazation_" + filename);
        //passing file instance in filewriter
        FileWriter wr = new FileWriter(path);
        //calling writer.write() method with the string
        wr.write(content);
        //flushing the writer
        wr.flush();
        //closing the writer
        wr.close();
    }

    /**
     * Converts Papyrus data types to promella data types
     *
     * @param dataType
     * @return
     */
    private String promelaDataType(String dataType) {
        switch (dataType) {
            case "boolean": {
                return "bool";
            }
            case "int": {
                return "int";
            }
            default:
                throw new IllegalArgumentException("Unexpected data type: " + dataType);
        }
    }

    /**
     * Converts Papyrus data types to promella data types
     *
     * @param dataType
     * @return
     */
    private String promelaData(String dataType, String data) {
        switch (dataType) {
            case "boolean": {
                if (data.equals("True")) {
                    return "true";
                }
                return "false";
            }
            case "int": {
                return data;
            }
            default:
                throw new IllegalArgumentException("Unexpected data type: " + dataType);
        }
    }

    /* Checks if a string is empty ("") or null. */
    public static boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }

	/**
	 * Counts how many times the substring appears in the larger string.
	 *
	 * @param text the searched text
	 * @param str the needle
	 * @return
	 */
	public static int countMatches(String text, String str) {
        if (isEmpty(text) || isEmpty(str)) {
            return 0;
        }
        Matcher matcher = Pattern.compile(str).matcher(text);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }
}
