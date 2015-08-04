package com.buildmlearn.labeldiagram.resources;

public class LessonCategoryRawItem {
	
	private String title;
	private String description;
	
	public LessonCategoryRawItem(String title, String description) {
		super();
		this.title = title;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
