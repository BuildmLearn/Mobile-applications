package com.buildmlearn.labeldiagram.resources;

import java.util.List;

import com.buildmlearn.labeldiagram.badges.BadgePopUpWindow;
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
import android.widget.TextView;

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
		final String badgeNameToPass;
		final int badgeIdToPass;
		String badgeName = null;
		int badgeId = 0;
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
		TextView bagdgeTxt = (TextView) child.findViewById(R.id.badgeTitle);

		switch (position) {
		case 0:
			badgeIcon.setImageResource(R.drawable.champion);
			bagdgeTxt.setText(R.string.badge_champion);
			badgeName = context.getResources().getString(R.string.badge_champion);
			badgeId = R.drawable.champion;
			break;
		case 1:
			badgeIcon.setImageResource(R.drawable.bio);
			bagdgeTxt.setText(R.string.badge_biology);
			badgeName = context.getResources().getString(R.string.badge_biology);
			badgeId = R.drawable.bio;
			break;
		case 2:
			badgeIcon.setImageResource(R.drawable.physics);
			bagdgeTxt.setText(R.string.badge_physics);
			badgeName = context.getResources().getString(R.string.badge_physics);
			badgeId = R.drawable.physics;
			break;
		case 3:
			badgeIcon.setImageResource(R.drawable.science);
			bagdgeTxt.setText(R.string.badge_science);
			badgeName = context.getResources().getString(R.string.badge_science);
			badgeId = R.drawable.science;
			break;
		case 4:
			badgeIcon.setImageResource(R.drawable.persistence);
			bagdgeTxt.setText(R.string.badge_persistence);
			badgeName = context.getResources().getString(R.string.badge_persistence);
			badgeId = R.drawable.persistence;
			break;
		case 5:
			badgeIcon.setImageResource(R.drawable.streak);
			bagdgeTxt.setText(R.string.badge_streak);
			badgeName = context.getResources().getString(R.string.badge_streak);
			badgeId = R.drawable.streak;
			break;

		default:
			break;
		}
		
		badgeNameToPass = badgeName;
		badgeIdToPass = badgeId;
		
		holder.layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				Intent intent = new Intent(context, BadgePopUpWindow.class);
				intent.putExtra("BADGE_TITLE", badgeNameToPass);
				intent.putExtra("BADGE_ID", badgeIdToPass);
				context.startActivity(intent);
				
			}
		});

		holder.layout.addView(child);

		return convertView;
	}

}
