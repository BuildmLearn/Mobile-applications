package com.buildmlearn.labeldiagram.resources;

import java.util.List;

import com.buildmlearn.labeldiagram.helper.TagContainerSingleton;
import com.example.labelthediagram.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DiagramResultAdapter extends ArrayAdapter<DiagramResultRawItem> {

	Context context;
	
	public DiagramResultAdapter(Context context, int resource,
			List<DiagramResultRawItem> objects) {
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
		DiagramResultRawItem rawItem = getItem(position);
		
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		
		if (convertView == null) {

			convertView = inflater.inflate(R.layout.diagram_result_row_item, null);
			holder = new ViewHolder();
			holder.tagLabel = (TextView) convertView.findViewById(R.id.resultTagTxt);
			holder.resultIcon = (ImageView) convertView.findViewById(R.id.result_icon);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.tagLabel.setText(rawItem.getTagLabel().getText());
		
		holder.tagLabel.getWidth();
		
		TagContainerSingleton container = TagContainerSingleton.getInstance();
			
		if(container.getCorrectLabelList().contains(rawItem.getTagLabel())){
			holder.resultIcon.setImageResource(R.drawable.correct_btn);
		}else if(container.getIncorrectLabelList().contains(rawItem.getTagLabel())){
			holder.resultIcon.setImageResource(R.drawable.incorrect_btn);
		}
		
		return convertView;
	}
}
