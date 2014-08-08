package org.buildmlearn.learnfrommap.adapter;

import java.util.ArrayList;

import org.buildmlearn.learnfrommap.R;
import org.buildmlearn.learnfrommap.helper.StatHolder;
import org.buildmlearn.learnfrommap.helper.TextViewPlus;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class StatsAdapter extends ArrayAdapter<StatHolder> {

	private Context mContext;
	private int mResource;
	private ArrayList<StatHolder> mData;

	public StatsAdapter(Context context, int resource, ArrayList<StatHolder> data) {
		super(context, resource, data);
		this.mContext = context;
		this.mData = data;
		this.mResource = resource;
	}
	
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		StatHolder stat = mData.get(position);
		Holder holder  = null;
		if(row == null)
		{
			holder = new Holder();
			LayoutInflater inflator =  ((Activity)mContext).getLayoutInflater();
			row = inflator.inflate(mResource, parent, false);
			holder.country = (TextViewPlus)row.findViewById(R.id.country_name);
			holder.legend = (View)row.findViewById(R.id.legend);
			holder.stats = (TextViewPlus)row.findViewById(R.id.stat_data);
			row.setTag(holder);
		}
		else
		{
			holder = (Holder)row.getTag();
		}
		holder.country.setText(stat.getCountry());
		holder.stats.setText(stat.getAnswered() + "/" + stat.getTotal());
		new Color();
		holder.legend.setBackgroundColor(Color.parseColor(stat.getColor()));
		return row;
	}



	static class Holder
	{
		TextViewPlus country;
		TextViewPlus stats;
		View legend;
	}
}


