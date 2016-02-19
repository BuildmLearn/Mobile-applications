package org.buildmlearn.learnfrommap.maphelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.content.Context;

public class CustomReverseGeocoder {

	private Context mContext;
	private RequestQueue mQueue;

	public CustomReverseGeocoder(Context mContext) {
		super();
		this.mContext = mContext;
		mQueue = Volley.newRequestQueue(this.mContext);
	}

	public void getState()
	{
		String latitude = "38.89";
		String longitude  = "-77.03";
		String googleurl = "http://maps.google.com/maps/api/geocode/json?";
//		Log.v("H`TTP" , "Latitude is: " + latitude + "Longitude is:" + longitude);
		StringBuilder sbuilder = new StringBuilder();
		sbuilder.append(googleurl);

		sbuilder.append("latlng=" + latitude + "," + longitude);
		sbuilder.append("&sensor=true");
		String url = sbuilder.toString();
//		Log.v("URL", url);
		StringRequest myReq = new StringRequest(Method.GET, 
				url,
				new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
//				Log.d("VOLLEY", response);
				try {
					JSONObject main = new JSONObject(response);
					JSONArray array = main.getJSONArray("results");
					JSONObject obj = array.getJSONObject(0);
					array = obj.getJSONArray("address_components");
					for(int i=0; i<array.length(); i++)
					{
						obj = array.getJSONObject(i);
//						Log.d("JSON" + i, obj.toString());	
						JSONArray tempArray = obj.getJSONArray("types");
						if(tempArray.getString(0).equals("administrative_area_level_1"))
						{
//							Log.e("State", obj.getString("long_name"));

						}
						else if(tempArray.getString(0).equals("country"))
						{
//							Log.e("Country", obj.getString("long_name"));
						}
					}

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		},
		new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
//				Log.d("VOLLEY ERROR", error.getMessage());
			}
		});

		mQueue.add(myReq);
	}

}
