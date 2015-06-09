package org.buildmlearn.practicehandwriting.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;


public class DrawingView extends View {
    //drawing path
    private Path drawPath;
    //drawing and canvas paint
    private Paint drawPaint, canvasPaint;
    //colour
    private int touch_colour = 0xFFFF0000;
    //canvas
    private Canvas drawCanvas;
    //canvas bitmap
    private Bitmap canvasBitmap;
    //Context
    private Context context;
    //Canvas width and height
    private int width;
    private int height;

    private long[] vibration_pattern;

    private Vibrator vibrator;



    public DrawingView(Context c,AttributeSet attrs) {
        super(c,attrs);
        context=c;	//setting current context
        vibration_pattern = new long[100];
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        for(int i=0;i<100;i++)
            vibration_pattern[i]=i;
        //getting height and width of the current display
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        width = metrics.widthPixels;
        height = metrics.heightPixels;

        // Initializing an empty canvas
        canvasBitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        setupDrawing();
    }


    public void setBitmap(Bitmap b) {
        canvasBitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
        drawCanvas.drawBitmap(b, (width - b.getWidth()) / 2,0, canvasPaint);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //draw view
        canvas.drawBitmap(canvasBitmap, 0,0, canvasPaint);
        canvas.drawPath(drawPath, drawPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //detect user touch
        float touchX = event.getX();
        float touchY = event.getY();

        // mapping screen touch co-ordinates to image pixel co-ordinates
        int x= (int) (touchX);// * canvasBitmap.getWidth() / width);
        int y= (int) (touchY);// * canvasBitmap.getHeight()/ height);

        //Vibrating if the current pixel is white
        if (x >= (width - canvasBitmap.getWidth()) / 2 && x < ((width - canvasBitmap.getWidth()) / 2) + canvasBitmap.getWidth()) {
            if (canvasBitmap.getPixel(x - (width - canvasBitmap.getWidth()) / 2, y) == 0)
                vibrator.vibrate(100);
        } else {
            vibrator.vibrate(100);
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                drawCanvas.drawPath(drawPath, drawPaint);
                drawPath.reset();
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }

    private void setupDrawing(){
        //get drawing area setup for interaction
        drawPath = new Path();
        drawPaint = new Paint();
        drawPaint.setColor(touch_colour);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(15);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        canvasPaint = new Paint(Paint.DITHER_FLAG);
    }



}
