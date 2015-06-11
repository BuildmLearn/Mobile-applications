package org.buildmlearn.practicehandwriting.practice;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.TextView;

import org.buildmlearn.practicehandwriting.R;
import org.buildmlearn.practicehandwriting.SplashActivity;

public class PracticeActivity extends Activity {//implements TextToSpeech.OnInitListener{

    //TODO add button animation and functionality, make options menu with next and previous, time dependant vibrations, error toast

    private Bitmap drawViewBitmap;
    private DrawingView drawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        Intent intent = getIntent();
        String practice_string = intent.getStringExtra(getResources().getString(R.string.practice_string));
        drawView = (DrawingView) findViewById(R.id.drawing);
        TextView canvasText = (TextView) findViewById(R.id.canvas);

        canvasText.setText(practice_string);
        if(practice_string.length()==1)
            canvasText.setTextSize(500);
        else
            canvasText.setTextSize(700 / practice_string.length());
        drawViewBitmap = takeScreenshot(R.id.canvas);
        drawView.setBitmap(drawViewBitmap);
        canvasText.setVisibility(View.INVISIBLE);

        if(Build.VERSION.SDK_INT>=21)
            SplashActivity.TTSobj.speak(practice_string,TextToSpeech.QUEUE_FLUSH,null, null);
        else
            SplashActivity.TTSobj.speak(practice_string,TextToSpeech.QUEUE_FLUSH,null);
    }

    private Bitmap takeScreenshot(int ResourceID) {
        View v1 = findViewById(ResourceID);
        //getting size of view
        v1.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        //selecting the part to be saved
        v1.layout(0, 0, v1.getMeasuredWidth(), v1.getMeasuredHeight());
        v1.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache()); // actually taking the screen shot
        v1.setDrawingCacheEnabled(false);
        return bitmap;
    }

    public void clear(View v) {
        drawView.setBitmap(drawViewBitmap);
    }
}
