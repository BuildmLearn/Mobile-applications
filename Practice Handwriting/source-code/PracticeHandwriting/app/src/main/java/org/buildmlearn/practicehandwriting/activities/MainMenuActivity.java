package org.buildmlearn.practicehandwriting.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.buildmlearn.practicehandwriting.R;


public class MainMenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        //TODO add animation
    }

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
                intent.putExtra(getResources().getString(R.string.practice_string),SplashActivity.CHARACTER_LIST[0]);
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
