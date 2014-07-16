package org.buildmlearn.learnfrommap;

import java.util.ArrayList;

import org.buildmlearn.learnfrommap.adapter.ScoreAdapter;
import org.buildmlearn.learnfrommap.questionmodule.UserAnsweredData;

import android.support.v7.app.ActionBarActivity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ListView;

public class ScoreActivity extends ActionBarActivity {
	
	private ScoreAdapter mAdapter;
	private ListView mQuestionList;
	private ArrayList<UserAnsweredData> mAnsweredList;
	private TextViewPlus mPoints;
	private TextViewPlus mCount;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score);

		mQuestionList  = (ListView)findViewById(R.id.score_question_list);
		mAnsweredList = (ArrayList<UserAnsweredData>) getIntent().getSerializableExtra("SCORE_DATA");
		mPoints = (TextViewPlus)findViewById(R.id.score_points);
		mCount = (TextViewPlus)findViewById(R.id.score_count);
		int count = 0;
		int points = 0;
		for (UserAnsweredData userAnswer : mAnsweredList) {
			if(userAnswer.isAnswerCorrect())
			{
				count++;
				points += userAnswer.getmPoints();
			}
		}
		
		mPoints.setText(String.valueOf(points));
		mCount.setText(count + "/" + GameActivity.QUESTION_COUNT);
		mAdapter = new ScoreAdapter(this, R.layout.listview_row_mcq, mAnsweredList);
		mQuestionList.setAdapter(mAdapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.score, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			showCustomDialog();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

    protected void showCustomDialog() {
        // TODO Auto-generated method stub
        final Dialog dialog = new Dialog(ScoreActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.about_dialog);   
        dialog.show();
    }

}
