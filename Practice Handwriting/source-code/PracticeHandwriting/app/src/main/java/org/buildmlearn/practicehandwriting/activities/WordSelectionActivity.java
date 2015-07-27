package org.buildmlearn.practicehandwriting.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

import org.buildmlearn.practicehandwriting.R;

import java.util.Random;


public class WordSelectionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_selection);

        //slide in animation for the buttons.
        final int[] buttons = new int[] {R.id.easy_button,R.id.medium_button,R.id.hard_button};
        for(int i=0;i<buttons.length;i++) {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_in);
            animation.setStartOffset(500 * i);
            findViewById(buttons[i]).startAnimation(animation);
        }

        SharedPreferences wmbPreference = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFirstRun = wmbPreference.getBoolean("FIRSTWORD", true);
        if (isFirstRun) {
            SharedPreferences.Editor editor = wmbPreference.edit();
            editor.putBoolean("FIRSTWORD", false);
            editor.apply();
            new ShowcaseView.Builder(this)
                    .setTarget(new ViewTarget(R.id.easy_button, this))
                    .setContentTitle("")
                    .setContentText(getString(R.string.word_selection))
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ((ShowcaseView) view.getParent()).hide();
                        }
                    })
                    .build();
        }
    }

    public void wordSelectionOnClick(View view) {
        String[] wordList = null;
        Intent intent = new Intent(this, PracticeActivity.class);
        switch (view.getId()) {
            case R.id.easy_button:
                wordList = getResources().getStringArray(R.array.Words_easy);
                break;

            case R.id.medium_button:
                wordList = getResources().getStringArray(R.array.Words_medium);
                break;

            case R.id.hard_button:
                wordList = getResources().getStringArray(R.array.Words_hard);
                break;
        }
        intent.putExtra(getResources().getString(R.string.practice_string), wordList[new Random().nextInt(wordList.length)]);
        startActivity(intent);
    }
}
