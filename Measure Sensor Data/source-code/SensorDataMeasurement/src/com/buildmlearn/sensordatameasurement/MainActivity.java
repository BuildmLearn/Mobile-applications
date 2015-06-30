package com.buildmlearn.sensordatameasurement;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {
	private SensorManager mSensorManager;
	private ListView mSensorListView;
	private ArrayAdapter<String> adapter;
	private ArrayList<String> mSensorNames = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mSensorListView = (ListView) findViewById(R.id.lv_supported_sensors);
		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		List<Sensor> mList = mSensorManager.getSensorList(Sensor.TYPE_ALL);
		for (int i = 0; i < mList.size(); i++) {

			mSensorNames.add(mList.get(i).getName());
			
		}

		adapter = new ArrayAdapter<String>(MainActivity.this,
				android.R.layout.simple_list_item_1, android.R.id.text1,
				mSensorNames);
		mSensorListView.setAdapter(adapter);
		mSensorListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if(mSensorNames.get(arg2).equalsIgnoreCase("Accelerometer"))
				{
					Intent accelerometer=new Intent(MainActivity.this,AccelerometerActivity.class);
					startActivity(accelerometer);
				}
				
			}
		});
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
