package com.buildmlearn.labeldiagram;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class DiagramMenuHuman extends DiagramMenu {

	FragmentPagerAdapter adapterViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		adapterViewPager = new mnbb(getSupportFragmentManager());
		

	}

	public class mnbb extends DiagramMenu.DiagramPagerAdapter{

	
		public mnbb(FragmentManager fragmentManager) {
			super(fragmentManager);
			NUM_ITEMS=3;
		}
		
		@Override
		public Fragment getItem(int position) {
			// TODO Auto-generated method stub
			switch (position) {
            case 0:
                return DiagramFragment.getInstance(1);
            case 1:
                return DiagramFragment.getInstance(2);
            case 2:
                return DiagramFragment.getInstance(3);
            default:
                return null;
            }
		}
	}
}