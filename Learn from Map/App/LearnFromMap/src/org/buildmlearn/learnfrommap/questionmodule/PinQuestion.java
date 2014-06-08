package org.buildmlearn.learnfrommap.questionmodule;

import android.content.Context;

public class PinQuestion extends BaseQuestion {

	public PinQuestion(Context mContext, Question question, float lat, float lng) {
		super(mContext, question, lat, lng);
		// TODO Auto-generated constructor stub
	}

	public PinQuestion(Context mContext, Question question, String locationKey,
			String locationValue) {
		super(mContext, question, locationKey, locationValue);
		// TODO Auto-generated constructor stub
	}

	public PinQuestion(Context mContext, Question question) {
		super(mContext, question);
		// TODO Auto-generated constructor stub
	}

}
