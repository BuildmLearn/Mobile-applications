package com.buildmlearn.labeldiagram;

import com.buildmlearn.labeldiagram.helper.HelperClass;
import com.buildmlearn.labeldiagram.resources.ScoreboardBioFragment;
import com.buildmlearn.labeldiagram.resources.ScoreboardPhysicsFragment;
import com.buildmlearn.labeldiagram.resources.ScoreboardScienceFragment;
import com.example.labelthediagram.R;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

public class ScoreboardViewer extends FragmentActivity{
	
	FragmentPagerAdapter adapterViewPager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.scoreboard_view);
		
		HelperClass.setActionBar("Scoreboard", this);
		
		ViewPager vpPager = (ViewPager) findViewById(R.id.scoreboard_pager);
		adapterViewPager = new ScoreboardPagerAdapter(
				getSupportFragmentManager());
		vpPager.setAdapter(adapterViewPager);
		vpPager.setClipToPadding(false);
		vpPager.setPageMargin(20);
		
	}
	
	public static class ScoreboardPagerAdapter extends FragmentPagerAdapter{
		
		private String[] pageTitles = new String[] { "Biology",
				"Physics", "Science" };
		public static final int NUM_ITEMS = 3;

		public ScoreboardPagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case 0:
				ScoreboardBioFragment bioFrag = new ScoreboardBioFragment();
				return bioFrag;
			case 1:
				ScoreboardPhysicsFragment phyFrag = new ScoreboardPhysicsFragment();
				return phyFrag;
			case 2:
				ScoreboardScienceFragment chemFrag = new ScoreboardScienceFragment();
				return chemFrag;
			default:
				return null;
			}
		}

		@Override
		public int getCount() {
			return NUM_ITEMS;
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
