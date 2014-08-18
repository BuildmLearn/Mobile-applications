package org.buildmlearn.learnfrommap;

import java.util.ArrayList;
import java.util.Collections;

import org.buildmlearn.learnfrommap.adapter.StatsAdapter;
import org.buildmlearn.learnfrommap.helper.HelperFunctions;
import org.buildmlearn.learnfrommap.helper.StatHolder;
import com.echo.holographlibrary.PieGraph;
import com.echo.holographlibrary.PieSlice;

import android.support.v7.app.ActionBarActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ListView;

/**
 * This activity shows the statistics screen
 * 
 * @author Abhishek
 *
 */
public class StatisticsActivity extends ActionBarActivity {

	private ArrayList<StatHolder> mData;
	private ListView mStatList;
	private StatsAdapter mAdapter;
	public static final String[] colors = {"#CCFF8800", "#CC9933CC", "#CCCC0000", "#CC669900", "#CC0099CC"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_statistics);
        getSupportActionBar().setHomeButtonEnabled(true);
		mData = HelperFunctions.bindStat(getApplicationContext());
		Collections.sort(mData);
		int total = 0;
		for(int i=0; i<mData.size(); i++)
		{
			StatHolder temp = mData.get(i);
			if(i>3)
			{
				temp.setColor(colors[4]);
				total += temp.getTotal();
			}
			else
			{
				temp.setColor(colors[i]);
			}
			mData.set(i, temp);	

		}

		mStatList = (ListView)findViewById(R.id.listView_stats);
		mAdapter = new StatsAdapter(this, R.layout.listview_row_stat, mData);
		mStatList.setAdapter(mAdapter);

		PieGraph pg = (PieGraph)findViewById(R.id.graph);
		PieSlice slice = new PieSlice();
		slice.setColor(Color.parseColor(colors[0]));
		pg.addSlice(slice);
		slice = new PieSlice();
		slice.setColor(Color.parseColor(colors[1]));
		pg.addSlice(slice);
		slice = new PieSlice();
		slice.setColor(Color.parseColor(colors[2]));
		pg.addSlice(slice);
		slice = new PieSlice();
		slice.setColor(Color.parseColor(colors[3]));
		pg.addSlice(slice);
		slice = new PieSlice();
		slice.setColor(Color.parseColor(colors[4]));
		pg.addSlice(slice);
		pg.setInnerCircleRatio(200);
		pg.setPadding(2);
		int count = 0;
		for (PieSlice s : pg.getSlices())
		{
			s.setValue((float) Math.random() * 10);
			if(count < 4)

				s.setGoalValue(mData.get(count).getTotal());
			else
				s.setGoalValue(total);
			count++;
		}
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
