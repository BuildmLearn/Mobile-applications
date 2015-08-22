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

public class DiagramPlayStarPatterns extends DiagramPlayBase {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(getResourcesId());

		actionBar.setTitle("Star Patterns");
		setDiagramName("StarPatterns");
		setDiagramCategory("Science");

		// Score board textViews
		compeleteRatio = (TextView) findViewById(R.id.complete_ratio);
		score = (TextView) findViewById(R.id.score);

		// Placeholder imageViews
		ImageView libraView = (ImageView) findViewById(R.id.libraBlb);
		ImageView little_dripperView = (ImageView) findViewById(R.id.little_dripperBlb);
		ImageView big_dripperView = (ImageView) findViewById(R.id.big_dripperBlb);
		ImageView scorpioView = (ImageView) findViewById(R.id.scorpioBlb);
		ImageView orianView = (ImageView) findViewById(R.id.orianBlb);
		ImageView leoView = (ImageView) findViewById(R.id.leoBlb);
		ImageView geminiView = (ImageView) findViewById(R.id.geminiBlb);
		ImageView dracoView = (ImageView) findViewById(R.id.dracoBlb);

		// Draggable Tags TextViews
		TextView libraTag = (TextView) findViewById(R.id.libraTag);
		TextView little_dripperTag = (TextView) findViewById(R.id.little_dripperTag);
		TextView big_dripperTag = (TextView) findViewById(R.id.big_dripperTag);
		TextView scorpioTag = (TextView) findViewById(R.id.scorpioTag);
		TextView orianTag = (TextView) findViewById(R.id.orianTag);
		TextView leoTag = (TextView) findViewById(R.id.leoTag);
		TextView geminiTag = (TextView) findViewById(R.id.geminiTag);
		TextView dracoTag = (TextView) findViewById(R.id.dracoTag);

		// Register draggable views to receive drag events
		libraView.setOnDragListener(this);
		little_dripperView.setOnDragListener(this);
		big_dripperView.setOnDragListener(this);
		scorpioView.setOnDragListener(this);
		orianView.setOnDragListener(this);
		leoView.setOnDragListener(this);
		geminiView.setOnDragListener(this);
		dracoView.setOnDragListener(this);

		// Register place holders to receive onLongclick events
		libraTag.setOnLongClickListener(this);
		little_dripperTag.setOnLongClickListener(this);
		big_dripperTag.setOnLongClickListener(this);
		scorpioTag.setOnLongClickListener(this);
		orianTag.setOnLongClickListener(this);
		leoTag.setOnLongClickListener(this);
		geminiTag.setOnLongClickListener(this);
		dracoTag.setOnLongClickListener(this);

		// Register place holders to receive onClick events
		libraTag.setOnClickListener(this);
		little_dripperTag.setOnClickListener(this);
		big_dripperTag.setOnClickListener(this);
		scorpioTag.setOnClickListener(this);
		orianTag.setOnClickListener(this);
		leoTag.setOnClickListener(this);
		geminiTag.setOnClickListener(this);
		dracoTag.setOnClickListener(this);
		
		placeHolderlist = container.diagramCaller("StarPatterns");
		tagPlaceHolderMap = tagPlaceholdermapper.diagramMapper("StarPatterns");
		incompleteTagList = tagPlaceholdermapper.diagramMapper("StarPatterns");
		tagListSize = tagPlaceHolderMap.size();
		
		openDB();

	}
	
	@Override
	public void onClick(View tagView) {
		super.onClick(tagView);
		
		InfoTooltip popup;
		
		switch (tagView.getId()) {
		case R.id.libraTag:
			popup = new InfoTooltip(getApplicationContext(),
					"Libra is Latin for weighing scales, \n"
					+ "only constellation of the Zodiac \n"
					+ "representing an inanimate object");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.little_dripperTag:
			popup = new InfoTooltip(getApplicationContext(),
					"Ursa Minor, also known as the Little Bear, \n"
					+ " is a constellation in the northern sky");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.big_dripperTag:
			popup = new InfoTooltip(getApplicationContext(),
					"The Big Dipper is an asterism of seven stars \n"
					+ "recognized as a distinct grouping \n"
					+ "in many cultures");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.scorpioTag:
			popup = new InfoTooltip(getApplicationContext(),
					"Eighth astrological sign in the Zodiac");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.orianTag:
			popup = new InfoTooltip(getApplicationContext(),
					"A constellation which named after a hunter, \n"
					+ "Orian in Greek mythology");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.leoTag:
			popup = new InfoTooltip(getApplicationContext(),
					"Fifth astrological sign of the zodiac, \n"
					+ "originating from the constellation of Leo");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.geminiTag:
			popup = new InfoTooltip(getApplicationContext(),
					"Third astrological sign in the Zodiac, \n"
					+ "originating from the constellation of Gemini");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.dracoTag:
			popup = new InfoTooltip(getApplicationContext(),
					"Draco is latin for dragon, \n"
					+ "a constellation in the far northern sky");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
			
			
		default:
			break;
		}
		
	}

	@Override
	protected int getResourcesId() {
		return R.layout.diagram_play_star_patterns;
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

		super.dialogBuilder(DiagramPlayStarPatterns.this);

	}

	@Override
	protected void dispatch(float totalScore, int gameScore) {
		Intent intent = new Intent(getBaseContext(), DiagramResult.class);
		intent.putExtra("SCORE", totalScore);
		intent.putExtra("GAME_SCORE", gameScore);
		intent.putExtra("SOURCE", "StarPatterns");
		intent.putExtra("BEST_SCORE", achievedBestScore);
		intent.putExtra("TRY_CYCLE", tryCycle);
		startActivity(intent);
	}
	
	@Override
	protected void intentBuilder(String badgeTitle, int badgeId, float totalScore, int gameScore, boolean completed, int tryCycle) {
		Intent intent;
		intent = new Intent(getApplicationContext(), BadgePopUpWindow.class);
		intent.putExtra("BADGE_TITLE", badgeTitle);
		intent.putExtra("BADGE_ID", badgeId);
		intent.putExtra("SCORE", totalScore);
		intent.putExtra("GAME_SCORE", gameScore);
		intent.putExtra("SOURCE", "StarPatterns");
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
