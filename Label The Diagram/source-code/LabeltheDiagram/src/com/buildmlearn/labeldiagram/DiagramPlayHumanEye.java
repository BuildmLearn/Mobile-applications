package com.buildmlearn.labeldiagram;

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

public class DiagramPlayHumanEye extends DiagramPlayBase {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(getResourcesId());

		actionBar.setTitle("Human Eye");
		setDiagramName("HumanEye");

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
		ImageView irisView = (ImageView) findViewById(R.id.irisBlb);
		ImageView pupilView = (ImageView) findViewById(R.id.pupilBlb);
		ImageView lensView = (ImageView) findViewById(R.id.lensBlb);
		ImageView corneaView = (ImageView) findViewById(R.id.corneaBlb);
		ImageView vitreousView = (ImageView) findViewById(R.id.vitreousBlb);
		ImageView ciliaryView = (ImageView) findViewById(R.id.ciliaryBodyBlb);
		ImageView nerveView = (ImageView) findViewById(R.id.opticNerveBlb);
		ImageView blindspotView = (ImageView) findViewById(R.id.blindSpotBlb);
		ImageView foveaView = (ImageView) findViewById(R.id.foveaBlb);
		ImageView retinaView = (ImageView) findViewById(R.id.retinaBlb);

		// Draggable Tags imageViews
		TextView irisTag = (TextView) findViewById(R.id.irisTag);
		TextView pupilTag = (TextView) findViewById(R.id.pupilTag);
		TextView lensTag = (TextView) findViewById(R.id.lensTag);
		TextView corneaTag = (TextView) findViewById(R.id.corneaTag);
		TextView vitreousTag = (TextView) findViewById(R.id.vitreousTag);
		TextView ciliaryTag = (TextView) findViewById(R.id.ciliaryTag);
		TextView nerveTag = (TextView) findViewById(R.id.nerveTag);
		TextView blindspotTag = (TextView) findViewById(R.id.opticdiskTag);
		TextView foveaTag = (TextView) findViewById(R.id.foveaTag);
		TextView retinaTag = (TextView) findViewById(R.id.retinaTag);

		// Register draggable views to receive drag events
		irisView.setOnDragListener(this);
		pupilView.setOnDragListener(this);
		lensView.setOnDragListener(this);
		corneaView.setOnDragListener(this);
		vitreousView.setOnDragListener(this);
		ciliaryView.setOnDragListener(this);
		nerveView.setOnDragListener(this);
		blindspotView.setOnDragListener(this);
		foveaView.setOnDragListener(this);
		retinaView.setOnDragListener(this);

		// Register place holders to receive onLongclick events
		irisTag.setOnLongClickListener(this);
		pupilTag.setOnLongClickListener(this);
		lensTag.setOnLongClickListener(this);
		corneaTag.setOnLongClickListener(this);
		vitreousTag.setOnLongClickListener(this);
		ciliaryTag.setOnLongClickListener(this);
		nerveTag.setOnLongClickListener(this);
		blindspotTag.setOnLongClickListener(this);
		foveaTag.setOnLongClickListener(this);
		retinaTag.setOnLongClickListener(this);

		// Register place holders to receive onClick events
		irisTag.setOnClickListener(this);
		pupilTag.setOnClickListener(this);
		lensTag.setOnClickListener(this);
		corneaTag.setOnClickListener(this);
		vitreousTag.setOnClickListener(this);
		ciliaryTag.setOnClickListener(this);
		nerveTag.setOnClickListener(this);
		blindspotTag.setOnClickListener(this);
		foveaTag.setOnClickListener(this);
		retinaTag.setOnClickListener(this);

		placeHolderlist = container.diagramCaller("HumanEye");
		tagPlaceHolderMap = tagPlaceholdermapper.diagramMapper("HumanEye");
		incompleteTagList = tagPlaceholdermapper.diagramMapper("HumanEye");
		tagListSize = tagPlaceHolderMap.size();

	}

	/*
	 * private int getRsourceId() { // TODO Auto-generated method stub return
	 * null; }
	 */

	@Override
	public void onClick(View tagView) {
		// TODO Extract messages to separate container
		switch (tagView.getId()) {
		case R.id.irisTag:
			InfoTooltip popup = new InfoTooltip(getApplicationContext(),
					"Iris has spesialized muscles that \n changes the size of the pupil ");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.pupilTag:
			InfoTooltip popup1 = new InfoTooltip(getApplicationContext(),
					"A round opening of the center of the iris.");
			popup1.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.lensTag:
			InfoTooltip popup2 = new InfoTooltip(
					getApplicationContext(),
					"Attached to muscles which contract or\n relax inorder to change the lens shape. ");
			popup2.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.corneaTag:
			InfoTooltip popup3 = new InfoTooltip(getApplicationContext(),
					"Light first passes through cornea \n Let light comes into the eye ");
			popup3.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.vitreousTag:
			InfoTooltip popup4 = new InfoTooltip(getApplicationContext(),
					"back portion of the eye that is filled\n with a clear, jelly-like substance ");
			popup4.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.ciliaryTag:
			InfoTooltip popup5 = new InfoTooltip(
					getApplicationContext(),
					" part of the eye that includes the ciliary muscle,\n which controls the shape of the lens ");
			popup5.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.nerveTag:
			InfoTooltip popup6 = new InfoTooltip(getApplicationContext(),
					"nerve that transmits visual information \n from the retina to the brain.  ");
			popup6.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.opticdiskTag:
			InfoTooltip popup7 = new InfoTooltip(getApplicationContext(),
					"The most sensitive place of the eye to light rays ");
			popup7.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.foveaTag:
			InfoTooltip popup8 = new InfoTooltip(getApplicationContext(),
					"The place of the eye that not sensitive to light ");
			popup8.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.retinaTag:
			InfoTooltip popup9 = new InfoTooltip(
					getApplicationContext(),
					"The light finally reaches the retina where \n rod and cone cells are stimulated \n converting the light to electrical impulese.");
			popup9.show(tagView, AlignMode.BOTTOM);
			break;
		default:
			break;
		}
	}

	@Override
	protected int getResourcesId() {

		return R.layout.diagram_play;
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
	protected void dispatch(float totalScore) {
		Intent intent = new Intent(getBaseContext(), DiagramResult.class);
		intent.putExtra("SCORE", totalScore);
		intent.putExtra("SOURCE", "DiagramPlayHumanEye");
		startActivity(intent);
	}
	
}
