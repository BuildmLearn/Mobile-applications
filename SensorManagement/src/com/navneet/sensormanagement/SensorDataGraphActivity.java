package com.navneet.sensormanagement;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.Arrays;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.androidplot.xy.BarFormatter;
import com.androidplot.xy.BarRenderer;
import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;

public class SensorDataGraphActivity extends Activity implements
		SensorEventListener {

	// A simple formatter to conver bar indexes into sensor names
	private class APRIndexFormat extends Format {
		@Override
		public StringBuffer format(Object obj, StringBuffer toAppendTo,
				FieldPosition pos) {
			Number num = (Number) obj;

			// using num.intValue() will floor the value, so we add 0.5 to round
			// instead:
			int roundNum = (int) (num.floatValue() + 0.5f);
			if (sensorType == Sensor.TYPE_ACCELEROMETER) {
				switch (roundNum) {
				case 0:
					toAppendTo.append("X");
					break;
				case 1:
					toAppendTo.append("Y");
					break;
				case 2:
					toAppendTo.append("Z");
					break;
				default:
					toAppendTo.append("Unknown");
				}
			}
			if (sensorType == Sensor.TYPE_LIGHT) {
				toAppendTo.append("LUX");
			}
			if (sensorType == Sensor.TYPE_PROXIMITY) {
				toAppendTo.append("Distance");
			}
			return toAppendTo;
		}

		@Override
		public Object parseObject(String source, ParsePosition pos) {
			return null; // We don't use this so just return null for now.
		}
	}

	private SensorManager mSensorManager;
	private Sensor mSensor;
	private XYPlot aprLevelsPlot = null;
	private SimpleXYSeries aprLevelsSeries = null;
	protected int sensorType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sensor_data_graph);
		sensorType = getIntent().getIntExtra("SensorType", 0);
		aprLevelsPlot = (XYPlot) findViewById(R.id.accelerometerPlot);
		if (sensorType == Sensor.TYPE_ACCELEROMETER) {
			aprLevelsSeries = new SimpleXYSeries("Acceleromter Levels");
		}
		if (sensorType == Sensor.TYPE_LIGHT) {
			aprLevelsSeries = new SimpleXYSeries("Light Sensitivity");
		}
		if (sensorType == Sensor.TYPE_PROXIMITY) {
			aprLevelsSeries = new SimpleXYSeries("Proximity Levels");
		}
		aprLevelsSeries.useImplicitXVals();
		aprLevelsPlot.addSeries(
				aprLevelsSeries,
				new BarFormatter(Color.argb(100, 0, 200, 0), Color
						.rgb(0, 80, 0)));
		aprLevelsPlot.setDomainStepValue(3);
		aprLevelsPlot.setTicksPerRangeLabel(3);
		if (sensorType == Sensor.TYPE_ACCELEROMETER) {
			aprLevelsPlot.setRangeBoundaries(-10, 20, BoundaryMode.FIXED);
		}
		if (sensorType == Sensor.TYPE_LIGHT) {
			aprLevelsPlot.setRangeBoundaries(-5, 15, BoundaryMode.FIXED);
		}
		// if (sensorType == Sensor.TYPE_PROXIMITY) {
		// aprLevelsPlot.setRangeBoundaries(2, 100, BoundaryMode.FIXED);
		// }
		// use our custom domain value formatter:
		aprLevelsPlot.setDomainValueFormat(new APRIndexFormat());

		// update our domain and range axis labels:
		aprLevelsPlot.setDomainLabel("Axis");
		aprLevelsPlot.getDomainLabelWidget().pack();
		aprLevelsPlot.setRangeLabel("Distance(metres)");
		aprLevelsPlot.getRangeLabelWidget().pack();
		aprLevelsPlot.setGridPadding(15, 5, 15, 5);
		BarRenderer<?> barRenderer = (BarRenderer<?>) aprLevelsPlot
				.getRenderer(BarRenderer.class);
		if (barRenderer != null && sensorType == Sensor.TYPE_ACCELEROMETER) {
			barRenderer.setBarWidth(25);
		}
		if (barRenderer != null && sensorType == Sensor.TYPE_LIGHT) {
			barRenderer.setBarWidth(35);
		}
		if (barRenderer != null && sensorType == Sensor.TYPE_PROXIMITY) {
			barRenderer.setBarWidth(45);
		}

		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mSensor = mSensorManager.getDefaultSensor(sensorType);

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mSensorManager.registerListener((SensorEventListener) this, mSensor,
				SensorManager.SENSOR_DELAY_NORMAL);

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mSensorManager.unregisterListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sensor_data_graph, menu);
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

	@Override
	public void onSensorChanged(SensorEvent event) {
		if (sensorType == Sensor.TYPE_ACCELEROMETER
				|| sensorType == Sensor.TYPE_PROXIMITY) {
			Number[] series1Numbers = { event.values[0], event.values[1],
					event.values[2] };
			aprLevelsSeries.setModel(Arrays.asList(series1Numbers),
					SimpleXYSeries.ArrayFormat.Y_VALS_ONLY);
			// Redraw the plots
			aprLevelsPlot.redraw();
		}

		if (sensorType == Sensor.TYPE_LIGHT) {
			Number number = event.values[0];
			aprLevelsSeries.setModel(Arrays.asList(number),
					SimpleXYSeries.ArrayFormat.Y_VALS_ONLY);
			aprLevelsPlot.redraw();
		}

	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}
}
