package org.buildmlearn.learnfrommap;

import java.util.ArrayList;
import java.util.Random;

import org.buildmlearn.learnfrommap.databasehelper.Database;
import org.buildmlearn.learnfrommap.parser.XmlParser;
import org.buildmlearn.learnfrommap.questionmodule.DbRow;
import org.buildmlearn.learnfrommap.questionmodule.GeneratedQuestion;
import org.buildmlearn.learnfrommap.questionmodule.QuestionModuleException;
import org.buildmlearn.learnfrommap.questionmodule.XmlQuestion;
import org.buildmlearn.learnfrommap.questionmodule.GeneratedQuestion.Type;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

public class AsyncTaskFragment extends Fragment {

	private static final String TAG = AsyncTaskFragment.class.getSimpleName();

	private Context mContext;

	static interface TaskCallbacks {
		void onPreExecute();
		void onProgressUpdate(int percent);
		void onCancelled();
		void onPostExecute(Object questions);
	}

	private TaskCallbacks mCallbacks;
	private GenerateQuestions genQuesAsync;
	private boolean mRunning;
	private String mSelection;
	private String mValue;
	public int mQuestionCount;
	private String mMode;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mCallbacks = (TaskCallbacks) activity;
		mContext = getActivity().getApplicationContext();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//mContext = getActivity().getApplicationContext();
		// Retain this fragment across configuration changes.
		setRetainInstance(true);
		Bundle bundle = getArguments();
		mSelection = bundle.getString("SELECTION");
		mValue = bundle.getString("VALUE");
		mQuestionCount = bundle.getInt("QUESTION_COUNT");
		mMode = bundle.getString("MODE");

		// Create and execute the background task.
		genQuesAsync = new GenerateQuestions();
		genQuesAsync.execute();
		mRunning = true;

	}

	/**
	 * Set the callback to null so we don't accidentally leak the 
	 * Activity instance.
	 */
	//	@Override
	//	public void onDetach() {
	//		super.onDetach();
	//		mCallbacks = null;
	//	}


	@Override
	public void onDestroy() 
	{
		Log.i(TAG, "onDestroy()");
		super.onDestroy();
		cancel();
	}

	public void start() {
		if (!mRunning) 
		{
			genQuesAsync = new GenerateQuestions();
			genQuesAsync.execute();
			mRunning = true;
		}
	}

	/**
	 * Cancel the background task.
	 */
	public void cancel() {
		if (mRunning) 
		{
			genQuesAsync.cancel(false);
			genQuesAsync = null;
			mRunning = false;
		}
	}

	/**
	 * Returns the current state of the background task.
	 */
	public boolean isRunning() {
		return mRunning;
	}

	public class GenerateQuestions extends AsyncTask<Void, Integer, Object>
	{
		private ArrayList<DbRow> globalDbRow;
		private ArrayList<GeneratedQuestion> questionList;

		@Override
		protected ArrayList<GeneratedQuestion> doInBackground(Void... params) {

			questionList = new ArrayList<GeneratedQuestion>();
			globalDbRow = new ArrayList<DbRow>();
//			String path = null;
//			try
//			{
//				path = mContext.getApplicationInfo().dataDir;
//			}
//			catch(NullPointerException e)
//			{
//				e.printStackTrace();
//			}
			//Log.e("Path", path);
			Database db = new Database(mContext, 1, mContext.getFilesDir().getPath());
			XmlParser xmlParser = new XmlParser(mContext);
			ArrayList<XmlQuestion> questionRules = xmlParser.fetchQuestions();		
			Random random = new Random();
			ArrayList<Integer> blackListRules = new ArrayList<Integer>();

			//Where condition selection
			//Explore Mode
			String where = null;
			if(mSelection.equals("CONTINENT"))
			{
				where = "continent = " + mValue;
			}
			else if(mSelection.equals("COUNTRY"))
			{
				try {
					where = "country = " + db.getId("SELECT * FROM country WHERE name='" + mValue + "'");
				} catch (QuestionModuleException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			for(int i = 1; i<= mQuestionCount; i++)
			{
				int randomNo = random.nextInt(questionRules.size());
				XmlQuestion questionRule = questionRules.get(randomNo);
				if(!blackListRules.contains(randomNo))
				{
					if(mMode.equals("CLASSIC_MODE"))
					{
						if(questionRule.getAnswer().equals("country"))
						{
							blackListRules.add(randomNo);
							i--;
							continue;
						}
						if(questionRule.getCount().equals("unique"))
						{
							blackListRules.add(randomNo);
						}
					}
					questionRule.printRule(); 
					Log.e("COUNT", i+ "");


					String tableName = questionRule.getCode();
					String query = "SELECT * FROM " + tableName + " WHERE "+ where +" LIMIT ";
					String countQuery = "SELECT COUNT(*) FROM " + tableName + " WHERE " + where;
					try {
						DbRow row = db.rawSelect(query, countQuery);
						String question = questionRule.getFormat().replace(":X:", row.getDataByColumnName(questionRule.getRelation()));
						String answer = row.getDataByColumnName(questionRule.getAnswer());
						GeneratedQuestion genQues;
						if(questionRule.getType() == XmlQuestion.Type.MultipleChoiceQuestion)
						{
							String[] options = db.createOptions(questionRule.getAnswer(), answer, questionRule.getCode());
							genQues = new GeneratedQuestion(row, questionRule, question, answer, options);
						}
						else if(questionRule.getType() == XmlQuestion.Type.FillBlanks)
						{
							genQues = new GeneratedQuestion(row, questionRule, question, answer, Type.Fill);
						}
						else
						{
							genQues = new GeneratedQuestion(row, questionRule, question, answer, Type.Pin);
						}
						questionList.add(genQues);
						publishProgress(i);
					} catch (QuestionModuleException e) {
						blackListRules.add(randomNo);
						i--;
						e.printStackTrace();
					}

					//					} catch (NoDbRowException e) {
					//						// TODO Auto-generated catch block
					//						e.printStackTrace();
					//						cancel(true);
					//					}
				}
				else
				{
					i--;
				}
			}
			return questionList;
		}

		@Override
		protected void onPostExecute(Object result) {
			super.onPostExecute(result);
			if (mCallbacks != null) 
			{
				mCallbacks.onPostExecute(result);
				mRunning = false;
			}

		}

		@Override
		protected void onProgressUpdate(final Integer... values) {
			super.onProgressUpdate(values);
			if (mCallbacks != null) 
			{
				mCallbacks.onProgressUpdate(values[0]);
			}
		}




	} 


}
