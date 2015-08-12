package com.buildmlearn.labeldiagram;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.buildmlearn.labeldiagram.badges.BadgePopUpWindow;
import com.buildmlearn.labeldiagram.tooltipkit.InfoTooltip;
import com.buildmlearn.labeldiagram.tooltipkit.CustomTooltip.AlignMode;
import com.example.labelthediagram.R;

public class DiagramPlayPlantFlower extends DiagramPlayBase {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(getResourcesId());

		actionBar.setTitle("Plant Flower");
		setDiagramName("PlantFlower");
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
		ImageView antherView = (ImageView) findViewById(R.id.antherBlb);
		ImageView petalView = (ImageView) findViewById(R.id.petalBlb);
		ImageView receptacleView = (ImageView) findViewById(R.id.receptacleBlb);
		ImageView pedicelView = (ImageView) findViewById(R.id.pedicelBlb);
		ImageView stigmaView = (ImageView) findViewById(R.id.stigmaBlb);
		ImageView styleView = (ImageView) findViewById(R.id.styleBlb);
		ImageView overyView = (ImageView) findViewById(R.id.overyBlb);
		ImageView filamentView = (ImageView) findViewById(R.id.filamentBlb);
		ImageView sepalView = (ImageView) findViewById(R.id.sepalBlb);
		ImageView ovuleView = (ImageView) findViewById(R.id.ovuleBlb);

		// Draggable Tags TextViews
		TextView antherTag = (TextView) findViewById(R.id.antherTag);
		TextView petalTag = (TextView) findViewById(R.id.petalTag);
		TextView receptacleTag = (TextView) findViewById(R.id.receptacleTag);
		TextView pedicelTag = (TextView) findViewById(R.id.pedicelTag);
		TextView stigmaTag = (TextView) findViewById(R.id.stigmaTag);
		TextView styleTag = (TextView) findViewById(R.id.styleTag);
		TextView overyTag = (TextView) findViewById(R.id.overyTag);
		TextView filamentTag = (TextView) findViewById(R.id.filamentTag);
		TextView sepalTag = (TextView) findViewById(R.id.sepalTag);
		TextView ovuleTag = (TextView) findViewById(R.id.ovuleTag);

		// Register draggable views to receive drag events
		antherView.setOnDragListener(this);
		petalView.setOnDragListener(this);
		receptacleView.setOnDragListener(this);
		pedicelView.setOnDragListener(this);
		stigmaView.setOnDragListener(this);
		styleView.setOnDragListener(this);
		overyView.setOnDragListener(this);
		filamentView.setOnDragListener(this);
		sepalView.setOnDragListener(this);
		ovuleView.setOnDragListener(this);

		// Register place holders to receive onLongclick events
		antherTag.setOnLongClickListener(this);
		petalTag.setOnLongClickListener(this);
		receptacleTag.setOnLongClickListener(this);
		pedicelTag.setOnLongClickListener(this);
		stigmaTag.setOnLongClickListener(this);
		styleTag.setOnLongClickListener(this);
		overyTag.setOnLongClickListener(this);
		filamentTag.setOnLongClickListener(this);
		sepalTag.setOnLongClickListener(this);
		ovuleTag.setOnLongClickListener(this);

		// Register place holders to receive onLongclick events
		antherTag.setOnClickListener(this);
		petalTag.setOnClickListener(this);
		receptacleTag.setOnClickListener(this);
		pedicelTag.setOnClickListener(this);
		stigmaTag.setOnClickListener(this);
		styleTag.setOnClickListener(this);
		overyTag.setOnClickListener(this);
		filamentTag.setOnClickListener(this);
		sepalTag.setOnClickListener(this);
		ovuleTag.setOnClickListener(this);

		placeHolderlist = container.diagramCaller("PlantFlower");
		tagPlaceHolderMap = tagPlaceholdermapper.diagramMapper("PlantFlower");
		incompleteTagList = tagPlaceholdermapper.diagramMapper("PlantFlower");
		tagListSize = tagPlaceHolderMap.size();
		
		openDB();

	}

	/*@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasFocus);
		// Here you can get the size!

		RelativeLayout scorePos = (RelativeLayout) findViewById(R.id.scoreTab);
		ImageView holderPos = (ImageView) findViewById(R.id.tagHolder);
		RelativeLayout container = (RelativeLayout) findViewById(R.id.imageContainer);
		
		
		container.getLayoutParams().height= holderPos.getTop()-scorePos.getBottom();

		int conTop = scorePos.getTop();
		int conBot = scorePos.getBottom();

		int mainTop = holderPos.getTop();
		int mainBot = holderPos.getBottom();
		addition = (conTop-mainTop)+(mainBot-conBot);

		Log.i("Height", mainTop + " " + conTop + "");
	}*/

	@Override
	public void onClick(View tagView) {
		// TODO Extract messages to separate container
		switch (tagView.getId()) {
		case R.id.antherTag:
			InfoTooltip popup = new InfoTooltip(getApplicationContext(),
					" Produces male gametes - pollen ");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.petalTag:
			InfoTooltip popup1 = new InfoTooltip(getApplicationContext(),
					"Brightly coloured, attract insects");
			popup1.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.receptacleTag:
			InfoTooltip popup2 = new InfoTooltip(getApplicationContext(),
					" Base of the flower ");
			popup2.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.pedicelTag:
			InfoTooltip popup3 = new InfoTooltip(getApplicationContext(),
					"Flower stalk of an individual \nflower in an inflorescence ");
			popup3.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.stigmaTag:
			InfoTooltip popup4 = new InfoTooltip(getApplicationContext(),
					"Sticky portion at the top of the style\n"
							+ " where pollen grains usually land ");
			popup4.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.styleTag:
			InfoTooltip popup5 = new InfoTooltip(
					getApplicationContext(),
					"The narrow elongated part of the pistil\n"
							+ " between the ovary and the stigma,\n grows pollen tube ");
			popup5.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.overyTag:
			InfoTooltip popup6 = new InfoTooltip(getApplicationContext(),
					"Contains ovules. After fetilisation,\n"
							+ " the ovary swells to produce fruit");
			popup6.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.filamentTag:
			InfoTooltip popup7 = new InfoTooltip(getApplicationContext(),
					"Supports anther to make it accessible to insects");
			popup7.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.sepalTag:
			InfoTooltip popup8 = new InfoTooltip(getApplicationContext(),
					"External covering of flower bud");
			popup8.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.ovuleTag:
			InfoTooltip popup9 = new InfoTooltip(getApplicationContext(),
					"In seed plants, the female reproductive\n"
							+ " part that produces the gamete - egg");
			popup9.show(tagView, AlignMode.BOTTOM);
			break;
		default:
			break;
		}
	}

	@Override
	protected int getResourcesId() {
		// TODO Auto-generated method stub
		return R.layout.diagram_play_plantflower;
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
		intent.putExtra("SOURCE", "PlantFlower");
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
		intent.putExtra("SOURCE", "PlantFlower");
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
