package org.buildmlearn.practicehandwriting.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import org.buildmlearn.practicehandwriting.R;


public class LanguageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        Animation animation_1 = AnimationUtils.loadAnimation(this, R.anim.slide_in);

        Animation animation_2 = AnimationUtils.loadAnimation(this, R.anim.slide_in);
        animation_2.setStartOffset(500);

        Animation animation_3 = AnimationUtils.loadAnimation(this, R.anim.slide_in);
        animation_3.setStartOffset(1000);

        findViewById(R.id.english_button).startAnimation(animation_1);
        findViewById(R.id.hindi_button).startAnimation(animation_2);
        findViewById(R.id.arabic_button).startAnimation(animation_3);
    }

    public void languageActivityOnClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.english_button:
                intent = new Intent(this,MainMenuActivity.class);
                break;

            case R.id.hindi_button:
                intent = new Intent(this,IncompleteActivity.class);
                break;

            case R.id.arabic_button:
                intent = new Intent(this,IncompleteActivity.class);
                break;
        }
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
