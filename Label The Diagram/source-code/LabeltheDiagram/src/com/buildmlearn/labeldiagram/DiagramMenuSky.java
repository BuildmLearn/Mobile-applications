package com.buildmlearn.labeldiagram;

import com.buildmlearn.labeldiagram.helper.HelperClass;
import com.buildmlearn.labeldiagram.resources.CircuitFragment;
import com.buildmlearn.labeldiagram.resources.DryCellFragment;
import com.buildmlearn.labeldiagram.resources.HumanEarFragment;
import com.buildmlearn.labeldiagram.resources.HumanEyeFragment;
import com.buildmlearn.labeldiagram.resources.HumanHeartFragment;
import com.buildmlearn.labeldiagram.resources.LensFragment;
import com.buildmlearn.labeldiagram.resources.MotorFragment;
import com.buildmlearn.labeldiagram.resources.PrismFragment;
import com.buildmlearn.labeldiagram.resources.SolarSystemFragment;
import com.example.labelthediagram.R;

import android.app.ActionBar;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class DiagramMenuSky extends FragmentActivity {

	FragmentPagerAdapter adapterViewPager;
	static float scoreSolarSystem;
	static float scoreStarPattern;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.diagram_menu);

		ViewPager vpPager = (ViewPager) findViewById(R.id.diagrampager);
		adapterViewPager = new SolarSystemPagerAdapter(
				getSupportFragmentManager());
		vpPager.setAdapter(adapterViewPager);
		vpPager.setClipToPadding(false);
		vpPager.setPageMargin(20);

		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Diagram Menu");
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.show();

		HelperClass.setActionBar("Diagram Menu", this);

		SharedPreferences pref = getApplicationContext().getSharedPreferences(
				"com.buildmlearn.labeldiagram.PREFERENCE_FILE_KEY",
				Context.MODE_PRIVATE);
		scoreSolarSystem = pref.getFloat("SolarSystem", 0);
		//scoreStarPattern = pref.getFloat("DryCell", 0);
	
	}

	public static class SolarSystemPagerAdapter extends FragmentPagerAdapter {

		private String[] pageTitles = new String[] { "Solar System",
				"Star Patterns" };
		public static int NUM_ITEMS = 2;

		public SolarSystemPagerAdapter(FragmentManager fragmentManager) {
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
				SolarSystemFragment solarFrag = new SolarSystemFragment();
				bundle.putFloat("SCORE_SAVED", scoreSolarSystem);
				solarFrag.setArguments(bundle);
				return solarFrag;
			case 1:
				SolarSystemFragment starFrag = new SolarSystemFragment();
				bundle.putFloat("SCORE_SAVED", scoreStarPattern);
				starFrag.setArguments(bundle);
				return starFrag;
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