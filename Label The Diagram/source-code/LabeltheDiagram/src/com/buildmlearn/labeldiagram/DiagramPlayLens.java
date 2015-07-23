package com.buildmlearn.labeldiagram;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.labelthediagram.R;

public class DiagramPlayLens extends DiagramPlayBase {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(getResourcesId());

		actionBar.setTitle("Lens");
		setDiagramName("Lens");

		// Score board textViews
		compeleteRatio = (TextView) findViewById(R.id.complete_ratio);
		score = (TextView) findViewById(R.id.score);

		// Placeholder imageViews
		ImageView principalRayView = (ImageView) findViewById(R.id.principalRayBlb);
		ImageView centralRayView = (ImageView) findViewById(R.id.centralRayBlb);
		ImageView focalRayView = (ImageView) findViewById(R.id.focalRayBlb);
		ImageView focusView = (ImageView) findViewById(R.id.focusBlb);
		ImageView objectView = (ImageView) findViewById(R.id.objectBlb);
		ImageView imageView = (ImageView) findViewById(R.id.imageBlb);
		ImageView lensView = (ImageView) findViewById(R.id.lensBlb);
		ImageView lensAxisView = (ImageView) findViewById(R.id.lensAxisBlb);

		// Draggable Tags imageViews
		TextView principalRayTag = (TextView) findViewById(R.id.principalRayTag);
		TextView centralRayTag = (TextView) findViewById(R.id.centralRayTag);
		TextView focalRayTag = (TextView) findViewById(R.id.focalRayTag);
		TextView focusTag = (TextView) findViewById(R.id.focusTag);
		TextView objectTag = (TextView) findViewById(R.id.objectTag);
		TextView imageTag = (TextView) findViewById(R.id.imageTag);
		TextView lensTag = (TextView) findViewById(R.id.lensTag);
		TextView lensAxisTag = (TextView) findViewById(R.id.lensAxisTag);

		// Register draggable views to receive drag events
		principalRayView.setOnDragListener(this);
		centralRayView.setOnDragListener(this);
		focalRayView.setOnDragListener(this);
		focusView.setOnDragListener(this);
		objectView.setOnDragListener(this);
		imageView.setOnDragListener(this);
		lensView.setOnDragListener(this);
		lensAxisView.setOnDragListener(this);

		// Register place holders to receive onLongclick events
		principalRayTag.setOnLongClickListener(this);
		centralRayTag.setOnLongClickListener(this);
		focalRayTag.setOnLongClickListener(this);
		focusTag.setOnLongClickListener(this);
		objectTag.setOnLongClickListener(this);
		imageTag.setOnLongClickListener(this);
		lensTag.setOnLongClickListener(this);
		lensAxisTag.setOnLongClickListener(this);

		// Register place holders to receive onClick events
		principalRayTag.setOnClickListener(this);
		centralRayTag.setOnClickListener(this);
		focalRayTag.setOnClickListener(this);
		focusTag.setOnClickListener(this);
		objectTag.setOnClickListener(this);
		imageTag.setOnClickListener(this);
		lensTag.setOnClickListener(this);
		lensAxisTag.setOnClickListener(this);
		
		placeHolderlist = container.diagramCaller("Lens");
		tagPlaceHolderMap = tagPlaceholdermapper.diagramMapper("Lens");
		incompleteTagList = tagPlaceholdermapper.diagramMapper("Lens");
		tagListSize = tagPlaceHolderMap.size();

	}

	@Override
	protected int getResourcesId() {
		// TODO Auto-generated method stub
		return R.layout.diagram_play_lens;
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
		intent.putExtra("SOURCE", "Lens");
		intent.putExtra("BEST_SCORE", achievedBestScore);
		startActivity(intent);
	}


}
