package com.buildmlearn.labeldiagram;

import com.example.labelthediagram.R;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class DiagramPlayVirus extends DiagramPlayBase {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);

		actionBar.setTitle("Virus");
		setDiagramName("Virus");

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
		ImageView headView = (ImageView) findViewById(R.id.headBlb);
		ImageView collarView = (ImageView) findViewById(R.id.collarBlb);
		ImageView neckView = (ImageView) findViewById(R.id.neckBlb);
		ImageView sheathView = (ImageView) findViewById(R.id.sheathBlb);
		ImageView tailpinsView = (ImageView) findViewById(R.id.tailpinsBlb);
		ImageView tailfibreView = (ImageView) findViewById(R.id.tailfibreBlb);
		ImageView danView = (ImageView) findViewById(R.id.dnaBlb);
		ImageView baseplateView = (ImageView) findViewById(R.id.baseplateBlb);

		// Draggable Tags imageViews
		TextView headTag = (TextView) findViewById(R.id.headTag);
		TextView collarTag = (TextView) findViewById(R.id.collarTag);
		TextView neckTag = (TextView) findViewById(R.id.neckTag);
		TextView sheathTag = (TextView) findViewById(R.id.sheathTag);
		TextView tailfibreTag = (TextView) findViewById(R.id.tailfibreTag);
		TextView tailpinsTag = (TextView) findViewById(R.id.tailpinsTag);
		TextView dnaTag = (TextView) findViewById(R.id.dnaTag);
		TextView baseplateTag = (TextView) findViewById(R.id.baseplateTag);
		
		// Register draggable views to receive drag events
		headView.setOnDragListener(this);
		collarView.setOnDragListener(this);
		neckView.setOnDragListener(this);
		sheathView.setOnDragListener(this);
		tailpinsView.setOnDragListener(this);
		tailfibreView.setOnDragListener(this);
		danView.setOnDragListener(this);
		baseplateView.setOnDragListener(this);
		
		// Register place holders to receive onLongclick events
		headTag.setOnLongClickListener(this);
		collarTag.setOnLongClickListener(this);
		neckTag.setOnLongClickListener(this);
		sheathTag.setOnLongClickListener(this);
		tailfibreTag.setOnLongClickListener(this);
		tailpinsTag.setOnLongClickListener(this);
		dnaTag.setOnLongClickListener(this);
		baseplateTag.setOnLongClickListener(this);
		
		
		// Register place holders to receive onClick events
		headTag.setOnClickListener(this);
		collarTag.setOnClickListener(this);
		neckTag.setOnClickListener(this);
		sheathTag.setOnClickListener(this);
		tailfibreTag.setOnClickListener(this);
		tailpinsTag.setOnClickListener(this);
		dnaTag.setOnClickListener(this);
		baseplateTag.setOnClickListener(this);
		
		placeHolderlist = container.diagramCaller("Virus");
		tagPlaceHolderMap = tagPlaceholdermapper.diagramMapper("Virus");
		incompleteTagList = tagPlaceholdermapper.diagramMapper("Virus");
		tagListSize = tagPlaceHolderMap.size();
		
	}


	@Override
	protected int getResourcesId() {
		
		return R.layout.diagram_play_virus;
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
	protected void dispatch(float totalScore) {
		Intent intent = new Intent(getBaseContext(), DiagramResult.class);
		intent.putExtra("SCORE", totalScore);
		intent.putExtra("SOURCE", "Virus");
		startActivity(intent);
	}

}
