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

public class DiagramScoreResultAdapter extends ArrayAdapter<DiagramScoreboardResultRawItem> {

	Context context;
	
	public DiagramScoreResultAdapter(Context context, int resource,
			List<DiagramScoreboardResultRawItem> objects) {
		super(context, resource, objects);
		
		this.context = context;
	}

	/*
	 * Holder class for single row item view
	 * 
	 */
	private class ViewHolder {

		ImageView resultIcon;		
		TextView tagLabel;
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		final int index = position;
		final ViewHolder holder;
		DiagramScoreboardResultRawItem rawItem = getItem(position);
		
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		
		if (convertView == null) {

			convertView = inflater.inflate(R.layout.scoreboard_result_row_view, null);
			holder = new ViewHolder();
			holder.tagLabel = (TextView) convertView.findViewById(R.id.resultItemTxt);
			holder.resultIcon = (ImageView) convertView.findViewById(R.id.resultIndiacator);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.tagLabel.setText(rawItem.getTagLabel());
		holder.resultIcon.setImageResource(rawItem.getResultIconId());
		
		return convertView;
	}
	
	
}
