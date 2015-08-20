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

public class DiagramPlayCircuit extends DiagramPlayBase {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(getResourcesId());

		actionBar.setTitle("Electric Circuit");
		setDiagramName("Circuit");
		setDiagramCategory("Physics");

		// Score board textViews
		compeleteRatio = (TextView) findViewById(R.id.complete_ratio);
		score = (TextView) findViewById(R.id.score);

		// Placeholder imageViews
		ImageView capacitorView = (ImageView) findViewById(R.id.capacitorBlb);
		ImageView batteryView = (ImageView) findViewById(R.id.batteryBlb);
		ImageView currentView = (ImageView) findViewById(R.id.currentBlb);
		ImageView resisterView = (ImageView) findViewById(R.id.resistorBlb);
		ImageView switchView = (ImageView) findViewById(R.id.switchBlb);
		ImageView ameterView = (ImageView) findViewById(R.id.ameterBlb);
		ImageView loadView = (ImageView) findViewById(R.id.loadBlb);
		ImageView voltmeterView = (ImageView) findViewById(R.id.voltmeterBlb);
		ImageView bulbView = (ImageView) findViewById(R.id.bulbBlb);

		// Draggable Tags TextViews
		TextView capacitorTag = (TextView) findViewById(R.id.capacitorTag);
		TextView batteryTag = (TextView) findViewById(R.id.batteryTag);
		TextView currentTag = (TextView) findViewById(R.id.currentTag);
		TextView resistorTag = (TextView) findViewById(R.id.resistorTag);
		TextView switchTag = (TextView) findViewById(R.id.switchTag);
		TextView ameterTag = (TextView) findViewById(R.id.ameterTag);
		TextView loadTag = (TextView) findViewById(R.id.loadTag);
		TextView voltmeterTag = (TextView) findViewById(R.id.voltmeterTag);
		TextView bulbTag = (TextView) findViewById(R.id.bulbTag);

		// Register draggable views to receive drag events
		capacitorView.setOnDragListener(this);
		batteryView.setOnDragListener(this);
		currentView.setOnDragListener(this);
		resisterView.setOnDragListener(this);
		switchView.setOnDragListener(this);
		ameterView.setOnDragListener(this);
		loadView.setOnDragListener(this);
		voltmeterView.setOnDragListener(this);
		bulbView.setOnDragListener(this);

		// Register place holders to receive onLongclick events
		capacitorTag.setOnLongClickListener(this);
		batteryTag.setOnLongClickListener(this);
		currentTag.setOnLongClickListener(this);
		resistorTag.setOnLongClickListener(this);
		switchTag.setOnLongClickListener(this);
		ameterTag.setOnLongClickListener(this);
		loadTag.setOnLongClickListener(this);
		voltmeterTag.setOnLongClickListener(this);
		bulbTag.setOnLongClickListener(this);

		// Register place holders to receive onClick events
		capacitorTag.setOnClickListener(this);
		batteryTag.setOnClickListener(this);
		currentTag.setOnClickListener(this);
		resistorTag.setOnClickListener(this);
		switchTag.setOnClickListener(this);
		ameterTag.setOnClickListener(this);
		loadTag.setOnClickListener(this);
		voltmeterTag.setOnClickListener(this);
		bulbTag.setOnClickListener(this);

		placeHolderlist = container.diagramCaller("Circuit");
		tagPlaceHolderMap = tagPlaceholdermapper.diagramMapper("Circuit");
		incompleteTagList = tagPlaceholdermapper.diagramMapper("Circuit");
		tagListSize = tagPlaceHolderMap.size();

		openDB();

	}

	@Override
	public void onClick(View tagView) {
		super.onClick(tagView);

		InfoTooltip popup;
		
		switch (tagView.getId()) {
		case R.id.capacitorTag:
			popup = new InfoTooltip(getApplicationContext(),
					"This stores energy in the form of \n"
					+ "an electrostatic field between its plates");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.batteryTag:
			popup = new InfoTooltip(getApplicationContext(),
					"The source of power fro the electric \n"
					+ "circuit");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.currentTag:
			popup = new InfoTooltip(getApplicationContext(),
					"The flow of electricity");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.resistorTag:
			popup = new InfoTooltip(getApplicationContext(),
					"A device designed to introduce \n"
					+ "resistance into an electric circuit");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.switchTag:
			popup = new InfoTooltip(getApplicationContext(),
					"Device for making and breaking \n "
					+ "the connection in the electric circuit");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.ameterTag:
			popup = new InfoTooltip(getApplicationContext(),
					"Instrument for measuring electric \n"
					+ "current in amperes");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.loadTag:
			popup = new InfoTooltip(getApplicationContext(),
					"Something in the circuit that will draw power");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.voltmeterTag:
			popup = new InfoTooltip(getApplicationContext(),
					"Instrument for measure electrical \n"
					+ "potential difference between two \n"
					+ "points in an electric circuit");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.bulbTag:
			popup = new InfoTooltip(getApplicationContext(),
					"The glass bulb that provides light \n"
					+ "by passing an electric current t\n"
					+ "hrough a filament");
			popup.show(tagView, AlignMode.BOTTOM);
			break;

		default:
			break;
		}

	}

	@Override
	protected int getResourcesId() {
		// TODO Auto-generated method stub
		return R.layout.diagram_play_circuit;
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
		intent.putExtra("SOURCE", "Circuit");
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
		intent.putExtra("SOURCE", "Circuit");
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
