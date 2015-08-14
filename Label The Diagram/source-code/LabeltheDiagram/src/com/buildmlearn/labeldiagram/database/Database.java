package com.buildmlearn.labeldiagram.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.buildmlearn.labeldiagram.entity.Badge;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class Database extends SQLiteAssetHelper{
	
	private static final String DATABASE_NAME = "staticdata.db";
	private static final String KEY_ACHIEVED = "isAchieved";
	private static final String KEY_NAME = "name";
	private static final String TABLE_BADGE = "Badge";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getReadableDatabase();
    }

    public String getLesson(String diagramTitle){
    	
    	db = getReadableDatabase();
    	String query = "SELECT * FROM Lesson where diagram_name='" + diagramTitle + "'";
    	String result = null;
    	Cursor cursor = db.rawQuery(query, null);
    	
    	if(cursor.moveToFirst()){
    		result = cursor.getString(1);
    	}
    	cursor.close();
    	return result;
    }

    public Badge getBadge(String badgeTitle){
    	
    	db = getReadableDatabase();
    	String query = "SELECT * FROM Badge where name='" + badgeTitle + "'";
    	Badge badge = new Badge();
    	Cursor cursor = db.rawQuery(query, null);
    	
    	if(cursor.moveToFirst()){
    		badge.setTitle(cursor.getString(0));
    		badge.setDescription(cursor.getString(1));
    		//badge.setIconId(Integer.parseInt(cursor.getString(3)));
    	}
    	cursor.close();
    	return badge;
    }
    
    public List<Badge> getAchievedBadge(boolean value){
    	db = getReadableDatabase();
    	String query = "SELECT * FROM Badge where isAchieved='" + 1 + "'";
    	
    	List<Badge> badgeList = new ArrayList<Badge>();
    	Cursor cursor = db.rawQuery(query, null);
    	
    	if(cursor.moveToFirst()){
    		do {
    			Badge badge = new Badge();
    			badge.setTitle(cursor.getString(0));
        		badge.setDescription(cursor.getString(1));
        		badgeList.add(badge);
			} while (cursor.moveToNext());
    		
    		//badge.setIconId(Integer.parseInt(cursor.getString(3)));
    	}
    	cursor.close();
    	return badgeList;
    }
    
    public boolean updateBadgeAchievement(String badgeTitle, boolean result){
    	
    	ContentValues values = new ContentValues();
		values.put(KEY_ACHIEVED, result);
		return db.update(TABLE_BADGE, values, KEY_NAME + " = ?", new String[]{ badgeTitle }) > 0;
		
    }
}
