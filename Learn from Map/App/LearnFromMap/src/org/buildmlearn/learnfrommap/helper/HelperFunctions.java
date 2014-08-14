package org.buildmlearn.learnfrommap.helper;

import java.util.ArrayList;
import java.util.Random;

import com.google.android.gms.maps.model.LatLng;

import android.content.Context;
import android.util.Log;

public class HelperFunctions {

	public static void ShuffleArray(String[] ar)
	{
		Random rnd = new Random();
		for (int i = ar.length - 1; i > 0; i--)
		{
			int index = rnd.nextInt(i + 1);
			String a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
	}

	//Calculates the distance between two geo-coordinates
	public static double distance(double lat1, double lon1, double lat2, double lon2, char unit) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		if (unit == 'K') {
			dist = dist * 1.609344;
		} else if (unit == 'N') {
			dist = dist * 0.8684;
		}
		return (dist);
	}


	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}


	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}

	public static String geoCoderUrlBuilder(double lat, double lng)
	{
		String googleurl = "https://maps.google.com/maps/api/geocode/json?key=AIzaSyACYVxd_d-49UnhqibCI6F9f7b5Gw1qTSc&";
		Log.v("HTTP" , "Latitude is: " + lat + "Longitude is:" + lng);
		StringBuilder sbuilder = new StringBuilder();
		sbuilder.append(googleurl);
		sbuilder.append("latlng=" + lat + "," + lng);
		sbuilder.append("&sensor=true");
		String url = sbuilder.toString();
		return url;
	}

	public static void updateStats(Context context, boolean isCorrect, String country)
	{
		TinyDB stat = new TinyDB(context);
		ArrayList<String> countryList = stat.getList("COUNTRY");
		int index = countryList.indexOf(country);
		Log.e("INDEX", "Index: " + index);
		ArrayList<Integer> total = stat.getListInt("COUNTRY_TOTAL", context);
		int iTotal = total.get(index);
		iTotal++;
		total.set(index, iTotal);
		Log.e("TOTAL", "Total: " + iTotal);
		stat.putListInt("COUNTRY_TOTAL", total, context);
		if(isCorrect)
		{
			ArrayList<Integer> ans = stat.getListInt("COUNTRY_ANS", context);
			int iAns = ans.get(index);
			iAns++;
			ans.set(index, iAns);
			stat.putListInt("COUNTRY_ANS", ans, context);
		}
	}

	public static ArrayList<StatHolder> bindStat(Context context)
	{
		TinyDB stat = new TinyDB(context);
		ArrayList<StatHolder> data = new ArrayList<StatHolder>();
		ArrayList<String> countryList = stat.getList("COUNTRY");
		ArrayList<Integer> total = stat.getListInt("COUNTRY_TOTAL", context);
		ArrayList<Integer> ans = stat.getListInt("COUNTRY_ANS", context);
		for(int i=0; i < countryList.size(); i++)
		{
			StatHolder temp = new StatHolder(countryList.get(i), total.get(i), ans.get(i));
			data.add(temp);
		}
		return data;

	}

	public static LatLng locationFromString(String location)
	{
		String[] latlng = location.split(",");
		double lat = Double.parseDouble(latlng[0]);
		double lng = Double.parseDouble(latlng[1]);
		LatLng loc = new LatLng(lat, lng);
		return loc;
	}


}
