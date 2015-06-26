package org.buildmlearn.practicehandwriting.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.buildmlearn.practicehandwriting.R;
import org.buildmlearn.practicehandwriting.helper.DrawingView;
import org.buildmlearn.practicehandwriting.helper.TimeTrialResult;

import java.util.ArrayList;

public class TimeTrialResultActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_trial_result);
        LinearLayout resultLL = (LinearLayout) findViewById(R.id.resultLL);
        LinearLayout resultMainLL = (LinearLayout) findViewById(R.id.resultMainLL);

        for(int i = 0;i<SplashActivity.mTimeTrialResults.size();i++) {
            final int index = i;
            final View result = getLayoutInflater().inflate(R.layout.layout_result,resultMainLL,false);
            final DrawingView drawingView = ((DrawingView)  result.findViewById(R.id.resultDrawingView));//width and height of this are zero if not hardcoded in the xml
            drawingView.setLayoutParams(new RelativeLayout.LayoutParams(getResources().getDisplayMetrics().widthPixels, ViewGroup.LayoutParams.WRAP_CONTENT));
            drawingView.setBitmapFromText(SplashActivity.mTimeTrialResults.get(index).getPracticeString());
            Bitmap b = drawingView.getCanvasBitmap();
            int size = SplashActivity.mTimeTrialResults.get(index).getTouches().get(0).size();
            for(int j = 0; j < size;j++) {
                int x = SplashActivity.mTimeTrialResults.get(index).getTouches().get(0).get(j);
                int y = SplashActivity.mTimeTrialResults.get(index).getTouches().get(1).get(j);
                for(int p=x-5;p<=x+5;p++)
                    for(int q=y-5;q<=y+5;q++)
                        b.setPixel(p,q, Color.RED);
            }
            drawingView.setBitmap(b);
            ((TextView) result.findViewById(R.id.resultTextView)).setText(String.valueOf(drawingView.score()));
            resultLL.addView(result);
/*
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //View view = resultLL.getChildAt(index);

                    drawingView.canDraw(true);
                    for(MotionEvent e : SplashActivity.mTimeTrialResults.get(index).getTouches())
                        drawingView.dispatchTouchEvent(e);
                    drawingView.canDraw(false);
                    ((TextView)  result.findViewById(R.id.resultTextView)).setText(String.valueOf(drawingView.score()));
                }
            });.start();*/
        }
    }

    @Override
    public void onBackPressed() {
        SplashActivity.mTimeTrialResults = new ArrayList<TimeTrialResult>(SplashActivity.CHARACTER_LIST.length);
        finish();
        startActivity(new Intent(TimeTrialResultActivity.this, MainMenuActivity.class));
    }
}
