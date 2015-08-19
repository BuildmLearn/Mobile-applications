package org.buildmlearn.practicehandwriting.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Database helper class to retrieve/update the maximum score for a string
 */
public class ScoreDbHelper extends SQLiteOpenHelper {

    /**
     * Table name in the DB
     */
    public static String TABLE_NAME = "SCORES";

    /**
     * Variable to access the "STRING" column in the table
     */
    public static String COLUMN_NAME_STRING = "STRING";

    /**
     * Variable to access the "SCORE" column in the table
     */
    public static String COLUMN_NAME_SCORE = "SCORE";

    /**
     * Query to create the table
     */
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    COLUMN_NAME_STRING + " VARCHAR(9) PRIMARY KEY, " +
                    COLUMN_NAME_SCORE + " FLOAT)";

    /**
     * Query to delete the table
     */
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    /**
     * If you change the database schema, you must increment the database version.
     */
    public static final int DATABASE_VERSION = 1;

    /**
     * Name of the DB used
     */
    public static final String DATABASE_NAME = "Score.db";

    /**
     * Constructor
     * @param context The context of the activity from where the class is used
     */
    public ScoreDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    /**
     * Getting the best score for a given string
     * @param practiceString The string for which the score needs to be retrieved
     * @return The best score for the given string
     */
    public float getScore(String practiceString) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{COLUMN_NAME_STRING, COLUMN_NAME_SCORE,},
                COLUMN_NAME_STRING + " = ?",
                new String[]{practiceString},
                null,
                null,
                null,
                null
        );
        if(cursor!=null && cursor.moveToFirst()) {//if an entry exists
            return cursor.getFloat(cursor.getColumnIndexOrThrow(COLUMN_NAME_SCORE));
        } else {
            return 0;
        }
    }

    /**
     * Write a score for a given string
     * @param practiceString The string for which the score is to be stored
     * @param score The score to be stored
     */
    public void writeScore(String practiceString, float score) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_STRING, practiceString);//String that is practiced
        values.put(COLUMN_NAME_SCORE, score);//Score obtained
        if(getScore(practiceString)==0) {//if no entry exists then insert a new value, otherwise update the previous value
            db.insert(TABLE_NAME, null, values);
        }else {
            db.update(TABLE_NAME,values,COLUMN_NAME_STRING + " = ?",new String[]{practiceString});
        }
    }
}
