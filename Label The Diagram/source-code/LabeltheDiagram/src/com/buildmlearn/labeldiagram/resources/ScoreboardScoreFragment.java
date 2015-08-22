package com.buildmlearn.labeldiagram.resources;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.buildmlearn.labeldiagram.DiagramCategoryViewer;
import com.buildmlearn.labeldiagram.ScoreboardViewer;
import com.buildmlearn.labeldiagram.database.DBAdapter;
import com.buildmlearn.labeldiagram.entity.Result;
import com.buildmlearn.labeldiagram.widget.DiagramAlertDialogFragment;
import com.example.labelthediagram.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ScoreboardScoreFragment extends Fragment {

	private DBAdapter diagramDb;
	private Result result;
	private String source;
	private Gson gsonObj;
	private List<DiagramScoreboardResultRawItem> resultList;
	private List<String> correctTagList;
	private List<String> incorrectTagList;
	private ArrayAdapter<DiagramScoreboardResultRawItem> diagramScoreResultAdapter;
	private int score;
	private int gameScore;

	private static final String TAG = "LOG MESSAGE";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		source = getArguments().getString("SOURCE").trim();
		resultList = new ArrayList<DiagramScoreboardResultRawItem>();
		Log.i("Arguments", getArguments().getString("SOURCE").trim() + "  "
				+ " source :" + source);
		openDB();
		loadData();

		if (result == null) {
			
			createDialog();

		} else {
			score = (int) result.getScore();
			gameScore = (int) result.getGameScore();
			correctTagList = result.getCorrectTagList();
			incorrectTagList = result.getIncorrectTagList();
			fillAdapterDataModel(correctTagList, incorrectTagList);
		}

		diagramScoreResultAdapter = new DiagramScoreResultAdapter(
				getActivity(), R.layout.scoreboard_result_row_view, resultList);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.scoreboard_fragment_layout,
				container, false);
		TextView scoreTxt = (TextView) v.findViewById(R.id.scoreboardScore);
		TextView gameScoreTxt = (TextView) v.findViewById(R.id.out_of_txt);
		ListView itemList = (ListView) v
				.findViewById(R.id.scoreboard_result_itemlist);

		scoreTxt.setText(Integer.toString(score));
		gameScoreTxt.setText(Integer.toString(gameScore));
		// Set DiagramResult adapter

		itemList.setAdapter(diagramScoreResultAdapter);

		return v;
	}

	private void fillAdapterDataModel(List<String> correctTagList,
			List<String> incorrectTagList) {

		for (int i = 0; i < correctTagList.size(); i++) {

			String tagLabel = correctTagList.get(i);
			DiagramScoreboardResultRawItem correctItem = new DiagramScoreboardResultRawItem(
					tagLabel, R.drawable.correct_btn);
			resultList.add(correctItem);

		}

		for (int i = 0; i < incorrectTagList.size(); i++) {

			String tagLabel = incorrectTagList.get(i);
			DiagramScoreboardResultRawItem incorrectItem = new DiagramScoreboardResultRawItem(
					tagLabel, R.drawable.incorrect_btn);
			resultList.add(incorrectItem);

		}

	}

	private void loadData() {

		Cursor cursor = diagramDb.getRowScore(source);
		gsonObj = new Gson();
		result = new Result();
		if (cursor == null) {
			Log.i("..TAG.. ", "Hore ahuuna");
		}
		result = getResultRecord(cursor);
		// Log.i("Loaded reulst", result.toString());

	}

	private Result getResultRecord(Cursor cursor) {

		Result currentResult = new Result();

		// Reset cursor to start, checking to see if there's data:
		if (cursor.moveToFirst()) {
			do {

				String name = cursor.getString(DBAdapter.COL_DIAGRAM_NAME);
				String result = cursor.getString(DBAdapter.COL_RESULT);
				Log.i("source & name", source + " " + name);

				Type type = new TypeToken<Result>() {
				}.getType();

				currentResult = gsonObj.fromJson(result, type);

				if (currentResult.getDiagramName() == null) {

					Log.i("NULL Pointer", "Null point");

				} else {

					String outputDiagram = currentResult.getDiagramName();
					float score = currentResult.getScore();
					List<String> correctTagList = currentResult
							.getCorrectTagList();
					List<String> incorrectTagList = currentResult
							.getIncorrectTagList();
					Log.i("POJO Object :", outputDiagram + " " + score + " "
							+ correctTagList + " " + incorrectTagList);
					Log.i("REsult to string", currentResult.toString());
				}

			} while (cursor.moveToNext());

			// Close the cursor to avoid a resource leak.
			cursor.close();
			return currentResult;

		} else {

			cursor.close();
			return null;
		}

	}

	private void createDialog() {

		final Dialog dialog = new Dialog(getActivity());
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setCanceledOnTouchOutside(false);
		dialog.setContentView(R.layout.custom_dialog_view);
		dialog.setCancelable(false);

		Button yesBtn = (Button) dialog.findViewById(R.id.yes_btn);
		Button noBtn = (Button) dialog.findViewById(R.id.no_btn);
		TextView tvTitle = (TextView) dialog.findViewById(R.id.dialog_title);
		TextView tvMessage = (TextView) dialog.findViewById(R.id.confirm_txt);
		
		tvTitle.setText(getResources().getString(R.string.not_complete));
		tvMessage.setText(getResources().getString(R.string.not_complete_msg));
		yesBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				launchIntent(DiagramCategoryViewer.class);
			}
		});
		
		noBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				launchIntent(ScoreboardViewer.class);
			}
		});

		dialog.show();

	}

	private void launchIntent(Class<?> dispatchedClass) {

		Intent intent = new Intent(getActivity(), dispatchedClass);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		getActivity().finish();

	}

	private void openDB() {
		diagramDb = new DBAdapter(getActivity());
		diagramDb.open();
		Log.i("OPEN msg", "databse opened");
	}

	private void closeDB() {
		diagramDb.close();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		closeDB();
	}
}
