package com.buildmlearn.labeldiagram;

import java.util.ArrayList;
import java.util.List;

import com.buildmlearn.labeldiagram.helper.HelperClass;
import com.buildmlearn.labeldiagram.resources.BadgeGridRowItem;
import com.buildmlearn.labeldiagram.resources.BadgesGridViewAdapter;
import com.buildmlearn.labeldiagram.resources.MenuGridRowItem;
import com.buildmlearn.labeldiagram.resources.MenuGridViewAdapter;
import com.example.labelthediagram.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;

public class MainMenuGrid extends Activity implements OnClickListener{
	
	List<MenuGridRowItem> gridArray = new ArrayList<MenuGridRowItem>();
	GridView gridView;
	MenuGridViewAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main_menu_grid);
		
		HelperClass.setActionBar("Main Menu", this);
		
		gridArray.add(new MenuGridRowItem(R.layout.main_menu_item_view));
		gridArray.add(new MenuGridRowItem(R.layout.main_menu_item_view));
		gridArray.add(new MenuGridRowItem(R.layout.main_menu_item_view));
		gridArray.add(new MenuGridRowItem(R.layout.main_menu_item_view));
		gridArray.add(new MenuGridRowItem(R.layout.main_menu_item_view));
		gridArray.add(new MenuGridRowItem(R.layout.main_menu_item_view));
		
		adapter = new MenuGridViewAdapter(this, R.layout.menu_grid_row_item, gridArray);
		
		gridView = (GridView)findViewById(R.id.mainMenuGrid);
		gridView.setAdapter(adapter);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onBackPressed() {

		// Disable back button behavior and exit the app
		new AlertDialog.Builder(this)
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setTitle("Exit")
				.setMessage("Are you sure?")
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {

								Intent intent = new Intent(Intent.ACTION_MAIN);
								intent.addCategory(Intent.CATEGORY_HOME);
								intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
								startActivity(intent);
								finish();
								System.exit(0);

								// finish(); // finish activity

							}
						}).setNegativeButton("No", null).show();
	}

}
