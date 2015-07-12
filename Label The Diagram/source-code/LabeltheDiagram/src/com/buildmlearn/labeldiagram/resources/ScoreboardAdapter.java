package com.buildmlearn.labeldiagram.resources;

import java.util.List;

import com.example.labelthediagram.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ScoreboardAdapter extends ArrayAdapter<ScoreboardRawItem> {
	
	Context context;
	Typeface tfThin;
	Typeface tfLight;

	public ScoreboardAdapter(Context context, int resource,
			List<ScoreboardRawItem> objects) {
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

		ImageView viewIcon;
		TextView diagramTitle;

	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		
		final int index = position;
		final ViewHolder holder;
		ScoreboardRawItem rawItem = getItem(position);
		
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		
		
		if (convertView == null) {

			convertView = inflater.inflate(R.layout.scoreboard_row_view, null);
			holder = new ViewHolder();
			holder.diagramTitle = (TextView) convertView.findViewById(R.id.scoreboardItemTxt);
			holder.viewIcon = (ImageView) convertView.findViewById(R.id.viewIcon);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.diagramTitle.setText(rawItem.getDiagramTitle());
		holder.viewIcon.setImageResource(R.drawable.view_btn);
		
		holder.diagramTitle.setTypeface(tfLight);
		return convertView;
		
	}

}
