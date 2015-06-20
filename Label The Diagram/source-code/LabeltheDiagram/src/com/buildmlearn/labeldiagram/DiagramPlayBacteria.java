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

public class DiagramPlayBacteria extends DiagramPlayBase {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(getResourcesId());

		actionBar.setTitle("Bacteria");
		setDiagramName("Bacteria");

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
		ImageView chromosomeView = (ImageView) findViewById(R.id.chromosomeBlb);
		ImageView ribosomeView = (ImageView) findViewById(R.id.ribosomesBlb);
		ImageView foodGranuleView = (ImageView) findViewById(R.id.food_granuleBlb);
		ImageView piliView = (ImageView) findViewById(R.id.piliBlb);
		ImageView flagellumView = (ImageView) findViewById(R.id.flagellumBlb);
		ImageView plasmidView = (ImageView) findViewById(R.id.plasmidBlb);
		ImageView capsuleView = (ImageView) findViewById(R.id.capsuleBlb);
		ImageView cellwallView = (ImageView) findViewById(R.id.cellwallBlb);
		ImageView cytoplasmView = (ImageView) findViewById(R.id.cytoplasmBlb);
		ImageView plasmaMembraneView = (ImageView) findViewById(R.id.plasma_membraneBlb);
		
		// Draggable Tags imageViews
		TextView pinnaTag = (TextView) findViewById(R.id.chromosomeTag);
		TextView ribosomesTag = (TextView) findViewById(R.id.ribosomesTag);
		TextView foodGranuleTag = (TextView) findViewById(R.id.food_granuleTag);
		TextView piliTag = (TextView) findViewById(R.id.piliTag);
		TextView flagellumTag = (TextView) findViewById(R.id.flagellumTag);
		TextView plasmidTag = (TextView) findViewById(R.id.plasmidTag);
		TextView capsuleTag = (TextView) findViewById(R.id.capsuleTag);
		TextView cellwallTag = (TextView) findViewById(R.id.cellwallTag);
		TextView cytoplasmTag = (TextView) findViewById(R.id.cytoplasmTag);
		TextView plasmeMembraneTag = (TextView) findViewById(R.id.plasma_membraneTag);
		
		
		// Register draggable views to receive drag events
		chromosomeView.setOnDragListener(this);
		ribosomeView.setOnDragListener(this);
		foodGranuleView.setOnDragListener(this);
		piliView.setOnDragListener(this);
		flagellumView.setOnDragListener(this);
		plasmidView.setOnDragListener(this);
		capsuleView.setOnDragListener(this);
		cellwallView.setOnDragListener(this);
		cytoplasmView.setOnDragListener(this);
		plasmaMembraneView.setOnDragListener(this);
		
		
		// Register place holders to receive onLongclick events
		pinnaTag.setOnLongClickListener(this);
		ribosomesTag.setOnLongClickListener(this);
		foodGranuleTag.setOnLongClickListener(this);
		piliTag.setOnLongClickListener(this);
		flagellumTag.setOnLongClickListener(this);
		plasmidTag.setOnLongClickListener(this);
		capsuleTag.setOnLongClickListener(this);
		cellwallTag.setOnLongClickListener(this);
		cytoplasmTag.setOnLongClickListener(this);
		plasmeMembraneTag.setOnLongClickListener(this);
		
		
		// Register place holders to receive onClick events
		pinnaTag.setOnClickListener(this);
		ribosomesTag.setOnClickListener(this);
		foodGranuleTag.setOnClickListener(this);
		piliTag.setOnClickListener(this);
		flagellumTag.setOnClickListener(this);
		plasmidTag.setOnClickListener(this);
		capsuleTag.setOnClickListener(this);
		cellwallTag.setOnClickListener(this);
		cytoplasmTag.setOnClickListener(this);
		plasmeMembraneTag.setOnClickListener(this);
		
		placeHolderlist = container.diagramCaller("Bacteria");
		tagPlaceHolderMap = tagPlaceholdermapper.diagramMapper("Bacteria");
		incompleteTagList = tagPlaceholdermapper.diagramMapper("Bacteria");
		tagListSize = tagPlaceHolderMap.size();
		
	}


	@Override
	protected int getResourcesId() {
		// TODO Auto-generated method stub
		return R.layout.diagram_play_bacteria;
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
		intent.putExtra("SOURCE", "DiagramPlayBacteria");
		startActivity(intent);
	}


}
