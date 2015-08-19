package org.buildmlearn.practicehandwriting.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.buildmlearn.practicehandwriting.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Activity to display the results of the Time Trial session
 */
public class TimeTrialResultActivity extends Activity {

    /**
     * The temporary directory where the user's traces are stored
     */
    private File mTempDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_time_trial_result);
            findViewById(R.id.TimeTrialToolbar).bringToFront();
            mTempDir = new File(Environment.getExternalStorageDirectory() + File.separator + getString(R.string.app_name) + File.separator + getIntent().getStringExtra(getString(R.string.directory_name)));
            final File[] savedFiles = mTempDir.listFiles();
            float mScore = 0;

            TextView finalScoreView = new TextView(this);
            finalScoreView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            finalScoreView.setTextSize(35);

            LinearLayout resultLL = (LinearLayout) findViewById(R.id.resultLL);
            resultLL.addView(finalScoreView);

            final ScrollView scrollView = (ScrollView) findViewById(R.id.ResultScrollView);

            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
            ImageLoader.getInstance().init(config);
            final ImageLoader imageLoader = ImageLoader.getInstance();
            for (int i = 0;i<savedFiles.length;i++) {
                final int index = i;

                final View result = View.inflate(this,R.layout.layout_result, null);
                resultLL.addView(result, i + 1); //The first view is the overall score TextView

                String score = savedFiles[i].getName().replace("_",": ");
                score = score.substring(0,score.length()-4);

                final TextView scoreView = (TextView) result.findViewById(R.id.resultTextView);
                scoreView.setText(score);
                result.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        System.gc();
                        scrollView.scrollBy(0,SplashActivity.mDisplayMetrics.heightPixels*3/8);
                        final ImageView resultImageView = (ImageView) result.findViewById(R.id.resultImageView);
                        if (resultImageView.getDrawable() == null)
                            imageLoader.displayImage(Uri.fromFile(savedFiles[index]).toString().replace("%20", " "), resultImageView);
                        else
                            resultImageView.setImageDrawable(null);
                        scrollView.smoothScrollTo(0, result.getTop());
                    }
                });

                mScore += Float.parseFloat(score.split(": ")[1]);
                finalScoreView.setText("Overall: " + String.valueOf(mScore / (float) (i + 1)));
                System.gc();
            }
        } catch (Exception e) {
            showErrorDialog(e);
        }
    }

    /**
     * Function to show the error that occurred while starting the practice session
     * @param e The exception that was caught
     */
    protected void showErrorDialog(final Exception e) {
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
                                fOut.write(("\n\n" + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()).format(new Date()) + "\n\n").getBytes());
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

                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    /**
     * Function that saves the user traces to the memory when the save button is pressed
     * @param view
     */
    public void saveTimeTrial(View view) {
        int i;
        String toastText = "";
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory() + File.separator + getString(R.string.app_name) + File.separator + "TIME_TRIAL_" + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date()));
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdir())
            toastText = "Could not save trace. Unable to create directory";
        else {
            File[] savedFiles = mTempDir.listFiles();
            for (i = 0; i < savedFiles.length; i++) {
                try {
                    File file = new File(mediaStorageDir.getAbsolutePath() + File.separator + savedFiles[i].getName());
                    InputStream inStream = new FileInputStream(savedFiles[i]);
                    OutputStream outStream = new FileOutputStream(file);

                    byte[] buffer = new byte[1024];
                    int length;
                    //copy the file content in bytes
                    while ((length = inStream.read(buffer)) > 0)
                        outStream.write(buffer, 0, length);

                    inStream.close();
                    outStream.close();
                } catch (IOException e) {
                    mediaStorageDir.delete();
                    toastText = "Could not save traces. An unexpected error occurred";
                    break;
                }
            }
            if (i == savedFiles.length)
                toastText = "Traces Saved";
        }
        Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop() {
        super.onStop();
        //Running Garbage Collection
        System.gc();
        if(mTempDir.exists()) {
            for (File file : mTempDir.listFiles())
                file.delete();
            mTempDir.delete();
        }
    }


    @Override
    public void onBackPressed() {
        //Going back to the main menu instead of the Tracing screen
        //Deleting the files in the temp directory and the directory itself
        if(mTempDir.exists()) {
            for (File file : mTempDir.listFiles())
                file.delete();
            mTempDir.delete();
        }
        startActivity(new Intent(TimeTrialResultActivity.this, MainMenuActivity.class));
    }
}
