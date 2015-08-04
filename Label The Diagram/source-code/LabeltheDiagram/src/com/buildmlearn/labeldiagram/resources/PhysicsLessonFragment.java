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

public class PhysicsLessonFragment extends Fragment {

	List<LessonCategoryRawItem> categories = new ArrayList<LessonCategoryRawItem>();
	ArrayAdapter<LessonCategoryRawItem> lessonAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		createModel();

		lessonAdapter = new BioLessonAdapter(
				getActivity(), R.layout.lesson_category_row_item, categories);
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

		int[] titleArray = new int[] { R.string.lesson_prism,
				R.string.lesson_lens, R.string.lesson_spectrum,
				R.string.lesson_motor, R.string.lesson_dry_cell,
				R.string.lesson_circuit };

		int[] descrptionArray = new int[] { R.string.lesson_prism_desc,
				R.string.lesson_lens_desc, R.string.lesson_spectrum_desc,
				R.string.lesson_motor_desc, R.string.lesson_dry_cell_desc,
				R.string.lesson_circuit_desc };

		for (int i = 0; i < titleArray.length; i++) {

			LessonCategoryRawItem item = new LessonCategoryRawItem(
					getResources().getString(titleArray[i]), getResources().getString(descrptionArray[i]));

			categories.add(item);
		}
		
	}
}
