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

public class LessonCategoryAdapter extends ArrayAdapter<LessonCategoryRawItem> {
	
	Context context;
	int nItems;

	public LessonCategoryAdapter(Context context, int resource,
			List<LessonCategoryRawItem> objects) {
		super(context, resource, objects);
		
		this.context = context;
		
	}
	
	/*
	 * Holder class for single lesson row item view 
	 */
	public class ViewHolder {

		ImageView navIcon;
		TextView titleTxt;
		TextView descriptionTxt;

	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		final int index = position;
		final ViewHolder holder;
		LessonCategoryRawItem rawItem = getItem(position);
		
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

		if (convertView == null) {

			convertView = inflater.inflate(R.layout.lesson_category_row_item, null);
			holder = new ViewHolder();
			holder.titleTxt = (TextView) convertView.findViewById(R.id.lessonTitle);
			holder.descriptionTxt = (TextView) convertView.findViewById(R.id.lessonDescription);
			holder.navIcon = (ImageView) convertView.findViewById(R.id.navigatorBtn);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.titleTxt.setText(rawItem.getTitle());
		holder.descriptionTxt.setText(rawItem.getDescription());
		holder.navIcon.setImageResource(R.drawable.proceed_btn);
		
		setDispatchClass(holder,index);
		
		return convertView;
	}
	
	public void setDispatchClass(ViewHolder holder, int index){};

}
