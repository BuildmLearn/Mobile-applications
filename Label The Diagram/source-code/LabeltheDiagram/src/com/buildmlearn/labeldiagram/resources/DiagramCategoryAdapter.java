package com.buildmlearn.labeldiagram.resources;

import java.util.List;

import com.buildmlearn.labeldiagram.DiagramCategory;
import com.buildmlearn.labeldiagram.DiagramPlay;
import com.example.labelthediagram.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.sax.StartElementListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/*
 *Adapter class to generate custom view of DiagramCategory list items
 * 
 */

public class DiagramCategoryAdapter extends ArrayAdapter<DiagramCategoryRawItem> {

	Context context;
	Typeface tfThin;
	Typeface tfLight;

	public DiagramCategoryAdapter(Context context, int resource,
			List<DiagramCategoryRawItem> objects) {
		super(context, resource, objects);
		this.context = context;
		
		tfThin = Typeface.createFromAsset(context.getAssets(),
				"fonts/Roboto-Thin.ttf");
		tfLight = Typeface.createFromAsset(context.getAssets(),
				"fonts/Roboto-Light.ttf");
	}

	/*
	 * Holder class for single row item view
	 * 
	 */
	private class ViewHolder {

		ImageView categoryIcon;
		ImageView goIcon;
		TextView titleTxt;
		TextView descriptionTxt;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		final int index = position;
		final ViewHolder holder;
		DiagramCategoryRawItem rawItem = getItem(position);

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

		if (convertView == null) {

			convertView = inflater.inflate(R.layout.category_row_item, null);
			holder = new ViewHolder();
			holder.titleTxt = (TextView) convertView.findViewById(R.id.categoryTitle);
			holder.descriptionTxt = (TextView) convertView.findViewById(R.id.catDescription);
			holder.categoryIcon = (ImageView) convertView.findViewById(R.id.categoryIcon);
			holder.goIcon = (ImageView) convertView.findViewById(R.id.navigatorBtn);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.titleTxt.setText(rawItem.getTitle());
		holder.descriptionTxt.setText(rawItem.getDescription());
		holder.categoryIcon.setImageResource(rawItem.getImageId());
		holder.goIcon.setImageResource(R.drawable.proceed_btn);
		
		holder.titleTxt.setTypeface(tfLight);
		holder.descriptionTxt.setTypeface(tfThin);
		
		holder.goIcon.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				if(index==0){
					Toast.makeText(getContext(), "Dispatching to Human Body Diagram Play screen", Toast.LENGTH_LONG).show();
					Intent intent = new Intent(v.getContext(), DiagramPlay.class);
					v.getContext().startActivity(intent);
					
				}else if(index==1){
					Toast.makeText(getContext(), "Dispatching to Plants Play screen", Toast.LENGTH_LONG).show();
				}else if(index==2){
					Toast.makeText(getContext(), "Dispatching to Micro-organisms Play screen", Toast.LENGTH_LONG).show();
				}else{
					Toast.makeText(getContext(), "Dispatching to Natural Cycles Play screen", Toast.LENGTH_LONG).show();
				} 
			}
		});
		
		return convertView;

	}

}
