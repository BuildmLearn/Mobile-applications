package com.buildmlearn.labeldiagram.resources;

import java.util.List;

import com.buildmlearn.labeldiagram.ScoreboardResult;
import com.buildmlearn.labeldiagram.helper.ClassMapper;
import com.buildmlearn.labeldiagram.helper.HelperClass;
import com.example.labelthediagram.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
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
	 */
	private class ViewHolder {

		ImageView viewIcon;
		TextView diagramTitle;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		final int index = position;
		final ViewHolder holder;
		final ScoreboardRawItem rawItem = getItem(position);

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

		if (convertView == null) {

			convertView = inflater.inflate(R.layout.scoreboard_row_view, null);
			holder = new ViewHolder();
			holder.diagramTitle = (TextView) convertView
					.findViewById(R.id.scoreboardItemTxt);
			holder.viewIcon = (ImageView) convertView
					.findViewById(R.id.viewIcon);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.diagramTitle.setText(rawItem.getDiagramTitle());
		holder.viewIcon.setImageResource(R.drawable.view_btn);

		holder.diagramTitle.setTypeface(tfLight);

		holder.viewIcon.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(v.getContext(),ScoreboardResult.class);
				intent.putExtra("SOURCE", ClassMapper.getInstance().getTagMap()
						.get(rawItem.getDiagramTitle()));
				v.getContext().startActivity(intent);

			}
		});
		return convertView;

	}

}
