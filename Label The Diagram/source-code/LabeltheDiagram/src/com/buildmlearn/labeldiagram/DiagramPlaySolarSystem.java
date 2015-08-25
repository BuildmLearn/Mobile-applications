package com.buildmlearn.labeldiagram;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.buildmlearn.labeldiagram.badges.BadgePopUpWindow;
import com.buildmlearn.labeldiagram.tooltipkit.InfoTooltip;
import com.buildmlearn.labeldiagram.tooltipkit.CustomTooltip.AlignMode;
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

		openDB();

	}

	@Override
	public void onClick(View tagView) {
		super.onClick(tagView);

		InfoTooltip popup;
		
		switch (tagView.getId()) {
		case R.id.sunTag:
			popup = new InfoTooltip(getApplicationContext(),
					"The star at the center of the Solar System \n"
					+ "and the most important source of energy \n"
					+ "for life on");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.mercuryTag:
			popup = new InfoTooltip(getApplicationContext(),
					"Fastest planet and has the greatest \n"
					+ "temperature variation of the planets \n"
					+ "in the Solar System");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.venusTag:
			popup = new InfoTooltip(getApplicationContext(),
					"After the Moon, this is the brightest \n"
					+ "natural object in the night sky");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.earthTag:
			popup = new InfoTooltip(getApplicationContext(),
					"The only astronomical object \n"
					+ "known to accommodate life");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.marsTag:
			popup = new InfoTooltip(getApplicationContext(),
					"Second smallest planet in solar system");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.jupiterTag:
			popup = new InfoTooltip(getApplicationContext(),
					"The largest planet in the Solar System \n"
					+ "with a mass one-thousandth that of the Sun");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.saturnTag:
			popup = new InfoTooltip(getApplicationContext(),
					"The second largest in the Solar System \n"
					+ "and has a prominent ring system");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.uranusTag:
			popup = new InfoTooltip(getApplicationContext(),
					"Has the third-largest planetary radius \n"
					+ "and fourth-largest planetary mass");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.neptuneTag:
			popup = new InfoTooltip(getApplicationContext(),
					"The most far away planet from the sun \n"
					+ "in the Solar System");
			popup.show(tagView, AlignMode.BOTTOM);
			break;

		default:
			break;
		}

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

		super.dialogBuilder(DiagramPlaySolarSystem.this);

	}

	@Override
	protected void dispatch(float totalScore, int gameScore) {
		Intent intent = new Intent(getBaseContext(), DiagramResult.class);
		intent.putExtra("SCORE", totalScore);
		intent.putExtra("GAME_SCORE", gameScore);
		intent.putExtra("SOURCE", "SolarSystem");
		intent.putExtra("BEST_SCORE", achievedBestScore);
		intent.putExtra("TRY_CYCLE", tryCycle);
		startActivity(intent);
	}

	@Override
	protected void intentBuilder(String badgeTitle, int badgeId,
			float totalScore, int gameScore, boolean completed, int tryCycle) {
		Intent intent;
		intent = new Intent(getApplicationContext(), BadgePopUpWindow.class);
		intent.putExtra("BADGE_TITLE", badgeTitle);
		intent.putExtra("BADGE_ID", badgeId);
		intent.putExtra("SCORE", totalScore);
		intent.putExtra("GAME_SCORE", gameScore);
		intent.putExtra("SOURCE", "SolarSystem");
		intent.putExtra("BEST_SCORE", achievedBestScore);
		intent.putExtra("COMPLETED", completed);
		intent.putExtra("TRY_CYCLE", tryCycle);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		finish();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		closeDB();
	}

}
