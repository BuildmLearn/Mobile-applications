package com.buildmlearn.labeldiagram;

import com.buildmlearn.labeldiagram.badges.BadgePopUpWindow;
import com.buildmlearn.labeldiagram.tooltipkit.InfoTooltip;
import com.buildmlearn.labeldiagram.tooltipkit.CustomTooltip.AlignMode;
import com.example.labelthediagram.R;

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

public class DiagramPlayBacteria extends DiagramPlayBase {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(getResourcesId());

		actionBar.setTitle("Bacteria");
		setDiagramName("Bacteria");
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
		TextView chromosomeTag = (TextView) findViewById(R.id.chromosomeTag);
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
		chromosomeTag.setOnLongClickListener(this);
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
		chromosomeTag.setOnClickListener(this);
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
	public void onClick(View tagView) {
		// TODO Extract messages to separate container
		switch (tagView.getId()) {
		case R.id.chromosomeTag:
			InfoTooltip popup = new InfoTooltip(getApplicationContext(),
					"This is situated within nucleoid region \n"
					+ "and contain a circular DNA molecule");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.ribosomesTag:
			InfoTooltip popup1 = new InfoTooltip(getApplicationContext(),
					"Structures that assemble proteins ");
			popup1.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.food_granuleTag:
			InfoTooltip popup2 = new InfoTooltip(getApplicationContext(),
					"They occur as small granules of lipids or glycogen\n"
					+ " held in sacs formed from lipid membrane");
			popup2.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.piliTag:
			InfoTooltip popup3 = new InfoTooltip(getApplicationContext(),
					"Short, thin, straight, hair like projections form surface  ");
			popup3.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.flagellumTag:
			InfoTooltip popup4 = new InfoTooltip(getApplicationContext(),
					"This is a lash-like appendage that begins\n"
					+ " from the cell body which is important for locomotion");
			popup4.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.plasmidTag:
			InfoTooltip popup5 = new InfoTooltip(
					getApplicationContext(),
					"Located in cytosol of bacterial cells which\n"
					+ " contains supplemental genetic information");
			popup5.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.capsuleTag:
			InfoTooltip popup6 = new InfoTooltip(getApplicationContext(),
					"A layer that lies outside the cell envelope\n"
					+ "of bacteria which can be the cause of \n"
					+ "various diseases");
			popup6.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.cellwallTag:
			InfoTooltip popup7 = new InfoTooltip(getApplicationContext(),
					"This protects the cell from shock,\n "
					+ "physical damage,confers rigidity \n"
					+ "and shape of bacterial cells ");
			popup7.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.cytoplasmTag:
			InfoTooltip popup8 = new InfoTooltip(getApplicationContext(),
					"The fluid (cytosol) and all its dissolved\n"
					+ " particles is called the cytoplasm of the cell");

			popup8.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.plasma_membraneTag:
			InfoTooltip popup9 = new InfoTooltip(getApplicationContext(),
					"This is a biological membrane that separates\n"
					+ " the interior of all cells from the outside environment ");
			popup9.show(tagView, AlignMode.BOTTOM);
			break;
		default:
			break;
		}
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
	protected void dispatch(float totalScore, int gameScore) {
		Intent intent = new Intent(getBaseContext(), DiagramResult.class);
		intent.putExtra("SCORE", totalScore);
		intent.putExtra("GAME_SCORE", gameScore);
		intent.putExtra("SOURCE", "Bacteria");
		intent.putExtra("BEST_SCORE", achievedBestScore);
		startActivity(intent);
	}

	@Override
	protected void intentBuilder(String badgeTitle, int badgeId, float totalScore, int gameScore) {
		Intent intent;
		intent = new Intent(getBaseContext(), BadgePopUpWindow.class);
		intent.putExtra("BADGE_TITLE", badgeTitle);
		intent.putExtra("BADGE_ID", badgeId);
		intent.putExtra("SCORE", totalScore);
		intent.putExtra("GAME_SCORE", gameScore);
		intent.putExtra("SOURCE", "Bacteria");
		intent.putExtra("BEST_SCORE", achievedBestScore);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		finish();
	}

}
