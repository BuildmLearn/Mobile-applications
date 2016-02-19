package com.buildmlearn.labeldiagram;

import com.buildmlearn.labeldiagram.resources.PlantCellFragment;
import com.buildmlearn.labeldiagram.resources.PlantFlowerFragment;
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

public class DiagramMenuPlants extends FragmentActivity {

	FragmentPagerAdapter adapterViewPager;
	static float plantCell=0;
	static float plantFlower=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.diagram_menu);

		ViewPager vpPager = (ViewPager) findViewById(R.id.diagrampager);
		adapterViewPager = new PlantPagerAdapter(
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
		plantCell = pref.getFloat("PlantCell", 0);
		plantFlower = pref.getFloat("PlantFlower", 0);

	}

	public static class PlantPagerAdapter extends FragmentPagerAdapter {

		private String[] pageTitles = new String[] { "Plant Cell",
				"Plant Flower" };

		public static int NUM_ITEMS = 2;

		public PlantPagerAdapter(FragmentManager fragmentManager) {
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

				PlantCellFragment cellFrag = new PlantCellFragment();
				bundle.putFloat("SCORE_SAVED", plantCell);
				cellFrag.setArguments(bundle);
				return cellFrag;

			case 1:

				PlantFlowerFragment flowerFrag = new PlantFlowerFragment();
				bundle.putFloat("SCORE_SAVED", plantFlower);
				flowerFrag.setArguments(bundle);
				return flowerFrag;
				

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
