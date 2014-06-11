package org.buildmlearn.learnfrommap;

import java.util.ArrayList;

import org.buildmlearn.learnfrommap.parser.XmlParser;
import org.buildmlearn.learnfrommap.questionmodule.McqQuestion;
import org.buildmlearn.learnfrommap.questionmodule.Question;
import org.buildmlearn.learnfrommap.questionmodule.QuestionModuleException;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {

	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);	
        setContentView(R.layout.activity_main);
        XmlParser parser = new XmlParser(this);
        ArrayList<Question> list = parser.fetchQuestions();
        McqQuestion question = new McqQuestion(this, list.get(0));
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
    }
    
    		


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
