package org.buildmlearn.learnfrommap;

import java.util.ArrayList;
import java.util.Date;
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

/**
 * This class extends the Fragment class and is used to generate questions in a separate fragment.
 * The main aim of generating questions in a different fragment is retain the state
 * 
 * @author Abhishek
 *
 */
public class AsyncTaskFragment extends Fragment {

	private static final String TAG = AsyncTaskFragment.class.getSimpleName();
	public static long lastSeed;

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

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onAttach(android.app.Activity)
	 */
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mCallbacks = (TaskCallbacks) activity;
		mContext = getActivity().getApplicationContext();
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//mContext = getActivity().getApplicationContext();
		// Retain this fragment across configuration changes.
		lastSeed = 0;
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

	/**
	 * Following Async Task contains the question generation module
	 * 
	 * 
	 * 
	 * @author Abhishek
	 *
	 */
	public class GenerateQuestions extends AsyncTask<Void, Integer, Object>
	{
		private ArrayList<GeneratedQuestion> questionList;

		@Override
		protected ArrayList<GeneratedQuestion> doInBackground(Void... params) {
			int globalCount = 0;

			questionList = new ArrayList<GeneratedQuestion>();
			Database db = new Database(mContext, 1, mContext.getFilesDir().getPath());
			XmlParser xmlParser = new XmlParser(mContext);
			ArrayList<XmlQuestion> questionRules = xmlParser.fetchQuestions();	
			lastSeed += new Date().getTime();
			Random random = new Random(lastSeed);
			ArrayList<Integer> blackListRules = new ArrayList<Integer>();
			ArrayList<KeyHolder> dbRows = new ArrayList<KeyHolder>();

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
			else
			{
				where = "1=1";
			}
			
			//
			//Main logic starts here//
			//
			for(int i = 1; i<= mQuestionCount; i++)
			{
				int randomNo = random.nextInt(questionRules.size());
				Log.d("Random", randomNo + "");
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
					else if(mMode.equals("CATEGORY_MODE"))
					{
						if(!questionRule.getAlias().equals(mValue))
						{
							blackListRules.add(randomNo);
							i--;
							continue;
						}
					}
					questionRule.printRule(); 
					Log.e("COUNT", i+ "");


					String tableName = questionRule.getCode();
					String query = "SELECT * FROM " + tableName + " WHERE "+ where +" LIMIT ";
					String countQuery = "SELECT COUNT(*) FROM " + tableName + " WHERE " + where;
					try {
						DbRow row = null;
						//
						boolean loop = true;
						boolean removeLoop = false;
						int counter = 0;
						while(loop)
						{
							boolean isPresent = false;
							row = db.rawSelect(query, countQuery);
							KeyHolder temp = new KeyHolder(questionRule.getCode(), row.getDataByColumnName(questionRule.getRelation()), row.getDataByColumnName(questionRule.getAnswer()));
							for(KeyHolder holder :  dbRows)
							{
								if(holder.equals(temp))
								{
									isPresent = true;
									break;
								}
							}
							if(isPresent)
							{
								counter++;
								Log.d("DUPLICATE", "Duplicate");
							}
							else
							{
								Log.d("RESET GLOBAL COUNT", "0");
								globalCount = 0;
								dbRows.add(temp);
								loop = false;
							}
							if(counter == 10)
							{
								loop = false;
								removeLoop = true;
							}
							
						}
						if(removeLoop)
						{
							blackListRules.add(randomNo);
							Log.d("REMOVED", "removed");
							i--;
							continue;
						}
						
						
						//
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
				}
				else
				{
					i--;
					globalCount++;
					Log.d("GLOBAL COUNT", globalCount + "");
					if(globalCount == 200)
					{
						return null;
					}
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

/**
 * Key Holder is used as a holder class for code, relation and answer. The main purpose of this holder class is
 * to find and delete duplicates in generated question.
 * 
 * @author Abhishek
 *
 */
class KeyHolder
{
	public String code;
	public String relation;
	public String answer;
	
	public KeyHolder(String code, String relation, String answer) {
		super();
		this.code = code;
		this.relation = relation;
		this.answer = answer;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		KeyHolder x = (KeyHolder)o;
		if(!this.answer.equals(x.answer))
			return false;
//		if(!this.code.equals(x.code))
//			return false;
		if(!this.relation.equals(x.relation))
			return false;
		return true;	
	}
	
}