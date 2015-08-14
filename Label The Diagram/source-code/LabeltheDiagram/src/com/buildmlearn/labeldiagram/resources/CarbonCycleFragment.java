package com.buildmlearn.labeldiagram.resources;

import com.buildmlearn.labeldiagram.DiagramPlayCarbonCycle;
import com.example.labelthediagram.R;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CarbonCycleFragment extends Fragment {

	private Typeface tfThin;
	private float score;

	// newInstance constructor for creating fragment with arguments
	public static Fragment getInstance(int id) {

		CarbonCycleFragment frag = new CarbonCycleFragment();
		return frag;
	}

	// Store instance variables based on arguments passed
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		tfThin = Typeface.createFromAsset(getActivity().getAssets(),
				"fonts/Roboto-Thin.ttf");

		if (getArguments() != null) {
			score = getArguments().getFloat("SCORE_SAVED");
		}

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		final View view = inflater.inflate(R.layout.carbon_cycle_view,
				container, false);

		TextView diagramTxt = (TextView) view.findViewById(R.id.txt_diagram);
		TextView savedScoreTxt = (TextView) view.findViewById(R.id.score_saved);

		Button startBtn = (Button) view.findViewById(R.id.go_diagram_btn);

		diagramTxt.setTypeface(tfThin);
		startBtn.setTypeface(tfThin);
		savedScoreTxt.setText((int)score + "% Sucess");

		startBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent carbonIntent = new Intent(getActivity(),
						DiagramPlayCarbonCycle.class);
				carbonIntent.putExtra("SOURCE", "Fragment");
				carbonIntent.putExtra("TRY_CYCLE", 0);
				carbonIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				carbonIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				carbonIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				startActivity(carbonIntent);

			}
		});

		return view;
	}

}
