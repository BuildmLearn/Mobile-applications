package com.buildmlearn.sensordatameasurement;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.TextView;

public class AccelerometerActivity extends Activity implements SensorEventListener {

	private float lastX, lastY, lastZ;

	private SensorManager sensorManager;
	private Sensor accelerometer;

	private float deltaXMax = 0;
	private float deltaYMax = 0;
	private float deltaZMax = 0;

	private float deltaX = 0;
	private float deltaY = 0;
	private float deltaZ = 0;

	private float vibrateThreshold = 0;

	private TextView currentX, currentY, currentZ, maxX, maxY, maxZ;

	public Vibrator v;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accelerometer);
		initializeViews();

		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
			// success! we have an accelerometer

			accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
			sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
			vibrateThreshold = accelerometer.getMaximumRange() / 2;
		} else {
			// fai! we dont have an accelerometer!
		}
		
		//initialize vibration
		v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);

	}

	public void initializeViews() {
		currentX = (TextView) findViewById(R.id.currentX);
		currentY = (TextView) findViewById(R.id.currentY);
		currentZ = (TextView) findViewById(R.id.currentZ);

		maxX = (TextView) findViewById(R.id.maxX);
		maxY = (TextView) findViewById(R.id.maxY);
		maxZ = (TextView) findViewById(R.id.maxZ);
	}

	//onResume() register the accelerometer for listening the events
	protected void onResume() {
		super.onResume();
		sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
	}

	//onPause() unregister the accelerometer for stop listening the events
	protected void onPause() {
		super.onPause();
		sensorManager.unregisterListener(this);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {

		// clean current values
		displayCleanValues();
		// display the current x,y,z accelerometer values
		displayCurrentValues();
		// display the max x,y,z accelerometer values
		displayMaxValues();

		// get the change of the x,y,z values of the accelerometer
		deltaX = Math.abs(lastX - event.values[0]);
		deltaY = Math.abs(lastY - event.values[1]);
		deltaZ = Math.abs(lastZ - event.values[2]);

		// if the change is below 2, it is just plain noise
		if (deltaX < 2)
			deltaX = 0;
		if (deltaY < 2)
			deltaY = 0;
		if (deltaZ < 2)
			deltaZ = 0;

		// set the last know values of x,y,z
		lastX = event.values[0];
		lastY = event.values[1];
		lastZ = event.values[2];

		vibrate();

	}

	// if the change in the accelerometer value is big enough, then vibrate!
	// our threshold is MaxValue/2
	public void vibrate() {
		if ((deltaX > vibrateThreshold) || (deltaY > vibrateThreshold) || (deltaZ > vibrateThreshold)) {
			v.vibrate(50);
		}
	}

	public void displayCleanValues() {
		currentX.setText("0.0");
		currentY.setText("0.0");
		currentZ.setText("0.0");
	}

	// display the current x,y,z accelerometer values
	public void displayCurrentValues() {
		currentX.setText(Float.toString(deltaX));
		currentY.setText(Float.toString(deltaY));
		currentZ.setText(Float.toString(deltaZ));
	}

	// display the max x,y,z accelerometer values
	public void displayMaxValues() {
		if (deltaX > deltaXMax) {
			deltaXMax = deltaX;
			maxX.setText(Float.toString(deltaXMax));
		}
		if (deltaY > deltaYMax) {
			deltaYMax = deltaY;
			maxY.setText(Float.toString(deltaYMax));
		}
		if (deltaZ > deltaZMax) {
			deltaZMax = deltaZ;
			maxZ.setText(Float.toString(deltaZMax));
		}
	}
}