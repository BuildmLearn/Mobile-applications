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

public class ScoreboardPhysicsFragment extends Fragment {

	private Typeface tfThin;
	private List<ScoreboardRawItem> physicsrawItems;

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
		TextView physicsTxt = (TextView) view.findViewById(R.id.subjectTxt);
		ListView physicsList = (ListView) view
				.findViewById(R.id.diagramListItemsView);

		physicsrawItems = new ArrayList<ScoreboardRawItem>();
		physicsTxt.setText("Physics");
		physicsTxt.setTypeface(tfThin);

		ArrayAdapter<ScoreboardRawItem> physicsListAdapter = new ScoreboardAdapter(
				getActivity(), R.layout.scoreboard_row_view, physicsrawItems);
		physicsList.setAdapter(physicsListAdapter);

		fillDataModel();

		return view;
	}

	// Fill data for ListViews
	private void fillDataModel() {
		// TODO Extract lists to database or separate class
		String physicsListItem[] = new String[] { "Light Spectrum",
				"Prism Refraction ", "Lens Refraction", "Electric Circuit",
				"Electric Motor", "Dry Cell" };
		
		for (int i = 0; i < physicsListItem.length; i++) {
			ScoreboardRawItem item = new ScoreboardRawItem(physicsListItem[i],
					R.drawable.view_btn);
			physicsrawItems.add(item);
		}

	}

}
