package com.buildmlearn.labeldiagram;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.buildmlearn.labeldiagram.badges.BadgePopUpWindow;
import com.example.labelthediagram.R;

public class DiagramPlaySolarSystem extends DiagramPlayBase {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(getResourcesId());

		actionBar.setTitle("Solar System");
		setDiagramName("SolarSystem");
		setDiagramCategory("Science");

		// Score board textViews
		compeleteRatio = (TextView) findViewById(R.id.complete_ratio);
		score = (TextView) findViewById(R.id.score);

		// Placeholder imageViews
		ImageView sunView = (ImageView) findViewById(R.id.sunBlb);
		ImageView mercuryView = (ImageView) findViewById(R.id.mercuryBlb);
		ImageView venusView = (ImageView) findViewById(R.id.venusBlb);
		ImageView earthView = (ImageView) findViewById(R.id.earthBlb);
		ImageView marsView = (ImageView) findViewById(R.id.marsBlb);
		ImageView jupiterView = (ImageView) findViewById(R.id.jupiterBlb);
		ImageView saturnView = (ImageView) findViewById(R.id.saturnBlb);
		ImageView uranusView = (ImageView) findViewById(R.id.uranusBlb);
		ImageView neptuneView = (ImageView) findViewById(R.id.neptuneBlb);

		// Draggable Tags TextViews
		TextView sunTag = (TextView) findViewById(R.id.sunTag);
		TextView mercuryTag = (TextView) findViewById(R.id.mercuryTag);
		TextView venusTag = (TextView) findViewById(R.id.venusTag);
		TextView earthTag = (TextView) findViewById(R.id.earthTag);
		TextView marsTag = (TextView) findViewById(R.id.marsTag);
		TextView jupiterTag = (TextView) findViewById(R.id.jupiterTag);
		TextView saturnTag = (TextView) findViewById(R.id.saturnTag);
		TextView uranusTag = (TextView) findViewById(R.id.uranusTag);
		TextView neptuneTag = (TextView) findViewById(R.id.neptuneTag);

		// Register draggable views to receive drag events
		sunView.setOnDragListener(this);
		mercuryView.setOnDragListener(this);
		venusView.setOnDragListener(this);
		earthView.setOnDragListener(this);
		marsView.setOnDragListener(this);
		jupiterView.setOnDragListener(this);
		saturnView.setOnDragListener(this);
		uranusView.setOnDragListener(this);
		neptuneView.setOnDragListener(this);

		// Register place holders to receive onLongclick events
		sunTag.setOnLongClickListener(this);
		mercuryTag.setOnLongClickListener(this);
		venusTag.setOnLongClickListener(this);
		earthTag.setOnLongClickListener(this);
		marsTag.setOnLongClickListener(this);
		jupiterTag.setOnLongClickListener(this);
		saturnTag.setOnLongClickListener(this);
		uranusTag.setOnLongClickListener(this);
		neptuneTag.setOnLongClickListener(this);
		
		// Register place holders to receive onClick events
		sunTag.setOnClickListener(this);
		mercuryTag.setOnClickListener(this);
		venusTag.setOnClickListener(this);
		earthTag.setOnClickListener(this);
		marsTag.setOnClickListener(this);
		jupiterTag.setOnClickListener(this);
		saturnTag.setOnClickListener(this);
		uranusTag.setOnClickListener(this);
		neptuneTag.setOnClickListener(this);
		
		placeHolderlist = container.diagramCaller("SolarSystem");
		tagPlaceHolderMap = tagPlaceholdermapper.diagramMapper("SolarSystem");
		incompleteTagList = tagPlaceholdermapper.diagramMapper("SolarSystem");
		tagListSize = tagPlaceHolderMap.size();
		
	}

	@Override
	public void onClick(View tagView) {
		// TODO Auto-generated method stub
		super.onClick(tagView);
	}

	@Override
	protected int getResourcesId() {
		return R.layout.diagram_play_solar_system;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.action_done:
			quitPlay();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void quitPlay() {

		new AlertDialog.Builder(this)
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setTitle("Quit Playing")
				.setMessage("Are you sure?")
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {

								quitPlayUpdataProgress();

							}
						}).setNegativeButton("No", null).show();

	}

	@Override
	protected void dispatch(float totalScore, int gameScore) {
		Intent intent = new Intent(getBaseContext(), DiagramResult.class);
		intent.putExtra("SCORE", totalScore);
		intent.putExtra("GAME_SCORE", gameScore);
		intent.putExtra("SOURCE", "SolarSystem");
		intent.putExtra("BEST_SCORE", achievedBestScore);
		startActivity(intent);
	}
	
	@Override
	protected void intentBuilder(String badgeTitle, int badgeId, float totalScore, int gameScore) {
		Intent intent;
		intent = new Intent(getApplicationContext(), BadgePopUpWindow.class);
		intent.putExtra("BADGE_TITLE", badgeTitle);
		intent.putExtra("BADGE_ID", badgeId);
		intent.putExtra("SCORE", totalScore);
		intent.putExtra("GAME_SCORE", gameScore);
		intent.putExtra("SOURCE", "SolarSystem");
		intent.putExtra("BEST_SCORE", achievedBestScore);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		finish();
	}

}
