package org.buildmlearn.practicehandwriting.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import com.software.shell.fab.ActionButton;

import org.buildmlearn.practicehandwriting.R;
import org.buildmlearn.practicehandwriting.helper.Animator;
import org.buildmlearn.practicehandwriting.helper.PracticeBaseActivity;

//Activity for normal practice of characters and words
public class PracticeActivity extends PracticeBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            SharedPreferences wmbPreference = PreferenceManager.getDefaultSharedPreferences(this);
            isFirstRun = wmbPreference.getBoolean("FIRSTPRACTICE", true);
            if (isFirstRun) {
                SharedPreferences.Editor editor = wmbPreference.edit();
                editor.putBoolean("FIRSTPRACTICE", false);
                editor.apply();
            }

            super.onCreate(savedInstanceState);
            mDrawView.setBitmapFromText(mPracticeString);
            mDrawView.canVibrate(true);
            if(isFirstRun)
                new ShowcaseView.Builder(PracticeActivity.this)
                        .setTarget(new ViewTarget(R.id.drawing, PracticeActivity.this))
                        .setContentTitle("")
                        .setContentText(getString(R.string.traceHelp))
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ((ShowcaseView) view.getParent()).hide();
                                new ShowcaseView.Builder(PracticeActivity.this)
                                        .setTarget(new ViewTarget(R.id.done_save_button, PracticeActivity.this))
                                        .setContentTitle("")
                                        .setContentText(getString(R.string.doneHelp))
                                        .setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                ((ShowcaseView) view.getParent()).hide();
                                                new ShowcaseView.Builder(PracticeActivity.this)
                                                        .setTarget(new ViewTarget(R.id.reset_button, PracticeActivity.this))
                                                        .setContentTitle("")
                                                        .setContentText(getString(R.string.resetHelp))
                                                        .setOnClickListener(new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View view) {
                                                                ((ShowcaseView) view.getParent()).hide();
                                                                new ShowcaseView.Builder(PracticeActivity.this)
                                                                        .setTarget(new ViewTarget(R.id.action_next, PracticeActivity.this))
                                                                        .setContentTitle("")
                                                                        .setContentText(getString(R.string.actionbarHelp))
                                                                        .build();
                                                            }
                                                        })
                                                        .build();
                                            }
                                        })
                                        .build();
                            }
                        })
                        .build();
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
                    if(isFirstRun)
                        new ShowcaseView.Builder(this)
                                .setTarget(new ViewTarget(R.id.done_save_button, this))
                                .setContentTitle("")
                                .setContentText(getString(R.string.saveHelp))
                                .build();
                }
                break;
        }
    }
}
