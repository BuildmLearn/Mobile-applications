package com.buildmlearn.labeldiagram.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class Database extends SQLiteAssetHelper{
	
	private static final String DATABASE_NAME = "lessons.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getReadableDatabase();
    }

    public String getLesson(String diagramTitle){
    	
    	db = getReadableDatabase();
    	String query = "SELECT * FROM Lesson where diagram_name='" + diagramTitle + "'";
    	String result=null;
    	Cursor cursor = db.rawQuery(query, null);
    	
    	if(cursor.moveToFirst()){
    		result = cursor.getString(1);
    	}
    	cursor.close();
    	return result;
    }

}
