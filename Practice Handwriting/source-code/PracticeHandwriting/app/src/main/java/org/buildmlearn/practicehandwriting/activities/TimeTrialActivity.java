package org.buildmlearn.practicehandwriting.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import org.buildmlearn.practicehandwriting.R;
import org.buildmlearn.practicehandwriting.helper.TimeTrialResult;

import java.util.Arrays;

public class TimeTrialActivity extends PracticeBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
        super.onCreate(savedInstanceState);
        mCountDownTimer = new CountDownTimer(120000, 1000) {
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

        mDrawView.setBitmapFromText(mPracticeString);
        mDrawView.canVibrate(true);
        mCountDownTimer.start();
        } catch (Exception e) {
            showErrorDialog(e);
        }
    }

    @Override
    public void practiceActivityOnClick(View v) {
        super.practiceActivityOnClick(v);
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
                        mPracticeString = SplashActivity.CHARACTER_LIST[index + 1];
                        mDrawView.setBitmapFromText(mPracticeString);
                    }
                }
                break;
        }
    }
}
