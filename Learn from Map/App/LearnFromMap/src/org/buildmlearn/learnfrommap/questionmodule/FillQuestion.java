package org.buildmlearn.learnfrommap.questionmodule;

import android.content.Context;

public class FillQuestion extends BaseQuestion{

	public FillQuestion(Context montext, Question question, float lat, float lng) {
		super(montext, question, lat, lng);
	}

	public FillQuestion(Context montext, Question question, String locationKey,
			String locationValue) {
		super(montext, question, locationKey, locationValue);
	}

	public FillQuestion(Context montext, Question question) {
		super(montext, question);
	}

	public StructuredQuestion structuredQuestion() throws QuestionModuleException {
		String[] question = super.makeQuestion();
		return new StructuredQuestion(question[0], question[1], StructuredQuestion.Type.Fill);
	}

}
