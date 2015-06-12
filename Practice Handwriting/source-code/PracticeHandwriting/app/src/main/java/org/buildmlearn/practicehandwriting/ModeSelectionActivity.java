package org.buildmlearn.practicehandwriting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class ModeSelectionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_selection);
    }

    public void modeSelectionOnClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.characters_button:
                intent = new Intent(this, CharacterSelectionActivity.class);
                break;

            case R.id.words_button:
                intent = new Intent(this, WordSelectionActivity.class);
                break;
        }
        startActivity(intent);
        //overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }
}
