package org.buildmlearn.practicehandwriting.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.buildmlearn.practicehandwriting.R;
import org.buildmlearn.practicehandwriting.helper.DrawingView;
import org.buildmlearn.practicehandwriting.helper.TimeTrialResult;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TimeTrialResultActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_trial_result);
        LinearLayout resultLL = (LinearLayout) findViewById(R.id.resultLL);
        findViewById(R.id.TimeTrialToolbar).bringToFront();

        for(int i = 0;i<SplashActivity.mTimeTrialResults.size();i++) {
            final int index = i;
            final View result = View.inflate(this,R.layout.layout_result,null);
            final DrawingView drawingView = ((DrawingView)  result.findViewById(R.id.resultDrawingView));
            drawingView.setBitmapFromText(SplashActivity.mTimeTrialResults.get(index).getPracticeString());
            drawingView.setLayoutParams(new RelativeLayout.LayoutParams(getResources().getDisplayMetrics().widthPixels, drawingView.getTextHeight()));
            drawingView.canVibrate(false);
            resultLL.addView(result);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    int size = SplashActivity.mTimeTrialResults.get(index).getTouches().size();
                    for(int j = 0; j < size;j++) {
                        ArrayList<Point> touchPoints = SplashActivity.mTimeTrialResults.get(index).getTouches().get(j);
                        drawingView.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(),
                                MotionEvent.ACTION_DOWN,
                                touchPoints.get(0).x, touchPoints.get(0).y, 0));
                        for (int k = 1; k < touchPoints.size() -1 ; k++) {
                            drawingView.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),SystemClock.uptimeMillis(),
                                    MotionEvent.ACTION_MOVE,
                                    touchPoints.get(k).x,touchPoints.get(k).y, 0));
                        }
                        drawingView.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),SystemClock.uptimeMillis(),
                                MotionEvent.ACTION_UP,
                                touchPoints.get(touchPoints.size() - 1).x,touchPoints.get(touchPoints.size() - 1).y, 0));
                    }
                    drawingView.canDraw(false);
                    ((TextView) result.findViewById(R.id.resultTextView)).setText(String.valueOf(drawingView.score()));
                }
            }).start();
        }
    }

    public void saveTimeTrial(View view) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String toastText = "";
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory() + File.separator + getResources().getString(R.string.app_name) + File.separator + "TIME_TRIAL_" + timeStamp );
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdir()) {
            toastText = "Could not save trace. Unable to create directory";
        } else {
            LinearLayout resultLL = (LinearLayout) findViewById(R.id.resultLL);
            for(int i = 0; i < resultLL.getChildCount(); i++) {

                File file = new File(mediaStorageDir.getPath() + File.separator + SplashActivity.mTimeTrialResults.get(i).getPracticeString() + ".jpg");
                Bitmap savedImg = ((DrawingView)  resultLL.getChildAt(i).findViewById(R.id.resultDrawingView)).getCanvasBitmap();
                FileOutputStream fOut;

                try {
                    fOut = new FileOutputStream(file);
                    savedImg.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
                    fOut.flush();
                    fOut.close();
                    toastText = "Traces saved";
                } catch (FileNotFoundException e) {
                    toastText = "Could not save trace. Unable to open file";
                    file.delete();
                } catch (IOException e) {
                    toastText = "Could not save trace. Unable to save file";
                    file.delete();
                }
            }
        }
        Toast.makeText(this,toastText,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        SplashActivity.mTimeTrialResults = new ArrayList<TimeTrialResult>(SplashActivity.CHARACTER_LIST.length);
        finish();
        startActivity(new Intent(TimeTrialResultActivity.this, MainMenuActivity.class));
    }
}
