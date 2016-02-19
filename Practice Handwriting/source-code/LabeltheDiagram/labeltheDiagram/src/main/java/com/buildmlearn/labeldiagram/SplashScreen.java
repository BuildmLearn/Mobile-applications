package com.buildmlearn.labeldiagram;

import io.fabric.sdk.android.Fabric;

import com.crashlytics.android.Crashlytics;
import com.example.labelthediagram.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;



public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		//Fabric.with(this, new Crashlytics());
        setContentView(R.layout.splash_screen);
        
        // Calling NavigationHandler to wait and dispatch to MainMenu
        Handler handler=new Handler();
		handler.postDelayed(new NavigationHandler(),3000);
        
    }
    
    class NavigationHandler implements Runnable{

		@Override
		public void run() {
			
			/*startActivity(new Intent(getApplication(),MainMenuGrid.class));*/
			Intent launchNextActivity;
			launchNextActivity = new Intent(getApplication(), MainMenuGrid.class);
			launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(launchNextActivity);
			finish();
			
		}
				
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
