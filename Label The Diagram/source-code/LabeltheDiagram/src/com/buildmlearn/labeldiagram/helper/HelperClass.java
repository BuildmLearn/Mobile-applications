package com.buildmlearn.labeldiagram.helper;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
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
	
	// Set custom fonts
	public static Typeface customFontManager(Typeface tf, Context context){		
		Typeface mTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/"+tf);
		return mTypeface;
	}
	

}
