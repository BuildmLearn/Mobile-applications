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

public class DiagramPlayMotor extends DiagramPlayBase {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(getResourcesId());

		actionBar.setTitle("Motor");
		setDiagramName("Motor");
		setDiagramCategory("Physics");

		// Score board textViews
		compeleteRatio = (TextView) findViewById(R.id.complete_ratio);
		score = (TextView) findViewById(R.id.score);

		// Placeholder imageViews
		ImageView northPoleView = (ImageView) findViewById(R.id.northPoleBlb);
		ImageView southPoleView = (ImageView) findViewById(R.id.southPoleBlb);
		ImageView magFieldView = (ImageView) findViewById(R.id.magFieldBlb);
		ImageView forceView = (ImageView) findViewById(R.id.forceBlb);
		ImageView axelView = (ImageView) findViewById(R.id.axelBlb);
		ImageView batteryView = (ImageView) findViewById(R.id.batteryBlb);
		ImageView commutatorView = (ImageView) findViewById(R.id.commutatorBlb);
		ImageView brushView = (ImageView) findViewById(R.id.brushBlb);
		ImageView armatureView = (ImageView) findViewById(R.id.armatureBlb);

		// Draggable Tags TextViews
		TextView northPoleTag = (TextView) findViewById(R.id.northPoleTag);
		TextView southPoleTag = (TextView) findViewById(R.id.southPoleTag);
		TextView magFieldTag = (TextView) findViewById(R.id.magFieldTag);
		TextView forceTag = (TextView) findViewById(R.id.forceTag);
		TextView axelTag = (TextView) findViewById(R.id.axelTag);
		TextView batteryTag = (TextView) findViewById(R.id.batteryTag);
		TextView commutatorTag = (TextView) findViewById(R.id.commutatorTag);
		TextView brushTag = (TextView) findViewById(R.id.brushTag);
		TextView armatureTag = (TextView) findViewById(R.id.armatureTag);

		// Register draggable views to receive drag events
		northPoleView.setOnDragListener(this);
		southPoleView.setOnDragListener(this);
		magFieldView.setOnDragListener(this);
		forceView.setOnDragListener(this);
		axelView.setOnDragListener(this);
		batteryView.setOnDragListener(this);
		commutatorView.setOnDragListener(this);
		brushView.setOnDragListener(this);
		armatureView.setOnDragListener(this);

		// Register place holders to receive onLongclick events
		northPoleTag.setOnLongClickListener(this);
		southPoleTag.setOnLongClickListener(this);
		magFieldTag.setOnLongClickListener(this);
		forceTag.setOnLongClickListener(this);
		axelTag.setOnLongClickListener(this);
		batteryTag.setOnLongClickListener(this);
		commutatorTag.setOnLongClickListener(this);
		brushTag.setOnLongClickListener(this);
		armatureTag.setOnLongClickListener(this);

		// Register place holders to receive onClick events
		northPoleTag.setOnClickListener(this);
		southPoleTag.setOnClickListener(this);
		magFieldTag.setOnClickListener(this);
		forceTag.setOnClickListener(this);
		axelTag.setOnClickListener(this);
		batteryTag.setOnClickListener(this);
		commutatorTag.setOnClickListener(this);
		brushTag.setOnClickListener(this);
		armatureTag.setOnClickListener(this);
		
		placeHolderlist = container.diagramCaller("Motor");
		tagPlaceHolderMap = tagPlaceholdermapper.diagramMapper("Motor");
		incompleteTagList = tagPlaceholdermapper.diagramMapper("Motor");
		tagListSize = tagPlaceHolderMap.size();

	}

	@Override
	public void onClick(View tagView) {
		// TODO Auto-generated method stub
		super.onClick(tagView);
	}
	
	@Override
	protected int getResourcesId() {
		// TODO Auto-generated method stub
		return R.layout.diagram_play_motor;
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
		intent.putExtra("SOURCE", "Motor");
		intent.putExtra("BEST_SCORE", achievedBestScore);
		startActivity(intent);
	}

	@Override
	protected void intentBuilder(String badgeTitle, int badgeId) {
		Intent intent;
		intent = new Intent(getBaseContext(), BadgePopUpWindow.class);
		intent.putExtra("BADGE_TITLE", badgeTitle);
		intent.putExtra("BADGE_ID", badgeId);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		finish();
	}
	
}
