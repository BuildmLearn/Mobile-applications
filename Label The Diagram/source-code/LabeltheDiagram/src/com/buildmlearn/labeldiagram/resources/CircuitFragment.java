package com.buildmlearn.labeldiagram.resources;

import com.buildmlearn.labeldiagram.DiagramPlayCircuit;
import com.buildmlearn.labeldiagram.DiagramPlayMotor;
import com.buildmlearn.labeldiagram.DiagramPlayPrism;
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

public class CircuitFragment extends Fragment {

	private Typeface tfThin;
	private float score;
	private int gameScore;

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

		final View view = inflater.inflate(R.layout.circuit_view, container,
				false);

		TextView savedScoreTxt = (TextView) view.findViewById(R.id.score_saved);

		Button startBtn = (Button) view.findViewById(R.id.go_diagram_btn);

		startBtn.setTypeface(tfThin);
		savedScoreTxt.setText((int)((score / 90 ) * 100) + "% Sucess");

		startBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent circuitIntent = new Intent(getActivity(),
						DiagramPlayCircuit.class);
				circuitIntent.putExtra("SOURCE", "Fragment");
				circuitIntent.putExtra("TRY_CYCLE", 0);
				circuitIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				circuitIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				circuitIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				// getActivity().finish();
				startActivity(circuitIntent);

			}
		});

		return view;
	}

}
