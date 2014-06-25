package org.buildmlearn.learnfrommap.databasehelper;

import org.buildmlearn.learnfrommap.maphelper.MapHelper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class DatabaseHelper extends MapHelper {

	private static final String TABLE_NAME = "main";

	public void queryDatabase(String where, String[] whereArgs, String orderBy, String limit)
	{
		class QueryDatabase implements Runnable
		{
			String where;
			String[] whereArgs;
			String orderBy;
			String limit;
			public QueryDatabase(String where, String[] whereArgs,
					String orderBy, String limit) {
				super();
				this.where = where;
				this.whereArgs = whereArgs;
				this.orderBy = orderBy;
				this.limit = limit;
			}
			@Override
			public void run() {
				Database main_db = new Database(getApplicationContext());
				SQLiteDatabase db = main_db.getReadableDatabase();
				Cursor cursor = db.query(TABLE_NAME, null, where, whereArgs, null, null, orderBy, limit);
				if (getApplicationContext() != null) 
				{
					runOnUiThread(new Runnable() 
					{
						@Override
						public void run() 
						{
							Toast.makeText(getApplicationContext()
									, "Successfull", Toast.LENGTH_LONG).show();
						}
					});
				}

			}

		}

		Thread t = new Thread(new QueryDatabase(where, whereArgs, orderBy, limit));
		t.start();
	}

	public void loadDatabase()
	{
		new Thread(new Runnable() {
			
			String msg;
			
			@Override
			public void run() {
				try
				{
					Database main_db = new Database(getApplicationContext());
					SQLiteDatabase db = main_db.getReadableDatabase();
					db.close();
					main_db.close();
					msg = "Successfull";
					Thread.sleep(1000);
					if (getApplicationContext() != null) 
					{
						runOnUiThread(new Runnable() 
						{
							@Override
							public void run() 
							{
								onDatabaseLoad(msg);
							}
						});
					}
				}
				catch(Exception e)
				{
					msg = "Error loading database\nError: " + e.getMessage();
					if (getApplicationContext() != null) 
					{
						runOnUiThread(new Runnable() 
						{
							@Override
							public void run() 
							{
								onDatabaseLoadError(msg);
							}
						});
					}

				}


			}
		}).start();
		
}

	public void onDatabaseLoad(String msg)
	{
		
	}
	
	public void onDatabaseLoadError(String msg)
	{
		
	}

}
