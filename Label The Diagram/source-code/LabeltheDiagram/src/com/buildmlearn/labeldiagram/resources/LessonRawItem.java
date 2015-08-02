package com.buildmlearn.labeldiagram.resources;

public class LessonRawItem {
	
	private String tagTitle;
	private String tagDescription;
	private int tagNumberIcon;
	
	public LessonRawItem(String tagTitle, String tagDescription,
			int tagNumberIcon) {
		super();
		this.tagTitle = tagTitle;
		this.tagDescription = tagDescription;
		this.tagNumberIcon = tagNumberIcon;
	}
	
	public String getTagTitle() {
		return tagTitle;
	}
	
	public void setTagTitle(String tagTitle) {
		this.tagTitle = tagTitle;
	}
	
	public String getTagDescription() {
		return tagDescription;
	}
	
	public void setTagDescription(String tagDescription) {
		this.tagDescription = tagDescription;
	}
	
	public int getTagNumberIcon() {
		return tagNumberIcon;
	}
	
	public void setTagNumberIcon(int tagNumberIcon) {
		this.tagNumberIcon = tagNumberIcon;
	}
	
	

}
