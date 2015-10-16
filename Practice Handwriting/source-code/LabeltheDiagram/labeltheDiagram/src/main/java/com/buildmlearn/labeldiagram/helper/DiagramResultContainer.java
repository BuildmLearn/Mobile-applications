package com.buildmlearn.labeldiagram.helper;

import java.util.HashMap;

import com.buildmlearn.labeldiagram.entity.Result;

public class DiagramResultContainer {

	private static DiagramResultContainer resultcontainer;
	
	private HashMap<String, Result> results;
	
	private DiagramResultContainer(){
		
		results = new HashMap<String, Result>();
		
	}
	
	public static DiagramResultContainer getInstance(){
		
		if(resultcontainer== null){
			
			resultcontainer = new DiagramResultContainer();
			
		}
		
		return resultcontainer;
	}

	public HashMap<String, Result> getResults() {
		return results;
	}

	public void setResults(HashMap<String, Result> results) {
		this.results = results;
	}
	
	
	
}
