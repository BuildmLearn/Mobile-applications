package org.buildmlearn.learnfrommap;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Activity used for displaying the App tutorial
 * 
 * This activity uses a ViewPager to display static views defined the res/layout folder. 
 * 
 * @author Abhishek	
 *
 */
public class AppTutorial extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_app_tutorial);
	    CustomPagerAdapter adapter = new CustomPagerAdapter();
	    ViewPager myPager = (ViewPager) findViewById(R.id.myfivepanelpager);
	    myPager.setAdapter(adapter);
	    myPager.setCurrentItem(0);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.app_tutorial, menu);
		return true;
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			// Handle action bar item clicks here. The action bar will
			// automatically handle clicks on the Home/Up button, so long
			// as you specify a parent activity in AndroidManifest.xml.
			int id = item.getItemId();
			if (id == R.id.action_settings) {
				return true;
			}
			return super.onOptionsItemSelected(item);
		}

	/**
	 * Custom Page Adapter used by View Pager
	 * 
	 * @author Abhishek
	 */
	class CustomPagerAdapter extends PagerAdapter {

		/* (non-Javadoc)
		 * @see android.support.v4.view.PagerAdapter#instantiateItem(android.view.View, int)
		 */
		public Object instantiateItem(View collection, int position) {
			getApplicationContext();
			//layout = inflater.inflate(R.layout.layout_evaluation, null);
			LayoutInflater inflater = (LayoutInflater) collection.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			int resId = 0;
			switch (position) {
			case 0:
				resId = R.layout.swipe1;
				break;
			case 1:
				resId = R.layout.swipe2;
				break;
			case 2:
				resId = R.layout.swipe3;
				break;
			case 3:
				resId = R.layout.swipe4;
				break;
			case 4:
				resId = R.layout.swipe5;
				break;
			case 5:
				resId = R.layout.swipe6;
				break;
			case 6:
				resId = R.layout.swipe6;
				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(intent);
				finish();
				break;
			}
			View view = inflater.inflate(resId, null);
			 
            ((ViewPager) collection).addView(view, 0);
			return view;
		}

        /* (non-Javadoc)
         * @see android.support.v4.view.PagerAdapter#destroyItem(android.view.View, int, java.lang.Object)
         */
        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) arg2);
 
        }
 
 
        /* (non-Javadoc)
         * @see android.support.v4.view.PagerAdapter#isViewFromObject(android.view.View, java.lang.Object)
         */
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == ((View) arg1);
 
        }
 
        /* (non-Javadoc)
         * @see android.support.v4.view.PagerAdapter#saveState()
         */
        @Override
        public Parcelable saveState() {
            return null;
        }

		/* (non-Javadoc)
		 * @see android.support.v4.view.PagerAdapter#getCount()
		 */
		@Override
		public int getCount() {
			return 7;
		}

	}

}
