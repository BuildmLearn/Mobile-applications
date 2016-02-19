package com.buildmlearn.labeldiagram.entity;

import android.widget.TextView;

public class Tag {

	private int id;
	private String message;
	private TextView tagLabel;
	private Position position;
	
	public enum Position{
		LEFT,
		RIGHT
	}
	
	
	public Tag(Position position){
		this.position=position;
	}
			
	public Tag(String message, TextView tagLabel, Position pos) {
		super();
		this.message = message;
		this.tagLabel = tagLabel;
		this.position = pos;
	}

	public Tag(int id, String message, TextView tagLabel, Position pos) {
		super();
		this.id = id;
		this.message = message;
		this.tagLabel = tagLabel;
		this.position = pos;
	}

	
	public Position getPos() {
		return position;
	}
	public void setPos(Position pos) {
		this.position = pos;
	}	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public TextView getTagLabel() {
		return tagLabel;
	}
	public void setTagLabel(TextView tagLabel) {
		this.tagLabel = tagLabel;
	}
	
	
	
}
