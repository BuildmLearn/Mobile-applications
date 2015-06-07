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
	
	

	public DrawingView(Context c,AttributeSet attrs) {
		super(c,attrs);
		context=c;	//setting current context

		//getting height and width of the current display
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		width = metrics.widthPixels;
		height = metrics.heightPixels;
		
		// Initializing an empty canvas
		canvasBitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
		setupDrawing();
	}
	
	
	public void setbitmap(Bitmap b) {	
		//centering the bitmap b in the canvas
		for (int i =(width-b.getWidth())/2 ; i<((width-b.getWidth())/2) + b.getWidth();i++)
			for (int j =0 ; j< b.getHeight();j++)
				canvasBitmap.setPixel(i, j, b.getPixel(i-((width-b.getWidth())/2), j));	
	
		drawCanvas = new Canvas(canvasBitmap);
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
		int x= (int) (touchX * canvasBitmap.getWidth() / width);
    	int y= (int) (touchY * canvasBitmap.getHeight()/ height);
	    
    	//Vibrating if the current pixel is white
	    if(canvasBitmap.getPixel(x,y)==0) {
	    	Vibrator vib = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
	    	 vib.vibrate(100);
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
