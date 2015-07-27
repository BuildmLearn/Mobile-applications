package org.buildmlearn.practicehandwriting.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.View;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

import org.buildmlearn.practicehandwriting.R;
import org.buildmlearn.practicehandwriting.helper.PracticeBaseActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

public class TimeTrialActivity extends PracticeBaseActivity {
    private CountDownTimer mCountDownTimer;
    private String mSaveDir;
    private HashMap<String, Boolean> isStringDone;

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
                    mDrawView.saveBitmap(mPracticeString, mSaveDir);
                    Intent intent = new Intent(TimeTrialActivity.this,TimeTrialResultActivity.class);
                    intent.putExtra(getString(R.string.directory_name),mSaveDir);
                    startActivity(intent);
                }
            };
            mSaveDir = File.separator + "TIME_TRIAL_TEMP_" + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
            mDrawView.setBitmapFromText(mPracticeString);
            mDrawView.canVibrate(true);
            isStringDone = new HashMap<>();
            isStringDone.put(mPracticeString,true);

            SharedPreferences wmbPreference = PreferenceManager.getDefaultSharedPreferences(this);
            isFirstRun = wmbPreference.getBoolean("FIRSTTIMETRIAL", true);
            if (isFirstRun) {
                SharedPreferences.Editor editor = wmbPreference.edit();
                editor.putBoolean("FIRSTTIMETRIAL", false);
                editor.apply();

                new ShowcaseView.Builder(TimeTrialActivity.this)
                        .setTarget(new ViewTarget(R.id.score_and_timer_View, TimeTrialActivity.this))
                        .setContentTitle("")
                        .setContentText(getString(R.string.timerHelp))
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ((ShowcaseView) view.getParent()).hide();
                                mCountDownTimer.start();
                            }
                        })
                        .build();
            } else {
                mCountDownTimer.start();
            }
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
                    mDrawView.saveBitmap(mPracticeString,mSaveDir);

                    System.gc();
                    mPracticeString = randomStringGenerator();
                    while(isStringDone.containsKey(mPracticeString))
                        mPracticeString = randomStringGenerator();
                    isStringDone.put(mPracticeString,true);
                    mDrawView.destroyDrawingCache();
                    mDrawView.setBitmapFromText(mPracticeString);
                    if(SplashActivity.TTSobj!=null) {
                        if (Build.VERSION.SDK_INT >= 21) //This function works only on devices with SDK version greater that 20
                            SplashActivity.TTSobj.speak(mPracticeString, TextToSpeech.QUEUE_FLUSH, null, null);
                        else //if the device is running an older version of android, use the previous speaking function
                            SplashActivity.TTSobj.speak(mPracticeString, TextToSpeech.QUEUE_FLUSH, null);
                    }
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
