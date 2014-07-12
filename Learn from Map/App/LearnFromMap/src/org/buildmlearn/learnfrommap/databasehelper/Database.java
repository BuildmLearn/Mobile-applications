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
import android.widget.SeekBar;

public class Database extends SQLiteAssetHelper  {

	public static String DB_PATH;
	private static final String TABLE_NAME = "main";
	public SQLiteDatabase database;
	public Context context;
	private SQLiteDatabase db;
	private static final String DB_NAME = "data_test12.db";
	private static final int DATABASE_VERSION = 1;
	private static long lastSeed;

	public Database(Context context) {
		super(context, DB_NAME, null, DATABASE_VERSION);
		Log.e("Here", "I am in constructor");
		//db = getReadableDatabase();
	}

	public Database(Context context,  int i) {
		super(context, DB_NAME, null, DATABASE_VERSION);
		Log.e("Here", "I am in constructor");
		db = getReadableDatabase();
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

	public String getId(String query) throws QuestionModuleException
	{
		return getId(query, 0);
	}

	public String getId(String query, int columnIndex) throws QuestionModuleException
	{
		Cursor cursor =db.rawQuery(query, null);
		if(cursor.moveToFirst())
		{
			if(cursor.getCount() == 1)
			{
				return cursor.getString(columnIndex);

			}
			else
			{
				throw new QuestionModuleException("getId() :" + cursor.getCount() + " results returned for " + query);
			}			
		}
		else
		{
			throw new QuestionModuleException("getId(): MoveToFirst null for " + query);
		}

	}


	public DbRow rawSelect(String query,String countQuery) throws QuestionModuleException
	{
		int randomNo = 0;
		Cursor cursor = db.rawQuery(countQuery, null);
		if(cursor.moveToFirst())
		{
			if(cursor.getCount() == 1)
			{
				int c = cursor.getInt(0);
				if(c == 0)
				{
					throw new QuestionModuleException("No of rows: 0 for " + countQuery);
				}
				Random random = new Random(lastSeed + new Date().getTime());
				randomNo = random.nextInt(c);
				lastSeed =  randomNo;
				Log.e("LAST SEED", lastSeed + "");
			}
			else
			{
				throw new QuestionModuleException(cursor.getCount() + " results returned for " + query);
			}			
		}
		else
		{
			throw new QuestionModuleException("Cursor Error: Count(*)");
		}
		cursor.close();
		String x = query + randomNo + ", 1";
		//Actual query 
		cursor = db.rawQuery(x, null);
		if(cursor.moveToFirst())
		{
			if(cursor.getCount() == 1)
			{
				//Store it in RowDb class object
				int id = cursor.getInt(0);
				String name = cursor.getString(1);
				float lat = cursor.getFloat(2);
				float lng = cursor.getFloat(3);
				int contId = cursor.getInt(4);
				String continent = null;
				switch (contId) {
				case 1:
					continent = "Africa";
					break;
				case 2:
					continent = "Asia";
					break;
				case 3:
					continent = "Europe	";
					break;
				case 4:
					continent = "North America";
					break;
				case 5:
					continent = "Oceania";
					break;
				case 6:
					continent = "South America";
					break;
				case 7:
					continent = "Antarctica";
					break;
				default:
					continent = "-1";
					break;
				}
				int countryId = cursor.getInt(5);
				String country;
				String capital;
				if(countryId != -1)
				{
					query = "SELECT * FROM country WHERE _id = " + countryId;
					Cursor subCursor = db.rawQuery(query, null);
					if(subCursor.moveToFirst())
					{
						int s = subCursor.getCount();
						if(s == 1)
						{
							country = subCursor.getString(1);
							capital = subCursor.getString(2);
							subCursor.close();
						}
						else
						{
							throw new QuestionModuleException("subCursor returned: " + s);
						}


					}
					else
					{
						throw new QuestionModuleException("subCursor move to first error for query: " + query);
					}
				}
				else
				{
					country = "-1";
					capital = "-1";
				}
				int stateId = cursor.getInt(6);
				String state;
				if(stateId != -1)
				{
					query = "SELECT * FROM state WHERE _id = " + stateId;
					Cursor subCursor = db.rawQuery(query, null);
					if(subCursor.moveToFirst())
					{
						int s = subCursor.getCount();
						if(s == 1)
						{
							state = subCursor.getString(1);
							subCursor.close();
						}
						else
						{
							throw new QuestionModuleException("subCursor returned: " + s);
						}


					}
					else
					{
						throw new QuestionModuleException("subCursor move to first error for query: " + query);
					}
				}
				else
				{
					state ="-1";
				}
				int population = cursor.getInt(7);
				int elevation = cursor.getInt(8);
				DbRow row = new DbRow(id, lng, lat, name, country, capital, state, continent, population, elevation);
				return row;
			}
			else
			{
				throw new QuestionModuleException(cursor.getCount() + " results returned for " + query);
			}			
		}
		else
		{
			//db.close();
			throw new QuestionModuleException("Cursor Error");
		}

	}
	
	
	public String[] createOptions(String columnName, String answer, String table) throws QuestionModuleException
	{
		String tableName;
		String column;
		if(columnName.equals("state"))
		{
			tableName = "state";
			column = "name";
			
		}
		else if(columnName.equals("country") || columnName.equals("capital"))
		{
			tableName = "country";
			column = "name";
		}
		else
		{
			tableName = table;
			column = columnName;
		}
		String countQuery = "SELECT COUNT(*) FROM " + tableName + " WHERE " + column + " != '" + answer + "'";
		int randomNo = 0;
		Cursor cursor = db.rawQuery(countQuery, null);
		if(cursor.moveToFirst())
		{
			if(cursor.getCount() == 1)
			{
				int c = cursor.getInt(0);
				if(c == 0)
				{
					throw new QuestionModuleException("No of rows: 0 for " + countQuery);
				}
				Random random = new Random(lastSeed + new Date().getTime());
				randomNo = random.nextInt(c);
				lastSeed =  randomNo;
				Log.e("LAST SEED", lastSeed + "");
			}
			else
			{
				throw new QuestionModuleException(cursor.getCount() + " results returned for " + countQuery);
			}			
		}
		else
		{
			throw new QuestionModuleException("Cursor Error: Count(*)");
		}
		cursor.close();
		randomNo -= 3;
		if(randomNo <= 0 )
		{
			randomNo = 1;
		}
		String query = "SELECT " + column + " FROM " + tableName + " WHERE " + column + " != '" + answer + "' LIMIT " + randomNo + ", 3";
		cursor = db.rawQuery(query, null);
		if(cursor.moveToFirst())
		{
			if(cursor.getCount() == 3)
			{
				int i = 0;
				String[] option = new String[3];
				do
				{
					option[i] = cursor.getString(0);
					i++;
					
				}
				while(cursor.moveToNext());
				return option;
			}
			else
			{
				throw new QuestionModuleException("Invalid row count while creating options: Count " + cursor.getCount());
			}
		}
		else
		{
			throw new QuestionModuleException("Cursor Error: MoveToFirst");
		}
		
		
		
	}

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
			//DbRow dbRow = new DbRow(id, lng, lat, name, code, country_code, country, capital, state, continent, population, elevation);
			cursor.close();
			db.close();
			return null;
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

