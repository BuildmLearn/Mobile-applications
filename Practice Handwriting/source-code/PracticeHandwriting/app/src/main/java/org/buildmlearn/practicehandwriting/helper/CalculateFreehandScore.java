package org.buildmlearn.practicehandwriting.helper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.widget.TextView;

import com.software.shell.fab.ActionButton;

import org.buildmlearn.practicehandwriting.R;

import java.util.ArrayList;
import java.util.Collections;

public class CalculateFreehandScore extends AsyncTask<Void,Void,float[]> {
    private Bitmap mTouchImg, mSavedImg;
    private ArrayList mTouches;
    private ProgressDialog mProgressDialog;
    private DrawingView mDrawView;
    private Context mContext;
    private String mPracticeString;

    public CalculateFreehandScore(Context context, DrawingView drawView, String practiceString) {
        mContext = context;
        mDrawView = drawView;
        mPracticeString = practiceString;
    }

    @Override
    protected void onPreExecute() {
        mTouchImg = mDrawView.getTouchesBitmap();
        mTouches = mDrawView.getTouchesList();

        mDrawView.clearCanvas();
        mDrawView.setupDrawing();
        mDrawView.setBitmapFromText(mPracticeString);

        mDrawView.setDrawingCacheEnabled(true);
        mSavedImg = Bitmap.createBitmap(mDrawView.getDrawingCache());
        mDrawView.destroyDrawingCache();

        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setTitle("Please wait ...");
        mProgressDialog.setMessage("Calculating...");
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    @Override
    protected float[] doInBackground(Void... voids) {
        return scoreBitmapForFreehand(mTouches, mSavedImg, (mSavedImg.getWidth() - mTouchImg.getWidth())/2, (mSavedImg.getHeight() - mTouchImg.getHeight())/2);
    }

    @Override
    protected void onPostExecute(float[] result) {
        mProgressDialog.dismiss();
        mDrawView.clearCanvas();
        mDrawView.setupDrawing();
        mDrawView.setBitmap(bitmapOverlay(mSavedImg,scaleBitmap(mTouchImg,result[1],result[2]),(int)result[3],(int)result[4]));
        mDrawView.startAnimation(Animator.createScaleDownAnimation());

        ((TextView) ((Activity) mContext).findViewById(R.id.score_and_timer_View)).setText("Score: " + String.valueOf(result[0]));
        ((Activity) mContext).findViewById(R.id.score_and_timer_View).setAnimation(Animator.createFadeInAnimation());

        Animator.createYFlipForwardAnimation(((Activity) mContext).findViewById(R.id.done_save_button));
        ((ActionButton) ((Activity) mContext).findViewById(R.id.done_save_button)).setImageResource(R.drawable.ic_save);
        Animator.createYFlipBackwardAnimation(((Activity) mContext).findViewById(R.id.done_save_button));
    }

    private float[] scoreBitmapForFreehand(ArrayList<ArrayList<Integer>> touches, Bitmap canvasBitmap, int centerX, int centerY) {
        int size = touches.get(0).size();
        if(size!=0) {
            int minx = Math.max(Collections.min(touches.get(0)) - 30, 0);
            int miny = Math.max(Collections.min(touches.get(1)) - 30, 0);
            int correctTouches;
            int i, cx, cy;
            float scaleX, scaleY;
            int textColour = mContext.getResources().getColor(R.color.Black);
            float score, maxScore = 0, scaleXForMaxScore = 1, scaleYForMaxScore = 1, cxForMaxScore = centerX, cyForMaxScore = centerY;
            Integer[] xTouches = new Integer[size];
            Integer[] yTouches = new Integer[size];
            xTouches = touches.get(0).toArray(xTouches);
            yTouches = touches.get(1).toArray(yTouches);

            for (i = 0; i < size; i++) {
                xTouches[i] = xTouches[i] - minx;
                yTouches[i] = yTouches[i] - miny;
            }
            outerLoop:
            for (scaleX = 0.8f; scaleX < 1.4f; scaleX += 0.1f)
                for (scaleY = 0.8f; scaleY < 1.4f; scaleY += 0.1f) {
                    for (cx = centerX - 20; cx <= centerX + 20; cx += 2)
                        for (cy = centerY - 20; cy <= centerY + 20; cy += 2) {
                            correctTouches = 0;
                            for (i = 0; i < size; i++) {
                                int x = (int) (xTouches[i] * scaleX) + cx;
                                int y = (int) (yTouches[i] * scaleY) + cy;
                                if (x >= 0 && x < canvasBitmap.getWidth() && y >= 0 && y < canvasBitmap.getHeight() && canvasBitmap.getPixel(x, y) == textColour)
                                    correctTouches++;
                            }
                            score = ((float) correctTouches) / ((float) size);
                            if (score > maxScore) {
                                maxScore = score;
                                scaleXForMaxScore = scaleX;
                                scaleYForMaxScore = scaleY;
                                cxForMaxScore = cx;
                                cyForMaxScore = cy;
                                if (score == 1)
                                    break outerLoop;
                            }
                        }
                }
            return new float[]{100 * maxScore, scaleXForMaxScore, scaleYForMaxScore, cxForMaxScore, cyForMaxScore};
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
}