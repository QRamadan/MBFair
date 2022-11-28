package mbFair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * @author charles
 * 
 * Implements Definition 1 of MBFair
 *
 */
public class Definition1 {
	
	
	public List<HashMap<String, String>> violations = new ArrayList<>();
	
	public List<HashMap> algorithm_2_results = new ArrayList<>();
	public DecisionDataAttributes dda;
	public ProtectedCharacteristics prot;

	private HashMap<String, String> iniConstructorParams;
	
	/**
	 * Class cunstructor
	 * 
	 * @param algorithm_2_results The result s of algorithm to
	 */
	public Definition1(List<HashMap> algorithm_2_results, HashMap iniConstructorParams, ProtectedCharacteristics prot, DecisionDataAttributes dda) {
		this.prot = prot;
		this.dda = dda;
		this.algorithm_2_results = algorithm_2_results;
		this.iniConstructorParams = iniConstructorParams;
	}

	/**
	 *
	 * @return
	 * @throws CloneNotSupportedException
	 */
	public boolean run() throws CloneNotSupportedException {
		
		// looping through list of protected characteristics and Proxies of protected characteristics
		for(int i=0; i< this.prot.protectedCharsProxies.size(); i++) {

			Initializations ini = new Initializations(
					iniConstructorParams.get("initializationsFileName"),
					iniConstructorParams.get("attributeDataTypeFileName"),
					iniConstructorParams.get("classNamesFileName")
			);
			
			String protectectedCharacteristic = this.prot.protectedCharsProxies.get(i);
			if(ini.getInitialization().size() == 0){
				continue;
			}
			if(!ini.getInitialization().get(0).containsKey(protectectedCharacteristic)) {
				continue;
			}
			System.out.println(protectectedCharacteristic + this.prot.protectedCharsProxies.size());

			for(String key: this.dda.decisionDataAttributes.keySet()) {
				String[] distinctValsOfDecisiontAttrib = this.dda.getDistinctColumnVals(key);
			    List<HashMap> initializations = ini.getInitialization();
			    List<HashMap> pairedInitialisations = this.similarInitializations(protectectedCharacteristic, initializations);
			    System.out.println("Paried inis");
			    
			    System.out.println(pairedInitialisations);
			    List<String> formular_4_validityList = new ArrayList<>();
			    List<HashMap> def_1_result = new ArrayList<>();
			    // Looping through the distinct values of a protected characteristic
			    for(int j=0; j<distinctValsOfDecisiontAttrib.length; j++) {
			    	List<String> validity_for_distinct_protected_charsArrayList = new ArrayList<>();
			    	String decisionDataVal = distinctValsOfDecisiontAttrib[j];
			    	System.out.println("------------Defintion result summary-------------");
			    	System.out.println("|Protected Characters: " + protectectedCharacteristic);
			    	System.out.println("|Ini_ID  |" + "Decision Data Attribute: " + key + " , Value " + decisionDataVal + "| Forumlar 4");
		
			    	for(int k=0; k<pairedInitialisations.size(); k++) {
			    		
			    		String ini_1 = (String) pairedInitialisations.get(k).get("ini_1");
			    		String ini_2 = (String) pairedInitialisations.get(k).get("ini_2");
			    		
			    		HashMap<String, String> def_1_intermediate_result = new HashMap<>();
			    		def_1_intermediate_result.put("ini_1", ini_1);
			    		def_1_intermediate_result.put("ini_2", ini_2);
			    		def_1_intermediate_result.put("protected_characteristic", protectectedCharacteristic);
			    		def_1_intermediate_result.put("protected_characteristic_value", decisionDataVal);
			    		String algo_2_result_for_ini_1 = this.algo2ResultForIniAndDecisionData(ini_1, key, decisionDataVal).get("result");
			    		String algo_2_result_for_ini_2 = this.algo2ResultForIniAndDecisionData(ini_2, key, decisionDataVal).get("result");
			    		
			    		def_1_intermediate_result.put("algo_2_result_for_ini_1", algo_2_result_for_ini_1);
			    		def_1_intermediate_result.put("algo_2_result_for_ini_2", algo_2_result_for_ini_2);
			    		// Checking if formula 4 is valid
			    		if(algo_2_result_for_ini_1.equals(algo_2_result_for_ini_2)) {
			    			formular_4_validityList.add("valid");
			    			def_1_intermediate_result.put("forlumlar_4", "valid");
			    			validity_for_distinct_protected_charsArrayList.add("valid");
			    			System.out.println("|"+ pairedInitialisations.get(k).get("ini_1") + " |" + algo_2_result_for_ini_1 + "  |");
			    			System.out.println("|"+ pairedInitialisations.get(k).get("ini_2") + " |" + algo_2_result_for_ini_2 + "  |" + "Forumlar 4 Valid");
			    			System.out.println("---------------------------------------------------------------");
			    			
			    		}
			    		else {
			    			HashMap<String, String> violation = new HashMap<>();
			    			
			    			violation.put("ini_1", ini_1);
			    			violation.put("ini_2", ini_2);
			    			violation.put("decision_data_attribute", key);
			    			violation.put("decision_data_attribute_val", decisionDataVal);
			    			violation.put("source", protectectedCharacteristic);
			    			try {
			    				for(String keyval: this.prot.protectedCharsAndItsProxies.keySet()) {
									for(int l=0; l<this.prot.protectedCharsAndItsProxies.get(keyval).size(); l++) {
										if (this.prot.protectedCharsAndItsProxies.get(keyval).get(l) == protectectedCharacteristic) {
											violation.put("against", keyval);
											break;
										}
									}
								}

								if(violation.get("against") == null){
									violation.put("against", protectectedCharacteristic);
									violation.replace("source", null);
								}
			    			}
			    			catch (Exception e) {
//								 violation.put("against", protectectedCharacteristic);
//								 violation.replace("source", null);
								// TODO: handle exception
							}
			    			
			    			violation.put("algo_2_result_for_ini_1", algo_2_result_for_ini_1);
			    			violation.put("algo_2_result_for_ini_2", algo_2_result_for_ini_2);
			    			this.violations.add(violation);
			    			def_1_intermediate_result.put("forlumlar_4", "invalid");
			    			validity_for_distinct_protected_charsArrayList.add("invalid");
			    			System.out.println("|"+ pairedInitialisations.get(k).get("ini_1") + " |" + algo_2_result_for_ini_1 + "  |");
			    			System.out.println("|"+ pairedInitialisations.get(k).get("ini_2") + " |" + algo_2_result_for_ini_2 + "  |" + "Forumlar 4 Invalid");
			    			System.out.println("---------------------------------------------------------------");
			    		}
			    		
			    		def_1_result.add(def_1_intermediate_result);	
			    	}
			    }
			}
		}
		return true;
	}
	
	/**
	 * returns a list of similar initializations.
	 * 
	 * @param protectedCharacteristic
	 * @param initializations
	 * @return
	 */
	public List<HashMap> similarInitializations(String protectedCharacteristic, List<HashMap> initializations){
		
		List<HashMap> paired = new ArrayList<>();
		while(true) {
			if(initializations.size() == 0) {
				break;
			}
			HashMap<String, String> initialisation = initializations.get(0);
			List<String> current = this.toComparableVector(initialisation, protectedCharacteristic);
			initializations.remove(0);
			for(int i=0; i<initializations.size(); i++) {
				HashMap<String, String> iterationCurrent = initializations.get(i);
				if(current.equals(this.toComparableVector(iterationCurrent, protectedCharacteristic))) {
					HashMap<String, String> entry = new HashMap<>();
					entry.put("ini_1", initialisation.get("__Ini_ID"));
					entry.put("ini_2", iterationCurrent.get("__Ini_ID"));
					entry.put("protected_characteristic", protectedCharacteristic);
					entry.put("value_1", initialisation.get(protectedCharacteristic));
					entry.put("value_2", iterationCurrent.get(protectedCharacteristic));					
					initializations.remove(i);
					paired.add(entry);
					break;
				}
			}
		}
		return paired;
	}
	
	/**
	 * Converts the initialization into a vector that can be easily compared
	 * 
	 * 
	 * @param initialisation
	 * @param ProtectedCharacteristic
	 * @return
	 */
	private List<String> toComparableVector(HashMap<String, String> initialisation, String ProtectedCharacteristic){
		List<String> result = new ArrayList<>();
		for (String key : initialisation.keySet()) {
			if(key.equals("__Ini_ID") || key.equals(ProtectedCharacteristic)) {
				continue;
			}
			result.add(initialisation.get(key));
		}
		return result;
	}
	
	/**
	 * Gets the result of algorithm 2 for an initialization given the decision data
	 * 
	 * @param initialisation_id
	 * @param decisionDataAttrib
	 * @param decsistionDataAttrVal
	 * @return
	 */
	private HashMap<String, String> algo2ResultForIniAndDecisionData(String initialisation_id, String decisionDataAttrib, String decsistionDataAttrVal){
		for(int i=0; i<this.algorithm_2_results.size(); i++){
			if(this.algorithm_2_results.get(i).get("__Ini_ID").equals(initialisation_id) && this.algorithm_2_results.get(i).get("decision_data_attribute").equals(decisionDataAttrib) && this.algorithm_2_results.get(i).get("value").equals(decsistionDataAttrVal)) {
				return this.algorithm_2_results.get(i);
			}
		}
		return null;
	}

}
