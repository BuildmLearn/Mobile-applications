package org.buildmlearn.practicehandwriting;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import org.buildmlearn.practicehandwriting.practice.PracticeActivity;

import java.util.Random;


public class WordSelectionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_selection);
        Animation animation_1 = AnimationUtils.loadAnimation(this, R.anim.slide_in);

        Animation animation_2 = AnimationUtils.loadAnimation(this, R.anim.slide_in);
        animation_2.setStartOffset(500);

        Animation animation_3 = AnimationUtils.loadAnimation(this, R.anim.slide_in);
        animation_3.setStartOffset(1000);

        findViewById(R.id.easy_button).startAnimation(animation_1);
        findViewById(R.id.medium_button).startAnimation(animation_2);
        findViewById(R.id.hard_button).startAnimation(animation_3);
    }

    public void wordSelectionOnClick(View view) {
        String[] wordList = null;
        Intent intent = new Intent(this, PracticeActivity.class);
        switch (view.getId()) {
            case R.id.easy_button:
                wordList = getResources().getStringArray(R.array.English_words_easy);
                break;

            case R.id.medium_button:
                wordList = getResources().getStringArray(R.array.English_words_medium);
                break;

            case R.id.hard_button:
                wordList = getResources().getStringArray(R.array.English_words_hard);
                break;
        }
        intent.putExtra(getResources().getString(R.string.practice_string), wordList[new Random().nextInt(wordList.length)]);

        startActivity(intent);
    }
}
