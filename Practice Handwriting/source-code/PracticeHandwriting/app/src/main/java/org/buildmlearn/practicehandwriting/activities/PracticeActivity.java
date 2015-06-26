package org.buildmlearn.practicehandwriting.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.software.shell.fab.ActionButton;

import org.buildmlearn.practicehandwriting.R;
import org.buildmlearn.practicehandwriting.helper.Animator;
import org.buildmlearn.practicehandwriting.helper.CalculateFreehandScore;
import org.buildmlearn.practicehandwriting.helper.DrawingView;
import org.buildmlearn.practicehandwriting.helper.TimeTrialResult;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class PracticeActivity extends ActionBarActivity {

    //TODO time dependant vibrations, make practice string width wider, add comments

    private DrawingView mDrawView;
    private boolean mDone;
    private String mPracticeString;
    private String mPracticeMode;
    private TextView mScoreTimerView;
    private CountDownTimer mCountDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_practice);

            Toolbar toolbar = (Toolbar) findViewById(R.id.PracticeToolbar);
            Intent intent = getIntent();
            mScoreTimerView = (TextView) findViewById(R.id.score_and_timer_View);
            mDrawView = (DrawingView) findViewById(R.id.drawing);
            mPracticeMode = intent.getStringExtra(getResources().getString(R.string.practice_mode));
            mDone = false;
            mCountDownTimer = new CountDownTimer(10000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    mScoreTimerView.setText(String.format("%02d", millisUntilFinished / 60000) + ":" + String.format("%02d", (millisUntilFinished / 1000) % 60));
                }

                @Override
                public void onFinish() {
                    mScoreTimerView.setText("00:00");
                    mDone = true;
                    int index = Arrays.asList(SplashActivity.CHARACTER_LIST).indexOf(mPracticeString);
                    mDrawView.canDraw(false);
                    SplashActivity.mTimeTrialResults.add(new TimeTrialResult(mPracticeString, mDrawView.getTouchesList()));
                    Intent intent = new Intent(PracticeActivity.this,TimeTrialResultActivity.class);
                    startActivity(intent);
                }
            };

            setSupportActionBar(toolbar);
            toolbar.bringToFront();
            toolbar.setNavigationIcon(R.drawable.ic_launcher);
            mScoreTimerView.bringToFront();

            switch(mPracticeMode) {
                case "Practice":
                    mPracticeString = intent.getStringExtra(getResources().getString(R.string.practice_string));
                    mDrawView.setBitmapFromText(mPracticeString);
                    mDrawView.canVibrate(true);
                    break;

                case "Freehand":
                    mPracticeString = intent.getStringExtra(getResources().getString(R.string.practice_string));
                    mDrawView.canVibrate(false);
                    break;

                case "Time Trial":
                    mPracticeString =SplashActivity.CHARACTER_LIST[0];
                    mDrawView.setBitmapFromText(mPracticeString);
                    mDrawView.canVibrate(true);
                    mCountDownTimer.start();
                    break;
            }

            if(SplashActivity.TTSobj!=null) {
                if (Build.VERSION.SDK_INT >= 21)
                    SplashActivity.TTSobj.speak(mPracticeString, TextToSpeech.QUEUE_FLUSH, null, null);
                else
                    SplashActivity.TTSobj.speak(mPracticeString, TextToSpeech.QUEUE_FLUSH, null);
            }
        } catch (Exception e) {
            showErrorDialog(e);
        }
    }

    public void practiceActivityOnClick(View v) {
        switch (v.getId()) {
            case R.id.reset_button:
                if(mDone) {
                    mDrawView.startAnimation(Animator.createScaleUpAnimation());

                    mScoreTimerView.setAnimation(Animator.createFadeOutAnimation());

                    Animator.createYFlipForwardAnimation(findViewById(R.id.done_save_button));
                    ((ActionButton) findViewById(R.id.done_save_button)).setImageResource(R.drawable.ic_done);
                    Animator.createYFlipBackwardAnimation(findViewById(R.id.done_save_button));

                    mDone = false;
                }
                mDrawView.setupDrawing();
                if(mPracticeMode.equals(getResources().getString(R.string.freehand)))
                    mDrawView.clearCanvas();
                else
                    mDrawView.setBitmapFromText(mPracticeString);
                break;

            case R.id.done_save_button:
                if (!mDone) {
                    switch(mPracticeMode) {
                        case "Practice":
                            mDrawView.startAnimation(Animator.createScaleDownAnimation());

                            mScoreTimerView.setText("Score: " + String.valueOf(mDrawView.score()));
                            mScoreTimerView.setAnimation(Animator.createFadeInAnimation());

                            Animator.createYFlipForwardAnimation(findViewById(R.id.done_save_button));
                            ((ActionButton) findViewById(R.id.done_save_button)).setImageResource(R.drawable.ic_save);
                            Animator.createYFlipBackwardAnimation(findViewById(R.id.done_save_button));
                            mDrawView.canDraw(false);
                            mDone = true;
                            break;

                        case "Freehand":
                            new CalculateFreehandScore(this, mDrawView, mPracticeString).execute();
                            mDrawView.canDraw(false);
                            mDone = true;
                            break;

                        case "Time Trial":
                            int index = Arrays.asList(SplashActivity.CHARACTER_LIST).indexOf(mPracticeString);
                            //mResults.add(new TimeTrialResult(mPracticeString,mDrawView.getTouchesList()));
                            mDrawView.canDraw(false);
                            SplashActivity.mTimeTrialResults.add(new TimeTrialResult(mPracticeString, mDrawView.getTouchesList()));

                            mDrawView.canDraw(true);
                            if(index == SplashActivity.CHARACTER_LIST.length - 1) {
                                Intent intent = new Intent(PracticeActivity.this, TimeTrialResultActivity.class);
                                startActivity(intent);
                            } else {
                                mPracticeString = SplashActivity.CHARACTER_LIST[index + 1];
                                mDrawView.setBitmapFromText(mPracticeString);
                            }
                            break;
                    }
                } else {
                    File mediaStorageDir = new File(Environment.getExternalStorageDirectory() + File.separator + getResources().getString(R.string.app_name));
                    if (!mediaStorageDir.exists() && !mediaStorageDir.mkdir()) {
                        Toast.makeText(getApplicationContext(), "Could not save trace. Unable to create directory", Toast.LENGTH_SHORT).show();
                    } else {
                        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                        File file = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + "_" + mPracticeString + ".jpg");
                        Bitmap savedImg = mDrawView.getCanvasBitmap();
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
        if (mPracticeString.length() > 1) {
            if (mPracticeString.length() < 5)
                intent.putExtra(getResources().getString(R.string.practice_string), SplashActivity.EASY_WORD_LIST[new Random().nextInt(SplashActivity.EASY_WORD_LIST.length)]);
            else if (mPracticeString.length() >= 5 && mPracticeString.length() < 7)
                intent.putExtra(getResources().getString(R.string.practice_string), SplashActivity.MEDIUM_WORD_LIST[new Random().nextInt(SplashActivity.MEDIUM_WORD_LIST.length)]);
            else if (mPracticeString.length() >= 7)
                intent.putExtra(getResources().getString(R.string.practice_string), SplashActivity.HARD_WORD_LIST[new Random().nextInt(SplashActivity.HARD_WORD_LIST.length)]);

        } else {
            int next = Arrays.asList(SplashActivity.CHARACTER_LIST).indexOf(mPracticeString);
            switch (item.getItemId()) {
                case R.id.action_next:
                    next = (next + 1) % SplashActivity.CHARACTER_LIST.length;
                    break;

                case R.id.action_previous:
                    next = (next - 1) % SplashActivity.CHARACTER_LIST.length;
                    break;
            }
            intent.putExtra(getResources().getString(R.string.practice_string), SplashActivity.CHARACTER_LIST[next]);
        }
        finish();
        startActivity(intent);
        return true;
    }

    private void showErrorDialog(final Exception e) {
        new AlertDialog.Builder(this)
                .setTitle("ERROR")
                .setMessage(Log.getStackTraceString(e))
                .setPositiveButton("Save to File", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            File mediaStorageDir = new File(Environment.getExternalStorageDirectory() + File.separator + getResources().getString(R.string.app_name));
                            File file = new File(mediaStorageDir.getPath() + File.separator + "error.txt");
                            if(file.exists() || file.createNewFile()) {
                                FileOutputStream fOut = new FileOutputStream(file, true);
                                fOut.write(("\n\n" + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()) + "\n\n").getBytes());
                                fOut.write(Log.getStackTraceString(e).getBytes());
                                fOut.flush();
                                fOut.close();
                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;

    }

    @Override
    public  void onBackPressed() {
        if(mPracticeMode.equals(getResources().getString(R.string.time_trial)))
            mCountDownTimer.cancel();
        super.onBackPressed();
    }

}