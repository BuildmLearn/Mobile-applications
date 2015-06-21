package com.buildmlearn.labeldiagram;

import com.buildmlearn.labeldiagram.resources.BacteriaFragment;
import com.buildmlearn.labeldiagram.resources.VirusFragment;
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

public class DiagramMenuMicroogranisms extends FragmentActivity {
	
	FragmentPagerAdapter adapterViewPager;
	static float bacteria=0;
	static float virus=0;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.diagram_menu);

		ViewPager vpPager = (ViewPager) findViewById(R.id.diagrampager);
		adapterViewPager = new BacteriaAdapter(
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
		bacteria = pref.getFloat("Bacteria", 0);
		virus = pref.getFloat("Virus", 0);

	}
	
	public static class BacteriaAdapter extends FragmentPagerAdapter {

		private String[] pageTitles = new String[] { "Bacteria",
				"Virus" };

		public static int NUM_ITEMS = 2;

		public BacteriaAdapter(FragmentManager fragmentManager) {
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

				BacteriaFragment bacteriaFrag = new BacteriaFragment();
				bundle.putFloat("SCORE_SAVED", bacteria);
				bacteriaFrag.setArguments(bundle);
				return bacteriaFrag;

			case 1:

				VirusFragment virusFrag = new VirusFragment();
				bundle.putFloat("SCORE_SAVED", virus);
				virusFrag.setArguments(bundle);
				return virusFrag;
				

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
