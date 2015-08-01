package com.buildmlearn.labeldiagram.resources;

import java.util.List;

import com.buildmlearn.labeldiagram.DiagramMenuNaturalCycles;
import com.buildmlearn.labeldiagram.DiagramMenuSky;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class ScienceCategoryAdapter extends DiagramCategoryAdapter {

	public ScienceCategoryAdapter(Context context, int resource,
			List<DiagramCategoryRawItem> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void setDispatchClass(ViewHolder holder, int position) {
		
		final int index = position;
		holder.goIcon.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				if(index==0){
					Intent intent = new Intent(v.getContext(), DiagramMenuSky.class);
					v.getContext().startActivity(intent);
					
				}else if(index==1){
					Intent intent = new Intent(v.getContext(), DiagramMenuNaturalCycles.class);
					v.getContext().startActivity(intent);
				}else{
					Log.e("Error TAG", "Menu item count not valid");
				}
				
				
			}
		});
		
	}
}
