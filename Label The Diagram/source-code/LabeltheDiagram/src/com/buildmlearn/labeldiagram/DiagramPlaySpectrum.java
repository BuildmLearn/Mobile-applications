package com.buildmlearn.labeldiagram;

import com.buildmlearn.labeldiagram.badges.BadgePopUpWindow;
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

public class DiagramPlaySpectrum extends DiagramPlayBase {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(getResourcesId());

		actionBar.setTitle("Spectrum");
		setDiagramName("Spectrum");
		setDiagramCategory("Physics");

		// Score board textViews
		compeleteRatio = (TextView) findViewById(R.id.complete_ratio);
		score = (TextView) findViewById(R.id.score);

		// Placeholder imageViews
		ImageView amView = (ImageView) findViewById(R.id.amBlb);
		ImageView fmView = (ImageView) findViewById(R.id.fmBlb);
		ImageView microwaveView = (ImageView) findViewById(R.id.microwaveBlb);
		ImageView visibleLightView = (ImageView) findViewById(R.id.visibleLightBlb);
		ImageView radio_wavesView = (ImageView) findViewById(R.id.radio_wavesBlb);
		ImageView irView = (ImageView) findViewById(R.id.irBlb);
		ImageView uvView = (ImageView) findViewById(R.id.uvBlb);
		ImageView xrayView = (ImageView) findViewById(R.id.xrayBlb);
		ImageView gammaView = (ImageView) findViewById(R.id.gammaBlb);

		// Draggable Tags TextViews
		TextView amTag = (TextView) findViewById(R.id.amTag);
		TextView fmTag = (TextView) findViewById(R.id.fmTag);
		TextView microwaveTag = (TextView) findViewById(R.id.microwaveTag);
		TextView visibleLightTag = (TextView) findViewById(R.id.visibleLightTag);
		TextView radio_wavesTag = (TextView) findViewById(R.id.radio_wavesTag);
		TextView irTag = (TextView) findViewById(R.id.irTag);
		TextView uvTag = (TextView) findViewById(R.id.uvTag);
		TextView xrayTag = (TextView) findViewById(R.id.xrayTag);
		TextView gammaTag = (TextView) findViewById(R.id.gammaTag);

		// Register draggable views to receive drag events
		amView.setOnDragListener(this);
		fmView.setOnDragListener(this);
		microwaveView.setOnDragListener(this);
		visibleLightView.setOnDragListener(this);
		radio_wavesView.setOnDragListener(this);
		irView.setOnDragListener(this);
		uvView.setOnDragListener(this);
		xrayView.setOnDragListener(this);
		gammaView.setOnDragListener(this);

		// Register place holders to receive onLongclick events
		amTag.setOnLongClickListener(this);
		fmTag.setOnLongClickListener(this);
		microwaveTag.setOnLongClickListener(this);
		visibleLightTag.setOnLongClickListener(this);
		radio_wavesTag.setOnLongClickListener(this);
		irTag.setOnLongClickListener(this);
		uvTag.setOnLongClickListener(this);
		xrayTag.setOnLongClickListener(this);
		gammaTag.setOnLongClickListener(this);

		// Register place holders to receive onClick events
		amTag.setOnClickListener(this);
		fmTag.setOnClickListener(this);
		microwaveTag.setOnClickListener(this);
		visibleLightTag.setOnClickListener(this);
		radio_wavesTag.setOnClickListener(this);
		irTag.setOnClickListener(this);
		uvTag.setOnClickListener(this);
		xrayTag.setOnClickListener(this);
		gammaTag.setOnClickListener(this);
		
		placeHolderlist = container.diagramCaller("Spectrum");
		tagPlaceHolderMap = tagPlaceholdermapper.diagramMapper("Spectrum");
		incompleteTagList = tagPlaceholdermapper.diagramMapper("Spectrum");
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
		return R.layout.diagram_play_spectrum;
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
		intent.putExtra("SOURCE", "Spectrum");
		intent.putExtra("BEST_SCORE", achievedBestScore);
		startActivity(intent);
	}
	
	@Override
	protected void intentBuilder(String badgeTitle, int badgeId, float totalScore, int gameScore, boolean completed, boolean isMasterBadge) {
		Intent intent;
		intent = new Intent(getApplicationContext(), BadgePopUpWindow.class);
		intent.putExtra("BADGE_TITLE", badgeTitle);
		intent.putExtra("BADGE_ID", badgeId);
		intent.putExtra("SCORE", totalScore);
		intent.putExtra("GAME_SCORE", gameScore);
		intent.putExtra("SOURCE", "Spectrum");
		intent.putExtra("BEST_SCORE", achievedBestScore);
		intent.putExtra("COMPLETED", completed);
		intent.putExtra("MASTER_BADGE", isMasterBadge);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		finish();
	}
}
