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

public class DiagramPlayVirus extends DiagramPlayBase {

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		actionBar.setTitle("Virus");
		setDiagramName("Virus");
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

		openDB();

	}

	@Override
	public void onClick(View tagView) {
		super.onClick(tagView);

		// TODO Extract messages to separate container
		switch (tagView.getId()) {
		case R.id.headTag:
			InfoTooltip popup = new InfoTooltip(getApplicationContext(),
					"This is situated within nucleoid region \n"
							+ "and contain a circular DNA molecule");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.collarTag:
			InfoTooltip popup1 = new InfoTooltip(getApplicationContext(),
					"Structures that assemble proteins ");
			popup1.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.neckTag:
			InfoTooltip popup2 = new InfoTooltip(getApplicationContext(),
					"They occur as small granules of lipids or glycogen\n"
							+ " held in sacs formed from lipid membrane");
			popup2.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.sheathTag:
			InfoTooltip popup3 = new InfoTooltip(getApplicationContext(),
					"Short, thin, straight, hair like projections form surface  ");
			popup3.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.tailfibreTag:
			InfoTooltip popup4 = new InfoTooltip(
					getApplicationContext(),
					"This is a lash-like appendage that begins\n"
							+ " from the cell body which is important for locomotion");
			popup4.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.tailpinsTag:
			InfoTooltip popup5 = new InfoTooltip(getApplicationContext(),
					"Located in cytosol of bacterial cells which\n"
							+ " contains supplemental genetic information");
			popup5.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.dnaTag:
			InfoTooltip popup6 = new InfoTooltip(getApplicationContext(),
					"A layer that lies outside the cell envelope\n"
							+ "of bacteria which can be the cause of \n"
							+ "various diseases");
			popup6.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.baseplateTag:
			InfoTooltip popup7 = new InfoTooltip(getApplicationContext(),
					"This protects the cell from shock,\n "
							+ "physical damage,confers rigidity \n"
							+ "and shape of bacterial cells ");
			popup7.show(tagView, AlignMode.BOTTOM);
			break;
		default:
			break;
		}
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
	protected void dispatch(float totalScore, int gameScore) {
		Intent intent = new Intent(getBaseContext(), DiagramResult.class);
		intent.putExtra("SCORE", totalScore);
		intent.putExtra("GAME_SCORE", gameScore);
		intent.putExtra("SOURCE", "Virus");
		intent.putExtra("BEST_SCORE", achievedBestScore);
		intent.putExtra("TRY_CYCLE", tryCycle);
		startActivity(intent);
	}

	@Override
	protected void intentBuilder(String badgeTitle, int badgeId,
			float totalScore, int gameScore, boolean completed, int tryCycle) {
		Intent intent;
		intent = new Intent(getApplicationContext(), BadgePopUpWindow.class);
		intent.putExtra("BADGE_TITLE", badgeTitle);
		intent.putExtra("BADGE_ID", badgeId);
		intent.putExtra("SCORE", totalScore);
		intent.putExtra("GAME_SCORE", gameScore);
		intent.putExtra("SOURCE", "Virus");
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
