package org.buildmlearn.practicehandwriting.activities.information;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.util.DisplayMetrics;
import android.widget.Toast;

import org.buildmlearn.practicehandwriting.R;
import org.buildmlearn.practicehandwriting.activities.selection.LanguageActivity;
import org.buildmlearn.practicehandwriting.helper.background.ScoreDbHelper;

/**
 * Splash Screen Activity
 */
public class SplashActivity extends Activity implements TextToSpeech.OnInitListener{
    //All the public static fields are going to be used throughout the app. They are initialized here to prevent recurring initialization while using the app.


    private static final int MY_DATA_CHECK_CODE = 100;

    /**
     * The TTS object that shall be speaking throughout the app
     */
    public static TextToSpeech TTSobj;

    /**
     * The list of characters that are available to the user
     */
    public static String[] CHARACTER_LIST;

    /**
     * The list of words of easy difficulty that are available to the user
     */
    public static String[] EASY_WORD_LIST;

    /**
     * The list of words of medium difficulty that are available to the user
     */
    public static String[] MEDIUM_WORD_LIST;

    /**
     * The list of words of hard difficulty that are available to the user
     */
    public static String[] HARD_WORD_LIST;

    /**
     * Boolean variable that is set to true when the TTS is initialized
     */
    private boolean mTtsInitDone;

    /**
     * Helper object to assist with writing and reading from the score database
     */
    public static ScoreDbHelper mDbHelper;

    /**
     * Display metrics of the device screen
     */
    public static DisplayMetrics mDisplayMetrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        setContentView(R.layout.activity_splash);
        mTtsInitDone = false;

        SharedPreferences wmbPreference = PreferenceManager.getDefaultSharedPreferences(this);
        final boolean isFirstRun = wmbPreference.getBoolean("FIRSTRUN", true);
        if (isFirstRun) {
            SharedPreferences.Editor editor = wmbPreference.edit();
            editor.putBoolean("FIRSTRUN", false);
            editor.apply();
        }

        //try catch block as action TextToSpeech.Engine.ACTION_CHECK_TTS_DATA might not be present if no TTS engine is present
        try {
            Intent checkIntent = new Intent();
            checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
            startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this,"App will not speak out as no TTS Engine was found",Toast.LENGTH_LONG).show();
            TTSobj = null;
            onInit(0);
        }

        mDbHelper = new ScoreDbHelper(this);
        mDisplayMetrics = getResources().getDisplayMetrics();

            //Starts the next activity only after all the inits ar done.
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(!(mTtsInitDone));
                if(!isFirstRun)
                    startActivity(new Intent(SplashActivity.this, LanguageActivity.class));
                else
                    startActivity(new Intent(SplashActivity.this, TutorialActivity.class));
            }
        }).start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == MY_DATA_CHECK_CODE) {

            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                // success, create the TTS instance
                TTSobj = new TextToSpeech(this, this);
            }
            else {
                // missing data, install it
                try {
                    Intent installIntent = new Intent();
                    installIntent.setAction(
                            TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                    startActivity(installIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(this,"App will not speak out as no TTS Engine was found",Toast.LENGTH_LONG).show();
                    TTSobj = null;
                    onInit(0);
                }
            }
        }
    }

    @Override
    public void onInit(int status) {
        try {
            //show the splash screen for sometime before proceeding.
            int SPLASH_TIME_OUT = 1500;
            Thread.sleep(SPLASH_TIME_OUT);
            mTtsInitDone = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        //releasing the TTS resources
        if (TTSobj != null) {
            TTSobj.stop();
            TTSobj.shutdown();
        }
        super.onDestroy();
    }
}
