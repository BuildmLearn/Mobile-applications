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

public class DiagramPlayHumanHeart extends DiagramPlayBase {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);

		setContentView(getResourcesId());

		actionBar.setTitle("Human Heart");
		setDiagramName("HumanHeart");

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
		ImageView aortaView = (ImageView) findViewById(R.id.pul_arteryBulb);
		ImageView supVenaCavaView = (ImageView) findViewById(R.id.sup_vena_cavaBlb);
		ImageView infVenaCavaView = (ImageView) findViewById(R.id.inf_vena_cavaBlb);
		ImageView leftAtriumView = (ImageView) findViewById(R.id.left_atriumBlb);
		ImageView leftVentricleView = (ImageView) findViewById(R.id.left_ventricleBlb);
		ImageView rightAtriumView = (ImageView) findViewById(R.id.right_atriumBlb);
		ImageView rightVentricleView = (ImageView) findViewById(R.id.right_ventricleBlb);
		ImageView pulArteryView = (ImageView) findViewById(R.id.aortaBlb);
		ImageView pulVeinView = (ImageView) findViewById(R.id.pul_veinBlb);

		// Draggable Tags imageViews
		TextView aortaTag = (TextView) findViewById(R.id.aortaTag);
		TextView supVenaCavaTag = (TextView) findViewById(R.id.sup_vena_cavaTag);
		TextView infVenaCavaTag = (TextView) findViewById(R.id.inf_vena_cavaTag);
		TextView leftAtriumTag = (TextView) findViewById(R.id.left_atriumTag);
		TextView leftVentricleTag = (TextView) findViewById(R.id.left_ventricleTag);
		TextView rightAtriumTag = (TextView) findViewById(R.id.right_atriunTag);
		TextView rightVentricleTag = (TextView) findViewById(R.id.right_ventricleTag);
		TextView pulArteryTag = (TextView) findViewById(R.id.pul_arteryTag);
		TextView pulVeinTag = (TextView) findViewById(R.id.pul_veinTag);

		// Register draggable views to receive drag events
		aortaView.setOnDragListener(this);
		supVenaCavaView.setOnDragListener(this);
		infVenaCavaView.setOnDragListener(this);
		leftAtriumView.setOnDragListener(this);
		leftVentricleView.setOnDragListener(this);
		rightAtriumView.setOnDragListener(this);
		rightVentricleView.setOnDragListener(this);
		pulArteryView.setOnDragListener(this);
		pulVeinView.setOnDragListener(this);

		// Register place holders to receive onLongclick events
		aortaTag.setOnLongClickListener(this);
		supVenaCavaTag.setOnLongClickListener(this);
		infVenaCavaTag.setOnLongClickListener(this);
		leftAtriumTag.setOnLongClickListener(this);
		leftVentricleTag.setOnLongClickListener(this);
		rightAtriumTag.setOnLongClickListener(this);
		rightVentricleTag.setOnLongClickListener(this);
		pulArteryTag.setOnLongClickListener(this);
		pulVeinTag.setOnLongClickListener(this);

		// Register place holders to receive onClick events
		aortaTag.setOnClickListener(this);
		supVenaCavaTag.setOnClickListener(this);
		infVenaCavaTag.setOnClickListener(this);
		leftAtriumTag.setOnClickListener(this);
		leftVentricleTag.setOnClickListener(this);
		rightAtriumTag.setOnClickListener(this);
		rightVentricleTag.setOnClickListener(this);
		pulArteryTag.setOnClickListener(this);
		pulVeinTag.setOnClickListener(this);

		placeHolderlist = container.diagramCaller("HumanHeart");
		tagPlaceHolderMap = tagPlaceholdermapper.diagramMapper("HumanHeart");
		incompleteTagList = tagPlaceholdermapper.diagramMapper("HumanHeart");
		tagListSize = tagPlaceHolderMap.size();

	}

	@Override
	public void onClick(View tagView) {
		// TODO Extract messages to separate container
		switch (tagView.getId()) {
		case R.id.aortaTag:
			InfoTooltip popup = new InfoTooltip(getApplicationContext(),
					" Aorta description ");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.sup_vena_cavaTag:
			InfoTooltip popup1 = new InfoTooltip(getApplicationContext(),
					"sup_vena_cavaTag description.");
			popup1.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.inf_vena_cavaTag:
			InfoTooltip popup2 = new InfoTooltip(
					getApplicationContext(),
					"inf_vena_cavaTag description ");
			popup2.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.left_atriumTag:
			InfoTooltip popup3 = new InfoTooltip(getApplicationContext(),
					"left_atriumTag description ");
			popup3.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.left_ventricleTag:
			InfoTooltip popup4 = new InfoTooltip(getApplicationContext(),
					"left_ventricleTag description ");
			popup4.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.right_atriunTag:
			InfoTooltip popup5 = new InfoTooltip(
					getApplicationContext(),
					" right_atriunTag description ");
			popup5.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.right_ventricleTag:
			InfoTooltip popup6 = new InfoTooltip(getApplicationContext(),
					"right_ventricleTag description  ");
			popup6.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.pul_arteryTag:
			InfoTooltip popup7 = new InfoTooltip(getApplicationContext(),
					"pul_arteryTag description ");
			popup7.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.pul_veinTag:
			InfoTooltip popup8 = new InfoTooltip(getApplicationContext(),
					"pul_veinTag description ");
			popup8.show(tagView, AlignMode.BOTTOM);
			break;
		default:
			break;
		}
	}
	
	@Override
	protected int getResourcesId() {

		return R.layout.diagram_play_heart;
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
		intent.putExtra("SOURCE", "DiagramPlayHumanHeart");
		startActivity(intent);
	}
}
