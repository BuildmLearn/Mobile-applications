package org.buildmlearn.practicehandwriting.activities.practice;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.View;

import org.buildmlearn.practicehandwriting.R;
import org.buildmlearn.practicehandwriting.activities.information.SplashActivity;
import org.buildmlearn.practicehandwriting.helper.practice.PracticeBaseActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

/**
 * Activity for Timed tracing of characters/words
 */
public class TimeTrialActivity extends PracticeBaseActivity {
    /**
     * Timer that counts down till zero
     */
    private CountDownTimer mCountDownTimer;

    /**
     * The temporary directory to store the user's traces
     */
    private String mSaveDir;

    /**
     * Hashmap of the strings that are traced so that they aren't repeated
     */
    private HashMap<String, Boolean> isStringDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            getIntent().putExtra(getResources().getString(R.string.practice_string), randomStringGenerator());//practice session with random strings, kept first since the string is spoken in super
            super.onCreate(savedInstanceState);
            mScoreTimerView.setText("02:00");
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
            mCountDownTimer.start();
        } catch (Exception e) {
            showErrorDialog(e);
        }
    }

    /**
     * Funtion to randomly generate strings from the lists in @SplashActivity
     * @return The next string that the user will trace
     */
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
                    System.gc();
                    mDrawView.saveBitmap(mPracticeString, mSaveDir);
                    mPracticeString = randomStringGenerator();
                    while(isStringDone.containsKey(mPracticeString))
                        mPracticeString = randomStringGenerator();
                    isStringDone.put(mPracticeString,true);
                    mDrawView.destroyDrawingCache();
                    System.gc();
                    mDrawView.init();
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
        System.gc();
        if(mCountDownTimer!=null)
            mCountDownTimer.cancel();//cancel the timer if the user decides to go back
        //Deleting the files in the temp directory and the directory itself
        File tempDir = new File(Environment.getExternalStorageDirectory() + File.separator + getResources().getString(R.string.app_name) + mSaveDir);
        if(tempDir.exists()) {
            for (File file : tempDir.listFiles())
                file.delete();
            tempDir.delete();
        }
        super.onBackPressed();
    }

    public String getSaveDir() {
        return mSaveDir;
    }
}
