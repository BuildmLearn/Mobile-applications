package org.buildmlearn.practicehandwriting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class SplashActivity extends Activity {

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //TODO pre processing if any

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a 3 second timer
             */

            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LanguageActivity.class);
                startActivity(intent);
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}
