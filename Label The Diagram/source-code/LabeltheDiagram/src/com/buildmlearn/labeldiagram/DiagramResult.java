package com.buildmlearn.labeldiagram;

import java.util.ArrayList;
import java.util.List;

import com.buildmlearn.labeldiagram.helper.TagContainerSingleton;
import com.buildmlearn.labeldiagram.resources.DiagramResultAdapter;
import com.buildmlearn.labeldiagram.resources.DiagramResultRawItem;
import com.example.labelthediagram.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class DiagramResult extends Activity implements OnClickListener {

	ArrayList<DiagramResultRawItem> reultList = new ArrayList<DiagramResultRawItem>();
	List<TextView> correctTagList = new ArrayList<TextView>();
	List<TextView> incorrectTagList = new ArrayList<TextView>();;
	TextView reulstTxt;
	TextView reulstheader1;
	TextView reulstheader2;
	String source;
	float rating;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.diagram_result);

		// Enabling ActionBar
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Result");
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.show();

		// Get the correctly and incorrectly labeled tag-list
		correctTagList = TagContainerSingleton.getInstance()
				.getCorrectLabelList();
		incorrectTagList = TagContainerSingleton.getInstance()
				.getIncorrectLabelList();

		// Capture intent values passed by DiagramPlay activity
		rating = getIntent().getExtras().getFloat("SCORE");
		source = getIntent().getExtras().getString("SOURCE");

		// Set the score on the ratingbar
		RatingBar scoreRater = (RatingBar) findViewById(R.id.resultBar);
		scoreRater.setRating((rating / 100.0f) * 5);

		Typeface tfThin = Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Thin.ttf");
		Typeface tfLight = Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Light.ttf");

		reulstTxt = (TextView) findViewById(R.id.reulstTxt);
		reulstTxt.setTypeface(tfThin);
		reulstheader1 = (TextView) findViewById(R.id.resultHeaderTxt);
		reulstheader1.setTypeface(tfThin);
		reulstheader2 = (TextView) findViewById(R.id.resultHeaderTxt2);
		reulstheader2.setTypeface(tfThin);

		// Set score on the score TextView
		TextView scoreTxt = (TextView) findViewById(R.id.resultScore);
		scoreTxt.setText((int) rating + "/100");

		// Referencing action buttons and register for onClick event
		Button tryAgain = (Button) findViewById(R.id.againButton);
		Button nextButton = (Button) findViewById(R.id.nextButton);

		tryAgain.setOnClickListener(this);
		nextButton.setOnClickListener(this);

		ListView list = (ListView) findViewById(R.id.mylistview);

		// Call for populating data for adapter
		fillDataModel();

		// Set DiagramResult adapter
		ArrayAdapter<DiagramResultRawItem> resultAdapter = new DiagramResultAdapter(
				this, R.layout.diagram_result_row_item, reultList);
		list.setAdapter(resultAdapter);
	}

	// Populate data for the use of Array adapter
	private void fillDataModel() {

		List<TextView> combinedList = new ArrayList<TextView>(correctTagList);
		combinedList.addAll(incorrectTagList);

		for (int i = 0; i < combinedList.size(); i++) {

			TextView labeledTag = combinedList.get(i);
			Log.i("Text", labeledTag.getText().toString());
			DiagramResultRawItem item = new DiagramResultRawItem(labeledTag,
					R.drawable.incorrect_btn);

			reultList.add(item);

		}

	}

	@Override
	public void onClick(View view) {

		switch (view.getId()) {
		case R.id.againButton:

			if(source.equals("DiagramPlayHumanEye")){
				Intent intent = new Intent(getApplicationContext(),
						DiagramPlayHumanEye.class);
				intentBuilder(intent);
			}else if(source.equals("DiagramPlayHumanHeart")){
				Intent intent = new Intent(getApplicationContext(),
						DiagramPlayHumanHeart.class);
				intentBuilder(intent);
			}else if(source.equals("DiagramPlayHumanEar")){
				Intent intent = new Intent(getApplicationContext(),
						DiagramPlayHumanEar.class);
				intentBuilder(intent);
			}else if(source.equals("DiagramPlayPlantCell")){
				Intent intent = new Intent(getApplicationContext(),
						DiagramPlayPlantCell.class);
				intentBuilder(intent);
			}else if(source.equals("DiagramPlayPlantFlower")){
				Intent intent = new Intent(getApplicationContext(),
						DiagramPlayPlantFlower.class);
				intentBuilder(intent);
			}else if(source.equals("DiagramPlayBacteria")){
				Intent intent = new Intent(getApplicationContext(),
						DiagramPlayBacteria.class);
				intentBuilder(intent);
			}else if(source.equals("DiagramPlayVirus")){
				Intent intent = new Intent(getApplicationContext(),
						DiagramPlayVirus.class);
				intentBuilder(intent);
			}else if(source.equals("DiagramPlayWaterCycle")){
				Intent intent = new Intent(getApplicationContext(),
						DiagramPlayWaterCycle.class);
				intentBuilder(intent);
			}else if(source.equals("DiagramPlayCarbonCycle")){
				Intent intent = new Intent(getApplicationContext(),
						DiagramPlayCarbonCycle.class);
				intentBuilder(intent);
			}else{
				Log.e("Unknown sorce exception", "Unknown source of intent");
			}
			
			

			break;

		case R.id.nextButton:

			Toast.makeText(getApplicationContext(),
					"Dispatching to Diagram Menu", 200).show();
			Intent intent = new Intent(getApplicationContext(),
					DiagramCategory.class);
			intentBuilder(intent);
			
			break;
		default:
			break;
		}

	}

	// Populate intent and start activity
	private void intentBuilder(Intent intent) {
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		startActivity(intent);
	}

}
