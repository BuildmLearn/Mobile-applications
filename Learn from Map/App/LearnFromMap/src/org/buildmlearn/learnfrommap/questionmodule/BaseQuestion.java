package org.buildmlearn.learnfrommap.questionmodule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import org.buildmlearn.learnfrommap.databasehelper.Database;
import org.buildmlearn.learnfrommap.questionmodule.XmlQuestion.Type;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

public class BaseQuestion {

	protected String code;
	protected Type type;
	protected String format;
	protected String answer;
	protected boolean location;
	protected String relation;
	protected LocationType locationType;
	protected float lat;
	protected float lng;
	protected String locationKey;
	protected String locationValue;
	protected Context mContext;
	private Database db;
	private XmlQuestion xml;


	public static enum LocationType {None, Coordiates, String}


	public BaseQuestion(Database db, XmlQuestion question, float lat, float lng)
	{
		this.db = db;
		this.code = question.getCode();
		this.type = question.getType();
		this.format = question.getFormat();
		this.answer = question.getAnswer();
		this.location= question.isLocation();
		this.relation = question.getRelation();
		this.lat = lat;
		this.lng = lng;
		this.locationType = LocationType.Coordiates;
		this.xml = question;
	}

	public BaseQuestion(Database db, XmlQuestion question, String locationKey, String locationValue)
	{
		this.db = db;
		this.code = question.getCode();
		this.type = question.getType();
		this.format = question.getFormat();
		this.answer = question.getAnswer();
		this.location = question.isLocation();
		this.relation = question.getRelation();
		this.locationKey = locationKey;
		this.locationValue = locationValue;
		this.locationType = LocationType.String;
		this.xml = question;
	}

	public BaseQuestion(Database db, XmlQuestion question)	{
		this.db = db;
		this.code = question.getCode();
		this.type = question.getType();
		this.format = question.getFormat();
		this.answer = question.getAnswer();
		this.location = question.isLocation();
		this.relation = question.getRelation();
		this.locationType = LocationType.None;
		this.xml = question;
		//db.queryDatabase();
	}

	
	//Converts coordinates to country
	private Map<String, String> getAddress(double lat, double lng) {
		Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
		Map<String, String> location = new HashMap<String, String>();
		try {
			List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
			Address obj = addresses.get(0);
			location.put("COUNTRY", obj.getCountryCode());

		} catch (IOException e) {
			e.printStackTrace();
		}
		return location;
	}

	private DbRow selectRowFromDb(String where, String[] whereArgs) throws QuestionModuleException {

		ArrayList<DbRow> dbRowList = db.select(where, whereArgs, "RANDOM()", "1");
		if(dbRowList.size() == 1)
		{
			return dbRowList.get(0);
		}
		else
		{
			//Get a random row between 0 to cursor.getcount and check if it has been used before
			return getRandomRow(dbRowList);
		}
	}

	private DbRow getRandomRow(ArrayList<DbRow> dbRowList) {
		Random random = new Random();
		int pos = random.nextInt(dbRowList.size());
		return dbRowList.get(pos);


	}
	
	
	//Logic for creating question from DbRow and XmlQuestion 
	public GeneratedQuestion makeQuestion() throws QuestionModuleException {

		String where;
		String[] whereArgs;
		DbRow dbRow;
		GeneratedQuestion genQues = null;
		if(locationType == LocationType.Coordiates)
		{
			whereArgs = new String[2];
			Map<String, String> map = getAddress(lat, lng);
			String key = (String) map.keySet().iterator().next();
			where = "code = ? AND " + key + "=?";
			whereArgs[0] = code;
			whereArgs[1] = (String) map.get(key);
			dbRow = selectRowFromDb(where, whereArgs);
		}
		else if(locationType == LocationType.String)
		{
			whereArgs = new String[2];
			where = "code=? AND " + locationKey + "=?";
			whereArgs[0] = code;
			whereArgs[1] = locationValue;
			dbRow = selectRowFromDb(where, whereArgs);
		}
		else if(locationType == LocationType.None)
		{
			whereArgs = new String[1];
			where = "code=?";
			whereArgs[0] = code;
			Log.e("CODE", whereArgs[0]);
			dbRow = selectRowFromDb(where, whereArgs);
		}
		else
		{
			throw new QuestionModuleException("Invalid locationType in BaseQuestion");
		}
		String x = dbRow.getName();
		String answer = null;

		x = dbRow.getDataByColumnName(this.relation);
		//Answer
		answer = dbRow.getDataByColumnName(this.answer);

		String format;
		format = this.format;
		format = format.replace(":X:", x);

		
		if(type ==  Type.MultipleChoiceQuestion)
		{	
			String[] options = createOption(this.answer, answer);
			genQues = new GeneratedQuestion(dbRow, xml, format, answer, options);
		}
		else if(type ==  Type.FillBlanks)
		{
			genQues = new GeneratedQuestion(dbRow, xml, format, answer, GeneratedQuestion.Type.Fill);
		}
		else if(type == Type.PinOnMap)
		{
			genQues = new GeneratedQuestion(dbRow, xml, format, answer, GeneratedQuestion.Type.Pin);
		}
		return genQues;
	}
	
	private String[] createOption(String columnName, String answer) throws QuestionModuleException
	{
		String where = columnName + "!=?";
		String[] whereArgs = new String[1];
		String[] options = new String[3];
		whereArgs[0] = answer;
		
		ArrayList<String> list = db.selectColumn(true, where, whereArgs, "RANDOM()", "3", columnName);
		if(list.size() != 3)
		{
			Log.e("Error", "Size not equal to 3");
			throw new QuestionModuleException("NO of elements selected is less then 3 in createOptions for Column Name: " + columnName + " and Value: " + answer);
		}
		else
		{
			for(int i =0; i< list.size(); i++)
			{
				options[i] = list.get(i);
			}
		}
		
		return options;
	}
	


}
