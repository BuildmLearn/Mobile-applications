package org.buildmlearn.practicehandwriting.activities.selection;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import org.buildmlearn.practicehandwriting.R;
import org.buildmlearn.practicehandwriting.activities.practice.FreehandActivity;
import org.buildmlearn.practicehandwriting.activities.practice.PracticeActivity;
import org.buildmlearn.practicehandwriting.activities.information.SplashActivity;

import java.util.Random;

import info.hoang8f.widget.FButton;

/**
 * Activity to let the user choose a character
 */
public class CharacterSelectionActivity extends Activity {

    /**
     *  colours for the buttons
     */
    private int[] colours = {R.color.Green,
            R.color.Orange,
            R.color.Pink,
            R.color.Red,
            R.color.Yellow
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        setContentView(R.layout.activity_character_selection);
        LinearLayout root = (LinearLayout) findViewById(R.id.CharacterLL);
        LinearLayout child = new LinearLayout(this);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int horizontal_padding = (int) getResources().getDimension(R.dimen.activity_horizontal_margin);
        int vertical_padding = (int) getResources().getDimension(R.dimen.activity_vertical_margin);
        //width and height to fit 4 buttons on the screen.
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((size.x-8*horizontal_padding)/4,(size.x-8*horizontal_padding)/4);
        params.setMargins(horizontal_padding, vertical_padding, horizontal_padding, vertical_padding);
        int count = 0;
        for(int i=0;i< SplashActivity.CHARACTER_LIST.length;i++) {
            String s = SplashActivity.CHARACTER_LIST[i];
            FButton fb = new FButton(this);
            fb.setText(s);
            fb.setTextSize(((size.x - 8 * horizontal_padding) / 4) * 1.0f/4.0f);
            fb.setAllCaps(false);
            fb.setButtonColor(getResources().getColor(colours[new Random().nextInt(colours.length)])); //setting a random colour
            fb.setShadowEnabled(false);
            fb.setCornerRadius((size.x - 8 * horizontal_padding) / 4); //radius to fit 4 buttons on the screen
            fb.setLayoutParams(params);
            fb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent;
                    if (getIntent().getStringExtra(getResources().getString(R.string.practice_mode)).equals(getResources().getString(R.string.practice)))
                        intent = new Intent(CharacterSelectionActivity.this, PracticeActivity.class);
                    else //only else and not else if as the value of getIntent().getStringExtra(getResources().getString(R.string.practice_mode)) could take only 2 values
                        intent = new Intent(CharacterSelectionActivity.this, FreehandActivity.class);
                    intent.putExtra(getResources().getString(R.string.practice_string), ((Button) view).getText());
                    startActivity(intent);
                }
            });
            child.addView(fb);

            count++;
            if(count==4) {
                root.addView(child);
                child = new LinearLayout(this);
                count=0;
            }
        }
        root.addView(child);
    }
}
