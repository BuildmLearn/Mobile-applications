package org.buildmlearn.practicehandwriting.testing;

import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import org.buildmlearn.practicehandwriting.R;
import org.buildmlearn.practicehandwriting.activities.information.SplashActivity;
import org.buildmlearn.practicehandwriting.helper.practice.PracticeBaseActivity;
import org.buildmlearn.practicehandwriting.helper.background.ScoreDbHelper;

/**
 * Base class to perform UI tests
 */
public class PracticeBaseActivityTest extends ActivityInstrumentationTestCase2 implements TextToSpeech.OnInitListener {

    /**
     * The Robotium Solo instance that will interact with the screen
     */
    protected Solo solo;

    /**
     * An instance of the activity that is being tested
     */
    protected PracticeBaseActivity activity;

    /**
     * The context in which the testing is occurring
     */
    protected Context mContext;

    /**
     * Boolean variable to notify us when the TTS has been initializeds
     */
    protected boolean mTTSinit;

    /**
     * Constructor
     * @param activityClass Class of the Activity being tested
     */
    public PracticeBaseActivityTest(Class activityClass) {
        super(activityClass);
    }

    @Override
    protected void setUp() {
        mContext = getInstrumentation().getTargetContext().getApplicationContext();
        Intent intent = new Intent();
        intent.putExtra(mContext.getString(R.string.practice_string), "A");
        setActivityIntent(intent);
        SplashActivity.mDbHelper = new ScoreDbHelper(mContext.getApplicationContext());
        SplashActivity.mDisplayMetrics = mContext.getResources().getDisplayMetrics();
        mTTSinit = false;
        try {
            SplashActivity.TTSobj = new TextToSpeech(mContext,this);
        } catch (Exception e) {
            SplashActivity.TTSobj = null;
            mTTSinit = true;
        }
        while(!mTTSinit);
        SplashActivity.CHARACTER_LIST = mContext.getResources().getStringArray(R.array.Characters);
        SplashActivity.EASY_WORD_LIST = mContext.getResources().getStringArray(R.array.Words_easy);
        SplashActivity.MEDIUM_WORD_LIST = mContext.getResources().getStringArray(R.array.Words_medium);
        SplashActivity.HARD_WORD_LIST = mContext.getResources().getStringArray(R.array.Words_hard);
        solo = new Solo(getInstrumentation(), getActivity());
        activity = (PracticeBaseActivity) getActivity();
    }

    @Override
    protected void tearDown() {
        SplashActivity.TTSobj.stop();
        SplashActivity.TTSobj.shutdown();
        solo.finishOpenedActivities();
    }

    @Override
    public void onInit(int status) {
        if(status!=TextToSpeech.SUCCESS)
            SplashActivity.TTSobj = null;
        mTTSinit = true;
    }

    /**
     * Test for pressing the done button. This is overridden and called from the derived class
     */
    public void testDonePress() {
        solo.clickOnView(solo.getView(R.id.done_save_button));
        solo.sleep(1000);
    }
}