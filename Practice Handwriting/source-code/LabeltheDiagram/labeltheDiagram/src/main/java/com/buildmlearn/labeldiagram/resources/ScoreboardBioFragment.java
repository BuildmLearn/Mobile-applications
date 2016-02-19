package com.buildmlearn.labeldiagram.resources;

import java.util.ArrayList;
import java.util.List;

import com.example.labelthediagram.R;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ScoreboardBioFragment extends Fragment {

	private Typeface tfThin;
	private List<ScoreboardRawItem> biorawItems;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		tfThin = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Thin.ttf");

	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		final View view = inflater.inflate(R.layout.scoreboard_page, container,
				false);
		TextView biologyTxt = (TextView) view.findViewById(R.id.subjectTxt);
		ListView bioList = (ListView) view.findViewById(R.id.diagramListItemsView);
		
		biorawItems = new ArrayList<ScoreboardRawItem>();
		biologyTxt.setTypeface(tfThin);
		
		ArrayAdapter<ScoreboardRawItem> bioListAdapter = new ScoreboardAdapter(
				getActivity(), R.layout.scoreboard_row_view, biorawItems);
		bioList.setAdapter(bioListAdapter);
		
		fillDataModel();
		
		return view;
	}
	
	// Fill data for ListViews
		private void fillDataModel() {
			// TODO Extract lists to database or separate class
			String biolistItems[] = new String[] { "Human Eye", "Human Heart",
					"Human Ear", "Plant Cell", "Plant Flower", "Bacteria", "Virus",
					 };

			for (int i = 0; i < biolistItems.length; i++) {
				ScoreboardRawItem item = new ScoreboardRawItem(biolistItems[i],
						R.drawable.view_btn);
				biorawItems.add(item);
			}

		}

}
