package org.buildmlearn.learnfrommap.questionmodule;

import android.content.Context;

public class PinQuestion extends BaseQuestion {

	public PinQuestion(Context mContext, Question question, float lat, float lng) {
		super(mContext, question, lat, lng);
	}

	public PinQuestion(Context mContext, Question question, String locationKey,
			String locationValue) {
		super(mContext, question, locationKey, locationValue);
	}

	public PinQuestion(Context mContext, Question question) {
		super(mContext, question);
	}

	public StructuredQuestion structuredQuestion() throws QuestionModuleException {
		String[] question = super.makeQuestion();
		return new StructuredQuestion(question[0], question[1], StructuredQuestion.Type.Pin);
	}
}
