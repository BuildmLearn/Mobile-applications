package com.buildmlearn.labeldiagram.entity;

import java.util.List;

import android.widget.TextView;

public class Record {
	private String diagramName;
	private float score;
	private float gameScore;
	private List<TextView> correctTagList; 
	
	
	public List<TextView> getCorrectTagList() {
		return correctTagList;
	}
	public void setCorrectTagList(List<TextView> correctTagList) {
		this.correctTagList = correctTagList;
	}
	public Record(String diagramName) {
		super();
		this.diagramName = diagramName;
	}
	public String getDiagramName() {
		return diagramName;
	}
	public void setDiagramName(String diagramName) {
		this.diagramName = diagramName;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public float getGameScore() {
		return gameScore;
	}
	public void setGameScore(float gameScore) {
		this.gameScore = gameScore;
	}
}
