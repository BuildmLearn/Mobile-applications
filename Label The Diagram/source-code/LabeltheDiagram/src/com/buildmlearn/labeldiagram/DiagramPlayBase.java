package com.buildmlearn.labeldiagram;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.buildmlearn.labeldiagram.badges.BadgePopUpWindow;
import com.buildmlearn.labeldiagram.database.DBAdapter;
import com.buildmlearn.labeldiagram.database.Database;
import com.buildmlearn.labeldiagram.entity.Result;
import com.buildmlearn.labeldiagram.helper.PlaceHolderContainer;
import com.buildmlearn.labeldiagram.helper.TagContainerSingleton;
import com.buildmlearn.labeldiagram.helper.TagPlaceholderMapper;
import com.example.labelthediagram.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
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
	static final String KEY_ACHIEVED = "isAchieved";
	static final int BIO_DIAGRAM_COUNT = 7;
	static final int PHYSICS_DIAGRAM_COUNT = 6;
	static final int SCIENCE_DIAGRAM_COUNT = 4;
	static final int TOTAL_DIAGRAM_COUNT = 17;
	static final int TOTAL_CATEGORY_COUNT = 3;
	static final int TOTAL_COMPLETE_TRIES_COUNT = 3;
	static final int MAX_ROWS_COUNT = 2;
	static final int MAX_PREF_UPDATE_CYCLES = 4;
	
	List<Integer[]> placeHolderlist;
	SparseIntArray tagPlaceHolderMap;
	SparseIntArray incompleteTagList;
	List<TextView> correctTagList;
	List<TextView> incorrectTagList;
	PlaceHolderContainer container;
	TagPlaceholderMapper tagPlaceholdermapper;
	SharedPreferences preferences;
	SharedPreferences.Editor editor;
	DBAdapter diagramDb;
	Database database;

	int correctLabeledCount = 0;
	int totalLabeledCount = 0;
	int tagListSize = 0;
	int tryCycle;
	boolean achievedBestScore = false;
	String diagramName;
	String diagramCategory;
	String source;
	TextView compeleteRatio;
	TextView score;
	ActionBar actionBar;
	Typeface tfThin;
	Typeface tfLight;
	boolean isToDispatch = false;

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

		// Create SharedPreferences object
		initPreferences();

		getIntentData();
	}

	private void getIntentData() {

		source = getIntent().getExtras().getString("SOURCE");
		tryCycle = getIntent().getExtras().getInt("TRY_CYCLE");
		
		
	}

	private void initPreferences() {
		preferences = getApplicationContext().getSharedPreferences(
				"com.buildmlearn.labeldiagram.PREFERENCE_FILE_KEY",
				Context.MODE_PRIVATE);
		editor = preferences.edit();
		
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

					saveResultForBadges(totalScore, gameScore);
					
					dispatch(totalScore,gameScore);

				}
			}, 3000);

		}

	}


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

		saveResultForBadges(totalScore, gameScore);
		
		if(isToDispatch == false){
			dispatch(totalScore, gameScore);
		}
		
	
	}
	
	private void saveResultForBadges(float score, int gameScore) {

		initPreferences();
		generateBadges(score, gameScore);

	}
	
	private void generateBadges(float score, int gameScore) {
		

		if((int)score < 20){
			
			if(source.equals("Fragment")){
				tryCycle = 0;
			}else if(source.equals("Result")){
				tryCycle = tryCycle + 1;
			}
			
		}
		
		if ((int)score >= 20) {
			
			generateGreatStreakBadge(score, gameScore);
			
			generatePersistanceBadge(score, gameScore);

			generateMasterBadges(score, gameScore);
			
			
		}

	}

	private void generateGreatStreakBadge(float score, int gameScore) {

		String key;
		String badgeTitle;
		int badgeId;
		boolean allDiagramsCompleted;
		Gson gson = new Gson();
		HashMap<String, Boolean> recordMap = new HashMap<String, Boolean>();
		
		key = getResources().getString(R.string.fully_completed_tries_count);
		
		boolean isGreatStreakBadge = preferences.getBoolean(key, false);
		
		openDB();
		Cursor cursor = diagramDb.getFirstThreeScoreRows(); 
		
		if (cursor.moveToFirst()) {
			do {
				String name = cursor.getString(DBAdapter.COL_DIAGRAM_NAME);
				String result = cursor.getString(DBAdapter.COL_RESULT);

				Type type = new TypeToken<Result>() {}.getType();
				Result resultObj = gson.fromJson(result, type);
				
				recordMap.put(resultObj.getDiagramName(), resultObj.getCompleted());
				
			} while (cursor.moveToNext());
			
		}else{
			Log.i("NULL RECORD SET", "No records yet");
			return;
		}
		
		if(!isGreatStreakBadge){
			
			if(recordMap.size() == MAX_ROWS_COUNT){
				
				if(!recordMap.containsKey(getDiagramName())){
					
					Collection<Boolean> val = recordMap.values();
					Boolean[] array = (Boolean[])(val.toArray(new Boolean[val.size()]));
					int count = 0;
					 for(int i=0; i<array.length; i++){
						 if(array[i] == true){
							 count += 1;
						 }
					 }
					 
					 if(count == MAX_ROWS_COUNT){
						 
						isToDispatch = true;
						allDiagramsCompleted = false;
						updateBoolPreferences(key);
						
						badgeTitle = getResources().getString(R.string.badge_streak);
						badgeId = R.drawable.streak;
						
						database.updateBadgeAchievement(badgeTitle, true);
						
						intentBuilder(badgeTitle,badgeId,score,gameScore,allDiagramsCompleted,tryCycle);
					 }
					
				}
				
			}else{
				return;
			}
			
		}else{
			return;
		}

	}

	private void generateMasterBadges(float score, int gameScore) {
		
		String key;
		String badgeTitle;
		int keyValue;
		int badgeId;
		String mMasterBioBadge;
		String mMasterPhysicsBadge;
		String mMasterScienceBadge;
		boolean allDiagramsCompleted = false;
		
		switch (diagramCategory) {

		case "Biology":
			
			key = getDiagramName().concat("Biology");
			keyValue = updateMasterPreferences(key,diagramCategory);
			
			mMasterBioBadge = "bioBadge";
			boolean isMasterBioBadge = preferences.getBoolean(mMasterBioBadge, false);
			
			if(!isMasterBioBadge){
				
				if(keyValue == BIO_DIAGRAM_COUNT){
					
					isToDispatch = true;
					updateBoolPreferences(mMasterBioBadge);
					badgeTitle = getResources().getString(R.string.badge_biology);
					badgeId = R.drawable.bio;
					
					key = getResources().getString(R.string.total_category_completed);
					keyValue = updatePreferences(key);
					
					if(keyValue == TOTAL_CATEGORY_COUNT){
						
						allDiagramsCompleted = true;
						intentBuilder(badgeTitle,badgeId,score,gameScore,allDiagramsCompleted,tryCycle);
						
					}else{
						
						database.updateBadgeAchievement(badgeTitle, true);
						intentBuilder(badgeTitle,badgeId,score,gameScore,allDiagramsCompleted,tryCycle);
					
					}
					
				}
				
			}
			
			
			break;
			
		case "Physics":
			
			key = getDiagramName().concat("Physics");
			keyValue = updateMasterPreferences(key,diagramCategory);
			
			mMasterPhysicsBadge = "physicsBadge";
			boolean isMasterPhysicsBadge = preferences.getBoolean(mMasterPhysicsBadge, false);
			
			if(!isMasterPhysicsBadge){
				
				if(keyValue == PHYSICS_DIAGRAM_COUNT){
					
					isToDispatch = true;
					updateBoolPreferences(mMasterPhysicsBadge);
					badgeTitle = getResources().getString(R.string.badge_physics);
					badgeId = R.drawable.physics;
					
					key = getResources().getString(R.string.total_category_completed);
					keyValue = updatePreferences(key);
					
					if(keyValue == TOTAL_CATEGORY_COUNT){
						
						allDiagramsCompleted = true;
						intentBuilder(badgeTitle,badgeId,score,gameScore,allDiagramsCompleted,tryCycle);
					
					}else{
						
						database.updateBadgeAchievement(badgeTitle, true);
						intentBuilder(badgeTitle,badgeId,score,gameScore,allDiagramsCompleted,tryCycle);
					
					}
					
				}
				
			}
			
			break;
			
		case "Science":
			
			key = getDiagramName().concat("Science");
			keyValue = updateMasterPreferences(key,diagramCategory);
			
			mMasterScienceBadge = "scienceBadge";
			boolean isMasterScienceBadge = preferences.getBoolean(mMasterScienceBadge, false);
			
			if(!isMasterScienceBadge){
				
				if(keyValue == SCIENCE_DIAGRAM_COUNT){
					
					isToDispatch = true;
					updateBoolPreferences(mMasterScienceBadge);
					badgeTitle = getResources().getString(R.string.badge_science);
					badgeId = R.drawable.science;

					key = getResources().getString(R.string.total_category_completed);
					keyValue = updatePreferences(key);
					
					if(keyValue == TOTAL_CATEGORY_COUNT){
						
						allDiagramsCompleted = true;
						intentBuilder(badgeTitle,badgeId,score,gameScore,allDiagramsCompleted,tryCycle);
					
					}else{
						
						database.updateBadgeAchievement(badgeTitle, true);
						intentBuilder(badgeTitle,badgeId,score,gameScore,allDiagramsCompleted,tryCycle);
					
					}
					
				}
				
			}
			
			break;
			
		default:
			break;
			
		}
	}
	
	
	private void generatePersistanceBadge(float score, int gameScore) {
		
		String badgeTitle;
		int badgeId;
		boolean allDiagramsCompleted;

			
			if(tryCycle == TOTAL_COMPLETE_TRIES_COUNT){
				
				isToDispatch = true;
				allDiagramsCompleted = false;
				
				badgeTitle = getResources().getString(R.string.badge_persistence);
				badgeId = R.drawable.persistence;
				
				database.updateBadgeAchievement(badgeTitle, true);
				
				intentBuilder(badgeTitle,badgeId,score,gameScore,allDiagramsCompleted,tryCycle);
				
			}
		
	}
	
	
	private int updateMasterPreferences(String key, String category) {
		
		boolean value;
		int count;
		
		value = preferences.getBoolean(key, false);
		count = preferences.getInt(category, 0);
		
		if(value == false){
			value = true;
			count += 1;
		}
		
		editor.putBoolean(key, value);
		editor.putInt(category, count);
		editor.commit();
		
		return count;
	}
	
	private void updateBoolPreferences(String key){
		
		boolean value;
		value = preferences.getBoolean(key, false);
		value = true;
		editor.putBoolean(key, value);
		editor.commit();
		
	}

	
	private int updatePreferences(String key){
		
		int previousVal;
		int updatedVal;
		
		previousVal = preferences.getInt(key, 0);
		updatedVal = previousVal += 1;
		editor.putInt(key, updatedVal);
		editor.commit();
		//preferences.getBoolean(key, defValue)
		return updatedVal;
		
	}

	protected abstract void intentBuilder(String badgeTitle, int badgeId, float score, int gameScore, boolean completed, int tryCycle);

	protected abstract void dispatch(float totalScore, int gameScore);
	
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
	

	public String getDiagramName() {
		return diagramName;
	}

	public void setDiagramName(String diagramName) {
		this.diagramName = diagramName;
	}

	public String getDiagramCategory() {
		return diagramCategory;
	}

	public void setDiagramCategory(String diagramCategory) {
		this.diagramCategory = diagramCategory;
	}
	
	protected abstract int getResourcesId();
	
	protected void openDB() {
		diagramDb = new DBAdapter(this);
		diagramDb.open();
		database = new Database(this);
	}

	protected void closeDB() {
		diagramDb.close();
		database.close();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		closeDB();
	}
	
}
