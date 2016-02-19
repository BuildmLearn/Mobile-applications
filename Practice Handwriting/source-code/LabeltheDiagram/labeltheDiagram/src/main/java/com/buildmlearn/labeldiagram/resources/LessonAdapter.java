package com.buildmlearn.labeldiagram.resources;

import java.util.List;

import com.example.labelthediagram.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LessonAdapter extends ArrayAdapter<LessonRawItem> {
	
	Context context;

	public LessonAdapter(Context context, int resource,
			List<LessonRawItem> objects) {
		super(context, resource, objects);
		
		this.context = context;
		
	}
	
	/*
	 * Holder class for single row item view
	 * 
	 */
	private class ViewHolder {

		ImageView tagIdIcon;		
		TextView tagTitle;
		TextView tagDescription;
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		final int index = position;
		final ViewHolder holder;
		LessonRawItem rawItem = getItem(position);
		
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		
		if (convertView == null) {

			convertView = inflater.inflate(R.layout.lesson_row_item, null);
			holder = new ViewHolder();
			holder.tagTitle = (TextView) convertView.findViewById(R.id.tag_title);
			holder.tagDescription = (TextView) convertView.findViewById(R.id.tag_description);
			holder.tagIdIcon = (ImageView) convertView.findViewById(R.id.rowid_view);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.tagTitle.setText(rawItem.getTagTitle());
		holder.tagDescription.setText(rawItem.getTagDescription());
		holder.tagIdIcon.setImageResource(rawItem.getTagNumberIcon());
		
		return convertView;
	}

}
