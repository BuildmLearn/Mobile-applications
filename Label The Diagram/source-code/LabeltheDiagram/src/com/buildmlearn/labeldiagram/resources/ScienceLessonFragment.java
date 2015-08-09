package com.buildmlearn.labeldiagram.resources;

import java.util.ArrayList;
import java.util.List;

import com.example.labelthediagram.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ScienceLessonFragment extends Fragment {

	List<LessonCategoryRawItem> categories = new ArrayList<LessonCategoryRawItem>();
	ArrayAdapter<LessonCategoryRawItem> lessonAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		createModel();

		lessonAdapter = new ScienceLessonAdapter(getActivity(),
				R.layout.lesson_category_row_item, categories);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.diagram_category_mainview,
				container, false);
		ListView list = (ListView) view.findViewById(R.id.category_mainList);
		list.setAdapter(lessonAdapter);

		return view;
	}

	private void createModel() {

		int[] titleArray = new int[] { R.string.lesson_solar_system,
				R.string.lesson_star_patterns, R.string.lesson_water_cycle,
				R.string.lesson_carbon_cycle };

		int[] descrptionArray = new int[] { R.string.lesson_solar_system_desc,
				R.string.lesson_star_patterns_desc, R.string.lesson_water_cycle_desc,
				R.string.lesson_carbon_cycle_desc };
		
		int[] iconArray = new int[] { R.drawable.icon_solar_system,
				R.drawable.icon_stars_pattern, R.drawable.icon_water_cycle,
				R.drawable.icon_corban_cycle };

		for (int i = 0; i < titleArray.length; i++) {

			LessonCategoryRawItem item = new LessonCategoryRawItem(
					getResources().getString(titleArray[i]), getResources()
							.getString(descrptionArray[i]), iconArray[i]);

			categories.add(item);
		}

	}
}
