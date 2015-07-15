package com.buildmlearn.labeldiagram.resources;

public class DiagramScoreboardResultRawItem {
	
	private String tagLabel;
	private int resultIconId;
	
	public DiagramScoreboardResultRawItem(String tagLabel, int resultIconId) {
		super();
		this.tagLabel = tagLabel;
		this.resultIconId = resultIconId;
	}
	
	public String getTagLabel() {
		return tagLabel;
	}
	
	public void setTagLabel(String tagLabel) {
		this.tagLabel = tagLabel;
	}
	
	public int getResultIconId() {
		return resultIconId;
	}
	
	public void setResultIconId(int resultIconId) {
		this.resultIconId = resultIconId;
	}
	
	

}
