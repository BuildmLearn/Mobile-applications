package org.buildmlearn.learnfrommap.questionmodule;

public class StructuredQuestion {

	public enum Type {Mcq, Fill, Pin};
	private String question;
	private String answer;
	private String[] option;
	private Type type;
	
	public StructuredQuestion(String question, String answer, String[] option) {
		super();
		this.question = question;
		this.answer = answer;
		this.option = new String[3];
		this.option = option;
		this.type = Type.Mcq;
	}

	public StructuredQuestion(String question, String answer, Type type) {
		super();
		this.question = question;
		this.answer = answer;
		this.type = type;
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
