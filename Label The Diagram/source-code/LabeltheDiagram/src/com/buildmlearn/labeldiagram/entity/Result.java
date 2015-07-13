package com.buildmlearn.labeldiagram.entity;

import java.util.ArrayList;
import java.util.List;

import android.widget.TextView;

public class Result {

	private String diagramName;
	private float score;
	private float gameScore;
	private List<String> correctTagList;
	private List<String> incorrectTagList;

	public Result(String diagramName) {
		super();
		this.diagramName = diagramName;
		this.correctTagList = new ArrayList<String>();
		this.incorrectTagList = new ArrayList<String>();
	}

	public Result() {
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

	public List<String> getCorrectTagList() {
		return correctTagList;
	}

	public void setCorrectTagList(List<String> correctTagList) {
		this.correctTagList = correctTagList;
	}

	public List<String> getIncorrectTagList() {
		return incorrectTagList;
	}

	public void setIncorrectTagList(List<String> incorrectTagList) {
		this.incorrectTagList = incorrectTagList;
	}

}
