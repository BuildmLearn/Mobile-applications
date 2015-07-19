package com.buildmlearn.labeldiagram.resources;

import java.util.ArrayList;

import com.example.labelthediagram.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BioCategoryFragment extends Fragment {
	
	ArrayList<DiagramCategoryRawItem> categories = new ArrayList<DiagramCategoryRawItem>();
	ArrayAdapter<DiagramCategoryRawItem> categryAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		createModel();

		categryAdapter = new BioCategoryAdapter(
				getActivity(), R.layout.category_row_item, categories);
		
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

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.diagram_category_mainview,
				container, false);
		ListView list = (ListView) view.findViewById(R.id.category_mainList);
		list.setAdapter(categryAdapter);

		return view;
	}

}
