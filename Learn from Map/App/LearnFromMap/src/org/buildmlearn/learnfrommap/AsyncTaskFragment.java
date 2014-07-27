package org.buildmlearn.learnfrommap;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

public class AsyncTaskFragment extends Fragment {
	
	private static final String TAG = AsyncTaskFragment.class.getSimpleName();
	
	static interface TaskCallbacks {
		void onPreExecute();
		void onProgressUpdate(int percent);
		void onCancelled();
		void onPostExecute();
		//void doInBackground();
	}

	private TaskCallbacks mCallbacks;
	private GenerateQuestions genQuesAsync;
	private boolean mRunning;

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

	}

	/**
	 * Set the callback to null so we don't accidentally leak the 
	 * Activity instance.
	 */
//	@Override
//	public void onDetach() {
//		super.onDetach();
//		mCallbacks = null;
//	}


	@Override
	public void onDestroy() 
	{
		Log.i(TAG, "onDestroy()");
		super.onDestroy();
		cancel();
	}

	public void start() {
		if (!mRunning) 
		{
			genQuesAsync = new GenerateQuestions();
			genQuesAsync.execute();
			mRunning = true;
		}
	}

	/**
	 * Cancel the background task.
	 */
	public void cancel() {
		if (mRunning) 
		{
			genQuesAsync.cancel(false);
			genQuesAsync = null;
			mRunning = false;
		}
	}

	/**
	 * Returns the current state of the background task.
	 */
	public boolean isRunning() {
		return mRunning;
	}



	public class GenerateQuestions extends AsyncTask<Void, Integer, Void>
	{


		@Override
		protected Void doInBackground(Void... params) {

//			if (mCallbacks != null) 
//			{
//				mCallbacks.doInBackground();
//			}
			for(int i=0; i<20; i++)
			{
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				publishProgress(i);
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			if (mCallbacks != null) 
			{
				mCallbacks.onPostExecute();
				mRunning = false;
			}

		}

		@Override
		protected void onProgressUpdate(final Integer... values) {
			super.onProgressUpdate(values);
			if (mCallbacks != null) 
			{
				mCallbacks.onProgressUpdate(values[0]);
			}
		}




	} 


}
