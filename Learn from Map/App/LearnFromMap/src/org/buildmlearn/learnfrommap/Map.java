package org.buildmlearn.learnfrommap;

import org.buildmlearn.learnfrommap.maphelper.MapHelper;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Map extends MapHelper implements OnCameraChangeListener {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);

		new Handler().post(new Runnable() {

			@Override
			public void run() {
				getMapView((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.mapFragment));

			}
		});		

	}



		@Override
		public void showErrorMessage(String msg) {
			// TODO Auto-generated method stub
			super.showErrorMessage(msg);
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
		}

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {

			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.map, menu);
			return true;
		}

		@Override
		public void onMapReady() {
			// TODO Auto-generated method stub
			super.onMapReady();

			Toast.makeText(getApplicationContext(), "Map ready", Toast.LENGTH_LONG).show();
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
		public void onCameraChange(CameraPosition pos) {
			CameraUpdateFactory.zoomTo(3.0f);

		}



	}
