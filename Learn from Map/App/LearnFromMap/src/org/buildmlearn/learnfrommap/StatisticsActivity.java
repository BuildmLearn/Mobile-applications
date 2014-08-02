package org.buildmlearn.learnfrommap;

import com.echo.holographlibrary.PieGraph;
import com.echo.holographlibrary.PieSlice;

import android.support.v7.app.ActionBarActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AccelerateDecelerateInterpolator;

public class StatisticsActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_statistics);
		PieGraph pg = (PieGraph)findViewById(R.id.graph);
		PieSlice slice = new PieSlice();
		slice.setColor(Color.parseColor("#99CC00"));
		slice.setValue(2);
		slice.setTitle("Cool");
		pg.addSlice(slice);
		slice = new PieSlice();
		slice.setColor(Color.parseColor("#FFBB33"));
		slice.setValue(3);
		pg.addSlice(slice);
		slice = new PieSlice();
		slice.setColor(Color.parseColor("#AA66CC"));
		slice.setValue(8);
		pg.addSlice(slice);
		pg.setInnerCircleRatio(150);
		for (PieSlice s : pg.getSlices())
			s.setGoalValue((float)Math.random() * 10);
		pg.setDuration(1000);//default if unspecified is 300 ms
		pg.setInterpolator(new AccelerateDecelerateInterpolator());//default if unspecified is linear; constant speed
		//pg.setAnimationListener(getAnimationListener());//optional
		pg.animateToGoalValues();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.statistics, menu);
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
