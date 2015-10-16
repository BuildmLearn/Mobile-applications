package com.buildmlearn.labeldiagram;

import java.util.ArrayList;
import java.util.List;

import com.buildmlearn.labeldiagram.resources.DiagramCategoryAdapter;
import com.buildmlearn.labeldiagram.resources.DiagramCategoryRawItem;
import com.example.labelthediagram.R;

import android.app.ActionBar;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DiagramCategory extends ListActivity {
	

	ArrayList<DiagramCategoryRawItem> categories = new ArrayList<DiagramCategoryRawItem>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
						
		// Enabling ActionBar
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Diagram Categories");
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.show();
		
		createModel();
		
		ArrayAdapter<DiagramCategoryRawItem> categryAdapter = new DiagramCategoryAdapter(this, R.layout.category_row_item, categories);
		setListAdapter(categryAdapter);
	}

	private void createModel() {
		
		DiagramCategoryRawItem item1 = new DiagramCategoryRawItem("Human Body", "Diagrams of Human Body", R.drawable.human_body);
		DiagramCategoryRawItem item2 = new DiagramCategoryRawItem("Plants", "Diagrams of plants", R.drawable.plants);
		DiagramCategoryRawItem item3 = new DiagramCategoryRawItem("Micro-organisams", "Diagrams of microbes", R.drawable.micro);
		DiagramCategoryRawItem item4 = new DiagramCategoryRawItem("Natural Cycles", "Diagrams of nature cycles", R.drawable.natural_cycle);
		
		categories.add(item1);
		categories.add(item2);
		categories.add(item3);
		categories.add(item4);
		
	}
}
