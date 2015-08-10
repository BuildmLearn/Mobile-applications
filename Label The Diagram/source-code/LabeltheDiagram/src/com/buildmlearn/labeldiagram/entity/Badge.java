package com.buildmlearn.labeldiagram.entity;

public class Badge {
	
	// Fields
	private String title;
	private String description;
	private int iconId;
	
	// Constructors
	public Badge() {
		super();
	}
	
	public Badge(String title, String description) {
		super();
		this.title = title;
		this.description = description;
	}
	
	public Badge(String title, String description, int iconId) {
		super();
		this.title = title;
		this.description = description;
		this.iconId = iconId;
	}
	
	// Getters and Setters
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
