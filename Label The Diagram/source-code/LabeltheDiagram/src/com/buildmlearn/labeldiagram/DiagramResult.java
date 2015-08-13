package com.buildmlearn.labeldiagram;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import com.buildmlearn.labeldiagram.badges.BadgePopUpWindow;
import com.buildmlearn.labeldiagram.database.DBAdapter;
import com.buildmlearn.labeldiagram.entity.Result;
import com.buildmlearn.labeldiagram.helper.ClassMapper;
import com.buildmlearn.labeldiagram.helper.HelperClass;
import com.buildmlearn.labeldiagram.helper.TagContainerSingleton;
import com.buildmlearn.labeldiagram.resources.DiagramResultAdapter;
import com.buildmlearn.labeldiagram.resources.DiagramResultRawItem;
import com.example.labelthediagram.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Akilaz
 *
 */
public class DiagramResult extends Activity implements OnClickListener {
	
	ArrayList<DiagramResultRawItem> resultList = new ArrayList<DiagramResultRawItem>();
	List<TextView> correctTagList = new ArrayList<TextView>();
	List<TextView> incorrectTagList = new ArrayList<TextView>();
	List<String> correctTagTextList = new ArrayList<String>();
	List<String> incorrectTagTextList = new ArrayList<String>();

	boolean isBestScore;
	int gameScore;
	float score;
	String source;
	String category;
	DBAdapter diagramDb;
	SharedPreferences preferences;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.diagram_result);

		// Enabling ActionBar
		HelperClass.setActionBar("Result", this);

		// Get the correctly and incorrectly labeled tag-list
		correctTagList = TagContainerSingleton.getInstance()
				.getCorrectLabelList();
		incorrectTagList = TagContainerSingleton.getInstance()
				.getIncorrectLabelList();

		// Capture intent values passed by DiagramPlay activity
		score = getIntent().getExtras().getFloat("SCORE");
		source = getIntent().getExtras().getString("SOURCE");
		isBestScore = getIntent().getExtras().getBoolean("BEST_SCORE");
		gameScore = getIntent().getExtras().getInt("GAME_SCORE");

		// Set the score on the rating bar
		RatingBar scoreRater = (RatingBar) findViewById(R.id.resultBar);
		scoreRater.setRating((score / gameScore) * 5);

		// Set score on the score TextView
		TextView scoreTxt = (TextView) findViewById(R.id.scoreboardScore);
		scoreTxt.setText((int) score + "/" + gameScore);

		// Referencing action buttons and register for onClick event
		Button tryAgain = (Button) findViewById(R.id.againButton);
		Button nextButton = (Button) findViewById(R.id.nextButton);

		tryAgain.setOnClickListener(this);
		nextButton.setOnClickListener(this);

		// Save diagram result for viewing on the ScoreBoard
		saveDiagramScore();

		// Call for populating data for adapter
		fillDataModel();

		ListView list = (ListView) findViewById(R.id.mylistview);

		// Set DiagramResult adapter
		ArrayAdapter<DiagramResultRawItem> resultAdapter = new DiagramResultAdapter(
				this, R.layout.diagram_result_row_item, resultList);
		list.setAdapter(resultAdapter);
	}

	/**
	 * save score for the diagram play action
	 */
	private void saveDiagramScore() {

		openDB();

		extractTags(correctTagList, incorrectTagList);
		
		boolean completed = checkCompleted();

		Result resultObj = new Result(source);
		resultObj.setScore(score);
		resultObj.setCorrectTagList(correctTagTextList);
		resultObj.setIncorrectTagList(incorrectTagTextList);
		resultObj.setGameScore(gameScore);
		resultObj.setCompleted(completed);
		
		Gson gson = new Gson();
		String resultData = gson.toJson(resultObj);

		if (isBestScore == true) {

			long id1 = diagramDb.insertScore(source, resultData);
			long id2 = diagramDb.insertBestScore(source, resultData);
			Log.i("Database Status", "record id : " + id1 + " " + id2
					+ "source :" + source);

			Cursor cursor = diagramDb.getRowScore(source);
			displayRecordSet(cursor, gson);

			Cursor cursor1 = diagramDb.getRowBestScore(source);
			displayRecordSet(cursor1, gson);

		} else {

			long id1 = diagramDb.insertScore(source, resultData);
			Log.i("Database Status", "record id : " + id1 + " source :"
					+ source);

			Cursor cursor = diagramDb.getRowScore(source);
			displayRecordSet(cursor, gson);

		}

	}

	private boolean checkCompleted() {
		
		if((int)score >= 10){
			return true;
		}
		return false;
	}

	/**
	 * Extract text from tags to save
	 * 
	 * @param correctTagList
	 * @param incorrectTagList
	 */
	private void extractTags(List<TextView> correctTagList,
			List<TextView> incorrectTagList) {

		for (int i = 0; i < correctTagList.size(); i++) {
			correctTagTextList.add(correctTagList.get(i).getText().toString());
		}

		for (int j = 0; j < incorrectTagList.size(); j++) {
			incorrectTagTextList.add(incorrectTagList.get(j).getText()
					.toString());
		}
	}

	/**
	 * Populate data for the use of Array adapter
	 */
	private void fillDataModel() {

		List<TextView> combinedList = new ArrayList<TextView>(correctTagList);
		combinedList.addAll(incorrectTagList);

		for (int i = 0; i < combinedList.size(); i++) {

			TextView labeledTag = combinedList.get(i);
			Log.i("Text", labeledTag.getText().toString());
			DiagramResultRawItem item = new DiagramResultRawItem(labeledTag,
					R.drawable.incorrect_btn);

			resultList.add(item);

		}

	}

	private void displayRecordSet(Cursor cursor, Gson gson) {
		
		String message = "";
		String testObj = "";

		// Reset cursor to start, checking to see if there's data:
		if (cursor.moveToFirst()) {
			do {
				// Process the data:
				String name = cursor.getString(DBAdapter.COL_DIAGRAM_NAME);
				String result = cursor.getString(DBAdapter.COL_RESULT);

				Type type = new TypeToken<Result>() {
				}.getType();
				Result finalResult = gson.fromJson(result, type);
				// Append data to the message:
				message += "name=" + name + ", result=" + result + "\n";
				String outputDiagram = finalResult.getDiagramName();
				float score = finalResult.getScore();

				testObj += "outputDiagram" + outputDiagram + "score" + score;
			} while (cursor.moveToNext());
			
		}

		// Close the cursor to avoid a resource leak.
		cursor.close();

		Log.i("Retrieved Data", message + " +++ obj +++" + testObj);
	}

	private void openDB() {
		diagramDb = new DBAdapter(this);
		diagramDb.open();
	}

	private void closeDB() {
		diagramDb.close();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		closeDB();
	}

	@Override
	public void onClick(View view) {

		switch (view.getId()) {
		case R.id.againButton:

			getDispatcingClass(source);

			break;

		case R.id.nextButton:

			Intent intent = new Intent(getApplicationContext(),
					DiagramCategoryViewer.class);
			intentBuilder(intent);

			break;
		default:
			break;
		}

	}

	// Populate intent and start activity
	private void intentBuilder(Intent intent) {

		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		startActivity(intent);

	}

	private void getDispatcingClass(String source) {

		HashMap<String, Class<?>> destMap = ClassMapper.getInstance().getMap();
		Class<?> destinationClass = destMap.get(source);
		Intent intent = new Intent(getApplicationContext(), destinationClass);
		intentBuilder(intent);

	}
}
