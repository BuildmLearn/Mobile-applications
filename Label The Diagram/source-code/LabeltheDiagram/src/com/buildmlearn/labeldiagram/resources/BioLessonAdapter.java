package com.buildmlearn.labeldiagram.resources;

import java.util.List;

import com.buildmlearn.labeldiagram.lessons.LessonHumanEar;
import com.buildmlearn.labeldiagram.lessons.LessonHumanEye;
import com.buildmlearn.labeldiagram.lessons.LessonHumanHeart;
import com.buildmlearn.labeldiagram.lessons.LessonPlantCell;
import com.buildmlearn.labeldiagram.lessons.LessonPlantFlower;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class BioLessonAdapter extends LessonCategoryAdapter {

	int nItems;

	public BioLessonAdapter(Context context, int resource,
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
					intent = new Intent(v.getContext(), LessonHumanEye.class);
					v.getContext().startActivity(intent);
					break;
				case 1:
					intent = new Intent(v.getContext(), LessonHumanHeart.class);
					v.getContext().startActivity(intent);
					break;
				case 2:
					intent = new Intent(v.getContext(), LessonHumanEar.class);
					v.getContext().startActivity(intent);
					break;
				case 3:
					intent = new Intent(v.getContext(), LessonPlantCell.class);
					v.getContext().startActivity(intent);
					break;
				case 4:
					intent = new Intent(v.getContext(), LessonPlantFlower.class);
					v.getContext().startActivity(intent);
					break;
				case 5:
					intent = new Intent(v.getContext(), LessonHumanEar.class);
					v.getContext().startActivity(intent);
					break;
				case 6:
					intent = new Intent(v.getContext(), LessonHumanEar.class);
					v.getContext().startActivity(intent);
					break;

				default:
					break;
				}

			}
		});

	}

}
