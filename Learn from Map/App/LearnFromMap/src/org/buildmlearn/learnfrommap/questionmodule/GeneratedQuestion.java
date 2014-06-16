package org.buildmlearn.learnfrommap.questionmodule;

public class GeneratedQuestion {

	public enum Type {Mcq, Fill, Pin};
	private String question;
	private String answer;
	private String[] option;
	private Type type;
	private DbRow dbRow;
	private XmlQuestion xml;
	
	public DbRow getDbRow() {
		return dbRow;
	}

	public void setDbRow(DbRow dbRow) {
		this.dbRow = dbRow;
	}

	public XmlQuestion getXml() {
		return xml;
	}

	public void setXml(XmlQuestion xml) {
		this.xml = xml;
	}

	public GeneratedQuestion(DbRow dbRow, XmlQuestion xml, String question, String answer, String[] option) {
		super();
		this.question = question;
		this.answer = answer;
		this.option = new String[3];
		this.option = option;
		this.type = Type.Mcq;
		this.dbRow = dbRow;
		this.xml = xml;
	}

	public GeneratedQuestion(DbRow dbRow, XmlQuestion xml, String question, String answer, Type type) {
		super();
		this.question = question;
		this.answer = answer;
		this.type = type;
		this.dbRow = dbRow;
		this.xml = xml;
	}

	public String getQuestion() {
		return question;
	}

	public String getAnswer() {
		return answer;
	}

	public String[] getOption() {
		return option;
	}

	public Type getType() {
		return type;
	}
	
	
	
}
