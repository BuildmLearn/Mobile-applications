package org.buildmlearn.learnfrommap;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class ClassicModeActivity extends ActionBarActivity {
	 	
	private LocationManager locationManager;
	private LocationListener locationListener;
	private Location location;
	private Intent intent;
	private Spinner spinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_classic_mode);
		spinner = (Spinner) findViewById(R.id.classic_spinner1);
		

    	intent = new Intent(getBaseContext(), GameActivity.class);
    	intent.putExtra("MODE", "CLASSIC_MODE");
    	intent.putExtra("SELECTION", "COUNTRY");

		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.countries_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		// Define a listener that responds to location updates
		locationListener = new LocationListener() {
			@Override
		    public void onLocationChanged(Location location) {
		      // Called when a new location is found by the network location provider.
		    	Toast.makeText(getApplicationContext(), "Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude(), Toast.LENGTH_LONG).show();
		    	String country = getCountry(location.getLatitude(), location.getLongitude());
		    	intent.putExtra("VALUE", country);
		    	intent.putExtra("DISPLAY", "Country: " + country);
		    	startActivity(intent);
		    	locationManager.removeUpdates(locationListener);
		    }

		    public void onStatusChanged(String provider, int status, Bundle extras) {
		    	Toast.makeText(getApplicationContext(), "onStatusChanged", Toast.LENGTH_LONG).show();
		    }

		    public void onProviderEnabled(String provider) {
		    	Toast.makeText(getApplicationContext(), "onProviderEnabled", Toast.LENGTH_LONG).show();
		    }

		    public void onProviderDisabled(String provider) {
		    	Toast.makeText(getApplicationContext(), "onProviderDisabled", Toast.LENGTH_LONG).show();
		    }
		  };
		
	}
	
	public void customCountry(View v)
	{
		String country = spinner.getSelectedItem().toString();
		intent.putExtra("VALUE", country);
		intent.putExtra("DISPLAY", "Country: " + country);
    	startActivity(intent);
		
	}
	
	public void loadLocation(View v)
	{


		// Register the listener with the Location Manager to receive location updates
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
		
		if(location != null)
		{
			Toast.makeText(getApplicationContext(), getCountry(location.getLatitude(), location.getLongitude()), Toast.LENGTH_LONG).show();
			intent.putExtra("VALUE", getCountry(location.getLatitude(), location.getLongitude()));
			startActivity(intent);
			locationManager.removeUpdates(locationListener);
		}

	}
	
	private String getCountry(double lat, double lng) {
		Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
		try {
			List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
			Address obj = addresses.get(0);
			return obj.getCountryName();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.classic_mode, menu);
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
