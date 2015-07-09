package com.buildmlearn.labeldiagram.helper;

import java.util.HashMap;

public class DiagramResultContainer {

	private static DiagramResultContainer resultcontainer;
	
	private HashMap<String, HashMap<String, Integer>> results;
	
	private DiagramResultContainer(){
		
		results = new HashMap<String, HashMap<String,Integer>>();
		
	}
	
	public static DiagramResultContainer getInstance(){
		
		if(resultcontainer== null){
			
			resultcontainer = new DiagramResultContainer();
			
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
