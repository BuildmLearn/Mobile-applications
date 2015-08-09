package com.buildmlearn.labeldiagram.resources;

import java.util.List;

import com.buildmlearn.labeldiagram.widget.CustomPopUpWindow;
import com.example.labelthediagram.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
			holder.layout = (RelativeLayout) convertView
					.findViewById(R.id.gridRowLayout);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		View child = inflater.inflate(rawItem.getLayoutId(), null);
		ImageView badgeIcon = (ImageView) child.findViewById(R.id.bioBadge);

		switch (position) {
		case 0:
			badgeIcon.setImageResource(R.drawable.champion);
			break;
		case 1:
			badgeIcon.setImageResource(R.drawable.bio);
			break;
		case 2:
			badgeIcon.setImageResource(R.drawable.physics);
			break;
		case 3:
			badgeIcon.setImageResource(R.drawable.science);
			break;
		case 4:
			badgeIcon.setImageResource(R.drawable.bio);
			break;
		case 5:
			badgeIcon.setImageResource(R.drawable.physics);
			break;

		default:
			break;
		}
		
		badgeIcon.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(context, CustomPopUpWindow.class);
				context.startActivity(intent);
				
			}
		});

		holder.layout.addView(child);

		return convertView;
	}

}
