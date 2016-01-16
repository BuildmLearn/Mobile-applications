package org.buildmlearn.practicehandwriting.helper.practice;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.software.shell.fab.ActionButton;

import org.buildmlearn.practicehandwriting.R;
import org.buildmlearn.practicehandwriting.activities.practice.FreehandActivity;
import org.buildmlearn.practicehandwriting.activities.information.SplashActivity;
import org.buildmlearn.practicehandwriting.helper.display.Animator;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * Base class for activities where the user would practice
 */
public class PracticeBaseActivity extends ActionBarActivity {

    /**
     * DrawingView on which the user shall trace
     */
    protected DrawingView mDrawView;

    /**
     * Boolean variable set if the user has finished tracing
     */
    protected boolean mDone;

    /**
     * String to be practiced
     */
    protected String mPracticeString;

    /**
     * TextView to display the current score/time left in a time trial
     */
    protected TextView mScoreTimerView;

    /**
     * TextViews to display the best score for a given string
     */
    protected TextView mBestScoreView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            System.gc();
            super.onCreate(savedInstanceState);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
            setContentView(R.layout.activity_practice);

            //Initializing all the variables
            Toolbar toolbar = (Toolbar) findViewById(R.id.PracticeToolbar);
            mBestScoreView = (TextView) findViewById(R.id.best_score_View);
            mScoreTimerView = (TextView) findViewById(R.id.score_and_timer_View);
            mDrawView = (DrawingView) findViewById(R.id.drawing);
            mPracticeString = getIntent().getStringExtra(getResources().getString(R.string.practice_string));
            mDone = false;

            findViewById(R.id.reset_button).setAnimation(Animator.createSlideInFromBottom());
            findViewById(R.id.done_save_button).setAnimation(Animator.createSlideInFromBottom());
            //Setting the toolbar as the actionbar
            setSupportActionBar(toolbar);
            toolbar.bringToFront();
            toolbar.setNavigationIcon(R.drawable.ic_launcher);

            mScoreTimerView.bringToFront();

            //speaking the string to be practiced
            if(SplashActivity.TTSobj!=null) {
                if (Build.VERSION.SDK_INT >= 21) //This function works only on devices with SDK version greater that 20
                    SplashActivity.TTSobj.speak(mPracticeString, TextToSpeech.QUEUE_FLUSH, null, null);
                else //if the device is running an older version of android, use the previous speaking function
                    SplashActivity.TTSobj.speak(mPracticeString, TextToSpeech.QUEUE_FLUSH, null);
            }
        } catch (Exception e) {
            showErrorDialog(e);
        }
    }

    /**
     * The onClick function the reset button and the done/save button. It is overridden in the derived classes but called by them using super
     * @param v the button that called the function
     */
    public void practiceOnClick(View v) {
        switch (v.getId()) {
            case R.id.reset_button:
                if(mDone) {
                    //Undo the animations performed when the user was done
                    mBestScoreView.setAnimation(Animator.createFadeOutAnimation());
                    mDrawView.startAnimation(Animator.createScaleUpAnimation());
                    mScoreTimerView.setAnimation(Animator.createFadeOutAnimation());

                    Animator.createYFlipForwardAnimation(findViewById(R.id.done_save_button));
                    ((ActionButton) findViewById(R.id.done_save_button)).setImageResource(R.drawable.ic_done);
                    Animator.createYFlipBackwardAnimation(findViewById(R.id.done_save_button));

                    mDone = false; //implies that the user isn't done
                }
                mDrawView.init();//reinitialize the view
                break;

            case R.id.done_save_button:
                if(mDone) {//if the user is done then save the trace
                    String result = mDrawView.saveBitmap(mPracticeString,"");
                    if(result.charAt(0)=='/')
                        result = "Trace Saved";
                    Toast.makeText(this,result,Toast.LENGTH_SHORT).show();//Toast displayed with the status of saving the trace
                }
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!mDone) {
            if (mPracticeString.length() > 1) {//if the string is a word then give a random when the for the previous/next button is clicked
                if (mPracticeString.length() < 5)
                    mPracticeString = SplashActivity.EASY_WORD_LIST[new Random().nextInt(SplashActivity.EASY_WORD_LIST.length)];
                else if (mPracticeString.length() >= 5 && mPracticeString.length() < 7)
                    mPracticeString = SplashActivity.MEDIUM_WORD_LIST[new Random().nextInt(SplashActivity.MEDIUM_WORD_LIST.length)];
                else if (mPracticeString.length() >= 7)
                    mPracticeString = SplashActivity.HARD_WORD_LIST[new Random().nextInt(SplashActivity.HARD_WORD_LIST.length)];
            } else { //If the string is a character, return the next or previous character in a circular manner (next of the last character is the first character and vice versa)
                int next = Arrays.asList(SplashActivity.CHARACTER_LIST).indexOf(mPracticeString);
                switch (item.getItemId()) {
                    case R.id.action_next:
                        next = (next + 1) % SplashActivity.CHARACTER_LIST.length;
                        break;

                    case R.id.action_previous:
                        next = (next + SplashActivity.CHARACTER_LIST.length - 1) % SplashActivity.CHARACTER_LIST.length;
                        break;
                }
                mPracticeString = SplashActivity.CHARACTER_LIST[next];
            }

            if(!(this instanceof FreehandActivity))
                mDrawView.setBitmapFromText(mPracticeString);
            if (SplashActivity.TTSobj != null) {
                if (Build.VERSION.SDK_INT >= 21) //This function works only on devices with SDK version greater that 20
                    SplashActivity.TTSobj.speak(mPracticeString, TextToSpeech.QUEUE_FLUSH, null, null);
                else //if the device is running an older version of android, use the previous speaking function
                    SplashActivity.TTSobj.speak(mPracticeString, TextToSpeech.QUEUE_FLUSH, null);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Function to show the error that occurred while starting the practice session
     * @param e The exception that was caught
     */
    protected void showErrorDialog(final Exception e) {
        new AlertDialog.Builder(this)
                .setTitle("ERROR")
                .setMessage(Log.getStackTraceString(e))
                .setPositiveButton("Save to File", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            File mediaStorageDir = new File(Environment.getExternalStorageDirectory() + File.separator + getResources().getString(R.string.app_name));
                            File file = new File(mediaStorageDir.getPath() + File.separator + "error.txt");
                            if(file.exists() || file.createNewFile()) {
                                FileOutputStream fOut = new FileOutputStream(file, true);
                                fOut.write(("\n\n" + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()).format(new Date()) + "\n\n").getBytes());
                                fOut.write(Log.getStackTraceString(e).getBytes());
                                fOut.flush();
                                fOut.close();
                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        System.gc();
        mDrawView.destroyBitmap();
        super.onBackPressed();
    }

    /**
     * Function to get the string that is being practiced
     * @return The string that is being practiced
     */
    public String getPracticeString() {
        return mPracticeString;
    }

    /**
     * Function to get the DrawingView that is being traced upon
     * @return The DrawingView that is being traced upon
     */
    public DrawingView getDrawingView() {
        return mDrawView;
    }
}