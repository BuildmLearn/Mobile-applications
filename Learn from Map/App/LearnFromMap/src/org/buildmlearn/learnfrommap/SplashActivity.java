package org.buildmlearn.learnfrommap;

import java.util.ArrayList;
import java.util.Collections;

import org.buildmlearn.learnfrommap.databasehelper.Database;
import org.buildmlearn.learnfrommap.databasehelper.DatabaseHelper;
import org.buildmlearn.learnfrommap.helper.TinyDB;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SplashActivity extends DatabaseHelper {

	private int mData;
	private TinyDB pref;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		loadDatabase();
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(getResources(), R.id.explore_world_map, options);
		pref = new TinyDB(getApplicationContext());
		ArrayList<String> list1 = pref.getList("NAME");
		Log.e("SIZE", list1.size() + "");
		mData = pref.getInt("TUTORIAL");
		Log.e("CHECK", "Data: " + pref.getInt("TUTORIAL"));
		

	}

	@Override
	public void onDatabaseLoad(String msg) {
		super.onDatabaseLoad(msg);
		ProgressBar pb = (ProgressBar)findViewById(R.id.splash_loading);
		pb.setVisibility(View.GONE);
		ConnectivityManager cm =
				(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		boolean isConnected = activeNetwork != null &&
				activeNetwork.isConnectedOrConnecting();
		if (!isConnected)
		{
			TextView tvMsg = (TextView)findViewById(R.id.splash_msg);
			tvMsg.setText("Error: Your device is not connected to internet");
			tvMsg.setVisibility(View.VISIBLE);
			pb.setVisibility(View.GONE);
		}
		else
		{
			new GetCountryList().execute();
		}
	}

	@Override
	public void onDatabaseLoadError(String msg) {
		super.onDatabaseLoadError(msg);
		TextView tvMsg = (TextView)findViewById(R.id.splash_msg);
		ProgressBar pb = (ProgressBar)findViewById(R.id.splash_loading);
		tvMsg.setText(msg);
		tvMsg.setVisibility(View.VISIBLE);
		pb.setVisibility(View.GONE);
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
	
	public class GetCountryList extends AsyncTask<Void, Void, Void>
	{
		ArrayList<String> countryList;
		@Override
		protected Void doInBackground(Void... arg0) {
			Database db =  new Database(getApplicationContext());
			countryList = db.countryList();
			Collections.sort(countryList);
			TinyDB pref = new TinyDB(getApplicationContext());
			ArrayList<String> temp = pref.getList("COUNTRY");
			if(temp.size() == 0)
			{
				Log.e("SHARED PRED", "Init");
				pref.putList("COUNTRY", countryList);
				ArrayList<Integer> tempScore = new ArrayList<Integer>(); 
				for(int i=0; i<countryList.size(); i++)
				{
					tempScore.add(0);
				}
				pref.putListInt("COUNTRY_TOTAL", tempScore, getApplicationContext());
				pref.putListInt("COUNTRY_ANS", tempScore, getApplicationContext());
			}
			
			db.close();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			Intent intent;
			if(mData == 0)
			{
				intent= new Intent(getApplicationContext(), AppTutorial.class);
				pref.putInt("TUTORIAL", 1);
			}
			else
			{
				intent= new Intent(getApplicationContext(), MainActivity.class);
			}
			startActivity(intent);
			finish();

		}
	}


}
