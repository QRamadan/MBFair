package mbFair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * This class aids with handling initialization with the from the csv file
 * 
 * @author sfa
 */
public class Initializations implements Cloneable {
	
	public List<HashMap> initializations = new ArrayList<>();
	public List<String> attibuteNames;
	public HashMap<String, String> dataTypes;
	public List<String> classNames;
	public String initializationFileName;
	public String attributeDataTypeFileName;
	public String classNamesFileName;


	public Initializations clone() throws CloneNotSupportedException
	{
		Initializations ini = (Initializations) super.clone();
		ini.initializations = this.initializations;
		return ini;
	}
	
	
	
	/**
	 * @param initializationFileName the name of the CSV file containing the initializations
	 * @param attributeDataTypeFileName the name of the file containing dataTypes of attributes
	 * @param classNamesFileName the name of file containing class names;
	 */
	public Initializations(String initializationFileName, String attributeDataTypeFileName, String classNamesFileName) {
		this.initializationFileName = initializationFileName;
		this.attributeDataTypeFileName = attributeDataTypeFileName;
		this.classNamesFileName = classNamesFileName;
		this.setInitializations(this.initializationFileName);
	}


	/**
	 * Sets initialization in a list of HashMaps -> [ [key: value, key: value], [], ]
	 * 
	 * @param initializationFileName The name of the file containing the initializations in CSV format
	 */
	public void setInitializations(String initializationFileName) {
		
		String absoluteFilePath = initializationFileName;
		// creating empty list that will hold the rows in a list -> [["x", "y", "z"], ["x", "y", "z"]]
		List<List<String>> records = new ArrayList<>();		
		// reading the csv file
		try (BufferedReader br = new BufferedReader(
				new FileReader(absoluteFilePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				records.add(Arrays.asList(values));
			}
		} catch (Exception E) {
			E.printStackTrace();
		}

		List<HashMap> records_with_attibutes = new ArrayList<>();

		List<String> columnNames = new ArrayList<>();

		// Converting the rows to hashmaps of key value pairs for easy reference
		columnNames = records.get(0);
		this.attibuteNames = columnNames;
		for (int i = 0; i < records.size(); i++) {
			if (i == 0) {
				continue;
			}
			HashMap<String, String> row = new HashMap<String, String>();
			for (int j = 0; j < columnNames.size(); j++) {
				row.put(columnNames.get(j), records.get(i).get(j));
			}
			records_with_attibutes.add(row);
		}

		this.initializations = records_with_attibutes;
		this.setDataTypes(this.attributeDataTypeFileName);
		this.setClassNames(this.classNamesFileName);
	}
	
	
	/**
	 * Sets the data_types of protected characteristics based on csv file
	 * 
	 */
	public void setDataTypes(String absoluteFilePath) {

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
			E.printStackTrace();

		}
		
		this.dataTypes = records;
		
		
	}
	
	/**
	 * 
	 */
	public void setClassNames(String absoluteFilePath) {

		// creating empty HashMap that will hold the form form key, value pairs
		List<String> records = new ArrayList<>();
		
		// reading the csv file
		try (BufferedReader br = new BufferedReader(
				new FileReader(absoluteFilePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				records = Arrays.asList(values);
			}
		} catch (Exception E) {
			System.out.println("file not found");

		}
		
		this.classNames = records;
		
	}
	
	public List<String> getDistinctColumnVals(String attributeName){
		String dataType = dataTypes.get(attributeName);
		List<String> data = new ArrayList<>();
		try {
			switch (dataType) {
			case "boolean": {
				data.add("true");
				data.add("false");
				return data;
			}
			case "int": {
				
				for(int i=0; i<this.initializations.size(); i++) {
					if(!data.contains((String) this.initializations.get(i).get(attributeName))) {
						data.add((String) this.initializations.get(i).get(attributeName));
					}
				}
				return data;
			}
		
			default:
				throw new IllegalArgumentException("Unexpected data type: " + dataType);
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return data;
		
	}
	
	public List<HashMap> getInitialization() {
		return this.initializations;
	}
	
//	public List<String> getDisctinctColumnVals(String columnName){
//		List<String> vals= new ArrayList<>();
//		for(int i = 0; i < this.initializations.size(); i++) {
//			if(!vals.contains((String)this.initializations.get(i).get(columnName))) {
//				vals.add((String)this.initializations.get(i).get(columnName));
//			}
//		}
//		return vals;
//	}
	
	
	
	
	
	
	
	

}
