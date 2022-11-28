package mbFair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class DecisionDataAttributes {

    public HashMap<String, String> decisionDataAttributes;

    public void setDecisionDataAttributes(String filename) {
        // Setting path
        String projectPath = new File("").getAbsolutePath();
        String absoluteFilePath = filename;
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
        this.decisionDataAttributes = records;
    }

    public String[] getDistinctColumnVals(String attributeName) {
        switch ((String) this.decisionDataAttributes.get(attributeName)) {
            case "boolean":
                String[] value = {"true", "false"};
                return value;
            default:
                throw new IllegalArgumentException("Unexpected value: " + this.decisionDataAttributes.get(attributeName));
        }
    }


}
