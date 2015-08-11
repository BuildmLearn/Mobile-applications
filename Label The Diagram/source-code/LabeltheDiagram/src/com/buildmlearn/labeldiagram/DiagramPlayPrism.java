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

public class DiagramPlayPrism extends DiagramPlayBase {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(getResourcesId());

		actionBar.setTitle("Prism");
		setDiagramName("Prism");
		setDiagramCategory("Physics");
		
		// Score board textViews
		compeleteRatio = (TextView) findViewById(R.id.complete_ratio);
		score = (TextView) findViewById(R.id.score);

		// Placeholder imageViews
		ImageView prismAngleView = (ImageView) findViewById(R.id.prismAngleBlb);
		ImageView refAngleView = (ImageView) findViewById(R.id.refAngleBlb);
		ImageView incAngleView = (ImageView) findViewById(R.id.incAngleBlb);
		ImageView incRayView = (ImageView) findViewById(R.id.incRayBlb);
		ImageView devAngleView = (ImageView) findViewById(R.id.devAngleBlb);
		ImageView refRayView = (ImageView) findViewById(R.id.refRayBlb);
		ImageView emergeAngleView = (ImageView) findViewById(R.id.emergeAngleBlb);
		ImageView emergeRayView = (ImageView) findViewById(R.id.emergeRayBlb);

		// Draggable Tags imageViews
		TextView prismAngleTag = (TextView) findViewById(R.id.prismAngleTag);
		TextView refAngleTag = (TextView) findViewById(R.id.refAngleTag);
		TextView incAngleTag = (TextView) findViewById(R.id.incAngleTag);
		TextView incRayTag = (TextView) findViewById(R.id.incRayTag);
		TextView devAngleTag = (TextView) findViewById(R.id.devAngleTag);
		TextView refRayTag = (TextView) findViewById(R.id.refRayTag);
		TextView emergeAngleTag = (TextView) findViewById(R.id.emergeAngleTag);
		TextView emergeRayTag = (TextView) findViewById(R.id.emergeRayTag);

		// Register draggable views to receive drag events
		prismAngleView.setOnDragListener(this);
		refAngleView.setOnDragListener(this);
		incAngleView.setOnDragListener(this);
		incRayView.setOnDragListener(this);
		devAngleView.setOnDragListener(this);
		refRayView.setOnDragListener(this);
		emergeAngleView.setOnDragListener(this);
		emergeRayView.setOnDragListener(this);

		// Register place holders to receive onLongclick events
		prismAngleTag.setOnLongClickListener(this);
		refAngleTag.setOnLongClickListener(this);
		incAngleTag.setOnLongClickListener(this);
		incRayTag.setOnLongClickListener(this);
		devAngleTag.setOnLongClickListener(this);
		refRayTag.setOnLongClickListener(this);
		emergeAngleTag.setOnLongClickListener(this);
		emergeRayTag.setOnLongClickListener(this);

		// Register place holders to receive onClick events
		prismAngleTag.setOnClickListener(this);
		refAngleTag.setOnClickListener(this);
		incAngleTag.setOnClickListener(this);
		incRayTag.setOnClickListener(this);
		devAngleTag.setOnClickListener(this);
		refRayTag.setOnClickListener(this);
		emergeAngleTag.setOnClickListener(this);
		emergeRayTag.setOnClickListener(this);
		
		placeHolderlist = container.diagramCaller("Prism");
		tagPlaceHolderMap = tagPlaceholdermapper.diagramMapper("Prism");
		incompleteTagList = tagPlaceholdermapper.diagramMapper("Prism");
		tagListSize = tagPlaceHolderMap.size();
		
	}
	
	@Override
	public void onClick(View tagView) {
		// TODO Auto-generated method stub
		super.onClick(tagView);
	}

	@Override
	protected int getResourcesId() {
		return R.layout.diagram_play_prism;
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
		intent.putExtra("SOURCE", "Prism");
		intent.putExtra("BEST_SCORE", achievedBestScore);
		startActivity(intent);
	}
	
	@Override
	protected void intentBuilder(String badgeTitle, int badgeId, float totalScore, int gameScore, boolean completed) {
		Intent intent;
		intent = new Intent(getApplicationContext(), BadgePopUpWindow.class);
		intent.putExtra("BADGE_TITLE", badgeTitle);
		intent.putExtra("BADGE_ID", badgeId);
		intent.putExtra("SCORE", totalScore);
		intent.putExtra("GAME_SCORE", gameScore);
		intent.putExtra("SOURCE", "Prism");
		intent.putExtra("BEST_SCORE", achievedBestScore);
		intent.putExtra("COMPLETED", completed);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		finish();
	}

}
