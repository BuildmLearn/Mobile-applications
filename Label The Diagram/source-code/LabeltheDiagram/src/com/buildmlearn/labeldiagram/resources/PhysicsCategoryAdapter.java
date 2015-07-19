package com.buildmlearn.labeldiagram.resources;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class PhysicsCategoryAdapter extends DiagramCategoryAdapter {

	public PhysicsCategoryAdapter(Context context, int resource,
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
					Toast.makeText(getContext(), "Comming soon...", Toast.LENGTH_SHORT).show();
					//Intent intent = new Intent(v.getContext(), DiagramMenuHuman.class);
					//v.getContext().startActivity(intent);
					
				}else if(index==1){
					Toast.makeText(getContext(), "Comming soon...", Toast.LENGTH_SHORT).show();
					//Intent intent = new Intent(v.getContext(), DiagramMenuPlants.class);
					//v.getContext().startActivity(intent);
				}
				
				
			}
		});
		
	}

}
