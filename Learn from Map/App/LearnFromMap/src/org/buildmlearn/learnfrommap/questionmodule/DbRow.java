package org.buildmlearn.learnfrommap.questionmodule;

public class DbRow {

	private int id;
	private float lng;
	private float lat;
	private String name;
	private String country;
	private String capital;
	private String state;
	private String continent;
	private int population;
	private int elevation;





	public DbRow(int id, float lng, float lat, String name, 
			String country, String capital, String state, String continent,
			int population, int elevation) {
		super();
		this.id = id;
		this.lng = lng;
		this.lat = lat;
		this.name = name;
		this.country = country;
		this.capital = capital;
		this.state = state;
		this.continent = continent;
		this.population = population;
		this.elevation = elevation;
	}


	public String toString()
	{
		String all = "";
		all += "Id: " + id + "\n";
		all += "Lng: " + lng + "\n";
		all += "Lat: " + lat + "\n";
		all += "Name: " + name + "\n";
		all += "Country: " + country + "\n";
		all += "Continent: " + continent + "\n";
		all += "Capiatl: " + capital + "\n";
		all += "State: " + state + "\n";
		all += "Population: " + population + "\n";
		all += "Elevation: " + elevation + "\n";
		return all;
	}


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





	public String getCountry() {
		return country;
	}





	public void setCountry(String country) {
		this.country = country;
	}





	public String getCapital() {
		return capital;
	}





	public void setCapital(String capital) {
		this.capital = capital;
	}





	public String getState() {
		return state;
	}





	public void setState(String state) {
		this.state = state;
	}





	public String getContinent() {
		return continent;
	}





	public void setContinent(String continent) {
		this.continent = continent;
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





	public String getDataByColumnName(String column) throws QuestionModuleException
	{
		if(column.equals("location"))
		{
			return this.getLat() + "," + this.getLng();
		}
		else if(column.equals("name"))
		{
			return this.getName();
		}
		else if(column.equals("capital"))
		{
			return this.getCapital();
		}
		else if(column.equals("country"))
		{
			return this.getCountry();
		}
		else if(column.equals("state"))
		{
			return this.getState();
		}
		else if(column.equals("continent"))
		{
			return this.getContinent();
		}
		else if(column.equals("population"))
		{
			return String.valueOf(this.getPopulation());
		}
		else if(column.equals("elevation"))
		{
			return String.valueOf(this.getElevation());
		}
		else
		{
			throw new QuestionModuleException("Invalid column name: " + column);
		}

	}


}
