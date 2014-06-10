package org.buildmlearn.learnfrommap.questionmodule;

public class Question {
	
	private String code;
	private Type type;
	private String format;
	private String answer;
	private boolean location;
	private String relation;
	public static enum Type {PinOnMap, MultipleChoiceQuestion, FillBlanks};
	
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

	public Question(String code, String type, String format, String answer,
			boolean location, String relation) {
		super();
		this.code = code;
		if(type == "PIN")
		{
			this.type = Type.PinOnMap;
		}
		else if(type == "MCQ")
		{
			this.type = Type.MultipleChoiceQuestion;
		}
		else if(type == "FILL")
		{
			this.type = Type.FillBlanks;	
		}
		this.format = format;
		this.answer = answer;
		this.location = location;
		this.relation = relation;
	}
	
	

}
