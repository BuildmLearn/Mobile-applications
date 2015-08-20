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

		openDB();

	}

	@Override
	public void onClick(View tagView) {
		super.onClick(tagView);

		InfoTooltip popup;

		switch (tagView.getId()) {
		case R.id.amTag:
			popup = new InfoTooltip(
					getApplicationContext(),
					"The Amplitude Modulation are ways of \n "
							+ "broadcasting radio signals, which ranges\n "
							+ "from 535 to 1605 kHz");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.fmTag:
			popup = new InfoTooltip(getApplicationContext(),
					"The Frequency Modulation radio ranges from 88 to 108 MHz");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.microwaveTag:
			popup = new InfoTooltip(getApplicationContext(),
					"Having a wavelength (in the range 0.001–0.3 m)"
							+ "\n shorter than that of a normal radio wave"
							+ "\n but longer than those of infrared radiation");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.visibleLightTag:
			popup = new InfoTooltip(getApplicationContext(),
					"The wavelengths that are visible to most human eyes");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.radio_wavesTag:
			popup = new InfoTooltip(getApplicationContext(),
					"EM radiation which has frequencies from \n"
					+ "300 GHz to 3 kHz. Two frequency ranges,\n AM and FM");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.irTag:
			popup = new InfoTooltip(
					getApplicationContext(),
					"Having a wavelength just greater than "
							+ "that of the red end of the visible light spectrum");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.uvTag:
			popup = new InfoTooltip(getApplicationContext(),
					"Having a wavelength shorter than that of the"
							+ "\n violet end of the visible spectrum "
							+ "\n but longer than that of X-rays");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.xrayTag:
			popup = new InfoTooltip(
					getApplicationContext(),
					"Have a wavelength ranging from 0.01 to 10 nm, \n"
							+ "corresponding to frequencies in the range \n"
							+ "3×10^16 Hz to 3×10^19 Hz");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.gammaTag:
			popup = new InfoTooltip(
					getApplicationContext(),
					"Have the smallest wavelengths and the most energy \n"
					+ "of any wave in the EM spectrum");
			popup.show(tagView, AlignMode.BOTTOM);
			break;

		default:
			break;
		}
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
		intent.putExtra("SOURCE", "Spectrum");
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
