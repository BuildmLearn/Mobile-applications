package org.buildmlearn.learnfrommap.helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;

public class ReverseGeoCodeJson {

	private String State;
	private String Country;
	private boolean HasError;


	public ReverseGeoCodeJson(String response) {
		try {
			JSONObject main = new JSONObject(response);
			JSONArray array = main.getJSONArray("results");
			JSONObject obj = array.getJSONObject(0);
			array = obj.getJSONArray("address_components");
			for(int i=0; i<array.length(); i++)
			{
				obj = array.getJSONObject(i);
				JSONArray tempArray = obj.getJSONArray("types");
				if(tempArray.getString(0).equals("country"))
				{
					Country = obj.getString("long_name");
					Log.e("Country", Country);
				}
				if(tempArray.getString(0).equals("administrative_area_level_1"))
				{
					State = obj.getString("long_name");
					Log.e("State", State);
				}
			}
			HasError = false;

		} catch (JSONException e) {
			e.printStackTrace();
			HasError = true;
		}
	}


	/**
	 * @return the state
	 */
	public String getState() {
		if(State != null)
		{
			return State;
		}
		return "";
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		State = state;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		if(Country != null)
		{
			return Country;
		}
		return "";
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		Country = country;
	}
	/**
	 * @return the hasError
	 */
	public boolean isHasError() {
		return HasError;
	}
	/**
	 * @param hasError the hasError to set
	 */
	public void setHasError(boolean hasError) {
		HasError = hasError;
	}



}
