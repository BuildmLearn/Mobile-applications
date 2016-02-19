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

public class DiagramPlayLens extends DiagramPlayBase {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(getResourcesId());

		actionBar.setTitle("Lens");
		setDiagramName("Lens");
		setDiagramCategory("Physics");

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

		openDB();

	}

	@Override
	public void onClick(View tagView) {
		super.onClick(tagView);

		InfoTooltip popup;

		switch (tagView.getId()) {
		case R.id.principalRayTag:
			popup = new InfoTooltip(getApplicationContext(),
					"This ray is initially parallel to the axis \n"
					+ "then passes through the focal point");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.centralRayTag:
			popup = new InfoTooltip(getApplicationContext(),
					"The ray that passes straight through the center \n"
					+ "of the lens and is not deflected");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.focalRayTag:
			popup = new InfoTooltip(getApplicationContext(),
					"This ray is directed through the other focal point,\n"
					+ "after passing the lens it goes parallel to the axis");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.focusTag:
			popup = new InfoTooltip(getApplicationContext(),
					"A point at which rays of light");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.objectTag:
			popup = new InfoTooltip(getApplicationContext(),
					"The material thing that can be seen");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.imageTag:
			popup = new InfoTooltip(getApplicationContext(),
					"The representation of the external form \n"
					+ "of the object may be in different size");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.lensTag:
			popup = new InfoTooltip(getApplicationContext(),
					"the lens having convex surface");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.lensAxisTag:
			popup = new InfoTooltip(getApplicationContext(),
					"An imaginary axis that passing through \n"
					+ "the center of the lens");
			popup.show(tagView, AlignMode.BOTTOM);
			break;

		default:
			break;
		}
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

		super.dialogBuilder(DiagramPlayLens.this);

	}

	@Override
	protected void dispatch(float totalScore, int gameScore) {
		Intent intent = new Intent(getBaseContext(), DiagramResult.class);
		intent.putExtra("SCORE", totalScore);
		intent.putExtra("GAME_SCORE", gameScore);
		intent.putExtra("SOURCE", "Lens");
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
		intent.putExtra("SOURCE", "Lens");
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
