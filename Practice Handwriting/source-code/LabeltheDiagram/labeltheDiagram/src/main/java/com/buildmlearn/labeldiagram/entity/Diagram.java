package com.buildmlearn.labeldiagram.entity;

import java.util.List;

import android.widget.ImageView;

public class Diagram {
	
	private String diagramTitle;
	private List<Tag> taglist;
	private List<ImageView> placeholderList;
	
	
	public List<Tag> getTaglist() {
		return taglist;
	}
	public void setTaglist(List<Tag> taglist) {
		this.taglist = taglist;
	}
	public List<ImageView> getPlaceholderList() {
		return placeholderList;
	}
	public void setPlaceholderList(List<ImageView> placeholderList) {
		this.placeholderList = placeholderList;
	}
	
	public String getDiagramTitle() {
		return diagramTitle;
	}
	public void setDiagramTitle(String diagramTitle) {
		this.diagramTitle = diagramTitle;
	}

}
