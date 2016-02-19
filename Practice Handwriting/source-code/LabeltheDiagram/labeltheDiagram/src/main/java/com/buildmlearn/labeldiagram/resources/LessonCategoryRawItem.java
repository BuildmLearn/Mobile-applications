package com.buildmlearn.labeldiagram.resources;

public class LessonCategoryRawItem {
	
	private String title;
	private String description;
	private int iconId;
	

	public LessonCategoryRawItem(String title, String description,int iconId) {
		super();
		this.title = title;
		this.description = description;
		this.iconId = iconId;
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
	
	public int getIconId() {
		return iconId;
	}

	public void setIconId(int iconId) {
		this.iconId = iconId;
	}
	
	

}
