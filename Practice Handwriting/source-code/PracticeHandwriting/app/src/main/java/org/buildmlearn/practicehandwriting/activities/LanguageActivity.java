package org.buildmlearn.practicehandwriting.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import org.buildmlearn.practicehandwriting.R;

import java.util.Locale;


public class LanguageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        //adding slide in animation for the buttons
        final int[] buttons = new int[] {R.id.english_button,R.id.hindi_button,R.id.arabic_button};
        for(int i=0;i<buttons.length;i++) {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_in);
            animation.setStartOffset(500 * i);
            findViewById(buttons[i]).startAnimation(animation);
        }
    }

    public void languageActivityOnClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.english_button:
                setLocale("en");
                intent = new Intent(this,MainMenuActivity.class);
                break;

            case R.id.hindi_button:
                setLocale("hi");
                intent = new Intent(this,MainMenuActivity.class);
                break;

            case R.id.arabic_button:
                intent = new Intent(this,IncompleteActivity.class);
                break;
        }
        startActivity(intent);
    }

    private void setLocale(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        if(SplashActivity.TTSobj!=null)
            SplashActivity.TTSobj.setLanguage(locale);
        //Loading the lists from Resources instead of loading them multiple times at runtime.
        SplashActivity.CHARACTER_LIST = getResources().getStringArray(R.array.Characters);
        SplashActivity.EASY_WORD_LIST = getResources().getStringArray(R.array.Words_easy);
        SplashActivity.MEDIUM_WORD_LIST = getResources().getStringArray(R.array.Words_medium);
        SplashActivity.HARD_WORD_LIST = getResources().getStringArray(R.array.Words_hard);
    }

    @Override
    public void onBackPressed() {
        //exiting the app instead of going back to the splash screen
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
