package org.buildmlearn.learnfrommap.questionmodule;

import java.io.Serializable;

import org.buildmlearn.learnfrommap.questionmodule.GeneratedQuestion.Type;

public class UserAnsweredData  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mQuestion;
	private String mAnswer;
	private String mUserAnswer;
	private GeneratedQuestion.Type mQuestionType;
	private String mAnswerType;
	private String[] mOptions;
	
	
	public UserAnsweredData(String mQuestion, String mAnswer,
			String mUserAnswer, Type mQuestionType, String mAnswerType) {
		super();
		this.mQuestion = mQuestion;
		this.mAnswer = mAnswer;
		this.mUserAnswer = mUserAnswer;
		this.mQuestionType = mQuestionType;
		this.mAnswerType = mAnswerType;
	}


	public UserAnsweredData(String mQuestion, String mAnswer,
			String mUserAnswer, Type mQuestionType, String mAnswerType,
			String[] mOptions) {
		super();
		this.mQuestion = mQuestion;
		this.mAnswer = mAnswer;
		this.mUserAnswer = mUserAnswer;
		this.mQuestionType = mQuestionType;
		this.mAnswerType = mAnswerType;
		this.mOptions = mOptions;
	}


	public String getmQuestion() {
		return mQuestion;
	}


	public void setmQuestion(String mQuestion) {
		this.mQuestion = mQuestion;
	}


	public String getmAnswer() {
		return mAnswer;
	}


	public void setmAnswer(String mAnswer) {
		this.mAnswer = mAnswer;
	}


	public String getmUserAnswer() {
		return mUserAnswer;
	}


	public void setmUserAnswer(String mUserAnswer) {
		this.mUserAnswer = mUserAnswer;
	}


	public GeneratedQuestion.Type getmQuestionType() {
		return mQuestionType;
	}


	public void setmQuestionType(GeneratedQuestion.Type mQuestionType) {
		this.mQuestionType = mQuestionType;
	}


	public String getmAnswerType() {
		return mAnswerType;
	}


	public void setmAnswerType(String mAnswerType) {
		this.mAnswerType = mAnswerType;
	}


	public String[] getmOptions() {
		return mOptions;
	}


	public void setmOptions(String[] mOptions) {
		this.mOptions = mOptions;
	}
	
	
	
	
}
