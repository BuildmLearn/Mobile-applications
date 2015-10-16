package com.buildmlearn.labeldiagram.resources;

import java.util.List;

import com.buildmlearn.labeldiagram.DiagramCategoryViewer;
import com.buildmlearn.labeldiagram.HelpMenu;
import com.buildmlearn.labeldiagram.ScoreboardViewer;
import com.buildmlearn.labeldiagram.Settings;
import com.buildmlearn.labeldiagram.badges.BadgesViewer;
import com.buildmlearn.labeldiagram.lessons.LessonCategoryViewer;
import com.example.labelthediagram.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MenuGridViewAdapter extends ArrayAdapter<MenuGridRowItem> {

	Context context;

	public MenuGridViewAdapter(Context context, int resource,
			List<MenuGridRowItem> objects) {
		super(context, resource, objects);

		this.context = context;
	}

	/*
	 * Holder class for single lesson row item view
	 */
	public class ViewHolder {

		RelativeLayout layout;
		int id;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		final ViewHolder holder;

		MenuGridRowItem rawItem = getItem(position);

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

		if (convertView == null) {

			convertView = inflater.inflate(R.layout.menu_grid_row_item, null);
			holder = new ViewHolder();
			holder.layout = (RelativeLayout) convertView
					.findViewById(R.id.menuGridRowLayout);
			convertView.setTag(holder);

		} else {

			holder = (ViewHolder) convertView.getTag();

		}

		View child = inflater.inflate(rawItem.getLayoutId(), null);
		ImageView iconView = (ImageView) child.findViewById(R.id.diagramIcon);
		TextView iconTxt = (TextView) child.findViewById(R.id.title_diagram);
		

		switch (position) {
		case 0:
			iconView.setImageResource(R.drawable.diagrams1);
			iconTxt.setText("Diagrams");
			holder.id = 0;
			break;
		case 1:
			iconView.setImageResource(R.drawable.score_board);
			iconTxt.setText("Scoreboard");
			holder.id = 1;
			break;
		case 2:
			iconView.setImageResource(R.drawable.lesson);
			iconTxt.setText("Lessons");
			holder.id = 2;
			break;
		case 3:
			iconView.setImageResource(R.drawable.medle);
			iconTxt.setText("Badges");
			holder.id = 3;
			break;
		case 4:
			iconView.setImageResource(R.drawable.settings);
			iconTxt.setText("Settings");
			holder.id = 4;
			break;
		case 5:
			iconView.setImageResource(R.drawable.help);
			iconTxt.setText("Help");
			holder.id = 5;
			break;

		default:
			break;
		}

		holder.layout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {

				switch (holder.id) {
				case 0:
					Intent diagramIntent = new Intent(context, DiagramCategoryViewer.class);
					context.startActivity(diagramIntent);
					break;
				case 1:
					Intent scoreboardIntent = new Intent(context, ScoreboardViewer.class);
					context.startActivity(scoreboardIntent);
					break;
				case 2:
					Intent lessonIntent = new Intent(context, LessonCategoryViewer.class);
					context.startActivity(lessonIntent);
					break;
				case 3:
					Intent badgesIntent = new Intent(context, BadgesViewer.class);
					context.startActivity(badgesIntent);
					break;
				case 4:
					Intent settingsIntent = new Intent(context, Settings.class);
					context.startActivity(settingsIntent);
					break;
				case 5:
					Intent helpIntent = new Intent(context, HelpMenu.class);
					context.startActivity(helpIntent);
					break;

				default:
					break;
				}

			}
		});

		holder.layout.addView(child);

		return convertView;
	}

}
