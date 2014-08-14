package org.buildmlearn.learnfrommap;

import java.util.ArrayList;
import java.util.Collections;

import org.buildmlearn.learnfrommap.databasehelper.Database;
import org.buildmlearn.learnfrommap.helper.CustomDialog;
import org.buildmlearn.learnfrommap.helper.TextViewPlus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.support.v7.app.ActionBarActivity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
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
	private ArrayAdapter<String> adapter;
	private Dialog dialog;

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

		locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

		// Define a listener that responds to location updates
		locationListener = new LocationListener() {
			@Override
			public void onLocationChanged(Location location) {

				getCountry(location.getLatitude(), location.getLongitude());
			}

			public void onStatusChanged(String provider, int status, Bundle extras) {
			}

			public void onProviderEnabled(String provider) {
			}

			public void onProviderDisabled(String provider) {
				mLoading.setVisibility(View.GONE);
				mMain.setVisibility(View.VISIBLE);
			}
		};
		new GetCountryList().execute(); 

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
		Database db =  new Database(getApplicationContext());
		String locat = db.getCountryCoordinates(country);
		db.close();
		intent.putExtra("VALUE", country);
		intent.putExtra("DISPLAY", "Country: " + country);
		intent.putExtra("LOCATION", locat);
		startActivity(intent);

	}

	public void loadLocation(View v)
	{
		// Register the listener with the Location Manager to receive location updates
		if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
		{



			mLoading.setVisibility(View.VISIBLE);
			mMain.setVisibility(View.GONE);
			if(location != null)
			{
				getCountry(location.getLatitude(), location.getLongitude());
			}
			else
			{		

				try 
				{
					locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 0, 0, locationListener);
					locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
					locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
				}
				catch(IllegalArgumentException e)
				{
					Toast.makeText(getApplicationContext(), "There was some error fetching your location\nError: " + e.getMessage(), Toast.LENGTH_LONG).show();
					mLoading.setVisibility(View.GONE);
					mMain.setVisibility(View.VISIBLE);
					e.printStackTrace();
				}

			}
		}
		else
		{
			showConfirmDialog();
		}

	}

	private void getCountry(double lat, double lng) {
		String googleurl = "https://maps.google.com/maps/api/geocode/json?key=AIzaSyACYVxd_d-49UnhqibCI6F9f7b5Gw1qTSc&";
		Log.v("HTTP" , "Latitude is: " + lat + "Longitude is:" + lng);
		StringBuilder sbuilder = new StringBuilder();
		sbuilder.append(googleurl);

		sbuilder.append("latlng=" + lat + "," + lng);
		sbuilder.append("&sensor=true");
		String url = sbuilder.toString();
		Log.v("URL", url);
		StringRequest myReq = new StringRequest(Method.GET, 
				url,
				new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				Log.d("VOLLEY", response);
				try {
					JSONObject main = new JSONObject(response);
					JSONArray array = main.getJSONArray("results");
					JSONObject obj = array.getJSONObject(0);
					array = obj.getJSONArray("address_components");
					for(int i=0; i<array.length(); i++)
					{
						obj = array.getJSONObject(i);
						Log.d("JSON" + i, obj.toString());	
						JSONArray tempArray = obj.getJSONArray("types");
						if(tempArray.getString(0).equals("country"))
						{
							String country = obj.getString("long_name");
							Log.e("Country", country);
							Database db =  new Database(getApplicationContext());
							String locat = db.getCountryCoordinates(country);
							db.close();
							intent.putExtra("LOCATION", locat);
							intent.putExtra("VALUE", country);
							intent.putExtra("DISPLAY", "Country: " + country);
							
							startActivity(intent);
							locationManager.removeUpdates(locationListener);
						}
					}

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					Toast.makeText(getApplicationContext(), "There was some error fetching your location\nError: " + e.getMessage(), Toast.LENGTH_LONG).show();
					mLoading.setVisibility(View.GONE);
					mMain.setVisibility(View.VISIBLE);
					e.printStackTrace();
				}

			}
		},
		new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				error.printStackTrace();
				Toast.makeText(getApplicationContext(), "There was some error fetching your location\nError: " + error.getMessage(), Toast.LENGTH_LONG).show();
				mLoading.setVisibility(View.GONE);
				mMain.setVisibility(View.VISIBLE);
			}
		});
		RequestQueue mQueue = Volley.newRequestQueue(this);
		mQueue.add(myReq);
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
			CustomDialog.AboutDialog(ClassicModeActivity.this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	public class GetCountryList extends AsyncTask<Void, Void, Void>
	{
		ArrayList<String> countryList;
		@Override
		protected Void doInBackground(Void... arg0) {
			Database db =  new Database(getApplicationContext());
			countryList = db.countryList();
			Collections.sort(countryList);			
			db.close();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_text_view, countryList);
			// Specify the layout to use when the list of choices appears
			adapter.setDropDownViewResource(R.layout.spinner_text_view_dropdown);
			// Apply the adapter to the spinner
			spinner.setAdapter(adapter);
		}



	}


	protected void showConfirmDialog() {
		dialog = new Dialog(ClassicModeActivity.this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		dialog.setContentView(R.layout.dialog_location);   
		dialog.show();
		TextViewPlus yes = (TextViewPlus)dialog.findViewById(R.id.confirm_yes);
		yes.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
				Intent myIntent = new Intent( Settings.ACTION_LOCATION_SOURCE_SETTINGS );
                startActivity(myIntent);
			}
		});
		TextViewPlus no = (TextViewPlus)dialog.findViewById(R.id.confirm_no);
		no.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
	}

}
