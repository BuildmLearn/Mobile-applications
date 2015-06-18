package com.buildmlearn.labeldiagram;

import com.buildmlearn.labeldiagram.resources.HumanEyeFragment;
import com.buildmlearn.labeldiagram.resources.HumanHeartFragment;
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

public class DiagramMenuHuman extends FragmentActivity {

	FragmentPagerAdapter adapterViewPager;
	static float scoreHumanEye;
	static float scoreHumanHeart;

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

		SharedPreferences pref = getApplicationContext().getSharedPreferences(
				"com.buildmlearn.labeldiagram.PREFERENCE_FILE_KEY",
				Context.MODE_PRIVATE);
		scoreHumanEye = pref.getFloat("HumanEye", 0);
		scoreHumanHeart = pref.getFloat("HumanHeart", 0);
	}

	public static class HumanBodyPagerAdapter extends FragmentPagerAdapter {

		private String[] pageTitles = new String[] { "Human Eye",
				"Human Heart", "Human Ear" };
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
				HumanEyeFragment eyeFrag = new HumanEyeFragment();
				bundle.putFloat("SCORE_SAVED", scoreHumanEye);
				eyeFrag.setArguments(bundle);
				return eyeFrag;
			case 1:
				HumanHeartFragment heartFrag = new HumanHeartFragment();
				bundle.putFloat("SCORE_SAVED", scoreHumanHeart);
				heartFrag.setArguments(bundle);
				return heartFrag;
			case 2:
				return DiagramFragment.getInstance(3);
			default:
				return null;
			}
		}

		public CharSequence getPageTitle(int position) {
			return pageTitles[position];
		}

		@Override
		public float getPageWidth(int position) {
			return 0.90f;
		}

	}
}