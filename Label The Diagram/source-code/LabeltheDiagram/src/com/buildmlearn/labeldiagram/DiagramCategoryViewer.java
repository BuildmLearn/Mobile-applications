package com.buildmlearn.labeldiagram;

import com.buildmlearn.labeldiagram.helper.HelperClass;
import com.buildmlearn.labeldiagram.resources.BioCategoryFragment;
import com.example.labelthediagram.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;

public class DiagramCategoryViewer extends FragmentActivity {

	FragmentPagerAdapter adapterViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.diagram_menu);

		ViewPager vpPager = (ViewPager) findViewById(R.id.diagrampager);
		adapterViewPager = new DiagramCategoryViewerAdapter(
				getSupportFragmentManager());
		vpPager.setAdapter(adapterViewPager);
		vpPager.setClipToPadding(false);
		vpPager.setPageMargin(20);
		vpPager.setBackgroundColor(getResources().getColor(R.color.appBg_color_white));
		PagerTabStrip strip = (PagerTabStrip) vpPager.findViewById(R.id.pager_title_strip);
		strip.setTabIndicatorColor(getResources().getColor(R.color.appBg_color));

		HelperClass.setActionBar("Diagram Categories", this);

	}

	public static class DiagramCategoryViewerAdapter extends
			FragmentPagerAdapter {

		private String[] pageTitles = new String[] { "Biology", "Physics",
				"Science" };
		public static int NUM_ITEMS = 3;

		public DiagramCategoryViewerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int position) {
			
			Bundle bundle = new Bundle();

			switch (position) {
			case 0:
				BioCategoryFragment eyeFrag = new BioCategoryFragment();
				//bundle.putFloat("SCORE_SAVED", scoreHumanEye);
				//eyeFrag.setArguments(bundle);
				return eyeFrag;
			case 1:
				BioCategoryFragment heartFrag = new BioCategoryFragment();
				//bundle.putFloat("SCORE_SAVED", scoreHumanHeart);
				//heartFrag.setArguments(bundle);
				return heartFrag;
			case 2:
				BioCategoryFragment earFrag = new BioCategoryFragment();
				//bundle.putFloat("SCORE_SAVED", scoreHumanEar);
				//earFrag.setArguments(bundle);
				return earFrag;
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
