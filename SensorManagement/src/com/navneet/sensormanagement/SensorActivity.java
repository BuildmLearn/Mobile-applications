package com.navneet.sensormanagement;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SensorActivity extends Activity {
	private SensorManager mSensorManager;
	private ListView sensorsListView;
	List<Sensor> deviceSensors = null;
	String[] sensors = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sensor_2);
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		sensorsListView = (ListView) findViewById(R.id.sensorsList);
		deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);

		ArrayAdapter<String> sensorNames = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1);
		for (Sensor s : deviceSensors) {
			sensorNames.add(s.getName() + "\n" + "\n" + s.getMaximumRange()
					+ "\n" + "\n" + s.getType());

		}
		sensorsListView.setAdapter(sensorNames);

		sensorsListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(SensorActivity.this,
						ViewSensorDataActivity.class);
				intent.putExtra("Sensor", deviceSensors.get(position).getName());
				intent.putExtra("SensorType", deviceSensors.get(position)
						.getType());
				startActivity(intent);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sensor, menu);
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
