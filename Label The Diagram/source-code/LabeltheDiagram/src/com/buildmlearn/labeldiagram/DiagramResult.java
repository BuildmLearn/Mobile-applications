package com.buildmlearn.labeldiagram;

import java.util.ArrayList;
import java.util.List;

import com.buildmlearn.labeldiagram.helper.TagContainerSingleton;
import com.buildmlearn.labeldiagram.resources.DiagramResultAdapter;
import com.buildmlearn.labeldiagram.resources.DiagramResultRawItem;
import com.example.labelthediagram.R;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

public class DiagramResult extends Activity {

	ArrayList<DiagramResultRawItem> reultList = new ArrayList<DiagramResultRawItem>();
	List<TextView> correctTagList = new ArrayList<TextView>();
	List<TextView> incorrectTagList = new ArrayList<TextView>();;
	TextView reulstTxt;
	TextView reulstheader1;
	TextView reulstheader2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.diagram_result);

		// Get the correctly and incorrectly labeled tag-list
		correctTagList = TagContainerSingleton.getInstance()
				.getCorrectLabelList();
		incorrectTagList = TagContainerSingleton.getInstance()
				.getIncorrectLabelList();

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

		final RatingBar minimumRating = (RatingBar) findViewById(R.id.resultBar);
		ListView list = (ListView) findViewById(R.id.mylistview);

		createModel();

		ArrayAdapter<DiagramResultRawItem> categryAdapter = new DiagramResultAdapter(
				this, R.layout.diagram_result_row_item, reultList);
		list.setAdapter(categryAdapter);
	}

	private void createModel() {

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

}
