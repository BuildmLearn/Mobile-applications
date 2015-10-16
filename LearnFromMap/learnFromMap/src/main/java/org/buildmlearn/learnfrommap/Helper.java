package org.buildmlearn.learnfrommap;

import org.buildmlearn.learnfrommap.databasehelper.Database;
import org.buildmlearn.learnfrommap.maphelper.CustomTileProvider;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;

import android.content.Context;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

public class Helper extends ActionBarActivity {

	public GoogleMap mapView;
	public Location myLocation;
	private SupportMapFragment mapFragment;
	private Handler handler;
	private MarkerOptions markerOptions;
	public Marker marker;



	/**
	 * Called whenever the maps are successfully loaded
	 */
	public void onMapReady() {
		mapView.setOnCameraChangeListener(new OnCameraChangeListener() {
			@Override
			public void onCameraChange(CameraPosition cameraPosition) {
				if (cameraPosition.zoom > (float) 5.0) {
					mapView.animateCamera(CameraUpdateFactory
							.zoomTo((float) 5.99));
				}

			}
		});
		mapView.setOnMapClickListener(new GoogleMap.OnMapClickListener() {



			@Override
			public void onMapClick(LatLng arg0) {
				if(marker != null)
				{
					marker.remove();
				}
				markerOptions = new MarkerOptions().draggable(true).position(arg0).flat(true).title("Your Answer");
				marker = mapView.addMarker(markerOptions);
			}
		});

	}
	
	/**
	 * @return GoogleMap
	 */
	public GoogleMap getMaps()
	{
		return mapView;
		
	}
	
	/**
	 * @return Marker position
	 */
	public LatLng getPosition()
	{
		if(marker != null)
		{
			return marker.getPosition();
		}
		return null;
		
	}

	/**
	 * Check if the map is ready
	 * 
	 * @return true if map is ready
	 * 
	 */
	public boolean isMapReady() {
		return mapView != null;
	}

	/**
	 * Error message if map is not successfully loaded
	 * 
	 * @param msg
	 */
	public void showErrorMessage(String msg) {

	}

	/**
	 * Called when database is successfully opened
	 * 
	 * @param msg
	 */
	public void onDatabaseLoad(String msg) {

	}

	/**
	 * Called if there is any database error
	 * 
	 * @param msg
	 */
	public void onDatabaseLoadError(String msg) {

	}

	// ////////////////Public Function //////////////////

	public void getMapView(SupportMapFragment mapFragment, LatLng loc) {
		this.handler = new Handler();
		this.mapFragment = mapFragment;
		CheckMap(loc);
	}

	public void queryDatabase(String where, String[] whereArgs, String orderBy,
			String limit) {
		class QueryDatabase implements Runnable {
			public QueryDatabase(String where, String[] whereArgs,
					String orderBy, String limit) {
				super();
			}

			@Override
			public void run() {
				Database main_db = new Database(getApplicationContext());
				main_db.getReadableDatabase();
				if (getApplicationContext() != null) {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(getApplicationContext(),
									"Successfull", Toast.LENGTH_LONG).show();
						}
					});
				}

			}

		}

		Thread t = new Thread(new QueryDatabase(where, whereArgs, orderBy,
				limit));
		t.start();
	}

	public void loadDatabase() {
		new Thread(new Runnable() {

			String msg;

			@Override
			public void run() {
				try {
					Database main_db = new Database(getApplicationContext());
					SQLiteDatabase db = main_db.getReadableDatabase();
					db.close();
					main_db.close();
					msg = "Successfull";
					Thread.sleep(500);
					if (getApplicationContext() != null) {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								onDatabaseLoad(msg);
							}
						});
					}
				} catch (Exception e) {
					msg = "Error loading database\nError: " + e.getMessage();
					if (getApplicationContext() != null) {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								onDatabaseLoadError(msg);
							}
						});
					}

				}

			}
		}).start();

	}

	// ////////////////Private Function //////////////////

	private void setUpMap(final LatLng loc) {
		handler.post(new Runnable() {


			@Override
			public void run() {
				mapView = mapFragment.getMap();
				if (mapView == null) {
					handler.postDelayed(this, 1000);
				} else {
					mapView.setMyLocationEnabled(false);
					mapView.setMapType(GoogleMap.MAP_TYPE_NONE);
					mapView.addTileOverlay(new TileOverlayOptions()
							.tileProvider(new CustomTileProvider(getResources()
									.getAssets(), mapView)));
					markerOptions = new MarkerOptions().draggable(true).position(loc).flat(true).title("Your Answer");
					marker = mapView.addMarker(markerOptions);
					CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(loc, 2);
					mapView.animateCamera(yourLocation);
					//marker = null;
					if (getApplicationContext() != null) {
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

	private void CheckMap(final LatLng loc) {

		new Thread(new Runnable() {

			@Override
			public void run() {
				int playServiceStatus = GooglePlayServicesUtil
						.isGooglePlayServicesAvailable(getApplication());

				if (playServiceStatus == ConnectionResult.SUCCESS) {
					if (getVersionFromPackageManager(getApplicationContext()) >= 2) {
						setUpMap(loc);
						if (getApplicationContext() != null) {
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									showErrorMessage("Google Maps  present");
								}
							});
						}
					} else {
						// OpenGL version is not supported
						if (getApplicationContext() != null) {		
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									showErrorMessage("Maps cannot be loaded:  OpenGL version is less than 2");
								}
							});
						}
					}
				} else if (GooglePlayServicesUtil
						.isUserRecoverableError(playServiceStatus)) {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							showErrorMessage("Google Play services not present");
						}
					});
				} else {
					if (getApplicationContext() != null) {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								showErrorMessage("Unknown Error");
							}
						});
					}
				}

			}

		}).start();

	}

	private int getVersionFromPackageManager(Context context) {
		PackageManager packageManager = context.getPackageManager();
		FeatureInfo[] featureInfos = packageManager
				.getSystemAvailableFeatures();
		if (featureInfos != null && featureInfos.length > 0) {
			for (FeatureInfo featureInfo : featureInfos) {
				// Null feature name means this feature is the open
				// GLes version feature.
				if (featureInfo.name == null) {
					if (featureInfo.reqGlEsVersion != FeatureInfo.GL_ES_VERSION_UNDEFINED) {
						return getMajorVersion(featureInfo.reqGlEsVersion);
					} else {
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
