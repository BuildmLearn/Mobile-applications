package org.buildmlearn.practicehandwriting.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.buildmlearn.practicehandwriting.R;

import java.util.ArrayList;
import java.util.Date;


public class DrawingView extends View {
    //TODO add comments

    //drawing path
    private Path mDrawPath;
    //drawing and canvas paint
    private Paint mDrawPaint, mCanvasPaint;
    //canvas
    private Canvas mDrawCanvas;
    //canvas bitmap
    private Bitmap mCanvasBitmap;
    //Canvas width and height
    private int mWidth;
    private int mHeight;

    private Vibrator mVibrator;

    private long mWrongTouches;
    private long mCorrectTouches;

    private boolean mDraw, mVibrate;

    private long mVibrationStartTime;

    private Toast mErrorToast;

    private int minX, minY, maxX, maxY;

    private ArrayList<ArrayList<Integer>> mTouches;


    public DrawingView(Context context,AttributeSet attrs) {
        super(context,attrs);
        mVibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        //Getting display width and height
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        mWidth = metrics.widthPixels;
        mHeight = metrics.heightPixels;
        clearCanvas();

        mErrorToast = new Toast(context);
        mErrorToast.setGravity(Gravity.CENTER_HORIZONTAL, 0, mHeight / 4);
        mErrorToast.setDuration(Toast.LENGTH_SHORT);
        mErrorToast.setView(((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.toast,
                (ViewGroup) findViewById(R.id.toast_layout_root)));

        setupDrawing();
    }

    public void setBitmap(Bitmap b) {
        clearCanvas();
        mDrawCanvas.drawBitmap(b, (mWidth - b.getWidth()) / 2, (mHeight - b.getHeight()) / 2, mCanvasPaint);
        invalidate();
    }

    public ArrayList getTouchesList() {
        return mTouches;
    }

    public Bitmap getTouchesBitmap() {
        if(minX!=mWidth && minY!=mHeight && maxX!=-1 && maxY!=-1)
            return Bitmap.createBitmap(mCanvasBitmap,Math.max(minX - 30, 0),Math.max(minY -30,0),Math.min(maxX - minX +40, mWidth - minX),Math.min(maxY - minY +40, mHeight - minY));
        else
            return Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
    }

    public void clearCanvas() {
        mCanvasBitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        mDrawCanvas = new Canvas(mCanvasBitmap);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //draw view
        canvas.drawBitmap(mCanvasBitmap, 0,0, mCanvasPaint);
        canvas.drawPath(mDrawPath, mDrawPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(mDraw) {
            //detect user touch
            float touchX = event.getX();
            float touchY = event.getY();

            // mapping screen touch co-ordinates to image pixel co-ordinates
            int x = (int) (touchX * mCanvasBitmap.getWidth() / mWidth);
            int y = (int) (touchY * mCanvasBitmap.getHeight() / mHeight);
            mTouches.get(0).add(x);
            mTouches.get(1).add(y);

            if(x < minX)
                minX = x;
            if(x > maxX)
                maxX = x;
            if(y < minY)
                minY = y;
            if(y > maxY)
                maxY = y;

            if(mVibrate) {
                if ((x >= 0 && x < mWidth && y >= 0 && y < mHeight && mCanvasBitmap.getPixel(x, y) == 0) || (x < 0 || x >= mWidth || y < 0 || y >= mHeight)) {
                    mVibrator.vibrate(100);
                    mWrongTouches++;
                    if (mVibrationStartTime == 0) {
                        mVibrationStartTime = new Date().getTime();
                        mErrorToast.cancel();
                    } else if (new Date().getTime() - mVibrationStartTime > 1000 && mErrorToast.getView().getWindowVisibility() != View.VISIBLE) {
                        mErrorToast.show();
                    }
                } else {
                    mVibrationStartTime = 0;
                    mCorrectTouches++;
                }
            }

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mDrawPath.moveTo(touchX, touchY);
                    break;
                case MotionEvent.ACTION_MOVE:
                    mDrawPath.lineTo(touchX, touchY);
                    break;
                case MotionEvent.ACTION_UP:
                    mVibrationStartTime = 0;
                    mDrawCanvas.drawPath(mDrawPath, mDrawPaint);
                    mDrawPath.reset();
                    break;
                default:
                    return false;
            }
            invalidate();
            return true;
        }
        return false;
    }

    public void setupDrawing() {
        //get drawing area setup for interaction
        mDrawPath = new Path();
        mDrawPaint = new Paint();
        mDrawPaint.setColor(getResources().getColor(R.color.Red));
        mDrawPaint.setAntiAlias(true);
        mDrawPaint.setStrokeWidth(15);
        mDrawPaint.setStyle(Paint.Style.STROKE);
        mDrawPaint.setStrokeJoin(Paint.Join.ROUND);
        mDrawPaint.setStrokeCap(Paint.Cap.ROUND);
        mCanvasPaint = new Paint(Paint.DITHER_FLAG);
        mCorrectTouches = 0;
        mWrongTouches = 0;
        mDraw = true;
        mVibrationStartTime = 0;

        minX = mWidth;
        maxX = -1;
        minY = mHeight;
        maxY = -1;

        mTouches = new ArrayList<>(2);
        mTouches.add(new ArrayList<Integer>());
        mTouches.add(new ArrayList<Integer>());
    }

    public void canVibrate(boolean vibrate){
        mVibrate = vibrate;
    }

    public void canDraw(boolean draw) {
        mDraw = draw;
    }

    public float score() {
        return (mCorrectTouches + mWrongTouches !=0)?100* mCorrectTouches /(mCorrectTouches + mWrongTouches):0;
    }
}
