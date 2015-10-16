package com.buildmlearn.labeldiagram.resources;

import java.util.List;

import com.buildmlearn.labeldiagram.lessons.LessonCircuit;
import com.buildmlearn.labeldiagram.lessons.LessonDryCell;
import com.buildmlearn.labeldiagram.lessons.LessonHumanEar;
import com.buildmlearn.labeldiagram.lessons.LessonLens;
import com.buildmlearn.labeldiagram.lessons.LessonMotor;
import com.buildmlearn.labeldiagram.lessons.LessonPrism;
import com.buildmlearn.labeldiagram.lessons.LessonSpectrum;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class PhysicsLessonAdapter extends LessonCategoryAdapter {

	int nItems;

	public PhysicsLessonAdapter(Context context, int resource,
			List<LessonCategoryRawItem> objects) {
		super(context, resource, objects);

		nItems = this.getCount();

	}

	@Override
	public void setDispatchClass(ViewHolder holder, int position) {

		final int index = position;

		holder.navIcon.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				final Intent intent;

				switch (index) {
				case 0:
					intent = new Intent(v.getContext(), LessonPrism.class);
					v.getContext().startActivity(intent);
					break;
				case 1:
					intent = new Intent(v.getContext(), LessonLens.class);
					v.getContext().startActivity(intent);
					break;
				case 2:
					intent = new Intent(v.getContext(), LessonSpectrum.class);
					v.getContext().startActivity(intent);
					break;
				case 3:
					intent = new Intent(v.getContext(), LessonMotor.class);
					v.getContext().startActivity(intent);
					break;
				case 4:
					intent = new Intent(v.getContext(), LessonDryCell.class);
					v.getContext().startActivity(intent);
					break;
				case 5:
					intent = new Intent(v.getContext(), LessonCircuit.class);
					v.getContext().startActivity(intent);
					break;
				default:
					break;
				}

			}
		});

	}

}
