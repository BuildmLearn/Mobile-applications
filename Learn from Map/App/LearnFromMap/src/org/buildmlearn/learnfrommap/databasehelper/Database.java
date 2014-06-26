package org.buildmlearn.learnfrommap.databasehelper;

import java.util.ArrayList;

import org.buildmlearn.learnfrommap.questionmodule.DbRow;
import org.buildmlearn.learnfrommap.questionmodule.QuestionModuleException;

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


	public ArrayList<DbRow> select(String where, String[] whereArgs, String orderBy, String limit) throws QuestionModuleException
	{
		db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, null, where, whereArgs, null, null, orderBy, limit);
		ArrayList<DbRow> dbRowList = new ArrayList<DbRow>();
		if(cursor.moveToFirst())
		{
			do
			{
				//0,1,2 represents the order of column in the table
				//like name is the second column so used 1
				int id = cursor.getInt(0);
				String name = cursor.getString(1);
				float lat = cursor.getFloat(2);
				float lng = cursor.getFloat(3);
				String code = cursor.getString(4);
				String country_code = cursor.getString(5);
				String capital = cursor.getString(6);
				String country = cursor.getString(7);
				String state = cursor.getString(8);
				String continent = cursor.getString(9);
				int population = cursor.getInt(10);
				int elevation = cursor.getInt(11);
				DbRow dbRow = new DbRow(id, lng, lat, name, code, country_code, country, capital, state, continent, population, elevation);
				dbRowList.add(dbRow);
			}
			while(cursor.moveToNext());

		}
		else
		{
			//throw error 
			Log.d("Error", "Cursor error");
			throw new QuestionModuleException("Cursor.moveToFirst Error ");
		}
		cursor.close();
		db.close();
		return dbRowList;
	}
	
	public ArrayList<String> selectColumn(Boolean distinct, String where, String[] whereArgs, String orderBy, String limit, String groupBy) throws QuestionModuleException
	{
		db = this.getReadableDatabase();
		String[] columns = {groupBy};
		Cursor cursor = db.query(distinct, TABLE_NAME, columns, where, whereArgs, groupBy, null, orderBy, limit);
		ArrayList<String> list = new ArrayList<String>();
		if(cursor.moveToFirst())
		{
			do
			{
				list.add(cursor.getString(0));
			}
			while(cursor.moveToNext());

		}
		else
		{
			//throw error 
			Log.d("Error", "Cursor error");
			throw new QuestionModuleException("Cursor.moveToFirst Error ");
		}
		cursor.close();
		db.close();
		return list;
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

