package org.buildmlearn.learnfrommap.databasehelper;

public class ResultHolder {

	private String country;
	private String category;
	//public static enum Type {PinOnMap, MultipleChoiceQuestion, FillBlanks};
	private String type;
	public boolean isCorrect;
	
	
	public ResultHolder(String country, String category, String type,
			boolean isCorrect) {
		super();
		this.country = country;
		this.category = category;
		this.type = type;
		this.isCorrect = isCorrect;
	}
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isCorrect() {
		return isCorrect;
	}
	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
	
	
}
