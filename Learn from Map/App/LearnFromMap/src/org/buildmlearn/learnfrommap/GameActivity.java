package org.buildmlearn.learnfrommap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.buildmlearn.learnfrommap.databasehelper.Database;
import org.buildmlearn.learnfrommap.parser.XmlParser;
import org.buildmlearn.learnfrommap.questionmodule.BaseQuestion;
import org.buildmlearn.learnfrommap.questionmodule.GeneratedQuestion;
import org.buildmlearn.learnfrommap.questionmodule.GeneratedQuestion.Type;
import org.buildmlearn.learnfrommap.questionmodule.QuestionModuleException;
import org.buildmlearn.learnfrommap.questionmodule.XmlQuestion;

import com.google.android.gms.maps.SupportMapFragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class GameActivity extends Helper implements AsyncTaskFragment.TaskCallbacks {

	private static final String TAG_TASK_FRAGMENT = "task_fragment";
	private static final String KEY_CURRENT_PROGRESS = "current_progress";
	private static final String KEY_PERCENT_PROGRESS = "percent_progress";
	private String mode;
	private String selection;
	private String value;
	private ProgressBar progressBar;
	private TextViewPlus loadingText;
	private TextViewPlus timer;
	private TextViewPlus option1;
	private TextViewPlus option2;
	private TextViewPlus option3;
	private TextViewPlus option4;
	private CountDownTimer countTimer;
	private TextViewPlus displayQuestion;
	private Database db;

	private RelativeLayout main;
	private View view;
	public List<GeneratedQuestion> question;
	private int questionCounter;
	private AsyncTaskFragment mTaskFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		Intent intent = getIntent();
		selection = intent.getStringExtra("SELECTION");
		value = intent.getStringExtra("VALUE");



		FragmentManager fm = getSupportFragmentManager();
		mTaskFragment = (AsyncTaskFragment) fm.findFragmentByTag(TAG_TASK_FRAGMENT);

		// If the Fragment is non-null, then it is currently being
		// retained across a configuration change.
		if (mTaskFragment == null) 
		{
			mTaskFragment = new AsyncTaskFragment();
			Bundle bundle = new Bundle();
			bundle.putString("SELECTION", selection);
			bundle.putString("VALUE", value);
			fm.beginTransaction().add(mTaskFragment, TAG_TASK_FRAGMENT).commit();
		}



		question = new ArrayList<GeneratedQuestion>();
		questionCounter = 0;

		mode = intent.getStringExtra("MODE");

		loadingText = (TextViewPlus)findViewById(R.id.question);
		progressBar = (ProgressBar)findViewById(R.id.game_progressbar);
		progressBar.setMax(20);
		progressBar.setProgress(0);
		progressBar.incrementProgressBy(1);
		if(mode.equals("EXPLORE_MODE"))
		{
			setTitle("Explore Mode");
		}

		main = (RelativeLayout)findViewById(R.id.main_layout);
		view = getLayoutInflater().inflate(R.layout.layout_play_game, main,false);


		// Restore saved state.
		if (savedInstanceState != null) {
			progressBar.setProgress(savedInstanceState.getInt(KEY_CURRENT_PROGRESS));
			loadingText.setText(savedInstanceState.getString(KEY_PERCENT_PROGRESS));
		}



	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(KEY_CURRENT_PROGRESS, progressBar.getProgress());
		outState.putString(KEY_PERCENT_PROGRESS, loadingText.getText().toString());
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
		countTimer.cancel();
		if(question.get(questionCounter-1).getType() == Type.Pin)
		{
			android.support.v4.app.Fragment fragment = (getSupportFragmentManager().findFragmentById(R.id.mapFragment));  
			if(fragment != null)
			{
				FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
				ft.remove(getSupportFragmentManager().findFragmentById(R.id.mapFragment)).commit();
				getSupportFragmentManager().popBackStackImmediate();
				main.removeAllViews();	

			}	

		}

		loadQuestion();
	}

	public void loadQuestion()
	{

		if(questionCounter == 20)
		{
			return;
		}
		GeneratedQuestion genQuestion = question.get(questionCounter++);
		if(genQuestion.getType() == Type.Fill)
		{
			view = getLayoutInflater().inflate(R.layout.layout_fill, main,false);
			main.removeAllViews();
			main.addView(view);
			displayQuestion = (TextViewPlus)findViewById(R.id.question);
			displayQuestion.setText(genQuestion.getQuestion());

		}
		else if(genQuestion.getType() == Type.Mcq)
		{

			view = getLayoutInflater().inflate(R.layout.activity_mcq, main,false);
			main.removeAllViews();
			main.addView(view);
			displayQuestion = (TextViewPlus)findViewById(R.id.question);
			displayQuestion.setText(genQuestion.getQuestion());
			option1 = (TextViewPlus)findViewById(R.id.mcq_option1);
			option2 = (TextViewPlus)findViewById(R.id.mcq_option2);
			option3 = (TextViewPlus)findViewById(R.id.mcq_option3);
			option4 = (TextViewPlus)findViewById(R.id.mcq_option4);
			String[] temp = genQuestion.getOption();
			String[] options = {temp[0], temp[1], temp[2], genQuestion.getAnswer()};
			shuffleArray(options);
			option1.setText(options[0]);
			option2.setText(options[1]);
			option3.setText(options[2]);
			option4.setText(options[3]);


		}
		else
		{
			//			killOldMap();	
			view = getLayoutInflater().inflate(R.layout.activity_map, main,false);
			main.removeAllViews();
			main.addView(view);
			displayQuestion = (TextViewPlus)findViewById(R.id.question);
			displayQuestion.setText(genQuestion.getQuestion());
			new Handler().post(new Runnable() {

				@Override
				public void run() {
					getMapView((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.mapFragment));

				}
			});	
		}


		timer = (TextViewPlus)findViewById(R.id.timer);
		countTimer = new CountDownTimer(30000, 1000) {

			public void onTick(long millisUntilFinished) {
				timer.setText("Time remaining: " + millisUntilFinished / 1000);
			}

			public void onFinish() {
				nextQuestion(null);
			}
		}.start();




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


	

	@Override
	public void onPreExecute() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProgressUpdate(final int percent) {

		progressBar.setProgress(percent);
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				loadingText.setText("Loading question " +percent + " of 20");

			}
		});

	}

	@Override
	public void onCancelled() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPostExecute() {
		// TODO Auto-generated method stub
		main.removeAllViews();
		main.addView(view);
	}

	@Override
	public void doInBackground() {
		db = new Database(getApplicationContext());
		Random random = new Random();
		XmlParser xmlParser = new XmlParser(getApplicationContext());
		ArrayList<XmlQuestion> questionRules = xmlParser.fetchQuestions();
		for(int i = 1; i< 21; i++)
		{
			XmlQuestion rule = getRandomQuestionRule(questionRules, random);
			makeQuestion(rule, selection, value, db);
			onProgressUpdate(i);
		}
		return;

	}

	private GeneratedQuestion makeQuestion(XmlQuestion questionRule, String selection, String value, Database db)
	{

		BaseQuestion question = new BaseQuestion(db, questionRule, selection, value);
		try {
			GeneratedQuestion formedQuestion = question.makeQuestion();
			GameActivity.this.question.add(formedQuestion);
			Log.e("Question", formedQuestion.getQuestion());
		} catch (QuestionModuleException e) {
			e.printStackTrace();
		}
		return null;
	}

	private XmlQuestion getRandomQuestionRule(ArrayList<XmlQuestion> questionRule, Random random)
	{
		int index = random.nextInt(questionRule.size());
		return questionRule.get(index);
	}

}
