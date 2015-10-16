package com.buildmlearn.labeldiagram.entity;

import java.util.List;

public class Lesson {

	private String diagramName;
	private List<String> tagList;
	private List<String> tagDescriptionList;

	public Lesson() {
	}

	public Lesson(String diagramName, List<String> tagList,
			List<String> tagDescriptionList) {
		super();
		this.diagramName = diagramName;
		this.tagList = tagList;
		this.tagDescriptionList = tagDescriptionList;
	}

	public String getDiagramName() {
		return diagramName;
	}

	public void setDiagramName(String diagramName) {
		this.diagramName = diagramName;
	}

	public List<String> getTagList() {
		return tagList;
	}

	public void setTagList(List<String> tagList) {
		this.tagList = tagList;
	}

	public List<String> getTagDescriptionList() {
		return tagDescriptionList;
	}

	public void setTagDescriptionList(List<String> tagDescriptionList) {
		this.tagDescriptionList = tagDescriptionList;
	}

}
