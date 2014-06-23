package org.buildmlearn.learnfrommap;

import org.buildmlearn.learnfrommap.maphelper.MapHelper;

import com.google.android.gms.maps.SupportMapFragment;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends MapHelper {

	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);	
        setContentView(R.layout.activity_main);
        
    }
    
    		
    public void loadMap(View v)
    {
    	
    	Intent intent = new Intent(getApplicationContext(), ExploreMode.class);
    	startActivity(intent);
    }

    public void mcq(View v)
    {
    	RelativeLayout main = (RelativeLayout)findViewById(R.id.main_layout);
    	View view = getLayoutInflater().inflate(R.layout.activity_map, main,false);
        main.addView(view);

		new Handler().post(new Runnable() {

			@Override
			public void run() {
				getMapView((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.mapFragment));

			}
		});	
		main.removeAllViews();
		
		
		
		
		
		
		
//    	Intent intent = new Intent(getApplicationContext(), ClassicModeActivity.class);
//    	startActivity(intent);
    }


    @Override
	public void showErrorMessage(String msg) {
		// TODO Auto-generated method stub
		super.showErrorMessage(msg);
		Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
	}


	@Override
	public void onMapReady() {
		// TODO Auto-generated method stub
		super.onMapReady();

		Toast.makeText(getApplicationContext(), "Map ready", Toast.LENGTH_LONG).show();
	}

    
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
