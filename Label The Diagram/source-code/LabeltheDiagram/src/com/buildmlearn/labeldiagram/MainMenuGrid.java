package com.buildmlearn.labeldiagram;

import java.util.ArrayList;
import java.util.List;

import com.buildmlearn.labeldiagram.helper.HelperClass;
import com.buildmlearn.labeldiagram.resources.MenuGridRowItem;
import com.buildmlearn.labeldiagram.resources.MenuGridViewAdapter;
import com.example.labelthediagram.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
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
		final Dialog dialog = new Dialog(this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.custom_dialog_view);
		
		Button yesBtn = (Button) dialog.findViewById(R.id.yes_btn);
		yesBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.dismiss();
				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_HOME);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				finish();
			}
		});
		Button noBtn = (Button) dialog.findViewById(R.id.no_btn);
		noBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.dismiss();				
			}
		});
		
		dialog.show();
	}

}
