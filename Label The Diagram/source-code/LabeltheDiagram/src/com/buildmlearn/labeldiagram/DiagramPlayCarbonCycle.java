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

public class DiagramPlayCarbonCycle extends DiagramPlayBase {

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		actionBar.setTitle("Carbon Cycle");
		setDiagramName("CarbonCycle");

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
		ImageView oceanUptakeView = (ImageView) findViewById(R.id.ocean_uptakeBlb);
		ImageView plantRespirationView = (ImageView) findViewById(R.id.plant_respirationBlb);
		ImageView animalRespirationView = (ImageView) findViewById(R.id.animal_respirationBlb);
		ImageView deadOrganismsView = (ImageView) findViewById(R.id.dead_organismsBlb);
		ImageView fossilsView = (ImageView) findViewById(R.id.fossilsBlb);
		ImageView sunlightView = (ImageView) findViewById(R.id.sunlightBlb);
		ImageView organicCarbonView = (ImageView) findViewById(R.id.organic_carbonBlb);
		ImageView vehicleEmissionsView = (ImageView) findViewById(R.id.vehicle_emissionsBlb);
		ImageView photosyntesisView = (ImageView) findViewById(R.id.photosynthesisBlb);
		
		// Draggable Tags imageViews
		TextView oceanUptakeTag = (TextView) findViewById(R.id.ocean_uptakeTag);
		TextView plantRespirationTag = (TextView) findViewById(R.id.plant_respirationTag);
		TextView animalRespirationTag = (TextView) findViewById(R.id.animal_respirationTag);
		TextView deadOrganismsTag = (TextView) findViewById(R.id.dead_organismsTag);
		TextView fossilsTag = (TextView) findViewById(R.id.fossilsTag);
		TextView sunlightTag = (TextView) findViewById(R.id.sunlightTag);
		TextView organicCarbonTag = (TextView) findViewById(R.id.organic_carbonTag);
		TextView vehicleEmissionsTag = (TextView) findViewById(R.id.vehicle_emissionsTag);
		TextView photosyntesisTag = (TextView) findViewById(R.id.photosynthesisTag);
		
		
		// Register draggable views to receive drag events
		oceanUptakeView.setOnDragListener(this);
		plantRespirationView.setOnDragListener(this);
		animalRespirationView.setOnDragListener(this);
		deadOrganismsView.setOnDragListener(this);
		fossilsView.setOnDragListener(this);
		sunlightView.setOnDragListener(this);
		organicCarbonView.setOnDragListener(this);
		vehicleEmissionsView.setOnDragListener(this);
		photosyntesisView.setOnDragListener(this);
		
		// Register place holders to receive onLongclick events
		oceanUptakeTag.setOnLongClickListener(this);
		plantRespirationTag.setOnLongClickListener(this);
		animalRespirationTag.setOnLongClickListener(this);
		deadOrganismsTag.setOnLongClickListener(this);
		fossilsTag.setOnLongClickListener(this);
		sunlightTag.setOnLongClickListener(this);
		organicCarbonTag.setOnLongClickListener(this);
		vehicleEmissionsTag.setOnLongClickListener(this);
		photosyntesisTag.setOnLongClickListener(this);
		
		
		// Register place holders to receive onClick events
		oceanUptakeTag.setOnClickListener(this);
		plantRespirationTag.setOnClickListener(this);
		animalRespirationTag.setOnClickListener(this);
		deadOrganismsTag.setOnClickListener(this);
		fossilsTag.setOnClickListener(this);
		sunlightTag.setOnClickListener(this);
		organicCarbonTag.setOnClickListener(this);
		vehicleEmissionsTag.setOnClickListener(this);
		photosyntesisTag.setOnClickListener(this);
		
		placeHolderlist = container.diagramCaller("CarbonCycle");
		tagPlaceHolderMap = tagPlaceholdermapper.diagramMapper("CarbonCycle");
		incompleteTagList = tagPlaceholdermapper.diagramMapper("CarbonCycle");
		tagListSize = tagPlaceHolderMap.size();
	}

	@Override
	public void onClick(View tagView) {
		// TODO Extract messages to a separate container
		switch (tagView.getId()) {
		case R.id.photosynthesisTag:
			InfoTooltip popup = new InfoTooltip(getApplicationContext(),
					"Carbon dioxide is absorbed by plants\n "
					+ "by producing their own food \n by photosynthesis");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.organic_carbonTag:
			InfoTooltip popup1 = new InfoTooltip(getApplicationContext(),
					"Passing the carbon compounds along the \n "
					+ "food chain since animals feed on plants");
			popup1.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.fossilsTag:
			InfoTooltip popup2 = new InfoTooltip(
					getApplicationContext(),
					"Plants and animals that die and are buried \n"
					+ "turn into fossil fuels made of carbon like \ncoal and oil");
			popup2.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.ocean_uptakeTag:
			InfoTooltip popup3 = new InfoTooltip(getApplicationContext(),
					"Living organisms in ocean absorb Carbon Dioxide \n"
					+ "to produce energy");
			popup3.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.sunlightTag:
			InfoTooltip popup4 = new InfoTooltip(getApplicationContext(),
					"Provide the energy to contain Carbon Dioxide\n"
					+ " in palnts in the process of photosynthesis");
			popup4.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.dead_organismsTag:
			InfoTooltip popup5 = new InfoTooltip(
					getApplicationContext(),
					"Dead organisms are decomposed in the ground\n"
					+ "and Carbon Dioxide contained within them \n is returned");
			popup5.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.animal_respirationTag:
			InfoTooltip popup6 = new InfoTooltip(getApplicationContext(),
					"Carbon enters the atmosphere as carbon dioxide \n"
					+ "from animal respiration (breathing)");
			popup6.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.vehicle_emissionsTag:
			InfoTooltip popup7 = new InfoTooltip(getApplicationContext(),
					"Carbon Dioxide is emitted to the atmosphere \n"
					+ "when fossil fuels are combusted");
			popup7.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.plant_respirationTag:
			InfoTooltip popup8 = new InfoTooltip(getApplicationContext(),
					"Carbon enters the atmosphere as carbon dioxide\n"
					+ " from plant respiration (breathing)");
			popup8.show(tagView, AlignMode.BOTTOM);
			break;
	
		default:
			break;
		}
	}
	
	@Override
	protected int getResourcesId() {
		
		return R.layout.diagram_play_carbon_cycle;
		
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
	protected void dispatch(float totalScore) {
		Intent intent = new Intent(getBaseContext(), DiagramResult.class);
		intent.putExtra("SCORE", totalScore);
		intent.putExtra("SOURCE", "CarbonCycle");
		startActivity(intent);
	}


}
