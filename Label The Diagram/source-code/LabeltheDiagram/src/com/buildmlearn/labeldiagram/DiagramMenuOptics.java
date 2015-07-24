package com.buildmlearn.labeldiagram;

import com.buildmlearn.labeldiagram.helper.HelperClass;
import com.buildmlearn.labeldiagram.resources.HumanEarFragment;
import com.buildmlearn.labeldiagram.resources.HumanEyeFragment;
import com.buildmlearn.labeldiagram.resources.HumanHeartFragment;
import com.buildmlearn.labeldiagram.resources.LensFragment;
import com.buildmlearn.labeldiagram.resources.PrismFragment;
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

public class DiagramMenuOptics extends FragmentActivity {

	FragmentPagerAdapter adapterViewPager;
	static float scorePrism;
	static float scoreLens;
	static float scoreEMSpectrum;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.diagram_menu);
		
		ViewPager vpPager = (ViewPager) findViewById(R.id.diagrampager);
		adapterViewPager = new HumanBodyPagerAdapter(
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
		scorePrism = pref.getFloat("Prism", 0);
		scoreLens = pref.getFloat("Lens", 0);
		/*scoreEMSpectrum = pref.getFloat("EMSpectrum", 0);*/
	}

	public static class HumanBodyPagerAdapter extends FragmentPagerAdapter {

		private String[] pageTitles = new String[] { "Refraction of Prism",
				"Refraction of Lens", "Light Spectrum" };
		public static int NUM_ITEMS = 3;

		public HumanBodyPagerAdapter(FragmentManager fragmentManager) {
			super(fragmentManager);

		}

		@Override
		public int getCount() {
			return NUM_ITEMS;
		}

		@Override
		public Fragment getItem(int position) {

			Bundle bundle=new Bundle();
			
			switch (position) {
			case 0:
				PrismFragment prismFrag = new PrismFragment();
				bundle.putFloat("SCORE_SAVED", scorePrism);
				prismFrag.setArguments(bundle);
				return prismFrag;
			case 1:
				LensFragment prismFrag1 = new LensFragment();
				bundle.putFloat("SCORE_SAVED", scoreLens);
				prismFrag1.setArguments(bundle);
				return prismFrag1;
			case 2:
				PrismFragment prismFrag2 = new PrismFragment();
				bundle.putFloat("SCORE_SAVED", scorePrism);
				prismFrag2.setArguments(bundle);
				return prismFrag2;
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