package org.buildmlearn.learnfrommap;

import java.util.ArrayList;

import org.buildmlearn.learnfrommap.adapter.CategoryAdapter;
import org.buildmlearn.learnfrommap.databasehelper.DatabaseHelper;
import org.buildmlearn.learnfrommap.helper.CustomDialog;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ListView;

public class CategoryActivity extends DatabaseHelper {
	
	ListView mCategoryList;
	ArrayList<String> mCList;
	CategoryAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category);
		getSupportActionBar().setHomeButtonEnabled(true);
		mCategoryList = (ListView)findViewById(R.id.listView_category);
		mCList = new ArrayList<String>();
		mCList.add("Abhishek");
		mCList.add("Batra");
		mAdapter = new CategoryAdapter(this, R.layout.listview_row_category_mode, mCList);
		mCategoryList.setAdapter(mAdapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.category, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			CustomDialog.AboutDialog(CategoryActivity.this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


}
