package org.buildmlearn.learnfrommap.databasehelper;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Database extends SQLiteAssetHelper  {

	public static String DB_PATH;
	private static final String TABLE_NAME = "main";
	public SQLiteDatabase database;
	public Context context;
	private SQLiteDatabase db;
	private static final String DB_NAME = "pls10.db";
	private static final int DATABASE_VERSION = 1;

	public Database(Context context) {
		super(context, DB_NAME, null, DATABASE_VERSION);
		Log.e("Here", "I am in constructor");
	}

	public SQLiteDatabase getDb() {
		return database;
	}



	@Override
	public synchronized void close() {
		if (database != null) {
			database.close();
		}
		super.close();
	}



	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}


	public Cursor select(String where, String[] whereArgs, String orderBy, String limit)
	{
		db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, null, where, whereArgs, null, null, orderBy, limit);
		return cursor;
	}

	
	public void queryDatabase()
	{
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				SQLiteDatabase db = Database.this.getReadableDatabase();
				Log.e("Database", db.toString());
				
			}
		}).start();

		
		
	}
}

