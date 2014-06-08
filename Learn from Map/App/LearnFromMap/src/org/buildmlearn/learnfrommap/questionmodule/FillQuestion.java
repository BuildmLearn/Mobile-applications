package org.buildmlearn.learnfrommap.questionmodule;

import android.content.Context;

public class FillQuestion extends BaseQuestion{

	public FillQuestion(Context montext, Question question, float lat, float lng) {
		super(montext, question, lat, lng);
		// TODO Auto-generated constructor stub
	}

	public FillQuestion(Context montext, Question question, String locationKey,
			String locationValue) {
		super(montext, question, locationKey, locationValue);
		// TODO Auto-generated constructor stub
	}

	public FillQuestion(Context montext, Question question) {
		super(montext, question);
		// TODO Auto-generated constructor stub
	}


}
