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
import com.buildmlearn.labeldiagram.tooltipkit.InfoTooltip;
import com.buildmlearn.labeldiagram.tooltipkit.CustomTooltip.AlignMode;
import com.example.labelthediagram.R;

public class DiagramPlayPlantCell extends DiagramPlayBase{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(getResourcesId());

		actionBar.setTitle("Plant Cell");
		setDiagramName("PlantCell");
		setDiagramCategory("Biology");

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
		ImageView ribosomeView = (ImageView) findViewById(R.id.ribosomesBlb);
		ImageView smootherView = (ImageView) findViewById(R.id.smootherBlb);
		ImageView rougherView = (ImageView) findViewById(R.id.rougherBlb);
		ImageView nucleusView = (ImageView) findViewById(R.id.nucleusBlb);
		ImageView vacuoleView = (ImageView) findViewById(R.id.vacuoleBlb);
		ImageView cellwallView = (ImageView) findViewById(R.id.cellwallBlb);
		ImageView cellMembranwView = (ImageView) findViewById(R.id.cell_membraneBlb);
		ImageView mitichondriunView = (ImageView) findViewById(R.id.mitochondriumBlb);
		ImageView choloroplatView = (ImageView) findViewById(R.id.chloroplastBlb);
		ImageView golgiView = (ImageView) findViewById(R.id.golgi_complexBlb);
		
		// Draggable Tags TextViews
		TextView ribosomeTag = (TextView) findViewById(R.id.ribosomeTag);
		TextView smootherTag = (TextView) findViewById(R.id.smootherTag);
		TextView rougherTag = (TextView) findViewById(R.id.rougherTag);
		TextView nucleusTag = (TextView) findViewById(R.id.nucleusTag);
		TextView vacuoleTag = (TextView) findViewById(R.id.vacuoleTag);
		TextView cellwallTag = (TextView) findViewById(R.id.cellwallTag);
		TextView cellMembraneTag = (TextView) findViewById(R.id.cell_membraneTag);
		TextView mitochondiunTag = (TextView) findViewById(R.id.mitochondiumTag);
		TextView chloroplastTag = (TextView) findViewById(R.id.chloroplastTag);
		TextView golgiTag = (TextView) findViewById(R.id.golgi_complexTag);
		
		// Register draggable views to receive drag events
		ribosomeView.setOnDragListener(this);
		smootherView.setOnDragListener(this);
		rougherView.setOnDragListener(this);
		nucleusView.setOnDragListener(this);
		vacuoleView.setOnDragListener(this);
		cellwallView.setOnDragListener(this);
		cellMembranwView.setOnDragListener(this);
		mitichondriunView.setOnDragListener(this);
		choloroplatView.setOnDragListener(this);
		golgiView.setOnDragListener(this);
		
		
		// Register place holders to receive onLongclick events
		ribosomeTag.setOnLongClickListener(this);
		smootherTag.setOnLongClickListener(this);
		rougherTag.setOnLongClickListener(this);
		nucleusTag.setOnLongClickListener(this);
		vacuoleTag.setOnLongClickListener(this);
		cellwallTag.setOnLongClickListener(this);
		cellMembraneTag.setOnLongClickListener(this);
		mitochondiunTag.setOnLongClickListener(this);
		chloroplastTag.setOnLongClickListener(this);
		golgiTag.setOnLongClickListener(this);
		
		// Register place holders to receive onClick events
		ribosomeTag.setOnClickListener(this);
		smootherTag.setOnClickListener(this);
		rougherTag.setOnClickListener(this);
		nucleusTag.setOnClickListener(this);
		vacuoleTag.setOnClickListener(this);
		cellwallTag.setOnClickListener(this);
		cellMembraneTag.setOnClickListener(this);
		mitochondiunTag.setOnClickListener(this);
		chloroplastTag.setOnClickListener(this);
		golgiTag.setOnClickListener(this);
		
		placeHolderlist = container.diagramCaller("PlantCell");
		tagPlaceHolderMap = tagPlaceholdermapper.diagramMapper("PlantCell");
		incompleteTagList = tagPlaceholdermapper.diagramMapper("PlantCell");
		tagListSize = tagPlaceHolderMap.size();
		
		openDB();
		
	}

	
	@Override
	public void onClick(View tagView) {
		// TODO Extract messages to separate container
		switch (tagView.getId()) {
		case R.id.ribosomeTag:
			InfoTooltip popup = new InfoTooltip(getApplicationContext(),
					" Structures that assemble proteins. ");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.smootherTag:
			InfoTooltip popup1 = new InfoTooltip(getApplicationContext(),
					"Carries substances, like proteins,\n"
					+ " to various parts of the cell");
			popup1.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.rougherTag:
			InfoTooltip popup2 = new InfoTooltip(
					getApplicationContext(),
					"Carries substances. Attached with ribosones");
			popup2.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.nucleusTag:
			InfoTooltip popup3 = new InfoTooltip(getApplicationContext(),
					"Control center of the cell.\n Makes Ribosomes");
			popup3.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.vacuoleTag:
			InfoTooltip popup4 = new InfoTooltip(getApplicationContext(),
					"Stores water, food, waste and more \n"
					+ "for a plant cell");
			popup4.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.cellwallTag:
			InfoTooltip popup5 = new InfoTooltip(
					getApplicationContext(),
					" Helps protect and support the cell.\n"
					+ " Gives a plant cell a shape");
			popup5.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.cell_membraneTag:
			InfoTooltip popup6 = new InfoTooltip(getApplicationContext(),
					"Controls what goes in and out of the cell");
			popup6.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.mitochondiumTag:
			InfoTooltip popup7 = new InfoTooltip(getApplicationContext(),
					"Organelle that produce \n"
					+ "most of the cells energy");
			popup7.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.chloroplastTag:
			InfoTooltip popup8 = new InfoTooltip(getApplicationContext(),
					"Captures energy from sunlight.\n"
					+ " Uses energy to produce cell food");
			popup8.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.golgi_complexTag:
			InfoTooltip popup9 = new InfoTooltip(getApplicationContext(),
					"The unit where proteins are \nsorted and packed");
			popup9.show(tagView, AlignMode.BOTTOM);
			break;
		default:
			break;
		}
	}
	

	@Override
	protected int getResourcesId() {
		// TODO Auto-generated method stub
		return R.layout.diagram_play_plantcell;
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
		intent.putExtra("SOURCE", "PlantCell");
		intent.putExtra("BEST_SCORE", achievedBestScore);
		startActivity(intent);
	}

	@Override
	protected void intentBuilder(String badgeTitle, int badgeId, float totalScore, int gameScore, boolean completed) {
		Intent intent;
		intent = new Intent(getBaseContext(), BadgePopUpWindow.class);
		intent.putExtra("BADGE_TITLE", badgeTitle);
		intent.putExtra("BADGE_ID", badgeId);
		intent.putExtra("SCORE", totalScore);
		intent.putExtra("GAME_SCORE", gameScore);
		intent.putExtra("SOURCE", "PlantCell");
		intent.putExtra("BEST_SCORE", achievedBestScore);
		intent.putExtra("COMPLETED", completed);
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
