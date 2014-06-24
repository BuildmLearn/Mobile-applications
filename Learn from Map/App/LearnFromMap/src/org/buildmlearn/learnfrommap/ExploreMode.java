package org.buildmlearn.learnfrommap;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class ExploreMode extends ActionBarActivity {


	private ImageView worldMap;
	private Boolean isClicked = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_explore_mode);
		worldMap = (ImageView)findViewById(R.id.explore_world_map);
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
				continent = "Asia";
			}
			else if(color.equals("ff333333"))
			{
				continent = "North America";
			}
			else if(color.equals("ff999999"))
			{
				continent = "South America";
			}
			else if(color.equals("ff777777"))
			{
				continent = "Africa";
			}
			else if(color.equals("ffaaaaaa"))
			{
				continent = "Europe";
			}
			else if(color.equals("ffffffff"))
			{
				continent = "Antarctica";
			}
			else if(color.equals("ff333333"))
			{
				continent = "North America";
			}
			else if(color.equals("ff555555"))
			{
				continent = "Australia";
			}
			if(continent.length() > 0 && !isClicked)
			{
				isClicked = true;
				worldMap.setClickable(false);
				Intent intent = new Intent(getApplicationContext(), GameActivity.class);
				intent.putExtra("MODE", "EXPLORE_MODE");
				intent.putExtra("SELECTION", "continent");
				intent.putExtra("VALUE", continent);
				startActivity(intent);
				return true;
				
			}

			return true;
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

}
