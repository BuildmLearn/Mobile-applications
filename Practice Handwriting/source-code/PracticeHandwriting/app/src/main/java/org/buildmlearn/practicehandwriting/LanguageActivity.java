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

    public void gotoMenu(View v) {
        Intent intent = new Intent(this,MainMenuActivity.class);
        startActivity(intent);
    }
}
