package com.buildmlearn.labeldiagram;

import com.example.labelthediagram.R;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class MainMenu extends Activity {
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        
        TextView diagramView = (TextView) findViewById(R.id.title_diagram);
        TextView startDigrm = (TextView) findViewById(R.id.start_diagram);
        TextView scoreboardView = (TextView) findViewById(R.id.title_scoreboard);
        TextView startScore = (TextView) findViewById(R.id.start_screboard);
        TextView lessonView = (TextView) findViewById(R.id.title_lesson);
        TextView startLesson = (TextView) findViewById(R.id.start_lesson);
        TextView badgeView = (TextView) findViewById(R.id.title_badge);
        TextView startbadges = (TextView) findViewById(R.id.start_badge);
        TextView settingsView = (TextView) findViewById(R.id.title_settings);
        TextView startSettings = (TextView) findViewById(R.id.start_settings);
        TextView helpView = (TextView) findViewById(R.id.title_help);
        TextView startHelp = (TextView) findViewById(R.id.start_help);
        
        Typeface tfThin = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Thin.ttf");
        Typeface tfLight = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        
        diagramView.setTypeface(tfThin);
        startDigrm.setTypeface(tfLight);
        scoreboardView.setTypeface(tfThin);
        startScore.setTypeface(tfLight);
        lessonView.setTypeface(tfThin);
        startLesson.setTypeface(tfLight);
        badgeView.setTypeface(tfThin);
        startbadges.setTypeface(tfLight);
        settingsView.setTypeface(tfThin);
        startSettings.setTypeface(tfLight);
        helpView.setTypeface(tfThin);
        startHelp.setTypeface(tfLight);
        
    }

}
