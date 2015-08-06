package com.buildmlearn.labeldiagram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.buildmlearn.labeldiagram.helper.PlaceHolderContainer;
import com.buildmlearn.labeldiagram.helper.DiagramResultContainer;
import com.buildmlearn.labeldiagram.helper.TagContainerSingleton;
import com.buildmlearn.labeldiagram.helper.TagPlaceholderMapper;
import com.example.labelthediagram.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.DragEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnLongClickListener;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Akilaz
 *
 */
public abstract class DiagramPlayBase extends Activity implements
		OnDragListener, OnLongClickListener, OnClickListener {

	static final String TAG = "InfoTag";
	static final String TAG_ERROR = "Error";
	List<Integer[]> placeHolderlist;
	SparseIntArray tagPlaceHolderMap;
	SparseIntArray incompleteTagList;
	List<TextView> correctTagList;
	List<TextView> incorrectTagList;
	PlaceHolderContainer container;
	TagPlaceholderMapper tagPlaceholdermapper;
	SharedPreferences preferences;

	int correctLabeledCount = 0;
	int totalLabeledCount = 0;
	int tagListSize = 0;
	boolean achievedBestScore = false;
	String diagramName;
	TextView compeleteRatio;
	TextView score;
	ActionBar actionBar;
	Typeface tfThin;
	Typeface tfLight;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(getResourcesId());

		// Enabling ActionBar
		actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.show();

		// Initializing custom fonts
		tfThin = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Thin.ttf");
		tfLight = Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Light.ttf");

		// Initializing object lists and classes
		placeHolderlist = new ArrayList<Integer[]>();
		tagPlaceHolderMap = new SparseIntArray();
		incompleteTagList = new SparseIntArray();
		correctTagList = new ArrayList<TextView>();
		incorrectTagList = new ArrayList<TextView>();
		container = new PlaceHolderContainer();
		tagPlaceholdermapper = new TagPlaceholderMapper();

		tagListSize = tagPlaceHolderMap.size();

		// Create sharedpreferences object
		preferences = getApplicationContext().getSharedPreferences(
				"com.buildmlearn.labeldiagram.PREFERENCE_FILE_KEY",
				Context.MODE_PRIVATE);

	}

	@Override
	public boolean onLongClick(View textview) {

		ClipData clipData = ClipData.newPlainText("", "");

		View.DragShadowBuilder shadowBuilder = new TagDragShadowBuilder(
				textview);

		// Start the drag - contains the data to be dragged, metadata for this
		// data and callback for drawing shadow

		textview.startDrag(clipData, shadowBuilder, textview, 0);
		// Dragging the shadow so make the view invisible
		textview.setVisibility(View.INVISIBLE);
		return true;
	}

	@Override
	public boolean onDrag(View droppableView, DragEvent event) {

		// Defines a variable to store the action type for the incoming event
		final int action = event.getAction();
		final View draggedImageTag = (View) event.getLocalState();

		// Handles each of the expected events
		switch (action) {

		case DragEvent.ACTION_DRAG_STARTED:

			// Determines if the View can accept the dragged data
			if (event.getClipDescription().hasMimeType(
					ClipDescription.MIMETYPE_TEXT_PLAIN)) {

				Log.i(TAG, "Can accept this data");

				// returns true to indicate that the View can accept the dragged
				// data.
				return true;

			} else {
				Log.i(TAG, "Can not accept this data");

			}

			return false;

		case DragEvent.ACTION_DRAG_ENTERED:

			Toast.makeText(getApplicationContext(), "Ready to drop !",
					20).show();
			Log.i(TAG, "drag action entered");
			return true;

		case DragEvent.ACTION_DRAG_LOCATION:
			Log.i(TAG, "drag action location");
			// Ignore the event
			return true;

		case DragEvent.ACTION_DRAG_EXITED:
			Log.i(TAG, "drag action exited");
			// Calls when the Tag exited the place holder imageView
			return true;

		case DragEvent.ACTION_DROP:

			boolean side;
			Log.i(TAG, "drag action dropped");

			// Set place holder imageViews invisible
			droppableView.setVisibility(View.INVISIBLE);

			// Get the positioning side(within diagram) of the currently entered
			// place holder
			side = getPlaceHolderSide(droppableView);

			// If placeholder is on left side, set XY coordinates of the
			// draggedImageTag to right top corner
			if (side == true) {
				draggedImageTag
						.setX(droppableView.getX() + droppableView.getWidth()
								- draggedImageTag.getWidth());
				draggedImageTag.setY(droppableView.getY());
			}

			// If placeholder is on right side, set XY coordinates of the
			// draggedImageTag to left top corner
			else if (side == false) {
				draggedImageTag.setX(droppableView.getX());
				draggedImageTag.setY(droppableView.getY());
			}

			// Obtain the correct Tag id corresponding to place holder id
			int desiredTagId = tagPlaceHolderMap.get(droppableView.getId());
			incompleteTagList.delete(droppableView.getId());
			// Get Tag id of currently moving tag
			int currentTagId = draggedImageTag.getId();

			if (currentTagId != desiredTagId) {

				incorrectTagList.add((TextView) draggedImageTag);

				// If currently moving tag id doesn't match actual tag id
				// change the tag color to RED
				draggedImageTag
						.setBackgroundResource(R.drawable.custom_textview_incorrect);

				totalLabeledCount += 1;

				// Update score and progress
				updateProgress(correctLabeledCount, totalLabeledCount);

			} else if (currentTagId == desiredTagId) {

				correctTagList.add((TextView) draggedImageTag);

				// If currently moving tag id does match, actual tag id change
				// the tag color to light greenS
				draggedImageTag
						.setBackgroundResource(R.drawable.custom_textview_correct);

				totalLabeledCount += 1;
				correctLabeledCount += 1;

				// Update score and progress
				updateProgress(correctLabeledCount, totalLabeledCount);
			}

			draggedImageTag.setVisibility(View.VISIBLE);
			return true;

		case DragEvent.ACTION_DRAG_ENDED:

			Log.i(TAG, "getResult: " + event.getResult());

			// if the drop was not successful, set the tag to original place
			if (!event.getResult()) {
				draggedImageTag.post(new Runnable() {

					@Override
					public void run() {

						Log.i(TAG, "setting visible");
						draggedImageTag.setVisibility(View.VISIBLE);

					}
				});
			}

			return true;

			// An unknown action type was received.
		default:
			Log.e("DragDrop Example",
					"Unknown action type received by OnDragListener.");
			break;
		}

		return true;
	}

	/**
	 * Update score and progress
	 * 
	 * @param correcTries
	 * @param totalTries
	 */
	private void updateProgress(int correcTries, int totalTries) {

		final float progress;
		final float totalScore;
		final int MAX_PROGRESS = 100;
		final int gameScore;

		totalScore = correcTries * 10;
		progress = (float) totalTries / tagListSize * 100;
		gameScore = tagListSize * 10;

		score.setText((int) totalScore + "");
		compeleteRatio.setText((int) progress + "%");

		if ((int) progress == MAX_PROGRESS) {

			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {

					// Initialize singleton class
					TagContainerSingleton container = TagContainerSingleton
							.getInstance();

					// set correct and incorrect tag lists
					container.setCorrectLabelList(correctTagList);
					container.setIncorrectLabelList(incorrectTagList);

					float previousScore = preferences.getFloat(
							getDiagramName(), 0);
					if (previousScore <= totalScore) {
						SharedPreferences.Editor editor = preferences.edit();
						editor.putFloat(getDiagramName(), totalScore);
						editor.commit();
						achievedBestScore = true;
					}

					dispatch(totalScore,gameScore);

				}
			}, 3000);

		}

	}

	protected abstract void dispatch(float totalScore, int gameScore);

	/**
	 * Update score and progress if quit playing without completing all the tags
	 *
	 */
	public void quitPlayUpdataProgress() {

		final float progress;
		final float totalScore;
		final int gameScore;

		totalScore = correctLabeledCount * 10;
		progress = (float) totalLabeledCount / tagListSize * 100;
		gameScore = tagListSize * 10;

		score.setText(Integer.toString((int) totalScore));
		compeleteRatio.setText((int) progress + "%");

		TagContainerSingleton container = TagContainerSingleton.getInstance();

		for (int i = 0; i < incompleteTagList.size(); i++) {

			TextView incompleteView = (TextView) findViewById(incompleteTagList
					.valueAt(i));
			incorrectTagList.add(incompleteView);
		}

		container.setCorrectLabelList(correctTagList);
		container.setIncorrectLabelList(incorrectTagList);

		float previousScore = preferences.getFloat(getDiagramName(), 0);
		if (previousScore <= totalScore) {
			SharedPreferences.Editor editor = preferences.edit();
			editor.putFloat(getDiagramName(), totalScore);
			editor.commit();
			achievedBestScore = true;
		}

		dispatch(totalScore,gameScore);
	
	}


	/**
	 * Indicate whether the placeholder is on left side or right side of the
	 * diagram
	 * 
	 * @param droppableView
	 * @return
	 */
	private boolean getPlaceHolderSide(View droppableView) {

		boolean isOnLeftSide = false;

		// Check whether the place holder(droppableView) is in left side
		if (Arrays.asList(placeHolderlist.get(0)).contains(
				(Integer) droppableView.getId())) {

			isOnLeftSide = true;

		}
		// Check whether the place holder(droppableView) is in right side
		else if (Arrays.asList(placeHolderlist.get(1)).contains(
				droppableView.getId())) {

			isOnLeftSide = false;

		} else {
			Log.e(TAG_ERROR,
					"Position is undefined of the place holder imageView !");
		}

		return isOnLeftSide;
	}

	/**
	 * Helper class for generating drag shadow
	 */
	private static class TagDragShadowBuilder extends View.DragShadowBuilder {

		// Defines the constructor for TagDragShadowBuilder
		public TagDragShadowBuilder(View v) {

			// Stores the View parameter passed to TagDragShadowBuilder.
			super(v);

		}

		/*
		 * Defines a callback that sends the drag shadow dimensions and touch
		 * point back to the system.
		 */
		@Override
		public void onProvideShadowMetrics(Point size, Point touch) {

			int width;
			int height;

			// Sets the width of the shadow with respect to original View
			width = (getView().getWidth()) * 3;

			// Sets the height of the shadow with respect to original View
			height = (getView().getHeight()) * 3;

			// Sets the size parameter's width and height values. These get back
			// to the system through the size parameter.
			size.set(width, height);

			// Sets the touch point's position to the middle point visual
			// clarity
			touch.set((width / 4), (height / 4));
		}

		/*
		 * Defines a callback that draws the drag shadow in a Canvas that the
		 * system constructs from the dimensions passed in
		 * onProvideShadowMetrics().
		 */
		@Override
		public void onDrawShadow(Canvas canvas) {

			// Draws the ColorDrawable in the Canvas passed in from the system.
			// Scale the canvas 1.5x
			canvas.scale(1.5f, 1.5f);
			getView().draw(canvas);
		}
	}

	@Override
	public void onClick(View tagView) {
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// Respond to the action bar's Up/Home button
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	protected abstract int getResourcesId();

	public String getDiagramName() {
		return diagramName;
	}

	public void setDiagramName(String diagramName) {
		this.diagramName = diagramName;
	}

}
