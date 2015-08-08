package com.buildmlearn.labeldiagram.resources;

import java.util.List;

import com.example.labelthediagram.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;

public class BadgesGridViewAdapter extends ArrayAdapter<BadgeGridRowItem> {
	
	Context context;

	public BadgesGridViewAdapter(Context context, int resource,
			List<BadgeGridRowItem> objects) {
		super(context, resource, objects);
		
		this.context = context;
		
	}
	
	/*
	 * Holder class for single lesson row item view 
	 */
	public class ViewHolder {

		RelativeLayout layout;

	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		final ViewHolder holder;
		BadgeGridRowItem rawItem = getItem(position);
		
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		
		if (convertView == null) {

			convertView = inflater.inflate(R.layout.grid_row_item, null);
			holder = new ViewHolder();
			holder.layout = (RelativeLayout) convertView.findViewById(R.id.gridRowLayout);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		View child = inflater.inflate(rawItem.getLayoutId(), null);
		holder.layout.addView(child);
		
		return convertView;
	}

}
