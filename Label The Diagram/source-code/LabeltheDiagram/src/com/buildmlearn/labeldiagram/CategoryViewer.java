package com.buildmlearn.labeldiagram;

import com.example.labelthediagram.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;

public class CategoryViewer extends FragmentActivity {
	
	protected FragmentPagerAdapter adapterViewPager;
	protected ViewPager vpPager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		PagerTabStrip strip;
		
		setContentView(R.layout.diagram_menu);
		
		// Set ViewPager parameters
		vpPager = (ViewPager) findViewById(R.id.diagrampager);
		vpPager.setClipToPadding(false);
		vpPager.setPageMargin(20);
		vpPager.setBackgroundColor(getResources().getColor(
				R.color.appBg_color_white));
		strip = (PagerTabStrip) vpPager
				.findViewById(R.id.pager_title_strip);
		strip.setTabIndicatorColor(getResources().getColor(
				R.color.appBg_color_white));

	}

}
