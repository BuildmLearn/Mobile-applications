package com.buildmlearn.labeldiagram.tooltipkit;

import com.buildmlearn.labeldiagram.tooltipkit.CustomTooltip.AlignMode;
import com.example.labelthediagram.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PopupActivity extends Activity {

	Button button;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tooltip_test_view);
		
		button = (Button)findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				InfoTooltip popup = new InfoTooltip(getApplicationContext(), "Your message here.. Your message here..  Your message here..  Your message here.. ");
				popup.show(arg0, AlignMode.BOTTOM);
			}
		});
	}
	
}
