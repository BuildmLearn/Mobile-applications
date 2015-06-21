package com.buildmlearn.labeldiagram;

import com.buildmlearn.labeldiagram.DiagramMenuMicroogranisms.BacteriaAdapter;
import com.buildmlearn.labeldiagram.resources.BacteriaFragment;
import com.buildmlearn.labeldiagram.resources.VirusFragment;
import com.buildmlearn.labeldiagram.resources.WaterCycleFragment;
import com.example.labelthediagram.R;

import android.app.ActionBar;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

public class DiagramMenuNaturalCycles extends FragmentActivity{
	
	FragmentPagerAdapter adapterViewPager;
	static float waterCycle=0;
	static float carbonCycle=0;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.diagram_menu);

		ViewPager vpPager = (ViewPager) findViewById(R.id.diagrampager);
		adapterViewPager = new WaterCycleAdapter(
				getSupportFragmentManager());
		vpPager.setAdapter(adapterViewPager);
		vpPager.setClipToPadding(false);
		vpPager.setPageMargin(20);

		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Diagram Menu");
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.show();

		SharedPreferences pref = getApplicationContext().getSharedPreferences(
				"com.buildmlearn.labeldiagram.PREFERENCE_FILE_KEY",
				Context.MODE_PRIVATE);
		waterCycle = pref.getFloat("WaterCycle", 0);
		carbonCycle = pref.getFloat("CarbonCycle", 0);

	}
	
	public static class WaterCycleAdapter extends FragmentPagerAdapter {

		private String[] pageTitles = new String[] { "Water Cycle",
				"Carbon Cycle" };

		public static int NUM_ITEMS = 2;

		public WaterCycleAdapter(FragmentManager fragmentManager) {
			super(fragmentManager);
		}

		@Override
		public int getCount() {
			return NUM_ITEMS;
		}

		@Override
		public Fragment getItem(int position) {

			Bundle bundle = new Bundle();

			switch (position) {
			case 0:

				WaterCycleFragment watercycFrag = new WaterCycleFragment();
				bundle.putFloat("SCORE_SAVED", waterCycle);
				watercycFrag.setArguments(bundle);
				return watercycFrag;

			case 1:

				WaterCycleFragment carboncycFrag = new WaterCycleFragment();
				bundle.putFloat("SCORE_SAVED", carbonCycle);
				carboncycFrag.setArguments(bundle);
				return carboncycFrag;
				

			default:
				return null;
			}
		}

		public CharSequence getPageTitle(int position) {
			return pageTitles[position];
		}

		@Override
		public float getPageWidth(int position) {
			return 1.0f;
		}

	}
	
	

}
