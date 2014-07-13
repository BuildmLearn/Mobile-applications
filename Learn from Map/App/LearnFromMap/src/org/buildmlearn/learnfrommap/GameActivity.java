package org.buildmlearn.learnfrommap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.buildmlearn.learnfrommap.databasehelper.Database;
import org.buildmlearn.learnfrommap.parser.XmlParser;
import org.buildmlearn.learnfrommap.questionmodule.DbRow;
import org.buildmlearn.learnfrommap.questionmodule.GeneratedQuestion;
import org.buildmlearn.learnfrommap.questionmodule.GeneratedQuestion.Type;
import org.buildmlearn.learnfrommap.questionmodule.QuestionModuleException;
import org.buildmlearn.learnfrommap.questionmodule.UserAnsweredData;
import org.buildmlearn.learnfrommap.questionmodule.XmlQuestion;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class GameActivity extends Helper {

	private final int QUESTION_COUNT = 10;
	private String mode;
	private String mSelection;
	private String mValue;	
	private ProgressBar mProgressBar;
	private TextViewPlus mLoadingText;
	private TextViewPlus mTimer;
	private TextViewPlus mOption1;
	private TextViewPlus mOption2;
	private TextViewPlus mOption3;
	private TextViewPlus mOption4;
	private CountDownTimer mCountTimer;
	private TextViewPlus mDisplayQuestion;
	private int mSdk;
	private int mSelectedOption;
	private ArrayList<UserAnsweredData> mAnsweredList;
	private RelativeLayout mMain;
	private View mView;
	private List<GeneratedQuestion> mQuestion;
	private int mQuestionCounter;
	private GeneratedQuestion genQuestion;
	private String[] options;
	private boolean mIsAnswered;
	private String mDisplatMsg;

	@Override	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mSdk = android.os.Build.VERSION.SDK_INT;
		mAnsweredList = new ArrayList<UserAnsweredData>();
		setContentView(R.layout.activity_game);
		mQuestion = new ArrayList<GeneratedQuestion>();
		mQuestionCounter = 0;
		Intent intent = getIntent();
		mode = intent.getStringExtra("MODE");
		mDisplatMsg = intent.getStringExtra("DISPLAY");
		mSelection = intent.getStringExtra("SELECTION");
		mValue = intent.getStringExtra("VALUE");
		mLoadingText = (TextViewPlus)findViewById(R.id.question);
		mProgressBar = (ProgressBar)findViewById(R.id.game_progressbar);
		mProgressBar.setMax(20);
		mProgressBar.setProgress(0);
		if(mode.equals("EXPLORE_MODE"))
		{
			setTitle("Explore Mode");
		}
		else if(mode.equals("CLASSIC_MODE"))
		{
			setTitle("Classic Mode");
		}
		GenerateQuestions genQues = new GenerateQuestions(mSelection, mValue);
		genQues.execute();
		mMain = (RelativeLayout)findViewById(R.id.main_layout);
		mView = getLayoutInflater().inflate(R.layout.layout_play_game, mMain,false);



	}





	static void shuffleArray(String[] ar)
	{
		Random rnd = new Random();
		for (int i = ar.length - 1; i > 0; i--)
		{
			int index = rnd.nextInt(i + 1);
			String a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
	}

	public void startGame(View v)
	{
		loadQuestion();

	}

	public void nextQuestion(View v)
	{
		mCountTimer.cancel();
		if(mQuestion.get(mQuestionCounter-1).getType() == Type.Pin)
		{
			android.support.v4.app.Fragment fragment = (getSupportFragmentManager().findFragmentById(R.id.mapFragment));  
			if(fragment != null)
			{
				FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
				ft.remove(getSupportFragmentManager().findFragmentById(R.id.mapFragment)).commit();
				getSupportFragmentManager().popBackStackImmediate();
				mMain.removeAllViews();	
			}	
		}
		loadQuestion();
	}

	@SuppressLint("NewApi") 
	public void onOptionClick(View v)
	{
		switch (v.getId()) {
		case R.id.mcq_option1:
			if(mSdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
				mOption1.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_click));
				mOption2.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
				mOption4.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
				mOption3.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));

			} else {
				mOption1.setBackground(getResources().getDrawable(R.drawable.button_click));
				mOption2.setBackground(getResources().getDrawable(R.drawable.border_white));
				mOption3.setBackground(getResources().getDrawable(R.drawable.border_white));
				mOption4.setBackground(getResources().getDrawable(R.drawable.border_white));
			}
			mSelectedOption = 0;
			break;
		case R.id.mcq_option2:
			if(mSdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
				mOption2.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_click));
				mOption1.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
				mOption3.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
				mOption4.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
			} else {
				mOption2.setBackground(getResources().getDrawable(R.drawable.button_click));
				mOption1.setBackground(getResources().getDrawable(R.drawable.border_white));
				mOption3.setBackground(getResources().getDrawable(R.drawable.border_white));
				mOption4.setBackground(getResources().getDrawable(R.drawable.border_white));
			}		
			mSelectedOption = 1;
			break;
		case R.id.mcq_option3:
			if(mSdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
				mOption3.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_click));
				mOption2.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
				mOption1.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
				mOption4.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
			} else {
				mOption3.setBackground(getResources().getDrawable(R.drawable.button_click));
				mOption2.setBackground(getResources().getDrawable(R.drawable.border_white));
				mOption1.setBackground(getResources().getDrawable(R.drawable.border_white));
				mOption4.setBackground(getResources().getDrawable(R.drawable.border_white));
			}
			mSelectedOption = 2;
			break;
		case R.id.mcq_option4:
			if(mSdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
				mOption4.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_click));
				mOption2.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
				mOption3.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
				mOption1.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
			} else {
				mOption4.setBackground(getResources().getDrawable(R.drawable.button_click));
				mOption2.setBackground(getResources().getDrawable(R.drawable.border_white));
				mOption3.setBackground(getResources().getDrawable(R.drawable.border_white));
				mOption1.setBackground(getResources().getDrawable(R.drawable.border_white));
			}
			mSelectedOption = 3;
			break;
		default:
			break;
		}
		mIsAnswered = true;

	}

	@SuppressWarnings("unchecked")
	public void loadQuestion()
	{
		if(mQuestionCounter > 0)
		{
			String question = genQuestion.getQuestion();
			String answer = genQuestion.getAnswer();
			UserAnsweredData userAnswerData;
			if(genQuestion.getType() == Type.Fill)
			{
				EditText fillAnswer = (EditText)findViewById(R.id.fill_answer);
				String userAnswer  = fillAnswer.getText().toString();
				userAnswerData = new UserAnsweredData(getApplicationContext(), question, answer, userAnswer, genQuestion.getType(), genQuestion.getXml().getAnswer());

			}
			else if(genQuestion.getType() == Type.Mcq)
			{
				String userAnswer;
				if(mIsAnswered)
				{
					userAnswer = options[mSelectedOption];
				}
				else
				{
					userAnswer = "";
				}
				userAnswerData = new UserAnsweredData(question, answer, userAnswer, genQuestion.getType(), genQuestion.getXml().getAnswer(), options, mIsAnswered);

			}
			else
			{
				LatLng postion = getPosition();
				String userAnswer;
				if(postion != null)
				{
					userAnswer = postion.latitude + "," + postion.longitude;
				}
				else
				{
					userAnswer = "";
				}
				userAnswerData = new UserAnsweredData(getApplicationContext(), question, answer, userAnswer, genQuestion.getType(), genQuestion.getXml().getAnswer());
			}
			mAnsweredList.add(userAnswerData);

		}
		if(mQuestionCounter == QUESTION_COUNT)
		{
			Intent intent = new Intent(getApplicationContext(), ScoreActivity.class);
			intent.putParcelableArrayListExtra("SCORE_DATA", (ArrayList<? extends Parcelable>) mAnsweredList);
			startActivity(intent);
			finish();
			return;
		}
		genQuestion = mQuestion.get(mQuestionCounter++);
		if(genQuestion.getType() == Type.Fill)
		{
			mView = getLayoutInflater().inflate(R.layout.layout_fill, mMain,false);
			mMain.removeAllViews();
			mMain.addView(mView);
			mDisplayQuestion = (TextViewPlus)findViewById(R.id.question);
			mDisplayQuestion.setText(genQuestion.getQuestion());
			EditText fillAnswer = (EditText)findViewById(R.id.fill_answer);
			fillAnswer.setText(genQuestion.getAnswer());
			startTimer(60000);

		}
		else if(genQuestion.getType() == Type.Mcq)
		{
			mIsAnswered = false;
			mView = getLayoutInflater().inflate(R.layout.activity_mcq, mMain,false);
			mMain.removeAllViews();
			mMain.addView(mView);
			mDisplayQuestion = (TextViewPlus)findViewById(R.id.question);
			mDisplayQuestion.setText(genQuestion.getQuestion());
			mOption1 = (TextViewPlus)findViewById(R.id.mcq_option1);
			mOption2 = (TextViewPlus)findViewById(R.id.mcq_option2);
			mOption3 = (TextViewPlus)findViewById(R.id.mcq_option3);
			mOption4 = (TextViewPlus)findViewById(R.id.mcq_option4);
			String[] temp = genQuestion.getOption();
			String[] _options = {temp[0], temp[1], temp[2], genQuestion.getAnswer()};
			options = _options;
			shuffleArray(options);
			mOption1.setText(options[0]);
			mOption2.setText(options[1]);
			mOption3.setText(options[2]);
			mOption4.setText(options[3]);
			startTimer(60000);
		}
		else
		{
			mView = getLayoutInflater().inflate(R.layout.activity_map, mMain,false);
			mMain.removeAllViews();
			mMain.addView(mView);
			mDisplayQuestion = (TextViewPlus)findViewById(R.id.question);
			mDisplayQuestion.setText(genQuestion.getQuestion());
			new Handler().post(new Runnable() {

				@Override
				public void run() {
					getMapView((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.mapFragment));

				}
			});	
		}

		getSupportActionBar().setTitle("Question " + mQuestionCounter + " of " + QUESTION_COUNT);

	}


	private void startTimer(int timer)
	{
		mTimer = (TextViewPlus)findViewById(R.id.timer);
		mCountTimer = new CountDownTimer(timer, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {
				mTimer.setText("Time remaining: " + millisUntilFinished / 1000);
			}
			@Override
			public void onFinish() {
				nextQuestion(null);
			}
		}.start();
	}

	@Override
	public void onMapReady() {
		super.onMapReady();
		ProgressBar loading = (ProgressBar)findViewById(R.id.map_progress);
		loading.setVisibility(View.GONE);
		startTimer(90000);	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	public class GenerateQuestions extends AsyncTask<Void, Integer, String>
	{

		String selection;
		String value;
		Database db;

		@Override
		protected String doInBackground(Void... params) {


			db = new Database(getApplicationContext(), 1);
			XmlParser xmlParser = new XmlParser(getApplicationContext());
			ArrayList<XmlQuestion> questionRules = xmlParser.fetchQuestions();		
			Random random = new Random();
			ArrayList<Integer> blackListRules = new ArrayList<Integer>();

			//Where condition selection
			//Explore Mode
			String where = null;
			if(selection.equals("CONTINENT"))
			{
				where = "continent = " + value;
			}
			else if(selection.equals("COUNTRY"))
			{


				try {
					where = "country = " + db.getId("SELECT * FROM country WHERE name='" + value + "'");
				} catch (QuestionModuleException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			for(int i = 1; i<= QUESTION_COUNT; i++)
			{
				int randomNo = random.nextInt(questionRules.size());
				if(!blackListRules.contains(randomNo))
				{
					XmlQuestion questionRule = questionRules.get(randomNo);
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
						mQuestion.add(genQues);
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
				}

			}

			return null;
		}

		public GenerateQuestions(String selection, String value) {
			super();
			this.selection = selection;
			this.value = value;
		}



		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			db.closeReadableDatabase();
			db.close();
			mMain.removeAllViews();
			mMain.addView(mView);
			TextViewPlus selection = (TextViewPlus)findViewById(R.id.play_selection);
			selection.setText(mDisplatMsg);

		}

		@Override
		protected void onProgressUpdate(final Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			mProgressBar.setProgress(values[0]);

			runOnUiThread(new Runnable() {
				public void run() {
					mLoadingText.setText("Loading question " + values[0] + " of " + QUESTION_COUNT);
				}
			});
		}
	}

}
