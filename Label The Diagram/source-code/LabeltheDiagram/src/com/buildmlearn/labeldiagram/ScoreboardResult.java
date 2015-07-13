package com.buildmlearn.labeldiagram;

import com.buildmlearn.labeldiagram.helper.HelperClass;
import com.buildmlearn.labeldiagram.resources.ScoreboardScoreFragment;
import com.example.labelthediagram.R;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ScoreboardResult extends FragmentActivity {
	
	private FragmentTabHost mTabHost;
	Typeface tfThin;
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);

	        setContentView(R.layout.scoreboard_result);
	        
	        HelperClass.setActionBar("Scoreboard", this);
	        
	        mTabHost = (FragmentTabHost) findViewById(R.id.tabhost);
	        mTabHost.setup(this, getSupportFragmentManager(), R.id.tabFrameLayout);

	        mTabHost.addTab(
	                mTabHost.newTabSpec("tab1").setIndicator(getTabIndicator(mTabHost.getContext(), "LAST ATTEMPT")),
	                ScoreboardScoreFragment.class, null);
	        mTabHost.addTab(
	                mTabHost.newTabSpec("tab2").setIndicator(getTabIndicator(mTabHost.getContext(), "BEST SCORE")),
	                ScoreboardScoreFragment.class, null);
	        
	        
	    }
	    
	    private View getTabIndicator(Context context, String title) {
	        View view = LayoutInflater.from(context).inflate(R.layout.scoreboard_tab, null);
	        TextView tv = (TextView) view.findViewById(R.id.textView);
	        tfThin = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Thin.ttf");
	        tv.setText(title);
	        tv.setTypeface(tfThin);
	        return view;
	    }

}
