package org.buildmlearn.learnfrommap;

import org.buildmlearn.learnfrommap.helper.TinyDB;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

/**
 * This activty shows the settings of the screen
 * 
 * @author Abhishek
 *
 */
public class SettingsActivity extends ActionBarActivity {
	
	private CheckBox notification;
	private CheckBox sound;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		notification = (CheckBox)findViewById(R.id.sett_noti);
		sound= (CheckBox)findViewById(R.id.sett_sound);
		final TinyDB pref = new TinyDB(getApplicationContext());
		boolean sound_pref = pref.getBoolean("SOUND");
		boolean noti_pref = pref.getBoolean("NOTI");
		notification.setChecked(noti_pref);
		sound.setChecked(sound_pref);
		
		notification.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				pref.putBoolean("NOTI", notification.isChecked());
			}
		});
		
		sound.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				pref.putBoolean("SOUND", sound.isChecked());
			}
		});

	}

	public void settingsClick(View v)
	{
		int id = v.getId();
		switch (id) {
		case R.id.sett_tutorial:
			Intent intent = new Intent(getApplicationContext(), AppTutorial.class);
			startActivity(intent);
			finish();
			break;
		case R.id.sett_rate:
			Uri uri = Uri.parse("market://details?id=" + getPackageName());
		    Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
		    try {
		        startActivity(myAppLinkToMarket);
		    } catch (ActivityNotFoundException e) {
		        Toast.makeText(this, " unable to find market app", Toast.LENGTH_LONG).show();
		    }
		    break;

		default:
			break;
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
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
