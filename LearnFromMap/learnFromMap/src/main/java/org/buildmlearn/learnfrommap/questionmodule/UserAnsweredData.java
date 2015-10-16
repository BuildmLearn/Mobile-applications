package org.buildmlearn.learnfrommap.questionmodule;

import java.io.Serializable;
import java.util.Locale;
import org.buildmlearn.learnfrommap.questionmodule.GeneratedQuestion.Type;
import uk.ac.shef.wit.simmetrics.similaritymetrics.JaroWinkler;
import android.annotation.SuppressLint;
import android.content.Context;

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
	private boolean mIsAnswered;
	private boolean mIsCorrect;
	private int mPoints;
	private String mState;
	private String mCountry;


	public UserAnsweredData(Context mContext, String mQuestion, String mAnswer,
			String mUserAnswer, Type mQuestionType, String mAnswerType) {
		super();
		this.mQuestion = mQuestion;
		this.mAnswer = mAnswer;
		this.mUserAnswer = mUserAnswer;
		this.mQuestionType = mQuestionType;
		this.mAnswerType = mAnswerType;

		if(mQuestionType == Type.Pin)
		{
			if(mUserAnswer.length() > 0)
			{
			}
			else
			{
				this.mPoints = 0;
				mIsCorrect = false;
			}
		}
		else
		{
			if(mUserAnswer.length() > 0)
			{
				evaluateFill();
			}
			else
			{
				this.mPoints = 0;
				mIsCorrect = false;
			}
		}
	}

	public void evaluatePin(boolean isCorrect)
	{
		if(isCorrect)
		{
			this.mPoints = 10;
			mIsCorrect = true;
		}
		else
		{
			this.mPoints = 0;
			mIsCorrect = false;
		}

	}



	public UserAnsweredData(String mQuestion, String mAnswer,
			String mUserAnswer, Type mQuestionType, String mAnswerType,
			String[] mOptions, boolean isAnswered) {
		super();
		this.mQuestion = mQuestion;
		this.mAnswer = mAnswer;
		this.mUserAnswer = mUserAnswer;
		this.mQuestionType = mQuestionType;
		this.mAnswerType = mAnswerType;
		this.mOptions = mOptions;
		this.mIsAnswered = isAnswered;
		if(this.mIsAnswered)
		{
			if(this.mAnswer.equals(this.mUserAnswer))
			{
				this.mPoints = 10;
				this.mIsCorrect = true;
			}
			else
			{
				this.mPoints = 0;
				this.mIsCorrect = false;
			}
		}
		else
		{
			this.mPoints = 0;
			this.mIsCorrect = false;
		}

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

	public boolean isAnswered()
	{
		return mIsAnswered;
	}

	public String getState() {
		return mState;
	}

	public void setState(String mState) {
		this.mState = mState;
	}

	public String getCountry() {
		return mCountry;
	}

	public void setCountry(String mCountry) {
		this.mCountry = mCountry;
	}

	private void evaluateFill()
	{
		if(this.mQuestionType == Type.Fill)
		{
			double result = CompareStrings(mUserAnswer, mAnswer);
			if(result > .95)
			{
				this.mIsCorrect = true;
				this.mPoints = 10;
			}
			else
			{
				this.mIsCorrect = false;
				this.mPoints = 0;
			}
		}

	}

	@SuppressLint("DefaultLocale")
	public static double CompareStrings(String stringA, String stringB) {
		stringA = stringA.toLowerCase(Locale.getDefault());
		stringB = stringB.toLowerCase(Locale.getDefault());
		JaroWinkler algorithm = new JaroWinkler();
		return algorithm.getSimilarity(stringA, stringB);
	}

	public boolean isAnswerCorrect()
	{
		return mIsCorrect;
	}

	public int getmPoints() {
		return mPoints;
	}


}
