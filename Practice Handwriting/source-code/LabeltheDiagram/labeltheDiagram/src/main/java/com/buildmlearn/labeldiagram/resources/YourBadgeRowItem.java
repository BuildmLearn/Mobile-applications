package com.buildmlearn.labeldiagram.resources;

public class YourBadgeRowItem {
	
	private String badgeTitle;
	private int badgeId;
	
	public YourBadgeRowItem(String badgeTitle) {
		super();
		this.badgeTitle = badgeTitle;
	}
	
	public YourBadgeRowItem(String badgeTitle, int badgeId) {
		super();
		this.badgeTitle = badgeTitle;
		this.badgeId = badgeId;
	}
	
	public String getBadgeTitle() {
		return badgeTitle;
	}
	
	public void setBadgeTitle(String badgeTitle) {
		this.badgeTitle = badgeTitle;
	}
	
	public int getBadgeId() {
		return badgeId;
	}
	
	public void setBadgeId(int badgeId) {
		this.badgeId = badgeId;
	}
	
	

}
