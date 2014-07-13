package org.buildmlearn.learnfrommap;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.Toast;

public class ExploreMode extends ActionBarActivity {


	private ImageView worldMap;
	private Boolean isClicked = false;
	private GestureDetector gestureDetector;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_explore_mode);
		worldMap = (ImageView)findViewById(R.id.explore_world_map);
		gestureDetector = new GestureDetector(this, new GestureListener(worldMap));
		worldMap.setOnTouchListener(worldMapTouchListener);

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		isClicked = false;
	}

	OnTouchListener worldMapTouchListener = new OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {

			return gestureDetector.onTouchEvent(event);
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.explore_mode, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public class GestureListener extends
	GestureDetector.SimpleOnGestureListener {
		
		private View v;
		public GestureListener(View v)
		{
			this.v = v;
			
		}

		@Override
		public boolean onDown(MotionEvent event) {
			float eventX = event.getX();
			float eventY = event.getY();
			float[] eventXY = new float[] {eventX, eventY};

			Matrix invertMatrix = new Matrix();
			((ImageView)v).getImageMatrix().invert(invertMatrix);

			invertMatrix.mapPoints(eventXY);
			int x = Integer.valueOf((int)eventXY[0]);
			int y = Integer.valueOf((int)eventXY[1]);
			Drawable imgDrawable = ((ImageView)v).getDrawable();
			Bitmap bitmap = ((BitmapDrawable)imgDrawable).getBitmap();
			if(x < 0){
				x = 0;
			}else if(x > bitmap.getWidth()-1){
				x = bitmap.getWidth()-1;
			}

			if(y < 0){
				y = 0;
			}else if(y > bitmap.getHeight()-1){
				y = bitmap.getHeight()-1;
			}

			int touchedRGB = bitmap.getPixel(x, y);
			String color = Integer.toHexString(touchedRGB);
			String continent = "";
			if(color.equals("ffeeeeee"))
			{
				continent = "2";
			}
			else if(color.equals("ff333333"))
			{
				continent = "4";
			}
			else if(color.equals("ff999999"))
			{
				continent = "6";
			}
			else if(color.equals("ff777777"))
			{
				continent = "1";
			}
			else if(color.equals("ffaaaaaa"))
			{
				continent = "3";
			}
			else if(color.equals("ffffffff"))
			{
				continent = "7";
			}
			else if(color.equals("ff555555"))
			{
				continent = "5";
			}
			if(continent.length() > 0 && !isClicked)
			{
				
				Toast.makeText(getApplicationContext(), "Double tap to select a continent", Toast.LENGTH_SHORT).show();

			}
			return true;
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			return true;
		}

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			return true;
		}

		@Override
		public boolean onDoubleTap(MotionEvent event) {

			float eventX = event.getX();
			float eventY = event.getY();
			float[] eventXY = new float[] {eventX, eventY};

			Matrix invertMatrix = new Matrix();
			((ImageView)v).getImageMatrix().invert(invertMatrix);

			invertMatrix.mapPoints(eventXY);
			int x = Integer.valueOf((int)eventXY[0]);
			int y = Integer.valueOf((int)eventXY[1]);
			Drawable imgDrawable = ((ImageView)v).getDrawable();
			Bitmap bitmap = ((BitmapDrawable)imgDrawable).getBitmap();
			if(x < 0){
				x = 0;
			}else if(x > bitmap.getWidth()-1){
				x = bitmap.getWidth()-1;
			}

			if(y < 0){
				y = 0;
			}else if(y > bitmap.getHeight()-1){
				y = bitmap.getHeight()-1;
			}

			int touchedRGB = bitmap.getPixel(x, y);
			String color = Integer.toHexString(touchedRGB);
			String continent = "";
			if(color.equals("ffeeeeee"))
			{
				continent = "2";
			}
			else if(color.equals("ff333333"))
			{
				continent = "4";
			}
			else if(color.equals("ff999999"))
			{
				continent = "6";
			}
			else if(color.equals("ff777777"))
			{
				continent = "1";
			}
			else if(color.equals("ffaaaaaa"))
			{
				continent = "3";
			}
			else if(color.equals("ffffffff"))
			{
				continent = "7";
			}
			else if(color.equals("ff555555"))
			{
				continent = "5";
			}
			if(continent.length() > 0 && !isClicked)
			{
				isClicked = true;
				worldMap.setClickable(false);
				Intent intent = new Intent(getApplicationContext(), GameActivity.class);
				intent.putExtra("MODE", "EXPLORE_MODE");
				intent.putExtra("SELECTION", "CONTINENT");
				intent.putExtra("VALUE", continent);
				startActivity(intent);
				return true;

			}

			return true;

		}
	}

}
