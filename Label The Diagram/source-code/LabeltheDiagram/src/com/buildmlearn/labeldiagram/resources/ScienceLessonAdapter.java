package com.buildmlearn.labeldiagram.resources;

import java.util.List;

import com.buildmlearn.labeldiagram.lessons.LessonHumanEar;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class ScienceLessonAdapter extends LessonCategoryAdapter {

	int nItems;

	public ScienceLessonAdapter(Context context, int resource,
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
					Toast.makeText(getContext(), "Comming soon...", 200).show();
					/*intent = new Intent(v.getContext(), LessonHumanEar.class);
					v.getContext().startActivity(intent);*/
					break;
				case 1:
					Toast.makeText(getContext(), "Comming soon...", 200).show();
					/*intent = new Intent(v.getContext(), LessonHumanEar.class);
					v.getContext().startActivity(intent);*/
					break;
				case 2:
					Toast.makeText(getContext(), "Comming soon...", 200).show();
					/*intent = new Intent(v.getContext(), LessonHumanEar.class);
					v.getContext().startActivity(intent);*/
					break;
				case 3:
					Toast.makeText(getContext(), "Comming soon...", 200).show();
					/*intent = new Intent(v.getContext(), LessonHumanEar.class);
					v.getContext().startActivity(intent);*/
					break;
				default:
					break;
				}

			}
		});

	}

}
