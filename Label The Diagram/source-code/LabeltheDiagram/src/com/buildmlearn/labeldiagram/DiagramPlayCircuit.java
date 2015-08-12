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

public class DiagramPlayCircuit extends DiagramPlayBase {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(getResourcesId());

		actionBar.setTitle("Electric Circuit");
		setDiagramName("Circuit");
		setDiagramCategory("Physics");

		// Score board textViews
		compeleteRatio = (TextView) findViewById(R.id.complete_ratio);
		score = (TextView) findViewById(R.id.score);

		// Placeholder imageViews
		ImageView capacitorView = (ImageView) findViewById(R.id.capacitorBlb);
		ImageView batteryView = (ImageView) findViewById(R.id.batteryBlb);
		ImageView currentView = (ImageView) findViewById(R.id.currentBlb);
		ImageView resisterView = (ImageView) findViewById(R.id.resistorBlb);
		ImageView switchView = (ImageView) findViewById(R.id.switchBlb);
		ImageView ameterView = (ImageView) findViewById(R.id.ameterBlb);
		ImageView loadView = (ImageView) findViewById(R.id.loadBlb);
		ImageView voltmeterView = (ImageView) findViewById(R.id.voltmeterBlb);
		ImageView bulbView = (ImageView) findViewById(R.id.bulbBlb);

		// Draggable Tags TextViews
		TextView capacitorTag = (TextView) findViewById(R.id.capacitorTag);
		TextView batteryTag = (TextView) findViewById(R.id.batteryTag);
		TextView currentTag = (TextView) findViewById(R.id.currentTag);
		TextView resistorTag = (TextView) findViewById(R.id.resistorTag);
		TextView switchTag = (TextView) findViewById(R.id.switchTag);
		TextView ameterTag = (TextView) findViewById(R.id.ameterTag);
		TextView loadTag = (TextView) findViewById(R.id.loadTag);
		TextView voltmeterTag = (TextView) findViewById(R.id.voltmeterTag);
		TextView bulbTag = (TextView) findViewById(R.id.bulbTag);

		// Register draggable views to receive drag events
		capacitorView.setOnDragListener(this);
		batteryView.setOnDragListener(this);
		currentView.setOnDragListener(this);
		resisterView.setOnDragListener(this);
		switchView.setOnDragListener(this);
		ameterView.setOnDragListener(this);
		loadView.setOnDragListener(this);
		voltmeterView.setOnDragListener(this);
		bulbView.setOnDragListener(this);

		// Register place holders to receive onLongclick events
		capacitorTag.setOnLongClickListener(this);
		batteryTag.setOnLongClickListener(this);
		currentTag.setOnLongClickListener(this);
		resistorTag.setOnLongClickListener(this);
		switchTag.setOnLongClickListener(this);
		ameterTag.setOnLongClickListener(this);
		loadTag.setOnLongClickListener(this);
		voltmeterTag.setOnLongClickListener(this);
		bulbTag.setOnLongClickListener(this);

		// Register place holders to receive onClick events
		capacitorTag.setOnClickListener(this);
		batteryTag.setOnClickListener(this);
		currentTag.setOnClickListener(this);
		resistorTag.setOnClickListener(this);
		switchTag.setOnClickListener(this);
		ameterTag.setOnClickListener(this);
		loadTag.setOnClickListener(this);
		voltmeterTag.setOnClickListener(this);
		bulbTag.setOnClickListener(this);
		
		placeHolderlist = container.diagramCaller("Circuit");
		tagPlaceHolderMap = tagPlaceholdermapper.diagramMapper("Circuit");
		incompleteTagList = tagPlaceholdermapper.diagramMapper("Circuit");
		tagListSize = tagPlaceHolderMap.size();
		
		openDB();
	
	}

	@Override
	public void onClick(View tagView) {
		// TODO Auto-generated method stub
		super.onClick(tagView);
	}

	@Override
	protected int getResourcesId() {
		// TODO Auto-generated method stub
		return R.layout.diagram_play_circuit;
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
		intent.putExtra("SOURCE", "Circuit");
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
		intent.putExtra("SOURCE", "Circuit");
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
