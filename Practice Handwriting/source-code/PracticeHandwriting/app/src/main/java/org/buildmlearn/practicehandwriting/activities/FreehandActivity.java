package org.buildmlearn.practicehandwriting.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

import org.buildmlearn.practicehandwriting.R;
import org.buildmlearn.practicehandwriting.helper.CalculateFreehandScore;
import org.buildmlearn.practicehandwriting.helper.PracticeBaseActivity;

public class FreehandActivity extends PracticeBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDrawView.canVibrate(false);
        mDrawView.canScore(false);
        SharedPreferences wmbPreference = PreferenceManager.getDefaultSharedPreferences(this);
        isFirstRun = wmbPreference.getBoolean("FIRSTFREEHAND", true);
        if (isFirstRun) {
            SharedPreferences.Editor editor = wmbPreference.edit();
            editor.putBoolean("FIRSTFREEHAND", false);
            editor.apply();
            new ShowcaseView.Builder(this)
                    .setTarget(new ViewTarget(R.id.drawing, this))
                    .setContentTitle("")
                    .setContentText(getString(R.string.freehandHelp))
                    .setStyle(R.style.CustomShowcaseTheme)
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ((ShowcaseView) view.getParent()).hide();
                            System.gc();
                        }
                    })
                    .build();
        }
    }

    @Override
    public void practiceOnClick(View v) {
        super.practiceOnClick(v);
        switch (v.getId()) {
            case R.id.reset_button:
                mDrawView.init();
                mDrawView.canVibrate(false);
                mDrawView.canScore(false);
                break;
            case R.id.done_save_button:
                if(!mDone) {
                    mDone = true;
                    //Using an asyncTask to calculate the score inorder to reduce the amount of computation done on UI thread
                    new CalculateFreehandScore(this, mDrawView, mPracticeString).execute();
                }
                break;
        }
    }
}
