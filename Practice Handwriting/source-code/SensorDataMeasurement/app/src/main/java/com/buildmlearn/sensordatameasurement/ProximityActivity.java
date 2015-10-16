package com.buildmlearn.sensordatameasurement;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;

public class ProximityActivity extends Activity implements SensorEventListener {
	 private SensorManager mSensorManager;
	 private Sensor mSensor;
	 ImageView iv;
	 
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_proximity);
	  mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
	  mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
	  iv = (ImageView) findViewById(R.id.imageView1);
	 }
	 protected void onResume() {
	  super.onResume();
	  mSensorManager.registerListener(this, mSensor,
	    SensorManager.SENSOR_DELAY_NORMAL);
	 }
	 protected void onPause() {
	  super.onPause();
	  mSensorManager.unregisterListener(this);
	 }
	 public void onAccuracyChanged(Sensor sensor, int accuracy) {
	 }
	 public void onSensorChanged(SensorEvent event) {
	  if (event.values[0] == 0) {
	   iv.setImageResource(R.drawable.near);
	  } else {
	   iv.setImageResource(R.drawable.far);
	  }
	 }
	}


