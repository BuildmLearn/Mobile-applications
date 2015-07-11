package com.buildmlearn.labeldiagram.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {

	// ///////////////////////////////////////////////////////////////////
	// Constants & Data
	// ///////////////////////////////////////////////////////////////////
	
	// For logging:
	private static final String TAG = "DBAdapter";

	// DB Fields
	public static final String KEY_ROWID = "id";
	public static final String KEY_DIAGRAM_NAME = "name";
	public static final String KEY_RESULT = "result";

	// Field numbers 
	public static final int COL_DIAGRAM_NAME = 0;
	public static final int COL_RESULT = 1;

	public static final String[] ALL_KEYS = new String[] { KEY_DIAGRAM_NAME,
			KEY_RESULT };

	// DB info: database name, and the table list .
	public static final String DATABASE_NAME = "DiagramsInfo";
	public static final String DATABASE_TABLE = "resultTable";
	// Track DB version if a new version of the application changes the format.
	public static final int DATABASE_VERSION = 1;

	private static final String DATABASE_CREATE_SQL = "create table "
			+ DATABASE_TABLE + " (" + KEY_DIAGRAM_NAME + " text primary key, "
			+ KEY_RESULT + " text not null "

			+ ");";

	// Context of application who uses us.
	private final Context context;

	private DatabaseHelper diagramsDBHelper;
	private SQLiteDatabase db;

	// ///////////////////////////////////////////////////////////////////
	// Public methods:
	// ///////////////////////////////////////////////////////////////////

	public DBAdapter(Context ctx) {
		this.context = ctx;
		diagramsDBHelper = new DatabaseHelper(context);
	}

	// Open the database connection.
	public DBAdapter open() {
		db = diagramsDBHelper.getWritableDatabase();
		return this;
	}

	// Close the database connection.
	public void close() {
		diagramsDBHelper.close();
	}

	// Add a new set of values to the database.
	public long insertRow(String diagramName, String result) {

		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_DIAGRAM_NAME, diagramName);
		initialValues.put(KEY_RESULT, result);

		return db.insert(DATABASE_TABLE, null, initialValues);
		
	}

	// Return all data in the database.
	public Cursor getAllRows() {
		
		String where = null;
		Cursor c = db.query(true, DATABASE_TABLE, ALL_KEYS, where, null, null,
				null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
		
	}

	// Get a specific row (by diagramName)
	public Cursor getRow(String diagramName) {
		
		String where = KEY_DIAGRAM_NAME + "=" + diagramName;
		Cursor c = db.query(true, DATABASE_TABLE, ALL_KEYS, where, null, null,
				null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
		
	}

	// Change an existing row to be equal to new data.
	public boolean updateRow(String diagramName, String result) {
		
		String where = KEY_DIAGRAM_NAME + "=" + diagramName;

		ContentValues newValues = new ContentValues();
		newValues.put(KEY_DIAGRAM_NAME, diagramName);
		newValues.put(KEY_RESULT, result);

		return db.update(DATABASE_TABLE, newValues, where, null) != 0;
		
	}

	// ///////////////////////////////////////////////////////////////////
	// Private Helper Classes:
	// ///////////////////////////////////////////////////////////////////

	/**
	 * Private class which handles database creation and upgrading. Used to
	 * handle low-level database access.
	 */
	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE_SQL);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading application's database from version "
					+ oldVersion + " to " + newVersion
					+ ", which will destroy all old data!");

			// Destroy old database:
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);

			// Recreate new database:
			onCreate(db);
		}
	}
}
