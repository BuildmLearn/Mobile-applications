package com.buildmlearn.labeldiagram.resources;

import com.buildmlearn.labeldiagram.DiagramPlaySolarSystem;
import com.example.labelthediagram.R;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class SolarSystemFragment extends Fragment {

	private static final int GAME_SCORE = 90;
	private Typeface tfThin;
	private float score;

	// Store instance variables based on arguments passed
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		tfThin = Typeface.createFromAsset(getActivity().getAssets(),
				"fonts/Roboto-Thin.ttf");

		score = getArguments().getFloat("SCORE_SAVED");

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		final View view = inflater.inflate(R.layout.solar_system_view, container,
				false);

		TextView savedScoreTxt = (TextView) view.findViewById(R.id.score_saved);

		Button startBtn = (Button) view.findViewById(R.id.go_diagram_btn);

		startBtn.setTypeface(tfThin);
		savedScoreTxt.setText((int)((score / GAME_SCORE ) * 100) + "% Sucess");

		startBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent spectrumIntent = new Intent(getActivity(),
						DiagramPlaySolarSystem.class);
				spectrumIntent.putExtra("SOURCE", "Fragment");
				spectrumIntent.putExtra("TRY_CYCLE", 0);
				spectrumIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				spectrumIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				spectrumIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				// getActivity().finish();
				startActivity(spectrumIntent);

			}
		});

		return view;
	}

}
