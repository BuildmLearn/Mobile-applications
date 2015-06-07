package org.buildmlearn.practicehandwriting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainMenuActivity extends Activity {

    private int PRACTICE_MODE = 0;
    private int TIME_TRIAL_MODE = 0;
    private int FREEHAND_MODE = 0;
    private int _MODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        //TODO add animation
    }

    public void mainMenuActivityOnClick(View view) {
        Intent intent = null;
        switch(view.getId()) {
            case R.id.practice_button:
                intent = new Intent(this, ModeSelectionActivity.class);
                break;

            case R.id.timetrial_button:
                intent = new Intent(this, IncompleteActivity.class);
                break;

            case R.id.freehand_button:
                intent = new Intent(this, IncompleteActivity.class);
                break;

            case R.id.settings_button:
                intent = new Intent(this, IncompleteActivity.class);
                break;
        }

        startActivity(intent);
    }
}
