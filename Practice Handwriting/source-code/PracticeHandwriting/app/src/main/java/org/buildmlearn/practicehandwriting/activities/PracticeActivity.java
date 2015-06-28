package org.buildmlearn.practicehandwriting.activities;

import android.os.Bundle;
import android.view.View;

import com.software.shell.fab.ActionButton;

import org.buildmlearn.practicehandwriting.R;
import org.buildmlearn.practicehandwriting.helper.Animator;

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
    public void practiceActivityOnClick(View v) {
        super.practiceActivityOnClick(v);
        switch (v.getId()) {
            case R.id.reset_button:
                mDrawView.setBitmapFromText(mPracticeString);
                break;
            case R.id.done_save_button:
                if(!mDone) {
                    mDrawView.startAnimation(Animator.createScaleDownAnimation());

                    mScoreTimerView.setText("Score: " + String.valueOf(mDrawView.score()));
                    mScoreTimerView.setAnimation(Animator.createFadeInAnimation());

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
