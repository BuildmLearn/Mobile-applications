package org.buildmlearn.learnfrommap.databasehelper;

import android.content.Context;

public class StatisticalDatabase {

	private Context mContext;

	public StatisticalDatabase(Context mContext) {
		super();
		this.mContext = mContext;
	}
	
	public void addData(ResultHolder result)
	{
		String country = result.getCountry();
		String category = result.getCategory();
		int isCorrect = (result.isCorrect)? 1: 0;
		String type =result.getType();
		String insertQuery = "";
		//Insert into stat table
		DatabaseHelper db = new DatabaseHelper(mContext);
		db.insert(insertQuery);			
	}
}
