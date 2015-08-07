package com.buildmlearn.labeldiagram.lessons;

import java.util.ArrayList;
import java.util.List;

import com.buildmlearn.labeldiagram.helper.HelperClass;
import com.buildmlearn.labeldiagram.resources.LessonAdapter;
import com.buildmlearn.labeldiagram.resources.LessonRawItem;
import com.example.labelthediagram.R;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class LessonCarbonCycle extends LessonBase {

	List<String> tagList = new ArrayList<String>();
	List<String> tagDescriptionList = new ArrayList<String>();
	List<LessonRawItem> lessonList = new ArrayList<LessonRawItem>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(getResourcesId());

		HelperClass.setActionBar("Carbon Cycle", this);
		setDiagramName(getDiagramName());

		ListView lessonListView = (ListView) findViewById(R.id.lesson_list);

		fillAdapterDataModel();

		ArrayAdapter<LessonRawItem> lessonAdapter = new LessonAdapter(this,
				R.layout.lesson_row_item, lessonList);
		lessonListView.setAdapter(lessonAdapter);
		
	}

	private void fillAdapterDataModel() {

		int[] iconArray = new int[] { R.drawable.one, R.drawable.two,
				R.drawable.three, R.drawable.four, R.drawable.five,
				R.drawable.six, R.drawable.seven, R.drawable.eight,
				R.drawable.nine, R.drawable.ten };

		if (lessonObj != null) {
			tagList = lessonObj.getTagList();
			tagDescriptionList = lessonObj.getTagDescriptionList();
		}

		for (int i = 0; i < tagList.size(); i++) {
			String tag = tagList.get(i);
			String description = tagDescriptionList.get(i);
			LessonRawItem mItem = new LessonRawItem(tag, description,
					iconArray[i]);
			lessonList.add(mItem);
		}
	}

	@Override
	public String getDiagramName() {
		return "CarbonCycle";
	}

	@Override
	protected int getResourcesId() {
		return R.layout.lesson_carbon_cycle;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

}
