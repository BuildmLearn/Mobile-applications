package org.buildmlearn.practicehandwriting.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.View;

import org.buildmlearn.practicehandwriting.R;
import org.buildmlearn.practicehandwriting.helper.PracticeBaseActivity;
import org.buildmlearn.practicehandwriting.helper.TimeTrialResult;

import java.util.Random;

public class TimeTrialActivity extends PracticeBaseActivity {
    private CountDownTimer mCountDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            getIntent().putExtra(getResources().getString(R.string.practice_string), randomStringGenerator());//practice session with random strings, kept first since the string is spoken in super
            super.onCreate(savedInstanceState);
            mCountDownTimer = new CountDownTimer(120000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    //Showing the user how much time he/she has left
                    mScoreTimerView.setText(String.format("%02d", millisUntilFinished / 60000) + ":" + String.format("%02d", (millisUntilFinished / 1000) % 60));
                }

                @Override
                public void onFinish() {
                    mScoreTimerView.setText("00:00");
                    mDone = true;
                    mDrawView.canDraw(false);
                    SplashActivity.mTimeTrialResults.add(new TimeTrialResult(mPracticeString, mDrawView.getTouchesList()));
                    startActivity(new Intent(TimeTrialActivity.this,TimeTrialResultActivity.class));
                }
            };
            mDrawView.setBitmapFromText(mPracticeString);
            mDrawView.canVibrate(true);
            mCountDownTimer.start();
        } catch (Exception e) {
            showErrorDialog(e);
        }
    }

    private String randomStringGenerator() {
        //Returns character or word with 50% probability
        int char_or_word = new Random().nextInt(2);
        switch (char_or_word) {
            case 0:
                return SplashActivity.CHARACTER_LIST[new Random().nextInt(SplashActivity.CHARACTER_LIST.length)];

            case 1:
                int wordlist = new Random().nextInt(3);
                switch (wordlist) {
                    case 0:
                        return SplashActivity.EASY_WORD_LIST[new Random().nextInt(SplashActivity.EASY_WORD_LIST.length)];

                    case 1:
                        return SplashActivity.MEDIUM_WORD_LIST[new Random().nextInt(SplashActivity.MEDIUM_WORD_LIST.length)];

                    case 2:
                        return SplashActivity.HARD_WORD_LIST[new Random().nextInt(SplashActivity.HARD_WORD_LIST.length)];
                }
        }
        return SplashActivity.CHARACTER_LIST[0];
    }

    @Override
    public void practiceOnClick(View v) {
        super.practiceOnClick(v);
        switch (v.getId()) {
            case R.id.reset_button:
                mDrawView.setBitmapFromText(mPracticeString);
                break;

            case R.id.done_save_button:
                if(!mDone) {
                    //Saving the string and the touches to be redrawn in the result
                    mDrawView.canDraw(false);
                    SplashActivity.mTimeTrialResults.add(new TimeTrialResult(mPracticeString, mDrawView.getTouchesList()));
                    mDrawView.canDraw(true);

                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
                break;
        }
    }

    //overriding this function to allow only the done button to go to the next string
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }


    @Override
    public void onBackPressed() {
        if(mCountDownTimer!=null)
            mCountDownTimer.cancel();//cancel the timer if the user decides to go back
        super.onBackPressed();
    }
}
