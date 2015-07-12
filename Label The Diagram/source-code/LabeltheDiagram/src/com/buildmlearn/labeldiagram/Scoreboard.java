package com.buildmlearn.labeldiagram;

import java.util.ArrayList;
import java.util.List;

import com.buildmlearn.labeldiagram.resources.ScoreboardAdapter;
import com.buildmlearn.labeldiagram.resources.ScoreboardRawItem;
import com.example.labelthediagram.R;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Scoreboard extends Activity {

	List<ScoreboardRawItem> biorawItems;
	List<ScoreboardRawItem> physicsrawItems;
	List<ScoreboardRawItem> sciencerawItems;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.scoreboard_biology);

		Typeface tfThin = Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Thin.ttf");

		TextView biologyTxt = (TextView) findViewById(R.id.biologyTxt);
		TextView physicsTxt = (TextView) findViewById(R.id.physicsTxt);
		TextView scienceTxt = (TextView) findViewById(R.id.scienceTxt);

		ListView bioList = (ListView) findViewById(R.id.bioListview);
		ListView physicsList = (ListView) findViewById(R.id.physicsListview);
		ListView scienceList = (ListView) findViewById(R.id.scienceListview);

		biorawItems = new ArrayList<ScoreboardRawItem>();
		physicsrawItems = new ArrayList<ScoreboardRawItem>();
		sciencerawItems = new ArrayList<ScoreboardRawItem>();

		ArrayAdapter<ScoreboardRawItem> bioListAdapter = new ScoreboardAdapter(
				this, R.layout.scoreboard_row_view, biorawItems);
		ArrayAdapter<ScoreboardRawItem> physicsListAdapter = new ScoreboardAdapter(
				this, R.layout.scoreboard_row_view, physicsrawItems);
		ArrayAdapter<ScoreboardRawItem> scienceListAdapter = new ScoreboardAdapter(
				this, R.layout.scoreboard_row_view, sciencerawItems);
		
		bioList.setAdapter(bioListAdapter);
		physicsList.setAdapter(physicsListAdapter);
		scienceList.setAdapter(scienceListAdapter);

		biologyTxt.setTypeface(tfThin);
		physicsTxt.setTypeface(tfThin);
		scienceTxt.setTypeface(tfThin);

		fillDataModel();
	}

	// Fill data for ListViews
	private void fillDataModel() {
		// TODO Extract lists to database or separate class
		String biolistItems[] = new String[] { "Human Eye", "Human Heart",
				"Human Ear", "Plant Cell", "Plant Flower", "Bacteria", "Virus",
				"Water Cycle", "Carbon Cycle" };

		String physicsListItem[] = new String[] { "Electromagnetic Spectrum",
				"Prism Refraction ", "Lens Refraction", "Electric Circuit",
				"Electric Motor", "Dry Cell" };

		String scienceListItem[] = new String[] { "Solar Panel",
				"Star Patterns" };

		for (int i = 0; i < biolistItems.length; i++) {
			ScoreboardRawItem item = new ScoreboardRawItem(biolistItems[i],
					R.drawable.view_btn);
			biorawItems.add(item);
		}

		for (int i = 0; i < physicsListItem.length; i++) {
			ScoreboardRawItem item = new ScoreboardRawItem(physicsListItem[i],
					R.drawable.view_btn);
			physicsrawItems.add(item);
		}

		for (int i = 0; i < scienceListItem.length; i++) {
			ScoreboardRawItem item = new ScoreboardRawItem(scienceListItem[i],
					R.drawable.view_btn);
			sciencerawItems.add(item);
		}
	}

}
