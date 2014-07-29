package org.buildmlearn.learnfrommap.databasehelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.buildmlearn.learnfrommap.questionmodule.DbRow;
import org.buildmlearn.learnfrommap.questionmodule.QuestionModuleException;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Database extends SQLiteAssetHelper  {

	public static String DB_PATH;
	public SQLiteDatabase database;
	public Context context;
	private SQLiteDatabase db;
	private static final String DB_NAME = "data_test12.db";
	private static final int DATABASE_VERSION = 1;
	private static long lastSeed;

	public Database(Context context) {
		super(context, DB_NAME, null, DATABASE_VERSION);
	}

	public Database(Context context,  int i , String path) {
		super(context, DB_NAME, path, null, DATABASE_VERSION);
		Log.d("Database", "Opening readable database");
		db = getReadableDatabase();
	}

	public SQLiteDatabase getDb() {
		return database;
	}

	public void closeReadableDatabase()
	{
		Log.d("Database", "Cloasing database");
		db.close();
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
	
	public ArrayList<String> countryList()
	{
		ArrayList<String> list = new ArrayList<String>();
		db = getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT name FROM country", null);
		if(cursor.moveToFirst())
		{
			do
			{
				list.add(cursor.getString(0));
			}
			while(cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return list;
		
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
		answer = answer.replaceAll("'", "''");
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
	
}

