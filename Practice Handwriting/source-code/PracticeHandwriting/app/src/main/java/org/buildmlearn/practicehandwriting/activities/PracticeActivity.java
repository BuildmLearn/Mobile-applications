package org.buildmlearn.practicehandwriting.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.software.shell.fab.ActionButton;

import org.buildmlearn.practicehandwriting.helper.Animator;
import org.buildmlearn.practicehandwriting.helper.CalculateFreehandScore;
import org.buildmlearn.practicehandwriting.R;
import org.buildmlearn.practicehandwriting.helper.DrawingView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class PracticeActivity extends ActionBarActivity {

    //TODO time dependant vibrations, make practice string width wider, add comments

    private Bitmap mDrawViewBitmap;
    private DrawingView mDrawView;
    private boolean mDone;
    private String practice_string;
    private String practice_mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        findViewById(R.id.PracticeToolbar).bringToFront();

        Toolbar toolbar = (Toolbar) findViewById(R.id.PracticeToolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_launcher);

        Intent intent = getIntent();
        practice_string = intent.getStringExtra(getResources().getString(R.string.practice_string));
        practice_mode = intent.getStringExtra(getResources().getString(R.string.practice_mode));

        mDrawView = (DrawingView) findViewById(R.id.drawing);
        TextView canvasText = (TextView) findViewById(R.id.canvas);

        canvasText.setText(practice_string);
        canvasText.setTextSize(1.4f * getResources().getDisplayMetrics().densityDpi / practice_string.length());
        mDone = false;

        mDrawViewBitmap = takeScreenshot(R.id.canvas);
        if(practice_mode.equals(getResources().getString(R.string.practice))) {
            mDrawView.setBitmap(mDrawViewBitmap);
            mDrawView.canVibrate(true);
        } else if(practice_mode.equals(getResources().getString(R.string.freehand))) {
            mDrawView.canVibrate(false);
        }
        canvasText.setVisibility(View.INVISIBLE);

        if(Build.VERSION.SDK_INT>=21)
            SplashActivity.TTSobj.speak(practice_string, TextToSpeech.QUEUE_FLUSH,null, null);
        else
            SplashActivity.TTSobj.speak(practice_string,TextToSpeech.QUEUE_FLUSH,null);
    }

    private Bitmap takeScreenshot(int ResourceID) {
        View view = findViewById(ResourceID);
        //getting size of view
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        //selecting the part to be saved
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache()); // actually taking the screen shot
        view.setDrawingCacheEnabled(false);
        return bitmap;
    }

    public void practiceActivityOnClick(View v) {
        switch (v.getId()) {
            case R.id.reset_button:
                if(mDone) {
                    mDrawView.startAnimation(Animator.createScaleUpAnimation());

                    findViewById(R.id.scoreView).setAnimation(Animator.createFadeOutAnimation());

                    Animator.createYFlipForwardAnimation(findViewById(R.id.done_save_button));
                    ((ActionButton) findViewById(R.id.done_save_button)).setImageResource(R.drawable.ic_done);
                    Animator.createYFlipBackwardAnimation(findViewById(R.id.done_save_button));

                    mDone = false;
                }
                mDrawView.setupDrawing();

                if(practice_mode.equals(getResources().getString(R.string.practice)))
                    mDrawView.setBitmap(mDrawViewBitmap);
                else if(practice_mode.equals(getResources().getString(R.string.freehand)))
                    mDrawView.clearCanvas();
                break;

            case R.id.done_save_button:
                if (!mDone) {
                    if(practice_mode.equals(getResources().getString(R.string.freehand))) {
                        new CalculateFreehandScore(this, mDrawView,mDrawViewBitmap).execute();

                    } else if(practice_mode.equals(getResources().getString(R.string.practice))) {
                        mDrawView.startAnimation(Animator.createScaleDownAnimation());

                        ((TextView) findViewById(R.id.scoreView)).setText("Score: " + String.valueOf(mDrawView.score()));
                        findViewById(R.id.scoreView).setAnimation(Animator.createFadeInAnimation());

                        Animator.createYFlipForwardAnimation(findViewById(R.id.done_save_button));
                        ((ActionButton) findViewById(R.id.done_save_button)).setImageResource(R.drawable.ic_save);
                        Animator.createYFlipBackwardAnimation(findViewById(R.id.done_save_button));
                    }
                    mDrawView.canDraw(false);
                    mDone = true;

                } else {
                    File mediaStorageDir = new File(Environment.getExternalStorageDirectory() + File.separator + getResources().getString(R.string.app_name));
                    if (!mediaStorageDir.exists() && !mediaStorageDir.mkdir()) {
                        Toast.makeText(getApplicationContext(), "Could not save trace. Unable to create directory", Toast.LENGTH_SHORT).show();
                    } else {
                        DateFormat.getDateTimeInstance();
                        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                        File file = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + practice_string + "_" + timeStamp + ".jpg");

                        DrawingView drawView = (DrawingView) findViewById(R.id.drawing);
                        drawView.setDrawingCacheEnabled(true);
                        Bitmap savedImg = drawView.getDrawingCache();

                        FileOutputStream fOut;
                        try {
                            fOut = new FileOutputStream(file);
                            savedImg.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
                            fOut.flush();
                            fOut.close();
                            Toast.makeText(getApplicationContext(), "Trace saved", Toast.LENGTH_SHORT).show();
                        } catch (FileNotFoundException e) {
                            Toast.makeText(getApplicationContext(), "Could not save trace. Unable to open file", Toast.LENGTH_SHORT).show();
                            file.delete();
                        } catch (IOException e) {
                            Toast.makeText(getApplicationContext(), "Could not save trace. Unable to save file", Toast.LENGTH_SHORT).show();
                            file.delete();
                        }
                    }
                }
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = getIntent();
        String next = practice_string;
        if(practice_string.length()>1) {
            if(practice_string.length() < 5)
                next = SplashActivity.EASY_WORD_LIST[new Random().nextInt(SplashActivity.EASY_WORD_LIST.length)];
            else if(practice_string.length()>=5 && practice_string.length()<7)
                next = SplashActivity.MEDIUM_WORD_LIST[new Random().nextInt(SplashActivity.MEDIUM_WORD_LIST.length)];
            else if(practice_string.length()>=7)
                next = SplashActivity.HARD_WORD_LIST[new Random().nextInt(SplashActivity.HARD_WORD_LIST.length)];
        } else {
            switch (item.getItemId()) {
                case R.id.action_next:
                    if (!practice_string.equals(SplashActivity.CHARACTER_LIST[SplashActivity.CHARACTER_LIST.length - 1]))
                        next = SplashActivity.CHARACTER_LIST[Arrays.asList(SplashActivity.CHARACTER_LIST).indexOf(practice_string) + 1];
                    else
                        next = SplashActivity.CHARACTER_LIST[0];
                    break;

                case R.id.action_previous:
                    if (!practice_string.equals(SplashActivity.CHARACTER_LIST[0]))
                        next = SplashActivity.CHARACTER_LIST[Arrays.asList(SplashActivity.CHARACTER_LIST).indexOf(practice_string) - 1];
                    else
                        next = SplashActivity.CHARACTER_LIST[SplashActivity.CHARACTER_LIST.length - 1];
                    break;
            }
        }
        intent.putExtra(getResources().getString(R.string.practice_string),next);
        finish();
        startActivity(intent);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}
