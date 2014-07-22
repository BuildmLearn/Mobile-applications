package org.buildmlearn.learnfrommap.questionmodule;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import org.buildmlearn.learnfrommap.questionmodule.GeneratedQuestion.Type;
import uk.ac.shef.wit.simmetrics.similaritymetrics.JaroWinkler;
import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.FloatMath;
import android.util.Log;

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
	private transient Context mContext;
	private int mPoints;
	private double mLat;
	private double mLog;


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
				String[] coords = mUserAnswer.split(",");
				this.mLat = Double.parseDouble(coords[0]);
				this.mLog = Double.parseDouble(coords[1]);
				this.mContext = mContext;
				evaluatePin();
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

	private void evaluatePin()
	{

		Address address = getAddress(mLat, mLog);
		if(address == null)
		{
			this.mPoints = 0;
			mIsCorrect = false;
			Log.e("GeoCoder", "Address Null");
		}
		else if(mAnswerType.equals("country"))
		{
			
			//Country()
			String country = address.getCountryName();
			Log.e("Country", country);
			if(country.equals(mAnswer))
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
		else if(mAnswerType.equals("state"))
		{
			//getState()
			String state = address.getAdminArea();

			if(state != null && state.equals(mAnswer))
			{
				Log.e("State", state);
				this.mPoints = 10;
				mIsCorrect = true;
			}
			else
			{
				this.mPoints = 0;
				mIsCorrect = false;
			}

		}
		else	
		{

			String[] coords = mAnswer.split(",");
			float lat = Float.parseFloat(coords[0]);
			float lng = Float.parseFloat(coords[1]);
			double distance = distanceBetween(lat, lng, (float)mLat, (float)mLog);
			distance /= 100000;
			if(distance < 10)
			{
				int point = 10-(int)distance;
				this.mPoints = point;
				mIsCorrect = true;
			}
			else
			{
				this.mPoints = 0;
				mIsCorrect = false;
			}
		}

	}

	@SuppressLint("FloatMath") 
	private double distanceBetween(float lat1, float lng1, float lat2, float lng2) {
		float x = (float) (180/3.14169);

		float a1 = lat1 / x;
		float a2 = lng1 / x;
		float b1 = lat2 / x;
		float b2 = lng2 / x;

		float t1 = FloatMath.cos(a1)*FloatMath.cos(a2)*FloatMath.cos(b1)*FloatMath.cos(b2);
		float t2 = FloatMath.cos(a1)*FloatMath.sin(a2)*FloatMath.cos(b1)*FloatMath.sin(b2);
		float t3 = FloatMath.sin(a1)*FloatMath.sin(b1);
		double tt = Math.acos(t1 + t2 + t3);

		return 6366000*tt;
	}

	//Converts coordinates to country
	private Address getAddress(double lat, double lng) {
		Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
		try {
			List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
			if(addresses.size() == 0)
			{
				return null;
			}
			Address obj = addresses.get(0);
			return obj;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
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
	
	public static double CompareStrings(String stringA, String stringB) {
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
