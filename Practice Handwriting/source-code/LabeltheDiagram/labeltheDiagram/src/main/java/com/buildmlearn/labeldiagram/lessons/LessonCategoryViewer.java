package com.buildmlearn.labeldiagram.lessons;

import com.buildmlearn.labeldiagram.CategoryViewer;
import com.buildmlearn.labeldiagram.helper.HelperClass;
import com.buildmlearn.labeldiagram.resources.BioLessonFragment;
import com.buildmlearn.labeldiagram.resources.CategoryViewerAdapter;
import com.buildmlearn.labeldiagram.resources.PhysicsLessonFragment;
import com.buildmlearn.labeldiagram.resources.ScienceLessonFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class LessonCategoryViewer extends CategoryViewer {

	@Override
	protected void onCreate(Bundle arg0) {

		super.onCreate(arg0);

		adapterViewPager = new LessonsCategoryViewerAdapter(
				getSupportFragmentManager());

		vpPager.setAdapter(adapterViewPager);

		HelperClass.setActionBar("Lessons", this);

	}

	public static class LessonsCategoryViewerAdapter extends
			CategoryViewerAdapter {

		private String[] pageTitles = new String[] { "Biology", "Physics",
				"Science" };
		public static int NUM_ITEMS = 3;

		public LessonsCategoryViewerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {

			switch (position) {
			case 0:
				BioLessonFragment bioFrag = new BioLessonFragment();
				return bioFrag;
			case 1:
				PhysicsLessonFragment physicsFrag = new PhysicsLessonFragment();
				return physicsFrag;
			case 2:
				ScienceLessonFragment scienceFrag = new ScienceLessonFragment();
				return scienceFrag;
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