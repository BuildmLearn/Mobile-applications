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
import com.buildmlearn.labeldiagram.tooltipkit.InfoTooltip;
import com.buildmlearn.labeldiagram.tooltipkit.CustomTooltip.AlignMode;
import com.example.labelthediagram.R;

public class DiagramPlayHumanEar extends DiagramPlayBase {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(getResourcesId());

		actionBar.setTitle("Human Ear");
		setDiagramName("HumanEar");
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
		ImageView pinnaView = (ImageView) findViewById(R.id.pinnaBlb);
		ImageView earcanalView = (ImageView) findViewById(R.id.earcanalBlb);
		ImageView eardrumView = (ImageView) findViewById(R.id.eardrumBlb);
		ImageView cochleaView = (ImageView) findViewById(R.id.cochleaBlb);
		ImageView earnerveView = (ImageView) findViewById(R.id.earnerveBlb);
		ImageView malleusView = (ImageView) findViewById(R.id.malleusBlb);
		ImageView incusView = (ImageView) findViewById(R.id.incusBlb);
		ImageView outerearView = (ImageView) findViewById(R.id.outerearBlb);
		ImageView stapesView = (ImageView) findViewById(R.id.stapesBlb);
		ImageView canalsView = (ImageView) findViewById(R.id.canalsBlb);

		// Draggable Tags imageViews
		TextView pinnaTag = (TextView) findViewById(R.id.pinnaTag);
		TextView earcanalTag = (TextView) findViewById(R.id.ear_canalTag);
		TextView eardrumTag = (TextView) findViewById(R.id.eardrumTag);
		TextView cochleaTag = (TextView) findViewById(R.id.cochleaTag);
		TextView earnerveTag = (TextView) findViewById(R.id.earnerveTag);
		TextView malleusTag = (TextView) findViewById(R.id.malleusTag);
		TextView incusTag = (TextView) findViewById(R.id.incusTag);
		TextView outerearTag = (TextView) findViewById(R.id.outerearTag);
		TextView stapesTag = (TextView) findViewById(R.id.stapesTag);
		TextView canalsTag = (TextView) findViewById(R.id.canalsTag);

		// Register draggable views to receive drag events
		pinnaView.setOnDragListener(this);
		earcanalView.setOnDragListener(this);
		eardrumView.setOnDragListener(this);
		cochleaView.setOnDragListener(this);
		earnerveView.setOnDragListener(this);
		malleusView.setOnDragListener(this);
		incusView.setOnDragListener(this);
		outerearView.setOnDragListener(this);
		stapesView.setOnDragListener(this);
		canalsView.setOnDragListener(this);

		// Register place holders to receive onLongclick events
		pinnaTag.setOnLongClickListener(this);
		earcanalTag.setOnLongClickListener(this);
		eardrumTag.setOnLongClickListener(this);
		cochleaTag.setOnLongClickListener(this);
		earnerveTag.setOnLongClickListener(this);
		malleusTag.setOnLongClickListener(this);
		incusTag.setOnLongClickListener(this);
		outerearTag.setOnLongClickListener(this);
		stapesTag.setOnLongClickListener(this);
		canalsTag.setOnLongClickListener(this);

		// Register place holders to receive onClick events
		pinnaTag.setOnClickListener(this);
		earcanalTag.setOnClickListener(this);
		eardrumTag.setOnClickListener(this);
		cochleaTag.setOnClickListener(this);
		earnerveTag.setOnClickListener(this);
		malleusTag.setOnClickListener(this);
		incusTag.setOnClickListener(this);
		outerearTag.setOnClickListener(this);
		stapesTag.setOnClickListener(this);
		canalsTag.setOnClickListener(this);

		placeHolderlist = container.diagramCaller("HumanEar");
		tagPlaceHolderMap = tagPlaceholdermapper.diagramMapper("HumanEar");
		incompleteTagList = tagPlaceholdermapper.diagramMapper("HumanEar");
		tagListSize = tagPlaceHolderMap.size();
		
		openDB();

	}

	@Override
	public void onClick(View tagView) {
		// TODO Extract messages to separate container
		switch (tagView.getId()) {
		case R.id.pinnaTag:
			InfoTooltip popup = new InfoTooltip(getApplicationContext(),
					" This is the entrance for the sound waves ");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.ear_canalTag:
			InfoTooltip popup1 = new InfoTooltip(getApplicationContext(),
					"2 cm and it guides the sound towards the inner ear");
			popup1.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.eardrumTag:
			InfoTooltip popup2 = new InfoTooltip(getApplicationContext(),
					"It is a delicate membrane which separates \n"
							+ "the outer ear from the middle ear ");
			popup2.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.cochleaTag:
			InfoTooltip popup3 = new InfoTooltip(getApplicationContext(),
					"It is a spiral shaped tube  ");
			popup3.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.earnerveTag:
			InfoTooltip popup4 = new InfoTooltip(getApplicationContext(),
					"Picks up electric impulses from the nerve cells\n"
					+ " on the cochlea and sends them to the brain ");
			popup4.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.malleusTag:
			InfoTooltip popup5 = new InfoTooltip(
					getApplicationContext(),
					"It is one of the three bones \nlocated next to the ear drum \n"
					+ "vibrates when sound reaches it");
			popup5.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.incusTag:
			InfoTooltip popup6 = new InfoTooltip(getApplicationContext(),
					"It is another tiny bone which vibrates \n"
					+ "in response to the previous vibration  ");
			popup6.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.outerearTag:
			InfoTooltip popup7 = new InfoTooltip(getApplicationContext(),
					"It is the part of the ear which can be seen ");
			popup7.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.stapesTag:
			InfoTooltip popup8 = new InfoTooltip(getApplicationContext(),
					" It is the last bone which receives \n"
					+ "the vibration from the other two \n"
					+ "and sends it into the inner ear ");
			popup8.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.canalsTag:
			InfoTooltip popup9 = new InfoTooltip(getApplicationContext(),
					"They are connected with the cochlea \n and are filled with fluid ");
			popup9.show(tagView, AlignMode.BOTTOM);
			break;
		default:
			break;
		}
	}

	@Override
	protected int getResourcesId() {
		// TODO Auto-generated method stub
		return R.layout.diagram_play_ear;
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
		intent.putExtra("SOURCE", "HumanEar");
		intent.putExtra("BEST_SCORE", achievedBestScore);
		startActivity(intent);
	}
	
	@Override
	protected void intentBuilder(String badgeTitle, int badgeId, float totalScore, int gameScore, boolean completed) {
		Intent intent;
		intent = new Intent(getBaseContext(), BadgePopUpWindow.class);
		intent.putExtra("BADGE_TITLE", badgeTitle);
		intent.putExtra("BADGE_ID", badgeId);
		intent.putExtra("SCORE", totalScore);
		intent.putExtra("GAME_SCORE", gameScore);
		intent.putExtra("SOURCE", "HumanEar");
		intent.putExtra("BEST_SCORE", achievedBestScore);
		intent.putExtra("COMPLETED", completed);
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
