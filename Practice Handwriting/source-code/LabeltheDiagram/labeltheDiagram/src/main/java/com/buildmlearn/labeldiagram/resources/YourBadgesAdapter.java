package com.buildmlearn.labeldiagram.resources;

import java.util.ArrayList;

import com.example.labelthediagram.R;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class YourBadgesAdapter extends ArrayAdapter<YourBadgeRowItem> {
	
	Context context;

	public YourBadgesAdapter(Context context, int resource,
			ArrayList<YourBadgeRowItem> container) {
		super(context, resource, container);
		
		this.context = context;
		
	}
	
	/*
	 * Holder class for single row item view
	 * 
	 */
	private class ViewHolder {

		ImageView badgeIcon;
		ImageView tickIcon;
		TextView badgeTitle;
		TextView achievedTxt;
		
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		final ViewHolder holder;
		YourBadgeRowItem rawItem = getItem(position);
		
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		
		if (convertView == null) {

			convertView = inflater.inflate(R.layout.your_badge_row_item, null);
			holder = new ViewHolder();
			holder.badgeTitle = (TextView) convertView.findViewById(R.id.achievement_title);
			holder.achievedTxt = (TextView) convertView.findViewById(R.id.achievement_txt);
			holder.badgeIcon = (ImageView) convertView.findViewById(R.id.achieved_badge);
			holder.tickIcon = (ImageView) convertView.findViewById(R.id.tick_icon);
			convertView.setTag(holder);

		} else {
			
			holder = (ViewHolder) convertView.getTag();
			
		}
		
		holder.badgeTitle.setText(rawItem.getBadgeTitle());
		holder.tickIcon.setImageResource(R.drawable.correct_btn);
		
		String title = rawItem.getBadgeTitle();
		
		int badgeId = iconSelector(title);
		holder.badgeIcon.setImageResource(badgeId);
		
		return convertView;
	}

	private int iconSelector(String title) {
		
		int iconId=0;
		
		if(title.equals(context.getResources().getString(R.string.badge_biology))){
			iconId = R.drawable.bio;
		}else if(title.equals(context.getResources().getString(R.string.badge_physics))){
			iconId = R.drawable.physics;
		}else if(title.equals(context.getResources().getString(R.string.badge_science))){
			iconId = R.drawable.science;
		}else if(title.equals(context.getResources().getString(R.string.badge_champion))){
			iconId = R.drawable.champion;
		}else if(title.equals(context.getResources().getString(R.string.badge_persistence))){
			iconId = R.drawable.persistence;
		}else if(title.equals(context.getResources().getString(R.string.badge_streak))){
			iconId = R.drawable.streak;
		}else{
			Log.i("TAG", "No icon id");
		}
		
		return iconId;
	}
}
