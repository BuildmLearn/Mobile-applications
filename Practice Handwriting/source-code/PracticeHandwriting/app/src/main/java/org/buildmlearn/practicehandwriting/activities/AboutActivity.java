package org.buildmlearn.practicehandwriting.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import org.buildmlearn.practicehandwriting.R;

/**
 * Activity that will be displayed when the user tries to access those activities that haven't been completed.
 */
public class AboutActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
}
