package org.buildmlearn.practicehandwriting.activities.selection;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;

import org.buildmlearn.practicehandwriting.R;
import org.buildmlearn.practicehandwriting.activities.practice.TimeTrialActivity;
import org.buildmlearn.practicehandwriting.helper.display.Animator;

/**
 * Activity that displayes the main menu where the user chooses his mode of practice.
 */
public class MainMenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        setContentView(R.layout.activity_main_menu);
        //adding zoom in animation for the buttons
        final int[] buttons = new int[] {R.id.character_button,R.id.word_button,R.id.timetrial_button,R.id.freehand_button};
        for(int i=0;i<buttons.length;i++) {
            Animation animation = Animator.createScaleUpCompleteAnimation();
            animation.setStartOffset(500 * i);
            findViewById(buttons[i]).startAnimation(animation);
        }
    }

    /**
     * The onClick function for the buttons in this activity
     * @param view The view that called the function
     */
    public void mainMenuActivityOnClick(View view) {
        Intent intent = null;
        switch(view.getId()) {
            case R.id.character_button:
                intent = new Intent(this, CharacterSelectionActivity.class);
                intent.putExtra(getResources().getString(R.string.practice_mode),getResources().getString(R.string.practice));
                break;

            case R.id.word_button:
                intent = new Intent(this, WordSelectionActivity.class);
                break;

            case R.id.freehand_button:
                intent = new Intent(this, CharacterSelectionActivity.class);
                intent.putExtra(getResources().getString(R.string.practice_mode),getResources().getString(R.string.freehand));
                break;

            case R.id.timetrial_button:
                intent = new Intent(this, TimeTrialActivity.class);
                break;
        }
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(MainMenuActivity.this, LanguageActivity.class));
    }
}
