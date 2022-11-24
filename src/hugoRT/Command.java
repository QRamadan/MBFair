package hugoRT;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.HashMap;

import hugoRT.AttributeNotFoundException;

/**
 * Runs the commands necessary for the algorithm
 * 
 * @author sfa
 */
public class Command {
	
	long totTimeToGenLtlClauses = 0;
	
	/**
	 * Generates LTL clauses with the pattern   "ltl formula1{[](CustomerProfile_income==1000)}"
	 * 
	 * @param attributeName The name of the attribute
	 * @param value the value of the attribute
	 * @return A String containing the ltl clause
	 */
	public String generateLtlClause(String attributeName, String value, String dataType) {
		
		long startTime = System.nanoTime();
		// A sample ltl clause
		String sampleLtlClause = "ltl formula1{[](attribute==value)}";
		
		// generating the ltl clause
		
		try {			
			String resultString =  sampleLtlClause.replace("attribute", attributeName).replace("value", this.promelaData(dataType, value));
			long endTime = System.nanoTime();
			long totalTime = endTime - startTime;
			this.totTimeToGenLtlClauses = this.totTimeToGenLtlClauses + totalTime;
			return resultString;
		}
		catch(Exception e) {
//					System.out.println(attributeName + " is not part of the initializations");			
		}
		
		
		return "";
		
	}
	
	/**
	 * Generates LTL clauses with the pattern   "ltl formula1{[](CustomerProfile_income==1000)}"
	 * 
	 * @param attributeName The name of the attribute
	 * @param value the value of the attribute
	 * @return A String containing the ltl clause
	 */
	public String generateLtlClauseAlgo2(String d, String vd) {
		long startTime = System.nanoTime();
//		System.out.print(d + " " + vd);
		
		// A sample ltl clause
		String sampleLtlClause = "ltl formula2{<>[](xxx==yyy)}";
		
		// generating the ltl clause
		
		try {
			String resultString =  sampleLtlClause.replace("xxx", d).replace("yyy", vd);
			long endTime = System.nanoTime();
			long totalTime = endTime - startTime;
			
			this.totTimeToGenLtlClauses = this.totTimeToGenLtlClauses + totalTime;
			return resultString;
		}
		catch(Exception e) {
//			System.out.println(attributeName + " is not part of the initializations");
			
		}
		return "";
	}
	
	/**
	 * Executes a shell command
	 * 
	 * @param command
	 * @return
	 */
	
	public String executeShellCommand(String command) {		

//	Run a shell command
	Process process = null;
	try {
		process = Runtime.getRuntime().exec(command);
		StringBuilder output = new StringBuilder();
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(process.getInputStream()));
	
		BufferedReader errorReader = new BufferedReader(
				new InputStreamReader(process.getErrorStream()));
		String line;
		while ((line = reader.readLine()) != null) {
			output.append(line + "\n");
		}
		
		int exitVal = process.waitFor();
		if (exitVal == 0) {
			
			return output.toString();
		} else {		
			
			while ((line = errorReader.readLine()) != null) {
				output.append(line + "\n");
			}
			return output.toString();
			
		}
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return "Unkown error occured while runing command: " + command;
	
}
	
	/**
	 * Executes a shell command
	 * 
	 * @param command
	 * @return
	 */
	
	public String executeShellCommand(String[] command) {		

//	Run a shell command
	Process process = null;
	try {
		process = Runtime.getRuntime().exec(command);
		StringBuilder output = new StringBuilder();
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(process.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {
			output.append(line + "\n");
		}
		
		int exitVal = process.waitFor();
		if (exitVal == 0) {
			
			return output.toString();
		} else {
			//something went wrong...
		}
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return "";
	
}
	/**
	 * Converts Papyrus data types to promella data types
	 * @param dataType
	 * @return
	 */
	private String promelaData(String dataType, String data) {
		switch (dataType) {
		case "boolean": {
			if(data.equals("True")) {
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
	
	public void deletePanFiles() {
		
		String projectPath = new File("").getAbsolutePath();
		try {
			File f1 = new File("C:\\Users\\wenci\\eclipse-workspace\\hugoRT\\pan.b");
			File f2 = new File("C:\\Users\\wenci\\eclipse-workspace\\hugoRT\\pan.c");
			File f3 = new File("C:\\Users\\wenci\\eclipse-workspace\\hugoRT\\pan.pre");
			File f4 = new File("C:\\Users\\wenci\\eclipse-workspace\\hugoRT\\pan.h");
			File f5 = new File("C:\\Users\\wenci\\eclipse-workspace\\hugoRT\\pan.m");
			File f6 = new File("C:\\Users\\wenci\\eclipse-workspace\\hugoRT\\pan.p");
			File f7 = new File("C:\\Users\\wenci\\eclipse-workspace\\hugoRT\\pan.t");
			f1.delete();
			f2.delete();
			f3.delete();
			f4.delete();
			f5.delete();
			f6.delete();
			f7.delete();
//			this.executeShellCommand(b);
			return;
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public long timeToGenerateLtlClause() {
		long startTime = System.nanoTime();
		this.generateLtlClauseAlgo2("help", "lean");
		long endTime = System.nanoTime();
		long totalTime = startTime - endTime;
		
		return totalTime;
	}
	
	
}
