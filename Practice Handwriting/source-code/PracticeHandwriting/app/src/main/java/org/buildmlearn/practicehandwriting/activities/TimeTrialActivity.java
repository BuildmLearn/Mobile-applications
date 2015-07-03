package org.buildmlearn.practicehandwriting.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.View;

import org.buildmlearn.practicehandwriting.R;
import org.buildmlearn.practicehandwriting.helper.PracticeBaseActivity;
import org.buildmlearn.practicehandwriting.helper.TimeTrialResult;

import java.util.Arrays;
import java.util.Random;

public class TimeTrialActivity extends PracticeBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            mCountDownTimer = new CountDownTimer(5000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    mScoreTimerView.setText(String.format("%02d", millisUntilFinished / 60000) + ":" + String.format("%02d", (millisUntilFinished / 1000) % 60));
                }

                @Override
                public void onFinish() {
                    mScoreTimerView.setText("00:00");
                    mDone = true;
                    mDrawView.canDraw(false);
                    SplashActivity.mTimeTrialResults.add(new TimeTrialResult(mPracticeString, mDrawView.getTouchesList()));
                    Intent intent = new Intent(TimeTrialActivity.this,TimeTrialResultActivity.class);
                    startActivity(intent);
                }
            };
            mPracticeString = randomStringGenerator();
            mDrawView.setBitmapFromText(mPracticeString);
            mDrawView.canVibrate(true);
            mCountDownTimer.start();
        } catch (Exception e) {
            showErrorDialog(e);
        }
    }

    private String randomStringGenerator() {
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
                    int index = Arrays.asList(SplashActivity.CHARACTER_LIST).indexOf(mPracticeString);
                    //mResults.add(new TimeTrialResult(mPracticeString,mDrawView.getTouchesList()));
                    mDrawView.canDraw(false);
                    SplashActivity.mTimeTrialResults.add(new TimeTrialResult(mPracticeString, mDrawView.getTouchesList()));

                    mDrawView.canDraw(true);
                    if(index == SplashActivity.CHARACTER_LIST.length - 1) {
                        Intent intent = new Intent(TimeTrialActivity.this, TimeTrialResultActivity.class);
                        startActivity(intent);
                    } else {
                        mPracticeString = randomStringGenerator();
                        mDrawView.setBitmapFromText(mPracticeString);
                    }
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }
}
