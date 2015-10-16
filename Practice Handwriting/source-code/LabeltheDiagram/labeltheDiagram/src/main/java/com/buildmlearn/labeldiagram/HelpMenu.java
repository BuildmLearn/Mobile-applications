package com.buildmlearn.labeldiagram;

import com.example.labelthediagram.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

public class HelpMenu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.help_tutorial_view);

		CustomPagerAdapter adapter = new CustomPagerAdapter();
		ViewPager myPager = (ViewPager) findViewById(R.id.tutorialpager);
		myPager.setAdapter(adapter);
		myPager.setCurrentItem(0);
	}

	class CustomPagerAdapter extends PagerAdapter {

		public Object instantiateItem(View collection, int position) {
			getApplicationContext();

			LayoutInflater inflater = (LayoutInflater) collection.getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			int resId = 0;
			switch (position) {
			case 0:
				resId = R.layout.help_screen1;
				break;
			case 1:
				resId = R.layout.help_screen2;
				break;
			case 2:
				resId = R.layout.help_screen3;
				break;
			case 3:
				resId = R.layout.help_screen4;
				break;
			case 4:
				resId = R.layout.help_screen5;
				break;
			case 5:
				resId = R.layout.help_screen6;
				break;
			case 6:
				resId = R.layout.help_screen7;
				break;
			case 7:
				resId = R.layout.help_screen8;
				break;
			case 8:
				resId = R.layout.help_screen9;
				break;
			case 9:
				resId = R.layout.help_screen10;
				Intent intent = new Intent(getApplicationContext(),MainMenuGrid.class);
				startActivity(intent);
				finish();
				break;
			}
			View view = inflater.inflate(resId, null);

			((ViewPager) collection).addView(view, 0);
			return view;
		}

		@Override
		public int getCount() {
			return 10;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			 return arg0 == ((View) arg1);
		}
		
		@Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) arg2);
 
        }
		
		@Override
        public Parcelable saveState() {
            return null;
        }
	
	}

}
