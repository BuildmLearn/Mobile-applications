package org.buildmlearn.learnfrommap.maphelper;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.TileOverlayOptions;

import android.content.Context;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

public class MapHelper extends ActionBarActivity {

	public GoogleMap mapView;
	public Location myLocation;
	private SupportMapFragment mapFragment;
	private Handler handler;
	//////////////////
	//Override these function 
	
	public void onMapReady()
	{
		mapView.setOnCameraChangeListener(new OnCameraChangeListener() {
			@Override
			public void onCameraChange(CameraPosition cameraPosition) {
				Toast.makeText(getApplicationContext(), "Zoom: " + cameraPosition.zoom , Toast.LENGTH_SHORT).show();
				if(cameraPosition.zoom > (float)4.0)
				{
					mapView.animateCamera(CameraUpdateFactory.zoomTo((float) 4.0));
				}
				
			}
		});

	}

	public boolean isMapReady() 
	{
		return mapView != null;
	}

	public void showErrorMessage(String msg)
	{

	}

	//////////////////
	

	public void getMapView(SupportMapFragment mapFragment)
	{
		this.handler = new Handler();
		this.mapFragment = mapFragment;
		CheckMap();		
	}

	private void setUpMap()
	{
		handler.post(new Runnable() {

			@Override
			public void run() {
				mapView = mapFragment.getMap();
				if(mapView == null)
				{
					handler.postDelayed(this, 1000);
				}
				else
				{
					mapView.setMapType(GoogleMap.MAP_TYPE_NONE);
					mapView.addTileOverlay(new TileOverlayOptions().tileProvider(new CustomTileProvider(getResources().getAssets(), mapView)));
					if(getApplicationContext() != null)
					{
						runOnUiThread(new Runnable() {

							@Override
							public void run() {
								onMapReady();

							}
						});
					}
				}

			}
		});

	}


	private void CheckMap() {

		new Thread(new Runnable() {

			@Override
			public void run() {
				int playServiceStatus = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplication());

				if (playServiceStatus == ConnectionResult.SUCCESS) 
				{
					if (getVersionFromPackageManager(getApplicationContext()) >= 2) 
					{
						setUpMap();
						if (getApplicationContext() != null) 
						{
							runOnUiThread(new Runnable() 
							{
								@Override
								public void run() 
								{
									showErrorMessage("Google Maps  present");
								}
							});
						}
					} 
					else 
					{
						// OpenGL version is not supported
						if (getApplicationContext() != null) 
						{
							runOnUiThread(new Runnable() 
							{
								@Override
								public void run() 
								{
									showErrorMessage("Maps cannot be loaded:  OpenGL version is less than 2");
								}
							});
						}
					}
				} 
				else if (GooglePlayServicesUtil.isUserRecoverableError(playServiceStatus)) 
				{
					runOnUiThread(new Runnable() 
					{
						@Override
						public void run() 
						{
							showErrorMessage("Google Play services not present");
						}
					});
				} else 
				{
					if (getApplicationContext() != null) 
					{
						runOnUiThread(new Runnable() 
						{
							@Override
							public void run() 
							{
								showErrorMessage("Unknown Error");
							}
						});
					}
				}

			}

		}).start();

	}


	private int getVersionFromPackageManager(Context context) 
	{
		PackageManager packageManager = context.getPackageManager();
		FeatureInfo[] featureInfos = packageManager.getSystemAvailableFeatures();
		if (featureInfos != null && featureInfos.length > 0) {
			for (FeatureInfo featureInfo : featureInfos) 
			{
				// Null feature name means this feature is the open
				// GLes version feature.
				if (featureInfo.name == null) 
				{
					if (featureInfo.reqGlEsVersion != FeatureInfo.GL_ES_VERSION_UNDEFINED)
					{
						return getMajorVersion(featureInfo.reqGlEsVersion);
					} else 
					{
						return 1;
					}
				}
			}
		}
		return 1;
	}

	private int getMajorVersion(int glEsVersion) {
		return ((glEsVersion & 0xffff0000) >> 16);
	}
}
