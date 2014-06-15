package org.buildmlearn.learnfrommap;

import java.util.ArrayList;

import org.buildmlearn.learnfrommap.databasehelper.DatabaseHelper;
import org.buildmlearn.learnfrommap.parser.XmlParser;
import org.buildmlearn.learnfrommap.questionmodule.BaseQuestion;
import org.buildmlearn.learnfrommap.questionmodule.XmlQuestion;
import org.buildmlearn.learnfrommap.questionmodule.QuestionModuleException;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class SplashActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		DatabaseProcess dbProcess = new DatabaseProcess();
		dbProcess.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
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

	
//	@SuppressLint("NewApi")
//	@Override
//	protected void onResume()
//	{
//	    super.onResume();
//
//	    if (Build.VERSION.SDK_INT < 16)
//	    {
//	        // Hide the status bar
//	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//	        // Hide the action bar
//	        getSupportActionBar().hide();
//	    }
//	    else
//	    {
//	        // Hide the status bar
//	        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
//	        // Hide the action bar
//	        getActionBar().hide();
//	    }
//}
	
	public class DatabaseProcess extends AsyncTask<Void, Void, Void>
	{

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
	        Intent intent= new Intent(getApplicationContext(), MainActivity.class);
	        startActivity(intent);
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			DatabaseHelper db = new DatabaseHelper(getApplicationContext());
	        db.close();
	        XmlParser parser = new XmlParser(getApplicationContext());
	        ArrayList<XmlQuestion> list = parser.fetchQuestions();	
	        BaseQuestion question = new BaseQuestion(getApplicationContext(), list.get(0));
	        try {
				question.makeQuestion();
				question.makeQuestion();
				question.makeQuestion();
				question.makeQuestion();
				question.makeQuestion();
				question.makeQuestion();
				question.makeQuestion();
				question.makeQuestion();
			} catch (QuestionModuleException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
			return null;
		}
		
	}

}
