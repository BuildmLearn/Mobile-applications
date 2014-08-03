package org.buildmlearn.learnfrommap.adapter;

import java.util.ArrayList;

import org.buildmlearn.learnfrommap.R;
import org.buildmlearn.learnfrommap.helper.TextViewPlus;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class CategoryAdapter extends ArrayAdapter<String> {

	private Context mContext;
	private int mLayoutResource;
	private ArrayList<String> mData;

	public CategoryAdapter(Context context, int resource, ArrayList<String> data) {
		super(context, resource, data);
		this.mContext = context;
		this.mData = data;
		this.mLayoutResource = resource;		
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		TextViewPlus categoryName;
		LayoutInflater inflator =  ((Activity) mContext).getLayoutInflater();
		row = inflator.inflate(mLayoutResource, parent, false);
		categoryName = (TextViewPlus)row.findViewById(R.id.category);
		categoryName.setText(mData.get(position));
		return row;
	}

}
