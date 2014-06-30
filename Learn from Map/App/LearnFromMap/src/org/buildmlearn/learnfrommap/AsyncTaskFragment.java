package org.buildmlearn.learnfrommap;

import org.buildmlearn.learnfrommap.databasehelper.Database;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public class AsyncTaskFragment extends Fragment {

	static interface TaskCallbacks {
		void onPreExecute();
		void onProgressUpdate(int percent);
		void onCancelled();
		void onPostExecute();
		void doInBackground();
	}

	private TaskCallbacks mCallbacks;
	private GenerateQuestions genQuesAsync;
	private String selection;
	private String value;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mCallbacks = (TaskCallbacks) activity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Retain this fragment across configuration changes.
		setRetainInstance(true);

		// Create and execute the background task.
		genQuesAsync = new GenerateQuestions(selection, value);
		genQuesAsync.execute();
	}

	/**
	 * Set the callback to null so we don't accidentally leak the 
	 * Activity instance.
	 */
	@Override
	public void onDetach() {
		super.onDetach();
		mCallbacks = null;
	}


	public class GenerateQuestions extends AsyncTask<Void, Integer, Void>
	{

		String selection;
		String value;
		Database db;

		@Override
		protected Void doInBackground(Void... params) {

			if (mCallbacks != null) 
			{
				mCallbacks.doInBackground();
			}
			return null;
		}

		public GenerateQuestions(String selection, String value) {
			super();
			this.selection = selection;
			this.value = value;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			//db.close();
			if (mCallbacks != null) 
			{
				mCallbacks.onPostExecute();
			}

		}

		@Override
		protected void onProgressUpdate(final Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			if (mCallbacks != null) 
			{
				mCallbacks.onProgressUpdate(values[0]);
			}
		}




	} 


}
