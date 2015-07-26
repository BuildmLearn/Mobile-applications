package com.buildmlearn.labeldiagram;

import com.buildmlearn.labeldiagram.helper.HelperClass;
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
	/*static float scoreLens;
	static float scoreEMSpectrum;*/

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
		scoreMotor = pref.getFloat("Prism", 0);
		//scoreLens = pref.getFloat("Lens", 0);
		/*scoreEMSpectrum = pref.getFloat("EMSpectrum", 0);*/
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
				MotorFragment cellFrag = new MotorFragment();
				bundle.putFloat("SCORE_SAVED", scoreMotor);
				cellFrag.setArguments(bundle);
				return cellFrag;
			case 2:
				MotorFragment circuitFrag = new MotorFragment();
				bundle.putFloat("SCORE_SAVED", scoreMotor);
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