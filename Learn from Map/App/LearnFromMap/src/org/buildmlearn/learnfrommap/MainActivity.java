package org.buildmlearn.learnfrommap;

import java.util.ArrayList;

import org.buildmlearn.learnfrommap.questionmodule.McqQuestion;
import org.buildmlearn.learnfrommap.questionmodule.Question;
import org.buildmlearn.learnfrommap.questionmodule.QuestionModuleException;
import org.buildmlearn.learnfrommap.questionmodule.XmlParser;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends ActionBarActivity {

	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        XmlParser parser = new XmlParser(this);
        ArrayList<Question> list = parser.fetchQuestions();
        McqQuestion question = new McqQuestion(this, list.get(0),"country_code", "IN");
        try {
			question.makeQuestion();
		} catch (QuestionModuleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
        

//        DatabaseHelper db = new DatabaseHelper(this, DB_NAME);
//        Cursor cursor = db.select(null, null, null, null);
//        if(cursor.moveToFirst())
//        {	
//        	do
//        	{
//        		String name = cursor.getString(1);
//        		Log.d("NAME", name);
//        		
//        	}
//        	while(cursor.moveToNext());
//        }
//        db.close();
        
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
