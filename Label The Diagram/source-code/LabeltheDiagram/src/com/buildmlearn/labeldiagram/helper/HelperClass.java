package com.buildmlearn.labeldiagram.helper;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Typeface;

public class HelperClass {
	
	// Set ActionBar
	public static void setActionBar(String title,Activity context){
		ActionBar actionBar = context.getActionBar();
		actionBar.setTitle(title);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.show();
	}
	
	public static void customFontManager(Typeface tf, Activity context){
		
	}
	

}
