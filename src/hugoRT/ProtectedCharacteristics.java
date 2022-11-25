package hugoRT;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Helps to handle protected characteristics
 *
 * @author sfa
 *
 */
public class ProtectedCharacteristics {
    // the variable that will keep the protected characteristics
    public List<String> protectedCharsProxies;

    public HashMap<String, List<String>> protectedCharsAndItsProxies;

    private Initializations ini;
    public List protecedCharsAndProxiesList = new ArrayList<>();

    /**
     * Sets protected characteristics
     *
     * @param filename The file name containing the list of protected characteristics and proxies
     * @return void
     */
    public void setProtectedCharsAndProxies(String filename) {
        // Setting path
        String projectPath = new File("").getAbsolutePath();
        String absoluteFilePath = filename;
        // Initializing list
        List<String> protectedChars = new ArrayList<>();
        HashMap<String, List<String>> protectedCharsAndItsProxiesPr = new HashMap<>();
        List<String> protectedCharNames = new ArrayList<>();
        // Reading file and saving in list
        try (BufferedReader br = new BufferedReader(new FileReader(absoluteFilePath))) {
            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                for (int i = 0; i < Arrays.asList(values).size(); i++) {
                    if(!protecedCharsAndProxiesList.contains(values[i])) {
                        protecedCharsAndProxiesList.add(values[i]);
                    }
                    if(!protectedChars.contains(values[i]) && this.ini.getInitialization().get(0).containsKey(values[i])) {
                        protectedChars.add(values[i]);
                    }
                    if(lineNumber == 0) {
                        protectedCharNames.add(values[i]);
                        List<String> proxiesInit = new ArrayList<>();
                        protectedCharsAndItsProxiesPr.put(values[i], proxiesInit);

                    }
                    else {
                        if(values[i].length() > 0) {
                            List<String> proxiesInitermidate = new ArrayList<>();
                            proxiesInitermidate = protectedCharsAndItsProxiesPr.get(protectedCharNames.get(i));
                            proxiesInitermidate.add(values[i]);
                            protectedCharsAndItsProxiesPr.remove(protectedCharNames.get(i));
                            protectedCharsAndItsProxiesPr.put(protectedCharNames.get(i), proxiesInitermidate);
                        }
                    }
                }
                lineNumber++;
            }
            this.protectedCharsProxies = protectedChars;
            this.protectedCharsAndItsProxies = protectedCharsAndItsProxiesPr;
        } catch (Exception E) {
            System.out.println("Path to path containing protected characteristics not found");
        }

    }

    /**
     *
     * @param ini
     * @param protectedCharateristicsFileName
     */
    public ProtectedCharacteristics(Initializations ini, String protectedCharateristicsFileName) {
        this.protectedCharsProxies = protectedCharsProxies;
        this.ini = ini;
        this.setProtectedCharsAndProxies(protectedCharateristicsFileName);
    }

    /**
     *
     * @param str
     * @return
     */
    public String beautify(String str) {
        try {
            int length = str.length();
            if (length < 50) {
                int diff = 50 - length;
                for (int i = 0; i < diff; i++) {
                    str = str + " ";
                }
            } else {
                str = str.substring(0, 49);
            }
            return str;
        }
        catch (Exception e){
            return "                                                  ";
        }

    }

}
