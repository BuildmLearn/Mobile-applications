package com.buildmlearn.labeldiagram;

import com.buildmlearn.labeldiagram.helper.HelperClass;
import com.example.labelthediagram.R;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Settings extends Activity implements OnClickListener {

	private static final String SOUND_SETTING = "sound";
	private static final String NOTIFICATION_SETTING = "notification";
	private CheckBox soundCheckBox;
	private CheckBox notificationCheckBox;
	private SharedPreferences preferences;
	private RelativeLayout updateLayout;
	private RelativeLayout rateLayout;
	private boolean sound_pref;
	private boolean notify_pref;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.settings);
		initPreferences();
		initViews();

	}

	private void initPreferences() {

		preferences = getApplicationContext().getSharedPreferences(
				"com.buildmlearn.labeldiagram.PREFERENCE_FILE_KEY",
				Context.MODE_PRIVATE);
		
		sound_pref = preferences.getBoolean(SOUND_SETTING, false);
		notify_pref = preferences.getBoolean(NOTIFICATION_SETTING, false);

	}

	private void initViews() {
		
		HelperClass.setActionBar("Settings", this);

		soundCheckBox = (CheckBox) findViewById(R.id.soundCheckBox);
		rateLayout = (RelativeLayout) findViewById(R.id.rateLayout);

		soundCheckBox.setOnClickListener(this);
		notificationCheckBox.setOnClickListener(this);
		updateLayout.setOnClickListener(this);
		rateLayout.setOnClickListener(this);
		
		
		
		soundCheckBox.setChecked(sound_pref);
		notificationCheckBox.setChecked(notify_pref);
	}

	@Override
	public void onClick(View view) {

		SharedPreferences.Editor editor = preferences.edit();
		boolean isChecked;
		
		switch (view.getId()) {

		case R.id.soundCheckBox:

			isChecked = soundCheckBox.isChecked();

			if (isChecked) {
				editor.putBoolean(SOUND_SETTING, true);
				editor.commit();
			} else {
				editor.putBoolean(SOUND_SETTING, false);
				editor.commit();
			}

			break;

		case R.id.rateLayout:
			
			Toast.makeText(this, "Comming soon...", Toast.LENGTH_SHORT).show();
			
			break;

		default:
			break;
		}
	}

}
