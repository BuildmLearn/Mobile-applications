package com.buildmlearn.labeldiagram.widget;

import com.buildmlearn.labeldiagram.DiagramCategory;
import com.buildmlearn.labeldiagram.ScoreboardResult;
import com.example.labelthediagram.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class DiagramAlertDialogFragment extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		return new AlertDialog.Builder(getActivity())
		// Set Dialog Icon
				.setIcon(R.drawable.incorrect_btn)
				// Set Dialog Title
				.setTitle("Not Complete")
				// Set Dialog Message
				.setMessage(
						"You have not yet take the challenge to view the score."
						+ "\n\nDo you take the challenge now ?")

				// Positive button
				.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						
						launchIntent(DiagramCategory.class);
						
					}
				})

				// Negative Button
				.setNegativeButton("No",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								
								launchIntent(ScoreboardResult.class);
								
							}
						}).create();
	}

	protected void launchIntent(Class<?> dispatchedClass) {
		
		Intent intent = new Intent(getActivity(), dispatchedClass);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		getActivity().finish();
		
	}

}
