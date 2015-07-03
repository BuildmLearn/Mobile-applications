package org.buildmlearn.practicehandwriting.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.software.shell.fab.ActionButton;

import org.buildmlearn.practicehandwriting.R;
import org.buildmlearn.practicehandwriting.helper.Animator;
import org.buildmlearn.practicehandwriting.helper.PracticeBaseActivity;

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
                    float best = SplashActivity.mDbHelper.getScore(mPracticeString);
                    if (best < mDrawView.score()) {
                        best = mDrawView.score();
                        SplashActivity.mDbHelper.writeScore(mPracticeString,best);
                    }

                    mDrawView.startAnimation(Animator.createScaleDownAnimation());
                    findViewById(R.id.best_score_View).bringToFront();
                    ((TextView) findViewById(R.id.best_score_View)).setText("Best: " + String.valueOf(best));
                    mScoreTimerView.setText("Score: " + String.valueOf(mDrawView.score()));
                    mScoreTimerView.setAnimation(Animator.createFadeInAnimation());
                    mBestScoreView.setAnimation(Animator.createFadeInAnimation());

                    Animator.createYFlipForwardAnimation(findViewById(R.id.done_save_button));
                    ((ActionButton) findViewById(R.id.done_save_button)).setImageResource(R.drawable.ic_save);
                    Animator.createYFlipBackwardAnimation(findViewById(R.id.done_save_button));
                    mDrawView.canDraw(false);
                    mDone = true;
                }
                break;
        }
    }
}
