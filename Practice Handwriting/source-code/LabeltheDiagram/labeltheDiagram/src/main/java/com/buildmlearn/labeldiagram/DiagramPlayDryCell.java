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

public class DiagramPlayDryCell extends DiagramPlayBase {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(getResourcesId());

		actionBar.setTitle("Dry Cell");
		setDiagramName("DryCell");
		setDiagramCategory("Physics");

		// Score board textViews
		compeleteRatio = (TextView) findViewById(R.id.complete_ratio);
		score = (TextView) findViewById(R.id.score);

		// Placeholder imageViews
		ImageView posTerminalView = (ImageView) findViewById(R.id.posTerminalBlb);
		ImageView negTerminalView = (ImageView) findViewById(R.id.negTerminalBlb);
		ImageView chemPasteView = (ImageView) findViewById(R.id.chemPasteBlb);
		ImageView anodeView = (ImageView) findViewById(R.id.anodeBlb);
		ImageView cathodeView = (ImageView) findViewById(R.id.cathodeBlb);
		ImageView airSpaceView = (ImageView) findViewById(R.id.airSpaceBlb);
		ImageView sealView = (ImageView) findViewById(R.id.sealBlb);
		ImageView mJacketView = (ImageView) findViewById(R.id.mJacketBlb);
		ImageView insulatorView = (ImageView) findViewById(R.id.insulatorBlb);

		// Draggable Tags TextViews
		TextView posTerminalTag = (TextView) findViewById(R.id.posTerminalTag);
		TextView negTerminalTag = (TextView) findViewById(R.id.negTerminalTag);
		TextView chemPasteTag = (TextView) findViewById(R.id.chemPasteTag);
		TextView anodeTag = (TextView) findViewById(R.id.anodeTag);
		TextView cathodeTag = (TextView) findViewById(R.id.cathodeTag);
		TextView airSpaceTag = (TextView) findViewById(R.id.airSpaceTag);
		TextView sealTag = (TextView) findViewById(R.id.sealTag);
		TextView mJacketTag = (TextView) findViewById(R.id.mJacketTag);
		TextView insulatorTag = (TextView) findViewById(R.id.insulatorTag);

		// Register draggable views to receive drag events
		posTerminalView.setOnDragListener(this);
		negTerminalView.setOnDragListener(this);
		chemPasteView.setOnDragListener(this);
		anodeView.setOnDragListener(this);
		cathodeView.setOnDragListener(this);
		airSpaceView.setOnDragListener(this);
		sealView.setOnDragListener(this);
		mJacketView.setOnDragListener(this);
		insulatorView.setOnDragListener(this);

		// Register place holders to receive onLongclick events
		posTerminalTag.setOnLongClickListener(this);
		negTerminalTag.setOnLongClickListener(this);
		chemPasteTag.setOnLongClickListener(this);
		anodeTag.setOnLongClickListener(this);
		cathodeTag.setOnLongClickListener(this);
		airSpaceTag.setOnLongClickListener(this);
		sealTag.setOnLongClickListener(this);
		mJacketTag.setOnLongClickListener(this);
		insulatorTag.setOnLongClickListener(this);

		// Register place holders to receive onClick events
		posTerminalTag.setOnClickListener(this);
		negTerminalTag.setOnClickListener(this);
		chemPasteTag.setOnClickListener(this);
		anodeTag.setOnClickListener(this);
		cathodeTag.setOnClickListener(this);
		airSpaceTag.setOnClickListener(this);
		sealTag.setOnClickListener(this);
		mJacketTag.setOnClickListener(this);
		insulatorTag.setOnClickListener(this);

		placeHolderlist = container.diagramCaller("DryCell");
		tagPlaceHolderMap = tagPlaceholdermapper.diagramMapper("DryCell");
		incompleteTagList = tagPlaceholdermapper.diagramMapper("DryCell");
		tagListSize = tagPlaceHolderMap.size();

		openDB();

	}

	@Override
	public void onClick(View tagView) {
		super.onClick(tagView);

		InfoTooltip popup;
		
		switch (tagView.getId()) {
		case R.id.posTerminalTag:
			popup = new InfoTooltip(getApplicationContext(),
					"Electrons flow to the positive terminal \n"
					+ "of the battery through the circuit");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.negTerminalTag:
			popup = new InfoTooltip(getApplicationContext(),
					"Electrons flow from the negative \n"
					+ "terminal of the battery through the circuit");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.chemPasteTag:
			popup = new InfoTooltip(getApplicationContext(),
					"A mixture of several substances to hold \n"
					+ "the cathode rigid in the center of the cell");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.anodeTag:
			popup = new InfoTooltip(getApplicationContext(),
					"The zinc container serves as the \n"
					+ "negative electrode of the cell");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.cathodeTag:
			popup = new InfoTooltip(getApplicationContext(),
					"The carbon electrode located in the center \n"
					+ "which serves as the positive terminal");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.airSpaceTag:
			popup = new InfoTooltip(getApplicationContext(),
					"Small space left at the top for \n"
					+ "expansion of the electrolytic paste");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.sealTag:
			popup = new InfoTooltip(getApplicationContext(),
					"Provides electrical & chemical insulation \n"
					+ " and prevents air infiltration");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.mJacketTag:
			popup = new InfoTooltip(getApplicationContext(),
					"The cell is enclosed with metal jacket \n"
					+ " to electrically isolate the anode");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.insulatorTag:
			popup = new InfoTooltip(getApplicationContext(),
					"Non-conducting material to separate \n"
					+ "the zinc from the paste");
			popup.show(tagView, AlignMode.BOTTOM);
			break;

		default:
			break;
		}
	}

	@Override
	protected int getResourcesId() {
		// TODO Auto-generated method stub
		return R.layout.diagram_play_dry_cell;
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

		super.dialogBuilder(DiagramPlayDryCell.this);
		
	}

	@Override
	protected void dispatch(float totalScore, int gameScore) {
		Intent intent = new Intent(getBaseContext(), DiagramResult.class);
		intent.putExtra("SCORE", totalScore);
		intent.putExtra("GAME_SCORE", gameScore);
		intent.putExtra("SOURCE", "DryCell");
		intent.putExtra("BEST_SCORE", achievedBestScore);
		intent.putExtra("TRY_CYCLE", tryCycle);
		startActivity(intent);
	}

	@Override
	protected void intentBuilder(String badgeTitle, int badgeId,
			float totalScore, int gameScore, boolean completed, int tryCycle) {
		Intent intent;
		intent = new Intent(getBaseContext(), BadgePopUpWindow.class);
		intent.putExtra("BADGE_TITLE", badgeTitle);
		intent.putExtra("BADGE_ID", badgeId);
		intent.putExtra("SCORE", totalScore);
		intent.putExtra("GAME_SCORE", gameScore);
		intent.putExtra("SOURCE", "DryCell");
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
