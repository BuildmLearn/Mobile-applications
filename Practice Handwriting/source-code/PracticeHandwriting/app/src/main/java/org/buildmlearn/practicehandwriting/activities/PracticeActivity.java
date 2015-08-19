package org.buildmlearn.practicehandwriting.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.software.shell.fab.ActionButton;

import org.buildmlearn.practicehandwriting.R;
import org.buildmlearn.practicehandwriting.helper.Animator;
import org.buildmlearn.practicehandwriting.helper.PracticeBaseActivity;

/**
 * Activity for normal practice of characters and words
 */
public class PracticeActivity extends PracticeBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            mDrawView.setBitmapFromText(mPracticeString);
            mDrawView.canVibrate(true);
        } catch (Exception e) {
            showErrorDialog(e);
        }
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
                    // getting the best score for the given letter and updating it if the current score is better
                    float best = SplashActivity.mDbHelper.getScore(mPracticeString);
                    if (best < mDrawView.score()) {
                        best = mDrawView.score();
                        SplashActivity.mDbHelper.writeScore(mPracticeString,best);
                    }

                    //Animations for when the user is done with the trace
                    mDrawView.startAnimation(Animator.createScaleDownAnimation());
                    findViewById(R.id.best_score_View).bringToFront();
                    ((TextView) findViewById(R.id.best_score_View)).setText("Best: " + String.valueOf(best));
                    mScoreTimerView.setText("Score: " + String.valueOf(mDrawView.score()));
                    mScoreTimerView.setAnimation(Animator.createFadeInAnimation());
                    mBestScoreView.setAnimation(Animator.createFadeInAnimation());

                    Animator.createYFlipForwardAnimation(findViewById(R.id.done_save_button));
                    ((ActionButton) findViewById(R.id.done_save_button)).setImageResource(R.drawable.ic_save);
                    Animator.createYFlipBackwardAnimation(findViewById(R.id.done_save_button));

                    //User cannot draw anymore on the View
                    mDrawView.canDraw(false);
                    mDone = true;
                }
                break;
        }
    }
}
