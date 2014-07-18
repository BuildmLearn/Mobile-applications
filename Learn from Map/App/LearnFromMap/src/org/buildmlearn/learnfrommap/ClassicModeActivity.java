package org.buildmlearn.learnfrommap;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.google.android.gms.common.api.Releasable;

import android.support.v7.app.ActionBarActivity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class ClassicModeActivity extends ActionBarActivity {

	private LocationManager locationManager;
	private LocationListener locationListener;
	private Location location;
	private Intent intent;
	private Spinner spinner;
	private RelativeLayout mLoading;
	private RelativeLayout mMain;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().setHomeButtonEnabled(true);
		setContentView(R.layout.activity_classic_mode);
		spinner = (Spinner) findViewById(R.id.classic_spinner1);
		mMain = (RelativeLayout)findViewById(R.id.classic_main);
		mLoading = (RelativeLayout)findViewById(R.id.classic_load);

		intent = new Intent(getBaseContext(), GameActivity.class);
		intent.putExtra("MODE", "CLASSIC_MODE");
		intent.putExtra("SELECTION", "COUNTRY");

		// Create an ArrayAdapter using the string array and a default spinner layout
		//ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_text_view,  R.array.countries_array);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
				R.array.countries_array, R.layout.spinner_text_view);
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
				//Toast.makeText(getApplicationContext(), "Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude(), Toast.LENGTH_LONG).show();
				String country;
				try {
					country = getCountry(location.getLatitude(), location.getLongitude());
					if(country == null)
					{
						Toast.makeText(getApplicationContext(), "Unable to fetch your location. Please select a country from the list", Toast.LENGTH_LONG).show();
						mLoading.setVisibility(View.GONE);
						mMain.setVisibility(View.VISIBLE);
					}
					else
					{
						intent.putExtra("VALUE", country);
						intent.putExtra("DISPLAY", "Country: " + country);
						startActivity(intent);
						locationManager.removeUpdates(locationListener);
					}
				}
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), "Unable to fetch your location. Please select a country from the list", Toast.LENGTH_LONG).show();
					mLoading.setVisibility(View.GONE);
					mMain.setVisibility(View.VISIBLE);
				}

			}

			public void onStatusChanged(String provider, int status, Bundle extras) {
				Toast.makeText(getApplicationContext(), "onStatusChanged", Toast.LENGTH_LONG).show();
			}

			public void onProviderEnabled(String provider) {
				Toast.makeText(getApplicationContext(), "onProviderEnabled", Toast.LENGTH_LONG).show();
			}

			public void onProviderDisabled(String provider) {
				Toast.makeText(getApplicationContext(), "Location Services are disabled", Toast.LENGTH_LONG).show();
				mLoading.setVisibility(View.GONE);
				mMain.setVisibility(View.VISIBLE);
			}
		};

	}


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mLoading.setVisibility(View.GONE);
		mMain.setVisibility(View.VISIBLE);
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

		mLoading.setVisibility(View.VISIBLE);
		mMain.setVisibility(View.GONE);
		// Register the listener with the Location Manager to receive location updates


		if(location != null)
		{

			new Thread(new Runnable() {
				public void run() {

					try {
						final String country;
						country = getCountry(location.getLatitude(), location.getLongitude());
						if(country != null)
						{
							runOnUiThread(new Runnable() {

								@Override
								public void run() {
									// TODO Auto-generated method stubstartActivity(intent);
									intent.putExtra("VALUE", country);	
									intent.putExtra("DISPLAY", "Country: " + country);
									startActivity(intent);

								}
							});
						}

					} catch (IOException e) {
						e.printStackTrace();
						runOnUiThread(new Runnable() {

							@Override
							public void run() {
								Toast.makeText(getApplicationContext(), "Unable to fetch your location. Please select a country from the list", Toast.LENGTH_LONG).show();
								mLoading.setVisibility(View.GONE);
								mMain.setVisibility(View.VISIBLE);

							}
						});

					}

				}
			}).start();

		}
		else
		{
			locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 0, 0, locationListener);
			locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
		}

	}

	private String getCountry(double lat, double lng) throws IOException {
		Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
		List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
		Address obj = addresses.get(0);
		return obj.getCountryName();
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
			showCustomDialog();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
    protected void showCustomDialog() {
        // TODO Auto-generated method stub
        final Dialog dialog = new Dialog(ClassicModeActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.about_dialog);   
        dialog.show();
    }



}
