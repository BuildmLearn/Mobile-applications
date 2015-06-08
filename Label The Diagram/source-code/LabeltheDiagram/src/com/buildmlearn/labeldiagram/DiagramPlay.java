package com.buildmlearn.labeldiagram;

import com.example.labelthediagram.R;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DiagramPlay extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.diagram_play);
		
		// Score board textViews
		TextView completeTxt = (TextView) findViewById(R.id.complatedTxt);
		TextView compeleteRatio = (TextView) findViewById(R.id.complete_ratio);
		TextView scoreTxt = (TextView) findViewById(R.id.scoreTxt);
		TextView score = (TextView) findViewById(R.id.score);
		
		Typeface tfThin = Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Thin.ttf");
		Typeface tfLight = Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Light.ttf");
		
		// Setting up font face to Roboto Light/Thin
		completeTxt.setTypeface(tfThin);
		scoreTxt.setTypeface(tfThin);
		compeleteRatio.setTypeface(tfThin);
		score.setTypeface(tfThin);
		
		// Placeholder imageViews		
		ImageView irisView = (ImageView) findViewById(R.id.irisBlb);
		ImageView pupilView = (ImageView) findViewById(R.id.pupilBlb);
		ImageView lensView = (ImageView) findViewById(R.id.lensBlb);
		ImageView corneaView = (ImageView) findViewById(R.id.corneaBlb);
		ImageView vitreousView = (ImageView) findViewById(R.id.vitreousBlb);
		ImageView ciliaryView = (ImageView) findViewById(R.id.ciliaryBodyBlb);
		ImageView nerveView = (ImageView) findViewById(R.id.opticNerveBlb);
		ImageView blindspotView = (ImageView) findViewById(R.id.blindSpotBlb);
		ImageView foveaView = (ImageView) findViewById(R.id.foveaBlb);
		ImageView retinaView = (ImageView) findViewById(R.id.retinaBlb);
		
		
		// Draggable Tags imageViews
		TextView irisTag =(TextView) findViewById(R.id.irisTag);
		TextView pupilTag =(TextView) findViewById(R.id.pupilTag);
		TextView lensTag =(TextView) findViewById(R.id.lensTag);
		TextView corneaTag =(TextView) findViewById(R.id.corneaTag);
		TextView vitreousTag =(TextView) findViewById(R.id.vitreousTag);
		TextView ciliaryTag =(TextView) findViewById(R.id.ciliaryTag);
		TextView nerveTag =(TextView) findViewById(R.id.nerveTag);
		TextView blindspotTag =(TextView) findViewById(R.id.opticdiskTag);
		TextView foveaTag =(TextView) findViewById(R.id.foveaTag);
		TextView retinaTag =(TextView) findViewById(R.id.retinaTag);
		
		
		
		
		
		
	}

}
