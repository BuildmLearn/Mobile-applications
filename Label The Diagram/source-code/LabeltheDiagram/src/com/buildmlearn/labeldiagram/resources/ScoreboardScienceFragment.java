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

public class ScoreboardScienceFragment extends Fragment {

	private Typeface tfThin;
	private List<ScoreboardRawItem> sciencerawItems;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		tfThin = Typeface.createFromAsset(getActivity().getAssets(),
				"fonts/Roboto-Thin.ttf");

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		final View view = inflater.inflate(R.layout.scoreboard_page, container,
				false);
		TextView scienceTxt = (TextView) view.findViewById(R.id.subjectTxt);
		ListView scienceList = (ListView) view
				.findViewById(R.id.diagramListItemsView);

		sciencerawItems = new ArrayList<ScoreboardRawItem>();
		scienceTxt.setText("Science");
		scienceTxt.setTypeface(tfThin);

		ArrayAdapter<ScoreboardRawItem> scienceListAdapter = new ScoreboardAdapter(
				getActivity(), R.layout.scoreboard_row_view, sciencerawItems);
		scienceList.setAdapter(scienceListAdapter);

		fillDataModel();

		return view;
	}

	// Fill data for ListViews
	private void fillDataModel() {
		// TODO Extract lists to database or separate class
		String scienceListItem[] = new String[] { "Solar System",
				"Star Patterns" };

		for (int i = 0; i < scienceListItem.length; i++) {
			ScoreboardRawItem item = new ScoreboardRawItem(scienceListItem[i],
					R.drawable.view_btn);
			sciencerawItems.add(item);
		}

	}

}
