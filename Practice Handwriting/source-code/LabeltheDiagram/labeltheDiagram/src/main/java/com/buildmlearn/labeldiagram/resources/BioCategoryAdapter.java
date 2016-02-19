package com.buildmlearn.labeldiagram.resources;

import java.util.List;

import com.buildmlearn.labeldiagram.DiagramMenuHuman;
import com.buildmlearn.labeldiagram.DiagramMenuMicroogranisms;
import com.buildmlearn.labeldiagram.DiagramMenuNaturalCycles;
import com.buildmlearn.labeldiagram.DiagramMenuPlants;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class BioCategoryAdapter extends DiagramCategoryAdapter{
	
	int nItems;

	public BioCategoryAdapter(Context context, int resource,
			List<DiagramCategoryRawItem> objects) {
		super(context, resource, objects);
		
		nItems = this.getCount();
		
	}

	@Override
	public void setDispatchClass(ViewHolder holder, int position) {
		
		final int index = position;
		holder.goIcon.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				if(index==0){
					Intent intent = new Intent(v.getContext(), DiagramMenuHuman.class);
					v.getContext().startActivity(intent);
					
				}else if(index==1){
					Intent intent = new Intent(v.getContext(), DiagramMenuPlants.class);
					v.getContext().startActivity(intent);
				}else if(index==2){
					Intent intent = new Intent(v.getContext(), DiagramMenuMicroogranisms.class);
					v.getContext().startActivity(intent);
				}else{
					Toast.makeText(getContext(), "Error occured", Toast.LENGTH_SHORT).show();
					Log.e("Error Tag", "Menu item count exceeded");
				} 
				
			}
		});
		
	}
}
