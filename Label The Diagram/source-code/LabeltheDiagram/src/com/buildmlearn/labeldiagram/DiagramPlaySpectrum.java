package com.buildmlearn.labeldiagram;

import com.example.labelthediagram.R;

import android.os.Bundle;
import android.widget.TextView;

public class DiagramPlaySpectrum extends DiagramPlayBase{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(getResourcesId());

		actionBar.setTitle("Spectrum");
		setDiagramName("Spectrum");
		
		// Score board textViews
		compeleteRatio = (TextView) findViewById(R.id.complete_ratio);
		score = (TextView) findViewById(R.id.score);
		
	}	
	
	
	@Override
	protected void dispatch(float totalScore, int gameScore) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected int getResourcesId() {
		// TODO Auto-generated method stub
		return R.layout.diagram_play_spectrum;
	}

}
