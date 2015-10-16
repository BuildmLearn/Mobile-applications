package com.buildmlearn.labeldiagram.resources;

public class ScoreboardRawItem {
	
	private String diagramTitle;
	private int imageId;
	
	public ScoreboardRawItem(String diagramTitle, int imageId) {
		super();
		this.diagramTitle = diagramTitle;
		this.imageId = imageId;
	}
	
	public String getDiagramTitle() {
		return diagramTitle;
	}
	public void setDiagramTitle(String diagramTitle) {
		this.diagramTitle = diagramTitle;
	}
	public int getImageId() {
		return imageId;
	}
	
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
	
	

}
