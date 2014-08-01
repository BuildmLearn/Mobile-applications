package org.buildmlearn.learnfrommap.questionmodule;

import android.util.Log;

public class XmlQuestion {
	
	private String code;
	private Type type;
	private String format;
	private String answer;
	private boolean location;
	private String relation;
	private String count;
	private String display;
	private String alias;
	private String marker;
	public static enum Type {PinOnMap, MultipleChoiceQuestion, FillBlanks};
	
	
	
	/**
	 * @return the marker
	 */
	public String getMarker() {
		return marker;
	}

	/**
	 * @param marker the marker to set
	 */
	public void setMarker(String marker) {
		this.marker = marker;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getFormat() {
		
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public boolean isLocation() {
		return location;
	}

	public void setLocation(boolean location) {
		this.location = location;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
	
	public void printRule()
	{
		Log.e("Rule", this.getFormat());
	}

	public XmlQuestion(String code, String type, String format, String answer,
			boolean location, String relation, String count, String alias, String marker) {
		super();
		this.code = code;
		if(type.equals("PIN"))
		{
			this.type = Type.PinOnMap;
		}
		else if(type.equals("MCQ"))
		{
			this.type = Type.MultipleChoiceQuestion;
		}
		else if(type.equals("FILL"))
		{
			this.type = Type.FillBlanks;	
		}
		this.format = format;
		this.answer = answer;
		this.location = location;
		this.relation = relation;
		this.count = count;
		this.alias = alias;
		this.display = "name";
		this.marker = marker;
	}
	
	

}
