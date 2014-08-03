package org.buildmlearn.learnfrommap.helper;

public class StatHolder implements Comparable<StatHolder>{
	
	private String country;
	private int total;
	private int answered;
	private String color;
	
	public StatHolder(String country, int total, int answered) {
		super();
		this.country = country;
		this.total = total;
		this.answered = answered;
	}

	
	
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	public String getColor() {
		return color;
	}



	public void setColor(String color) {
		this.color = color;
	}



	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * @return the answered
	 */
	public int getAnswered() {
		return answered;
	}

	/**
	 * @param answered the answered to set
	 */
	public void setAnswered(int answered) {
		this.answered = answered;
	}

	@Override
	public int compareTo(StatHolder another) {
		
		return another.getTotal() - total;
	}
	
	
	
	

}
