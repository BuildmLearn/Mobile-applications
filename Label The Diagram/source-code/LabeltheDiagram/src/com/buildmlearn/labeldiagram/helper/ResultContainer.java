package com.buildmlearn.labeldiagram.helper;

import java.util.HashMap;

public class ResultContainer {

	private static ResultContainer resultcontainer;
	
	private HashMap<String, HashMap<String, Integer>> results;
	
	private ResultContainer(){
		
		results = new HashMap<String, HashMap<String,Integer>>();
		
	}
	
	public static ResultContainer getInstance(){
		
		if(resultcontainer== null){
			
			resultcontainer = new ResultContainer();
			
		}
		
		return resultcontainer;
	}

	public HashMap<String, HashMap<String, Integer>> getResults() {
		return results;
	}

	public void setResults(HashMap<String, HashMap<String, Integer>> results) {
		this.results = results;
	}
	
	
	
}
