package org.buildmlearn.practicehandwriting;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import org.buildmlearn.practicehandwriting.practice.PracticeActivity;

import java.util.Random;

import info.hoang8f.widget.FButton;


public class CharacterSelectionActivity extends Activity {

    private int[] colours = {R.color.Green,
            R.color.Orange,
            R.color.Pink,
            R.color.Red,
            R.color.Yellow};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_selection);
        LinearLayout root = (LinearLayout) findViewById(R.id.CharacterLL);
        LinearLayout child = new LinearLayout(this);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int horizontal_padding = (int) getResources().getDimension(R.dimen.activity_horizontal_margin);
        int vertical_padding = (int) getResources().getDimension(R.dimen.activity_vertical_margin);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((size.x-8*horizontal_padding)/4,(size.x-8*horizontal_padding)/4);
        params.setMargins(horizontal_padding, vertical_padding, horizontal_padding, vertical_padding);

        int count = 0;
        String[] charSet = SplashActivity.CHARACTER_LIST;
        for(String s: charSet) {

            FButton fb = new FButton(this);
            fb.setText(s);
            fb.setTextSize(40);
            fb.setAllCaps(false);
            fb.setButtonColor(getResources().getColor(colours[new Random().nextInt(colours.length)]));
            fb.setShadowEnabled(false);
            fb.setCornerRadius((size.x - 8 * horizontal_padding) / 4);
            fb.setLayoutParams(params);
            fb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(CharacterSelectionActivity.this, PracticeActivity.class);
                    intent.putExtra(getResources().getString(R.string.practice_string),((Button) view).getText());
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
