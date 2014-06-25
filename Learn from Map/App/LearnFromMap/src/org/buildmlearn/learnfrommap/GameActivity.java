package org.buildmlearn.learnfrommap;

import java.util.ArrayList;
import java.util.Random;

import org.buildmlearn.learnfrommap.databasehelper.Database;
import org.buildmlearn.learnfrommap.parser.XmlParser;
import org.buildmlearn.learnfrommap.questionmodule.BaseQuestion;
import org.buildmlearn.learnfrommap.questionmodule.GeneratedQuestion;
import org.buildmlearn.learnfrommap.questionmodule.QuestionModuleException;
import org.buildmlearn.learnfrommap.questionmodule.XmlQuestion;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class GameActivity extends Helper {

	private String mode;
	private String selection;
	private String value;
	private ProgressBar progressBar;
	private TextViewPlus loadingText;
	private RelativeLayout main;
	private View view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		Intent intent = getIntent();
		mode = intent.getStringExtra("MODE");
		selection = intent.getStringExtra("SELECTION");
		value = intent.getStringExtra("VALUE");
		loadingText = (TextViewPlus)findViewById(R.id.question);
		progressBar = (ProgressBar)findViewById(R.id.game_progressbar);
		progressBar.setMax(20);
		progressBar.setProgress(0);
		progressBar.incrementProgressBy(1);
		if(mode.equals("EXPLORE_MODE"))
		{
			setTitle("Explore Mode");
		}
		GenerateQuestions genQues = new GenerateQuestions(selection, value);
		genQues.execute();

		main = (RelativeLayout)findViewById(R.id.main_layout);
		view = getLayoutInflater().inflate(R.layout.layout_play_game, main,false);
		//        main.addView(view);
		//


	}

	public void startGame(View v)
	{
		Toast.makeText(getApplicationContext(), "Starting game", Toast.LENGTH_SHORT).show();
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
			db.close();
			main.removeAllViews();
			main.addView(view);

		}

		@Override
		protected void onProgressUpdate(final Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			progressBar.setProgress(values[0]);

			runOnUiThread(new Runnable() {
				public void run() {
					loadingText.setText("Loading question " + values[0] + " of 20");
				}
			});
		}

		private GeneratedQuestion makeQuestion(XmlQuestion questionRule, String selection, String value, Database db)
		{
			
			BaseQuestion question = new BaseQuestion(db, questionRule, selection, value);
			try {
				GeneratedQuestion formedQuestion = question.makeQuestion();
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

}
