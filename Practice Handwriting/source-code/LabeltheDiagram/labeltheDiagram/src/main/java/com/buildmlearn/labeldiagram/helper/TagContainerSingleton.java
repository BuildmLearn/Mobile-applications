package com.buildmlearn.labeldiagram.helper;

import java.util.ArrayList;
import java.util.List;

import android.widget.TextView;

public class TagContainerSingleton {

	private static TagContainerSingleton tagContainer;

	private List<TextView> correctLabelList;
	private List<TextView> incorrectLabelList;

	private TagContainerSingleton() {

		correctLabelList = new ArrayList<TextView>();
		incorrectLabelList = new ArrayList<TextView>();

	}
	
	public static TagContainerSingleton getInstance(){
		
		if(tagContainer == null){
			
			tagContainer = new TagContainerSingleton();
			
		}
		return tagContainer;
		
	}

	public List<TextView> getCorrectLabelList() {
		return correctLabelList;
	}

	public void setCorrectLabelList(List<TextView> correctLabelList) {
		this.correctLabelList = correctLabelList;
	}

	public List<TextView> getIncorrectLabelList() {
		return incorrectLabelList;
	}

	public void setIncorrectLabelList(List<TextView> incorrectLabelList) {
		this.incorrectLabelList = incorrectLabelList;
	}

}
