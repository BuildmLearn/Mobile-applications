package org.buildmlearn.learnfrommap;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);	
        setContentView(R.layout.activity_main);
        
    }
    


    public void loadMode(View v)
    {
    	int id = v.getId();
    	Intent intent;
		switch (id) {
		case R.id.expore_mode:
			intent = new Intent(getApplicationContext(), ExploreMode.class);
	    	startActivity(intent);
			break;
		case R.id.classic_mode:
			intent = new Intent(getApplicationContext(), ClassicModeActivity.class);
	    	startActivity(intent);
	    	break;
		case R.id.category_mode:
			Toast.makeText(getApplicationContext(), "Coming Soon!", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
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
