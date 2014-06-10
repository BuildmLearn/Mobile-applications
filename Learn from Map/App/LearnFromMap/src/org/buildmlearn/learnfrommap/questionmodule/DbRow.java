package org.buildmlearn.learnfrommap.questionmodule;

public class DbRow {

	private int id;
	private float lng;
	private float lat;
	private String name;
	private String code;
	private String country_code;
	private int population;
	private int elevation;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getLng() {
		return lng;
	}
	public void setLng(float lng) {
		this.lng = lng;
	}
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCountry_code() {
		return country_code;
	}
	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	public int getElevation() {
		return elevation;
	}
	public void setElevation(int elevation) {
		this.elevation = elevation;
	}
	public DbRow(int id, float lng, float lat, String name, String code,
			String country_code, int population, int elevation) {
		super();
		this.id = id;
		this.lng = lng;
		this.lat = lat;
		this.name = name;
		this.code = code;
		this.country_code = country_code;
		this.population = population;
		this.elevation = elevation;
	}
	
}
