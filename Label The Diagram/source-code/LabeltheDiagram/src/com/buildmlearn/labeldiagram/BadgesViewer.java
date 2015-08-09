package com.buildmlearn.labeldiagram;

import com.buildmlearn.labeldiagram.helper.HelperClass;
import com.buildmlearn.labeldiagram.resources.BadgesAll;
import com.buildmlearn.labeldiagram.resources.ScoreboardBestScoreFragment;
import com.buildmlearn.labeldiagram.resources.ScoreboardScoreFragment;
import com.example.labelthediagram.R;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class BadgesViewer extends FragmentActivity {

	private FragmentTabHost mTabHost;
	FragmentManager fm = getSupportFragmentManager();
	Typeface tfThin;
	String source;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.scoreboard_result);

		HelperClass.setActionBar("Badges", this);

		// source = getIntent().getExtras().getString("SOURCE");

		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(),
				android.R.id.tabcontent);

		mTabHost.addTab(
				mTabHost.newTabSpec("tab1").setIndicator(
						getTabIndicator(mTabHost.getContext(), "ALL BADGES")),
				BadgesAll.class, null);
		mTabHost.addTab(
				mTabHost.newTabSpec("tab2").setIndicator(
						getTabIndicator(mTabHost.getContext(), "YOUR BADGES")),
				BadgesAll.class, null);

		findViewById(android.R.id.tabcontent).setBackgroundColor(
				getResources().getColor(R.color.appBg_color_white));

	}

	private View getTabIndicator(Context context, String title) {
		View view = LayoutInflater.from(context).inflate(
				R.layout.scoreboard_tab, null);
		TextView tv = (TextView) view.findViewById(R.id.textView);
		tfThin = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Thin.ttf");
		tv.setText(title);
		tv.setTypeface(tfThin);
		return view;
	}

}
