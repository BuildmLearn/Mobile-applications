package com.buildmlearn.labeldiagram;

import com.buildmlearn.labeldiagram.badges.BadgePopUpWindow;
import com.buildmlearn.labeldiagram.tooltipkit.InfoTooltip;
import com.buildmlearn.labeldiagram.tooltipkit.CustomTooltip.AlignMode;
import com.example.labelthediagram.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DiagramPlayWaterCycle extends DiagramPlayBase {

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		actionBar.setTitle("Water Cycle");
		setDiagramName("WaterCycle");
		setDiagramCategory("Science");

		// Score board textViews
		TextView completeTxt = (TextView) findViewById(R.id.complatedTxt);
		compeleteRatio = (TextView) findViewById(R.id.complete_ratio);
		TextView scoreTxt = (TextView) findViewById(R.id.scoreTxt);
		score = (TextView) findViewById(R.id.score);

		// Setting up font face to Roboto Light/Thin
		completeTxt.setTypeface(tfThin);
		scoreTxt.setTypeface(tfThin);
		compeleteRatio.setTypeface(tfThin);
		score.setTypeface(tfThin);

		// Placeholder imageViews
		ImageView oceanView = (ImageView) findViewById(R.id.oceanBlb);
		ImageView precipitationView = (ImageView) findViewById(R.id.precipitationBlb);
		ImageView infiltraionView = (ImageView) findViewById(R.id.infiltrationBlb);
		ImageView condensationView = (ImageView) findViewById(R.id.condensationBlb);
		ImageView eveporaionView = (ImageView) findViewById(R.id.eveporationBlb);
		ImageView transpirationView = (ImageView) findViewById(R.id.transpirationBlb);
		ImageView undergroundwaterView = (ImageView) findViewById(R.id.underground_waterBlb);
		ImageView surfacerunView = (ImageView) findViewById(R.id.surface_runBlb);
		
		
		// Draggable Tags imageViews
		TextView oceanTag = (TextView) findViewById(R.id.oceanTag);
		TextView preciptationTag = (TextView) findViewById(R.id.preciptationTag);
		TextView infiltratoinTag = (TextView) findViewById(R.id.infiltratoinTag);
		TextView condensationTag = (TextView) findViewById(R.id.condensationTag);
		TextView eveporationTag = (TextView) findViewById(R.id.eveporationTag);
		TextView transpirationTag = (TextView) findViewById(R.id.transpirationTag);
		TextView undergroundWaterTag = (TextView) findViewById(R.id.underground_waterTag);
		TextView surfaceRunTag = (TextView) findViewById(R.id.surface_runTag);
		
		
		// Register draggable views to receive drag events
		oceanView.setOnDragListener(this);
		precipitationView.setOnDragListener(this);
		infiltraionView.setOnDragListener(this);
		condensationView.setOnDragListener(this);
		eveporaionView.setOnDragListener(this);
		transpirationView.setOnDragListener(this);
		undergroundwaterView.setOnDragListener(this);
		surfacerunView.setOnDragListener(this);		

		
		// Register place holders to receive onLongclick events
		oceanTag.setOnLongClickListener(this);
		preciptationTag.setOnLongClickListener(this);
		infiltratoinTag.setOnLongClickListener(this);
		condensationTag.setOnLongClickListener(this);
		eveporationTag.setOnLongClickListener(this);
		transpirationTag.setOnLongClickListener(this);
		undergroundWaterTag.setOnLongClickListener(this);
		surfaceRunTag.setOnLongClickListener(this);
		
		
		// Register place holders to receive onClick events
		oceanTag.setOnClickListener(this);
		preciptationTag.setOnClickListener(this);
		infiltratoinTag.setOnClickListener(this);
		condensationTag.setOnClickListener(this);
		eveporationTag.setOnClickListener(this);
		transpirationTag.setOnClickListener(this);
		undergroundWaterTag.setOnClickListener(this);
		surfaceRunTag.setOnClickListener(this);
		
		placeHolderlist = container.diagramCaller("WaterCycle");
		tagPlaceHolderMap = tagPlaceholdermapper.diagramMapper("WaterCycle");
		incompleteTagList = tagPlaceholdermapper.diagramMapper("WaterCycle");
		tagListSize = tagPlaceHolderMap.size();
		
		openDB();
		
	}
	
	@Override
	public void onClick(View tagView) {
		super.onClick(tagView);
		
		InfoTooltip popup;
		
		switch (tagView.getId()) {
		case R.id.oceanTag:
			popup = new InfoTooltip(getApplicationContext(),
					"The sea, main source for evoporation");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.preciptationTag:
			popup = new InfoTooltip(getApplicationContext(),
					"Rain, snow, sleet, or hail that falls \n"
					+ "to or condenses on the ground");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.infiltratoinTag:
			popup = new InfoTooltip(getApplicationContext(),
					"The process by which water on the \n"
					+ "ground surface enters the soil");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.condensationTag:
			popup = new InfoTooltip(getApplicationContext(),
					"The conversion of a vapour or gas to a liquid");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.eveporationTag:
			popup = new InfoTooltip(getApplicationContext(),
					"The process of a substance in a \n"
					+ "liquid state changing to a gaseous state");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.transpirationTag:
			popup = new InfoTooltip(getApplicationContext(),
					"The process which evaporation \n"
					+ "of water from plant leaves");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.underground_waterTag:
			popup = new InfoTooltip(getApplicationContext(),
					"The water found underground \n"
					+ "in the cracks and spaces in soil, \n"
					+ "sand and rock");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.surface_runTag:
			popup = new InfoTooltip(getApplicationContext(),
					"Surface runoff is water, from rain, \n"
					+ " snowmelt, or other sources, \n"
					+ "that flows over the land surface");
			popup.show(tagView, AlignMode.BOTTOM);
			break;

		default:
			break;
		}
		
	}

	@Override
	protected int getResourcesId() {
		// TODO Auto-generated method stub
		return R.layout.diagram_play_water_cycle;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
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

		super.dialogBuilder(DiagramPlayWaterCycle.this);

	}

	@Override
	protected void dispatch(float totalScore, int gameScore) {
		Intent intent = new Intent(getBaseContext(), DiagramResult.class);
		intent.putExtra("SCORE", totalScore);
		intent.putExtra("GAME_SCORE", gameScore);
		intent.putExtra("SOURCE", "WaterCycle");
		intent.putExtra("BEST_SCORE", achievedBestScore);
		intent.putExtra("TRY_CYCLE", tryCycle);
		startActivity(intent);
	}
	
	@Override
	protected void intentBuilder(String badgeTitle, int badgeId, float totalScore, int gameScore, boolean completed, int tryCycle) {
		Intent intent;
		intent = new Intent(getBaseContext(), BadgePopUpWindow.class);
		intent.putExtra("BADGE_TITLE", badgeTitle);
		intent.putExtra("BADGE_ID", badgeId);
		intent.putExtra("SCORE", totalScore);
		intent.putExtra("GAME_SCORE", gameScore);
		intent.putExtra("SOURCE", "WaterCycle");
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
