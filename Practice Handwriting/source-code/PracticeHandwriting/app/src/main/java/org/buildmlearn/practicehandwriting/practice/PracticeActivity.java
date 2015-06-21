package org.buildmlearn.practicehandwriting.practice;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.AsyncTask;
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

import org.buildmlearn.practicehandwriting.Animator;
import org.buildmlearn.practicehandwriting.R;
import org.buildmlearn.practicehandwriting.SplashActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
                        new CalculateFreehandScore().execute();

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

    private float[] scoreBitmapForFreehand(ArrayList<ArrayList<Integer>> touches, Bitmap canvasBitmap, int centerX, int centerY) {
        int size = touches.get(0).size();
        if(size!=0) {
            int minx = Math.max(Collections.min(touches.get(0)) - 30, 0);
            int miny = Math.max(Collections.min(touches.get(1)) - 30, 0);
            int correctTouches, wrongTouches;
            float score, maxScore = 0, scaleXForMaxScore = 1, scaleYForMaxScore = 1, cxForMaxScore = centerX, cyForMaxScore = centerY;
            ArrayList<Integer> xTouches = touches.get(0), yTouches = touches.get(1);
            outerloop:
            for (float scaleX = 0.8f; scaleX < 1.4f; scaleX += 0.1f)
                for (float scaleY = 0.8f; scaleY < 1.4f; scaleY += 0.1f) {
                    for (int cx = centerX - 20; cx <= centerX + 20; cx += 2)
                        for (int cy = centerY - 20; cy <= centerY + 20; cy += 2) {
                            correctTouches = 0;
                            wrongTouches = 0;
                            for (int i = 0; i < size; i++) {
                                int x = (int) ((xTouches.get(i) - minx) * scaleX) + cx;
                                int y = (int) ((yTouches.get(i) - miny) * scaleY) + cy;
                                try {
                                    if (canvasBitmap.getPixel(x, y) == getResources().getColor(R.color.Black))
                                        correctTouches++;
                                    else
                                        wrongTouches++;
                                } catch (IllegalArgumentException e) { // instead of if conditions for boundary cases
                                    wrongTouches++;
                                }
                            }
                            score = 100 * correctTouches / (correctTouches + wrongTouches);
                            if (score > maxScore) {
                                maxScore = score;
                                scaleXForMaxScore = scaleX;
                                scaleYForMaxScore = scaleY;
                                cxForMaxScore = cx;
                                cyForMaxScore = cy;
                            }
                            if (score == 100)
                                break outerloop;
                        }
                }
            return new float[]{maxScore, scaleXForMaxScore, scaleYForMaxScore, cxForMaxScore, cyForMaxScore};
        } else {
            return new float[]{0, 1, 1, centerX, centerY};
        }
    }

    private Bitmap scaleBitmap(Bitmap originalImage, float scaleX, float scaleY) {
        float width = originalImage.getWidth() * scaleX;
        float height = originalImage.getHeight() * scaleY;

        Bitmap background = Bitmap.createBitmap((int) width, (int) height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(background);

        float xTranslation = 0.0f, yTranslation = (height - originalImage.getHeight() * scaleY)/2.0f;
        Matrix transformation = new Matrix();
        transformation.postTranslate(xTranslation, yTranslation);
        transformation.preScale(scaleX, scaleY);
        Paint paint = new Paint();
        paint.setFilterBitmap(true);
        canvas.drawBitmap(originalImage, transformation, paint);
        return background;
    }

    private Bitmap bitmapOverlay(Bitmap bitmap1, Bitmap bitmap2, int xOffset, int yOffset) {
        Bitmap resultBitmap = Bitmap.createBitmap(bitmap1.getWidth(), bitmap1.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas c = new Canvas(resultBitmap);

        c.drawBitmap(bitmap1, 0, 0, null);

        Paint p = new Paint();
        p.setAlpha(255);

        c.drawBitmap(bitmap2, xOffset, yOffset, p);
        return resultBitmap;
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

    private class CalculateFreehandScore extends AsyncTask<Void,Void,float[]> {
        Bitmap touchImg, savedImg;
        ArrayList touches;
        ProgressDialog ringProgressDialog;

        @Override
        protected void onPreExecute() {
            touchImg = mDrawView.getTouchesBitmap();
            touches = mDrawView.getTouchesList();

            mDrawView.clearCanvas();
            mDrawView.setupDrawing();
            mDrawView.setBitmap(mDrawViewBitmap);

            mDrawView.setDrawingCacheEnabled(true);
            savedImg = Bitmap.createBitmap(mDrawView.getDrawingCache());
            mDrawView.destroyDrawingCache();

            ringProgressDialog = new ProgressDialog(PracticeActivity.this);
            ringProgressDialog.setTitle("Please wait ...");
            ringProgressDialog.setMessage("Calculating...");
            ringProgressDialog.setIndeterminate(true);
            ringProgressDialog.setCancelable(false);
            ringProgressDialog.show();
        }

        @Override
        protected float[] doInBackground(Void... voids) {
            return scoreBitmapForFreehand(touches, savedImg, (savedImg.getWidth() - touchImg.getWidth())/2, (savedImg.getHeight() - touchImg.getHeight())/2);
        }

        @Override
        protected void onPostExecute(float[] result) {
            ringProgressDialog.dismiss();
            mDrawView.clearCanvas();
            mDrawView.setupDrawing();
            mDrawView.setBitmap(bitmapOverlay(savedImg,scaleBitmap(touchImg,result[1],result[2]),(int)result[3],(int)result[4]));
            mDrawView.startAnimation(Animator.createScaleDownAnimation());

            ((TextView) findViewById(R.id.scoreView)).setText("Score: " + String.valueOf(result[0]));
            findViewById(R.id.scoreView).setAnimation(Animator.createFadeInAnimation());

            Animator.createYFlipForwardAnimation(findViewById(R.id.done_save_button));
            ((ActionButton) findViewById(R.id.done_save_button)).setImageResource(R.drawable.ic_save);
            Animator.createYFlipBackwardAnimation(findViewById(R.id.done_save_button));
        }
    }
}
