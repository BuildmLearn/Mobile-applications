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

public class BioLessonFragment extends Fragment {

	List<LessonCategoryRawItem> categories = new ArrayList<LessonCategoryRawItem>();
	ArrayAdapter<LessonCategoryRawItem> lessonAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		createModel();

		lessonAdapter = new BioLessonAdapter(getActivity(),
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

		int[] titleArray = new int[] { R.string.lesson_human_eye,
				R.string.lesson_human_heart, R.string.lesson_human_ear,
				R.string.lesson_plant_cell, R.string.lesson_plant_flower,
				R.string.lesson_bacteria, R.string.lesson_virus };

		int[] descrptionArray = new int[] { R.string.lesson_human_eye_desc,
				R.string.lesson_human_heart_desc,
				R.string.lesson_human_ear_desc,
				R.string.lesson_plant_cell_desc,
				R.string.lesson_plant_flower_desc,
				R.string.lesson_bacteria_desc, R.string.lesson_virus_desc };

		int[] iconArray = new int[] { R.drawable.icon_eye,
				R.drawable.icon_heart, R.drawable.icon_ear,
				R.drawable.icon_plant_cell, R.drawable.icon_flower,
				R.drawable.icon_bacteria, R.drawable.icon_virus };

		for (int i = 0; i < titleArray.length; i++) {

			LessonCategoryRawItem item = new LessonCategoryRawItem(
					getResources().getString(titleArray[i]), getResources()
							.getString(descrptionArray[i]),iconArray[i]);

			categories.add(item);
		}

	}
}
