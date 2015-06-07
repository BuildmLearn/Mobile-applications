package org.buildmlearn.practicehandwriting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class LanguageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        //TODO add animation for buttons
    }

    public void languageActivityOnClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.english_button:
                intent = new Intent(this,MainMenuActivity.class);
                break;

            case R.id.hindi_button:
                intent = new Intent(this,IncompleteActivity.class);
                break;

            case R.id.arabic_button:
                intent = new Intent(this,IncompleteActivity.class);
                break;
        }
        startActivity(intent);
    }
}
