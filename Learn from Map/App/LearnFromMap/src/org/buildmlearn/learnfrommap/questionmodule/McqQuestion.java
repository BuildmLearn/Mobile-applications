package org.buildmlearn.learnfrommap.questionmodule;

import android.content.Context;
import android.util.Log;

public class McqQuestion extends BaseQuestion {



	
	public StructuredQuestion structuredQuestion() throws QuestionModuleException {
		String[] question = super.makeQuestion();
		String[] options = generateOptions(relation, answer);
		return new StructuredQuestion(question[0], question[1], options);
	}

	public McqQuestion(Context montext, Question question, String locationKey,
			String locationValue) {
		super(montext, question, locationKey, locationValue);
	}

	public McqQuestion(Context montext, Question question, float lat, float lng) {
		super(montext, question, lat, lng);
	}

	public McqQuestion(Context montext, Question question) {
		super(montext, question);
	}

	private String[] generateOptions(String relation, String Answer)
	{
		
		return null;
	}
	
	
}
