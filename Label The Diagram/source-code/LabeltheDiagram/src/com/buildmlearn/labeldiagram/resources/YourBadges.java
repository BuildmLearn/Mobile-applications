package com.buildmlearn.labeldiagram.resources;

import java.util.ArrayList;
import java.util.List;

import com.buildmlearn.labeldiagram.DiagramCategoryViewer;
import com.buildmlearn.labeldiagram.database.Database;
import com.buildmlearn.labeldiagram.entity.Badge;
import com.buildmlearn.labeldiagram.widget.MessagePopupWindow;
import com.example.labelthediagram.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class YourBadges extends Fragment {

	Database database;
	List<Badge> achievedList = new ArrayList<Badge>();
	ArrayList<YourBadgeRowItem> container = new ArrayList<YourBadgeRowItem>();
	ArrayAdapter<YourBadgeRowItem> adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		loadDatabase();
		loadData();
		fillAdapterDataModel();
		init();
		
	}

	private void init() {
		adapter = new YourBadgesAdapter(
				getActivity(), R.layout.your_badge_row_item, container);
	}

	private void fillAdapterDataModel() {

		Badge badge = new Badge();

		if(achievedList.size()!=0){
			
			for (int i = 0; i < achievedList.size(); i++) {

				badge = achievedList.get(i);
				String badgeTitle = badge.getTitle();
				YourBadgeRowItem rowItem = new YourBadgeRowItem(badgeTitle);
				container.add(rowItem);

			}
			
		}else{
			
			Intent intent = new Intent(getActivity(),MessagePopupWindow.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			
		}
		

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.your_badges,
				container, false);
		ListView list = (ListView) view.findViewById(R.id.your_badges_list);
		list.setAdapter(adapter);

		return view;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		closeDatabase();
	}

	private void loadDatabase() {
		database = new Database(this.getActivity());
	}

	private void closeDatabase() {
		database.close();
	}

	private void loadData() {
		achievedList = database.getAchievedBadge(true);
	}
}
