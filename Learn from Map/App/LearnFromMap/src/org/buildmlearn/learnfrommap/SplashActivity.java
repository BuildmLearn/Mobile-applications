package org.buildmlearn.learnfrommap;

import java.util.ArrayList;

import org.buildmlearn.learnfrommap.databasehelper.DatabaseHelper;
import org.buildmlearn.learnfrommap.helper.TinyDB;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SplashActivity extends DatabaseHelper {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		loadDatabase();
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(getResources(), R.id.explore_world_map, options);
		TinyDB pref = new TinyDB(getApplicationContext());
		ArrayList<String> list1 = pref.getList("NAME");
		Log.e("SIZE", list1.size() + "");
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
		if (isConnected)
		{
			Intent intent= new Intent(getApplicationContext(), MainActivity.class);
			startActivity(intent);
			finish();
		}
		else 
		{
			TextView tvMsg = (TextView)findViewById(R.id.splash_msg);
			tvMsg.setText("Error: Your device is not connected to internet");
			tvMsg.setVisibility(View.VISIBLE);
			pb.setVisibility(View.GONE);
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

}
