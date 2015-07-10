package com.buildmlearn.labeldiagram.entity;

import java.util.ArrayList;
import java.util.List;

import android.widget.TextView;

public class Result {

	private String diagramName;
	private float score;
	private float gameScore;
	private List<TextView> correctTagList;
	private List<TextView> incorrectTagList;

	public Result(String diagramName) {
		super();
		this.diagramName = diagramName;
		this.correctTagList = new ArrayList<TextView>();
		this.incorrectTagList = new ArrayList<TextView>();
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

	public List<TextView> getCorrectTagList() {
		return correctTagList;
	}

	public void setCorrectTagList(List<TextView> correctTagList) {
		this.correctTagList = correctTagList;
	}

	public List<TextView> getIncorrectTagList() {
		return incorrectTagList;
	}

	public void setIncorrectTagList(List<TextView> incorrectTagList) {
		this.incorrectTagList = incorrectTagList;
	}

}
