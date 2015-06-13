package com.buildmlearn.labeldiagram;

import com.example.labelthediagram.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainMenu extends Activity implements OnClickListener {

	TextView diagramView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);

		diagramView = (TextView) findViewById(R.id.title_diagram);
		TextView startDigrm = (TextView) findViewById(R.id.start_diagram);
		TextView scoreboardView = (TextView) findViewById(R.id.title_scoreboard);
		TextView startScore = (TextView) findViewById(R.id.start_screboard);
		TextView lessonView = (TextView) findViewById(R.id.title_lesson);
		TextView startLesson = (TextView) findViewById(R.id.start_lesson);
		TextView badgeView = (TextView) findViewById(R.id.title_badge);
		TextView startbadges = (TextView) findViewById(R.id.start_badge);
		TextView settingsView = (TextView) findViewById(R.id.title_settings);
		TextView startSettings = (TextView) findViewById(R.id.start_settings);
		TextView helpView = (TextView) findViewById(R.id.title_help);
		TextView startHelp = (TextView) findViewById(R.id.start_help);

		Typeface tfThin = Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Thin.ttf");
		Typeface tfLight = Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Light.ttf");

		diagramView.setTypeface(tfThin);
		startDigrm.setTypeface(tfLight);
		scoreboardView.setTypeface(tfThin);
		startScore.setTypeface(tfLight);
		lessonView.setTypeface(tfThin);
		startLesson.setTypeface(tfLight);
		badgeView.setTypeface(tfThin);
		startbadges.setTypeface(tfLight);
		settingsView.setTypeface(tfThin);
		startSettings.setTypeface(tfLight);
		helpView.setTypeface(tfThin);
		startHelp.setTypeface(tfLight);

		startDigrm.setOnClickListener(this);
		startScore.setOnClickListener(this);
		startLesson.setOnClickListener(this);
		startbadges.setOnClickListener(this);
		startSettings.setOnClickListener(this);
		startHelp.setOnClickListener(this);

	}

	@Override
	public void onBackPressed() {

		// Disable back button behavior and exit the app
		new AlertDialog.Builder(this)
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setTitle("Exit")
				.setMessage("Are you sure?")
				.setPositiveButton("yes",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {

								Intent intent = new Intent(Intent.ACTION_MAIN);
								intent.addCategory(Intent.CATEGORY_HOME);
								intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
								startActivity(intent);
								finish();
								System.exit(0);

								// finish(); // finish activity

							}
						}).setNegativeButton("no", null).show();
	}

	@Override
	public void onClick(View v) {

		// Declare action to be fired according to the textView tapped

		switch (v.getId()) {
		case R.id.start_diagram:
			Toast.makeText(getApplication(), "Dispatching to diagrams", 2000)
					.show();
			Intent intent = new Intent(v.getContext(), DiagramCategory.class);
			startActivity(intent);
			
			break;
		case R.id.start_screboard:
			Toast.makeText(getApplication(), "Dispatching to scoreboard", 2000)
					.show();
			break;
		case R.id.start_lesson:
			Toast.makeText(getApplication(), "Dispatching to lessons", 2000)
					.show();
			break;
		case R.id.start_badge:
			Toast.makeText(getApplication(), "Dispatching to badges", 2000)
					.show();
			break;
		case R.id.start_settings:
			Toast.makeText(getApplication(), "Dispatching to settings", 2000)
					.show();
			break;
		case R.id.start_help:
			Toast.makeText(getApplication(), "Dispatching to help", 2000)
					.show();
			break;
		}

	}
}
