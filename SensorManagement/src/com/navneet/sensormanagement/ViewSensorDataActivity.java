package com.navneet.sensormanagement;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ViewSensorDataActivity extends Activity implements
		SensorEventListener {
	protected int sensorType;
	private SensorManager mSensorManager;
	private Sensor mSensor;
	protected TextView sensorInformation1;
	protected TextView sensorInformation2;
	protected TextView sensorInformation3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_sensor_data);
		sensorInformation1 = (TextView) findViewById(R.id.sensorInformation1);
		sensorInformation2 = (TextView) findViewById(R.id.sensorInformation2);
		sensorInformation3 = (TextView) findViewById(R.id.sensorInformation3);
		sensorType = getIntent().getIntExtra("SensorType", 0);
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mSensor = mSensorManager.getDefaultSensor(sensorType);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_sensor_data, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent intent = new Intent(ViewSensorDataActivity.this,
					SensorDataGraphActivity.class);
			intent.putExtra("SensorType", sensorType);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if (sensorType == Sensor.TYPE_ACCELEROMETER) {
			float x = event.values[0];
			float y = event.values[1];
			float z = event.values[2];
			sensorInformation1.setText("Acceleration along x-axis is "
					+ String.valueOf(x));
			sensorInformation2.setText("Acceleration along y-axis is "
					+ String.valueOf(y));
			sensorInformation3.setText("Acceleration along z-axis is "
					+ String.valueOf(z));
		}
		if (sensorType == Sensor.TYPE_LIGHT) {
			float lux = event.values[0];
			sensorInformation1.setText("Light Sensitivity is "
					+ String.valueOf(lux));
		}
		if (sensorType == Sensor.TYPE_PROXIMITY) {
			float x = event.values[0];

			sensorInformation1.setText("Proximity Level is "
					+ String.valueOf(x));

		}

	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mSensorManager.registerListener(this, mSensor,
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mSensorManager.unregisterListener(this);
	}
}
