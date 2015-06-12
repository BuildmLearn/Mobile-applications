package com.buildmlearn.labeldiagram;

import com.example.labelthediagram.R;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.RatingBar;
import android.widget.TextView;

public class DiagramResult extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.diagram_result);
		
		Typeface tfThin = Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Thin.ttf");
		Typeface tfLight = Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Light.ttf");
		
		TextView reulstTxt = (TextView) findViewById(R.id.reulstTxt);
		reulstTxt.setTypeface(tfThin);
		TextView reulstheader1 = (TextView) findViewById(R.id.resultHeaderTxt);
		reulstheader1.setTypeface(tfThin);
		TextView reulstheader2 = (TextView) findViewById(R.id.resultHeaderTxt2);
		reulstheader2.setTypeface(tfThin);
		
		final RatingBar minimumRating = (RatingBar)findViewById(R.id.resultBar);
	    minimumRating.setOnTouchListener(new OnTouchListener()
	    { 
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				float touchPositionX = event.getX();
	            float width = minimumRating.getWidth();
	            float starsf = (touchPositionX / width) * 5.0f;
	            int stars = (int)starsf + 1;
	            minimumRating.setRating(stars);
	            return true; 
			} 
	    });
		
	}

}
