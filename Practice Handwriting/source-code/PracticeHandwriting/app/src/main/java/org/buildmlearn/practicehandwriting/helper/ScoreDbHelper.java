package org.buildmlearn.practicehandwriting.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ScoreDbHelper extends SQLiteOpenHelper {
    public static String TABLE_NAME = "SCORES",
            COLUMN_NAME_STRING = "STRING",
            COLUMN_NAME_SCORE = "SCORE";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    COLUMN_NAME_STRING + " VARCHAR(9) PRIMARY KEY, " +
                    COLUMN_NAME_SCORE + " FLOAT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Score.db";

    public ScoreDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        System.out.println("DB");
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

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
        if(cursor!=null && cursor.moveToFirst()) {
            return cursor.getFloat(cursor.getColumnIndexOrThrow(COLUMN_NAME_SCORE));
        } else {
            return 0;
        }
    }

    public void writeScore(String practiceString, float score) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_STRING, practiceString);
        values.put(COLUMN_NAME_SCORE, score);
        if(getScore(practiceString)==0) {
            db.insert(TABLE_NAME, null, values);
        }else {
            db.update(TABLE_NAME,values,COLUMN_NAME_STRING + " = ?",new String[]{practiceString});
        }
    }
}
