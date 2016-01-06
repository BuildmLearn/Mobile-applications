package org.buildmlearn.practicehandwriting.activities.information;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import org.buildmlearn.practicehandwriting.R;
import org.buildmlearn.practicehandwriting.helper.display.TutorialPagerAdapter;

/**
 * Activity that displays the tutorials to a first time user
 */
public class TutorialActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        setContentView(R.layout.activity_tutorial);

        // Instantiate a ViewPager and a PagerAdapter.
        ViewPager mPager = (ViewPager) findViewById(R.id.Tutorialpager);
        mPager.setAdapter(new TutorialPagerAdapter(this));
    }
}
