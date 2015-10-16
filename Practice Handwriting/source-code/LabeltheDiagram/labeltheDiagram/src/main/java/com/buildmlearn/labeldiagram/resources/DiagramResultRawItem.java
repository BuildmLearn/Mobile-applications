package com.buildmlearn.labeldiagram.resources;

import android.widget.TextView;

public class DiagramResultRawItem {
	
	private TextView tagLabel;
	private int resultIconId;
		
	
	public DiagramResultRawItem(TextView tagLabel, int resultIconId) {
		super();
		this.tagLabel = tagLabel;
		this.resultIconId = resultIconId;
	}

	public int getResultIconId() {
		return resultIconId;
	}

	public void setResultIconId(int resultIconId) {
		this.resultIconId = resultIconId;
	}	

	public TextView getTagLabel() {
		return tagLabel;
	}

	public void setTagLabel(TextView tagLabel) {
		this.tagLabel = tagLabel;
	}

	
	
	
	

}
