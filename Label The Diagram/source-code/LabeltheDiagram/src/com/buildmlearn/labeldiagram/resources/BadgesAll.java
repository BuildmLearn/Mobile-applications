package com.buildmlearn.labeldiagram.resources;

import java.util.ArrayList;
import java.util.List;

import com.example.labelthediagram.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class BadgesAll extends Fragment {
	
	List<BadgeGridRowItem> gridArray = new ArrayList<BadgeGridRowItem>();
	GridView gridView;
	BadgesGridViewAdapter adapter;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		gridArray.add(new BadgeGridRowItem(R.layout.champion_badge_layout));
		gridArray.add(new BadgeGridRowItem(R.layout.champion_badge_layout));
		gridArray.add(new BadgeGridRowItem(R.layout.champion_badge_layout));
		gridArray.add(new BadgeGridRowItem(R.layout.champion_badge_layout));
		gridArray.add(new BadgeGridRowItem(R.layout.champion_badge_layout));
		gridArray.add(new BadgeGridRowItem(R.layout.champion_badge_layout));
		
		adapter = new BadgesGridViewAdapter(this.getActivity(), R.layout.grid_row_item, gridArray);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.badges_all_view,
				container, false);
		gridView = (GridView) view.findViewById(R.id.badgeGrid);
		gridView.setAdapter(adapter);
		return view;
		
	}

}
