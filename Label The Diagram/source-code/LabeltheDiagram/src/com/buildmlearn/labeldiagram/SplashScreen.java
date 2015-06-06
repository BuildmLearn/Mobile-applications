package com.buildmlearn.labeldiagram;

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
        setContentView(R.layout.splash_screen);
        
        // Calling NavigationHandler to wait and dispatch to MainMenu
        Handler handler=new Handler();
		handler.postDelayed(new NavigationHandler(),3000);
        
		// Disable the ActionBar
		ActionBar bar=getActionBar();
		bar.hide();
        
    }
    
    class NavigationHandler implements Runnable{

		@Override
		public void run() {
			
			startActivity(new Intent(getApplication(),MainMenu.class));
			/*Intent launchNextActivity;
			launchNextActivity = new Intent(getApplication(), MainMenu.class);
			launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);                  
			launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			startActivity(launchNextActivity);*/
			
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
