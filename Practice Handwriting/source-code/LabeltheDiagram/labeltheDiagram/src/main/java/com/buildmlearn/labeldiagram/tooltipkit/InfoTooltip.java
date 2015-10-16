package com.buildmlearn.labeldiagram.tooltipkit;

import com.example.labelthediagram.R;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;


public class InfoTooltip extends CustomTooltip implements OnClickListener {

	LinearLayout view_main;
	TextView tv_label;
	String message;

	private static final String TEXT_COLOR = "#009FE3";

	public InfoTooltip(Context context, String message) {
		super(context);
		this.message = message;
		initLayout();
	}

	private void initLayout() {
		view_main = new LinearLayout(mContext);
		tv_label = new TextView(mContext);
		tv_label.setText(message);
		tv_label.setGravity(Gravity.CENTER);
		view_main.setBackgroundResource(R.drawable.tooltip_background);

		LayoutParams tvParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

		view_main.setOrientation(LinearLayout.VERTICAL);
		view_main.addView(tv_label, tvParams);
		
		addView(view_main);

	}

	@Override
	public void onClick(View v) {
		
	}
}
