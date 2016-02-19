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

public class DiagramMenuElectricity extends FragmentActivity {

	FragmentPagerAdapter adapterViewPager;
	static float scoreMotor;
	static float scoreDryCell;
	static float scoreCircuit;

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
		scoreMotor = pref.getFloat("Motor", 0);
		scoreDryCell = pref.getFloat("DryCell", 0);
		scoreCircuit = pref.getFloat("Circuit", 0);
	}

	public static class HumanBodyPagerAdapter extends FragmentPagerAdapter {

		private String[] pageTitles = new String[] { "Electric Motor",
				"Dry Cell", "Electric Circuit" };
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
				MotorFragment motorFrag = new MotorFragment();
				bundle.putFloat("SCORE_SAVED", scoreMotor);
				motorFrag.setArguments(bundle);
				return motorFrag;
			case 1:
				DryCellFragment cellFrag = new DryCellFragment();
				bundle.putFloat("SCORE_SAVED", scoreDryCell);
				cellFrag.setArguments(bundle);
				return cellFrag;
			case 2:
				CircuitFragment circuitFrag = new CircuitFragment();
				bundle.putFloat("SCORE_SAVED", scoreCircuit);
				circuitFrag.setArguments(bundle);
				return circuitFrag;
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