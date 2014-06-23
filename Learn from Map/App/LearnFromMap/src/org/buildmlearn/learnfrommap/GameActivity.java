package org.buildmlearn.learnfrommap;

import java.util.ArrayList;

import org.buildmlearn.learnfrommap.parser.XmlParser;
import org.buildmlearn.learnfrommap.questionmodule.BaseQuestion;
import org.buildmlearn.learnfrommap.questionmodule.GeneratedQuestion;
import org.buildmlearn.learnfrommap.questionmodule.QuestionModuleException;
import org.buildmlearn.learnfrommap.questionmodule.XmlQuestion;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class GameActivity extends Helper {

	private String mode;
	private String selection;
	private String value;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		Intent intent = getIntent();
		mode = intent.getStringExtra("MODE");
		selection = intent.getStringExtra("SELECTION");
		value = intent.getStringExtra("VALUE");
		
		if(mode.equals("EXPLORE_MODE"))
		{
			exploreMode(selection, value);
		}
		
	}

	private void exploreMode(String selection, String value) {
		
		XmlParser xmlParser = new XmlParser(getApplicationContext());
		ArrayList<XmlQuestion> questionRules = xmlParser.fetchQuestions();
		BaseQuestion question = new BaseQuestion(getApplicationContext(), questionRules.get(0), selection, value);
		try {
			GeneratedQuestion formedQuestion = question.makeQuestion();
			Toast.makeText(getApplicationContext(), formedQuestion.getQuestion(), Toast.LENGTH_SHORT).show();
		} catch (QuestionModuleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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


}
