package org.buildmlearn.learnfrommap;

import java.util.ArrayList;
import java.util.Collections;

import org.buildmlearn.learnfrommap.adapter.CategoryAdapter;
import org.buildmlearn.learnfrommap.databasehelper.DatabaseHelper;
import org.buildmlearn.learnfrommap.helper.CustomDialog;
import org.buildmlearn.learnfrommap.helper.TextViewPlus;
import org.buildmlearn.learnfrommap.parser.XmlParser;
import org.buildmlearn.learnfrommap.questionmodule.XmlQuestion;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * 
 * This activity shows the categories.
 * 
 * @author Abhishek
 *
 */
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
		XmlParser parser = new XmlParser(getApplicationContext());
		ArrayList<XmlQuestion> xmlList = parser.fetchQuestions();
		for(XmlQuestion temp: xmlList)
		{
			if(!mCList.contains(temp.getAlias()))
			{
				mCList.add(temp.getAlias());
			}
		}
		Collections.sort(mCList);
		mAdapter = new CategoryAdapter(this, R.layout.listview_row_category_mode, mCList);
		mCategoryList.setAdapter(mAdapter);
		mCategoryList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				TextViewPlus categoryName = (TextViewPlus)arg1.findViewById(R.id.category);
				//Toast.makeText(getApplicationContext(), categoryName.getText(), 1000).show();
				Intent intent = new Intent(getBaseContext(), GameActivity.class);
				intent.putExtra("MODE", "CATEGORY_MODE");
				intent.putExtra("SELECTION", "CATEGORY");
				intent.putExtra("VALUE", categoryName.getText());
				intent.putExtra("LOCATION", "0,0");
				intent.putExtra("DISPLAY", "Category: " + categoryName.getText());
				startActivity(intent);
			}
		});

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
