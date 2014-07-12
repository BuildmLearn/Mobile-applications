package org.buildmlearn.learnfrommap;

import java.util.ArrayList;
import org.buildmlearn.learnfrommap.databasehelper.Database;
import org.buildmlearn.learnfrommap.parser.XmlParser;
import org.buildmlearn.learnfrommap.questionmodule.QuestionModuleException;
import org.buildmlearn.learnfrommap.questionmodule.XmlQuestion;

import android.support.v7.app.ActionBarActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class TestDb extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_db);
		new GenerateQuestions("Continent", "1").execute();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test_db, menu);
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
		private long total;

		@Override
		protected String doInBackground(Void... params) {

			db = new Database(getApplicationContext(), 1);
			XmlParser xmlParser = new XmlParser(getApplicationContext());
			ArrayList<XmlQuestion> questionRules = xmlParser.fetchQuestions();			

			int count = 0;
			total = 0;	
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

			long start = System.currentTimeMillis();
			for(int i = 1; i< 3; i++)
			{

				for(XmlQuestion questionRule : questionRules)
				{					
					try
					{
						String tableName = questionRule.getCode();
						String query = "SELECT * FROM " + tableName + " "+ where +" LIMIT ";
						String countQuery = "SELECT COUNT(*) FROM " + tableName + " WHERE " + where;

						Log.e("DATA", db.rawSelect(query, countQuery).toString());


						count++;
					}
					catch (QuestionModuleException e) {
						e.printStackTrace();
					}

				}
				long end =  System.currentTimeMillis();
				long diff = end - start;
				total += diff;
				Log.e("Duration", "Duration: " + diff);

				total /= 200.0;
				Log.e("Avg", "Duration: " + total);

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
			runOnUiThread(new Runnable() {
				public void run() {
					Toast.makeText(getApplicationContext(), total + "", Toast.LENGTH_LONG).show();
				}
			});
			db.close(); 


		}

		@Override
		protected void onProgressUpdate(final Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}

	}




}
