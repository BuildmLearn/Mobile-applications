package com.buildmlearn.labeldiagram.helper;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;

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
	
	// Remove whitespaces from tags
	public static String removeSpaces(String tag){		
		tag = tag.replaceAll("\\s+", "").trim();	
		return tag;
	}
	
	// Set preferences for all the activities
	public static void setPreferences(String key, String value, Context context) {
	    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
	    SharedPreferences.Editor editor = prefs.edit();
	    editor.putString(key, value);
	    editor.commit();
	}

	// Get preferences for each activity
	public static String getPreferences(String key, Context context) {
	    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
	    return preferences.getString(key, null);
	}

}
