package org.buildmlearn.learnfrommap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.buildmlearn.learnfrommap.helper.CustomDialog;
import org.buildmlearn.learnfrommap.helper.HelperFunctions;
import org.buildmlearn.learnfrommap.helper.StatHolder;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	
	private TextViewPlus c1;
	private TextViewPlus p1;
	private TextViewPlus c2;
	private TextViewPlus p2;
	private TextViewPlus c3;
	private TextViewPlus p3;
	private ArrayList<StatHolder> data;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);	
        setContentView(R.layout.activity_main);
        getSupportActionBar().setHomeButtonEnabled(true);     
    }
    
    @Override
	protected void onResume() {
		
        data = HelperFunctions.bindStat(getApplicationContext());
        
        Collections.sort(data, new Comparator<StatHolder>(){

			@Override
			public int compare(StatHolder a, StatHolder b) {
				return a.getAnswered() < b.getAnswered() ? 1 : -1;
			}
       });
       
        c1 = (TextViewPlus)findViewById(R.id.c1);
        c2 = (TextViewPlus)findViewById(R.id.c2);
        c3 = (TextViewPlus)findViewById(R.id.c3);
        p1 = (TextViewPlus)findViewById(R.id.p1);
        p2 = (TextViewPlus)findViewById(R.id.p2);
        p3 = (TextViewPlus)findViewById(R.id.p3);
        c1.setText(data.get(0).getCountry());
        c2.setText(data.get(1).getCountry());
        c3.setText(data.get(2).getCountry());
        p1.setText(data.get(0).getAnswered() + "/" + data.get(0).getTotal());
        p2.setText(data.get(1).getAnswered() + "/" + data.get(1).getTotal());
        p3.setText(data.get(2).getAnswered() + "/" + data.get(2).getTotal());
        if(data.get(0).getAnswered() == 0)
        {
        	LinearLayout statsLl = (LinearLayout)findViewById(R.id.main_top_countries);
        	statsLl.setVisibility(View.GONE);
        	TextViewPlus noStatMsg = (TextViewPlus)findViewById(R.id.main_no_country_msg);
        	noStatMsg.setVisibility(View.VISIBLE);
        }
        super.onResume();
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
			intent = new Intent(getApplicationContext(), CategoryActivity.class);
	    	startActivity(intent);
			break;
		case R.id.settings:
			intent = new Intent(getApplicationContext(), StatisticsActivity.class);
	    	startActivity(intent);
			break;

		default:
			break;
		}
    }
    
    public void loadStats(View v)
    {
		Intent intent = new Intent(getApplicationContext(), StatisticsActivity.class);
    	startActivity(intent);
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
        	CustomDialog.AboutDialog(MainActivity.this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    

}
