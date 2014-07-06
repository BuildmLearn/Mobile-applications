package org.buildmlearn.learnfrommap.databasehelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

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
	private static long lastSeed = 0;

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


	public DbRow select(String where, String[] whereArgs, String orderBy) throws QuestionModuleException
	{
		db = this.getReadableDatabase();
		int count = 0;
		if(orderBy != null && orderBy.equals("RANDOM()"))
		{
			String[] column = {"COUNT(*)"};
			Cursor cursor = db.query(TABLE_NAME, column, where, whereArgs, null, null, null, null);
			if(cursor.moveToFirst())
			{
				count = cursor.getInt(0);
				cursor.close();
			}
		}
		Random random = new Random(new Date().getTime() + lastSeed);
		int next = random.nextInt(count);
		lastSeed = next;
		Cursor cursor = db.query(TABLE_NAME, null, where, whereArgs, null, null, null, next + ", 1");
		if(cursor.moveToFirst())
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
			cursor.close();
			db.close();
			return dbRow;
		}
		else
		{
			cursor.close();
			db.close();
			Log.d("Error", "Cursor error");
			throw new QuestionModuleException("Cursor.moveToFirst Error ");
		}

	}

	public ArrayList<String> selectColumn(Boolean distinct, String where, String[] whereArgs, String orderBy, String limit, String groupBy) throws QuestionModuleException
	{
		db = this.getReadableDatabase();
		int count = 0;
		if(orderBy != null && orderBy.equals("RANDOM()"))
		{
			String[] column = {"COUNT(DISTINCT " + orderBy + ")"};
			String rawQuery = "SELECT COUNT(DISTINCT " + groupBy + ") FROM "  + TABLE_NAME;
			Cursor cursor = db.rawQuery(rawQuery, null);
			if(cursor.moveToFirst())
			{
				count = cursor.getInt(0);
				cursor.close();
			}
		}
		Random random = new Random(new Date().getTime() + lastSeed);
		int next = random.nextInt(count-Integer.parseInt(limit));
		lastSeed = next;
		String[] columns = {groupBy};
		Cursor cursor = db.query(distinct, TABLE_NAME, columns, where, whereArgs, groupBy, null, null, next + "," +limit);
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

