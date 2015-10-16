package com.buildmlearn.labeldiagram.resources;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public abstract class CategoryViewerAdapter extends FragmentPagerAdapter {

	public CategoryViewerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int arg0) {
		return null;
	}

	@Override
	public int getCount() {
		return 0;
	}

}
