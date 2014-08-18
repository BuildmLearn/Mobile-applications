package org.buildmlearn.learnfrommap;

import java.util.ArrayList;
import java.util.List;

import org.buildmlearn.learnfrommap.helper.CustomDialog;
import org.buildmlearn.learnfrommap.helper.HelperFunctions;
import org.buildmlearn.learnfrommap.helper.ReverseGeoCodeJson;
import org.buildmlearn.learnfrommap.helper.TextViewPlus;
import org.buildmlearn.learnfrommap.helper.TinyDB;
import org.buildmlearn.learnfrommap.questionmodule.GeneratedQuestion;
import org.buildmlearn.learnfrommap.questionmodule.GeneratedQuestion.Type;
import org.buildmlearn.learnfrommap.questionmodule.QuestionModuleException;
import org.buildmlearn.learnfrommap.questionmodule.UserAnsweredData;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * This activity is the backbone of the app and performs the question generation operation. 
 * 
 * @author Abhishek
 *
 */
public class GameActivity extends Helper implements AsyncTaskFragment.TaskCallbacks {

	private static final String TAG_TASK_FRAGMENT = "task_fragment";
	public static final int QUESTION_COUNT = 10;

	private String mode;
	private String mSelection;
	private String mValue;	
	private ProgressBar mProgressBar;
	private TextViewPlus mLoadingText;
	private TextViewPlus mTimer;
	private TextViewPlus mOption1;
	private TextViewPlus mOption2;
	private TextViewPlus mOption3;
	private TextViewPlus mOption4;
	private CountDownTimer mCountTimer;
	private TextViewPlus mDisplayQuestion;
	private int mSdk;
	private int mSelectedOption;
	private ArrayList<UserAnsweredData> mAnsweredList;
	private RelativeLayout mMain;
	private View mView;
	private List<GeneratedQuestion> mQuestion;
	private int mQuestionCounter;
	private GeneratedQuestion genQuestion;
	private String[] options;
	private boolean mIsAnswered;
	private boolean mIsCorrect;
	private String mDisplatMsg;
	protected long timeLeft;
	private Dialog dialog;
	private LatLng mMapLocation;
	private Marker userMarker;
	private AsyncTaskFragment mTaskFragment;
	private MediaPlayer mpCorrect;
	private MediaPlayer mpWrong;
	private TinyDB pref;
	
	@Override	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().setHomeButtonEnabled(true);
		mSdk = android.os.Build.VERSION.SDK_INT;
		mAnsweredList = new ArrayList<UserAnsweredData>();
		setContentView(R.layout.activity_game);
		mQuestion = new ArrayList<GeneratedQuestion>();
		mQuestionCounter = 0;
		Intent intent = getIntent();
		mode = intent.getStringExtra("MODE");
		String mapLocation = intent.getStringExtra("LOCATION");
		mMapLocation = HelperFunctions.locationFromString(mapLocation);
		mDisplatMsg = intent.getStringExtra("DISPLAY");
		mSelection = intent.getStringExtra("SELECTION");
		mValue = intent.getStringExtra("VALUE");
		mpCorrect = MediaPlayer.create(this, R.raw.correct_answer);
		mpWrong = MediaPlayer.create(this, R.raw.wrong_answer);
		pref = new TinyDB(getApplicationContext());
		
		if(!pref.getBoolean("SOUND"))
		{
			mpCorrect.setVolume(0, 0);
			mpWrong.setVolume(0, 0);
		}


		FragmentManager fm = getSupportFragmentManager();
		mTaskFragment = (AsyncTaskFragment) fm.findFragmentByTag(TAG_TASK_FRAGMENT);

		// If the Fragment is non-null, then it is currently being
		// retained across a configuration change.
		if (mTaskFragment == null) 
		{
			mTaskFragment = new AsyncTaskFragment();
			Bundle bundle = new Bundle();
			bundle.putString("SELECTION", mSelection);
			bundle.putString("VALUE", mValue);
			bundle.putString("MODE", mode);
			bundle.putInt("QUESTION_COUNT", QUESTION_COUNT);
			fm.beginTransaction().add(mTaskFragment, TAG_TASK_FRAGMENT).commit();
			mTaskFragment.setArguments(bundle);
			//mTaskFragment.start();
		}
		mLoadingText = (TextViewPlus)findViewById(R.id.sett_tutorial);
		mProgressBar = (ProgressBar)findViewById(R.id.game_progressbar);
		mProgressBar.setMax(QUESTION_COUNT);
		mProgressBar.setProgress(0);
		if(mode.equals("EXPLORE_MODE"))
		{
			setTitle("Explore Mode");
		}
		else if(mode.equals("CLASSIC_MODE"))
		{
			setTitle("Classic Mode");
		}
		else if(mode.equals("CATEGORY_MODE"))
		{
			setTitle("Category Mode");
		}
		mMain = (RelativeLayout)findViewById(R.id.main_layout);
		mView = getLayoutInflater().inflate(R.layout.layout_play_game, mMain,false);
	}

	/**
	 * Called when user clicks the start challenge button
	 * 
	 * @param v
	 */
	public void startGame(View v)
	{
		loadQuestion();

	}

	/**
	 * Called to load the next question
	 * 
	 * @param v
	 */
	@SuppressLint("NewApi") 
	public void nextQuestion(View v)
	{
		final TextViewPlus button = (TextViewPlus)findViewById(R.id.next_btn);
		if(button.getText().toString().equals("Submit"))
		{
			Boolean isCorrect = false;
			mCountTimer.cancel();
			mCountTimer = null;
			mTimer.setText("");
			if(genQuestion.getType() == Type.Mcq)
			{
				String answer = genQuestion.getAnswer();
				TextViewPlus[] options = {mOption1, mOption2, mOption3, mOption4};
				if(!mIsAnswered)
				{
					mTimer.setText("Correct answer is " + answer);
					isCorrect = null;
					HelperFunctions.updateStats(getApplicationContext(), false, genQuestion.getDbRow().getCountry());
				}
				for(int i=0; i<4; i++)
				{

					options[i].setClickable(false);
					if(mIsAnswered && mSelectedOption == i)
					{
						if(options[i].getText().toString().equals(answer))
						{
							mpCorrect.start();
							mTimer.setText("That's the correct answer!");
							isCorrect = true;
							HelperFunctions.updateStats(getApplicationContext(), true, genQuestion.getDbRow().getCountry());
						}
						else
						{
							HelperFunctions.updateStats(getApplicationContext(), false, genQuestion.getDbRow().getCountry());
							mTimer.setText("Sorry, wrong answer!");
							mpWrong.start();
							if(mSdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
								options[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.wrong_answer));

							} else {
								options[i].setBackground(getResources().getDrawable(R.drawable.wrong_answer));
							}
						}
					}

					if(options[i].getText().toString().equals(answer))
					{
						if(mSdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
							options[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.right_answer));

						} else {
							options[i].setBackground(getResources().getDrawable(R.drawable.right_answer));
						}
					}
				}

			}
			else if(genQuestion.getType() ==  Type.Fill)
			{
				EditText fillAnswer = (EditText)findViewById(R.id.fill_answer);
				String userAnswer  = fillAnswer.getText().toString();
				if(UserAnsweredData.CompareStrings(userAnswer, genQuestion.getAnswer()) > 0.95)
				{
					mpCorrect.start();
					mTimer.setText("That's the correct answer!");
					isCorrect = true;
				}
				else
				{
					mpWrong.start();
					mTimer.setText("Sorry, wrong answer!");
				}
				if(userAnswer.length() == 0)
				{
					isCorrect = null;
				}
				fillAnswer.setVisibility(View.GONE);
				TextViewPlus correctAnswer = (TextViewPlus)findViewById(R.id.fill_correct_answer);
				correctAnswer.setText("Answer: " + genQuestion.getAnswer());
				LinearLayout layout = (LinearLayout) findViewById(R.id.answer_status);
				if(isCorrect != null && isCorrect)
				{
					new Color();
					layout.setBackgroundColor(Color.argb(128, 102, 153, 00));
					HelperFunctions.updateStats(getApplicationContext(), true, genQuestion.getDbRow().getCountry());
				}
				else if(isCorrect != null && !isCorrect)
				{
					layout.setBackgroundColor(Color.argb(128, 204, 00, 00));
					HelperFunctions.updateStats(getApplicationContext(), false, genQuestion.getDbRow().getCountry());
				}



			}
			else if(genQuestion.getType() ==  Type.Pin)
			{
				mIsCorrect = false;
				mTimer.setText("Please wait");
				button.setVisibility(View.INVISIBLE);
				final ProgressBar progress = (ProgressBar)findViewById(R.id.map_progress1);
				mapView.setOnMapClickListener(null);
				marker.setDraggable(false);
				progress.setVisibility(View.VISIBLE);
				String url = HelperFunctions.geoCoderUrlBuilder(getPosition().latitude, getPosition().longitude);
//				Log.v("URL", url);
				StringRequest myReq = new StringRequest(Method.GET, url, new Response.Listener<String>() 
						{
					@Override
					public void onResponse(String response) {
//						Log.d("RESPONSE", response);
						button.setVisibility(View.VISIBLE);
						progress.setVisibility(View.INVISIBLE);
						ReverseGeoCodeJson reGeoData = new ReverseGeoCodeJson(response);
						String markerTitle = "";
						double userLat = marker.getPosition().latitude;
						double userLng = marker.getPosition().longitude;
						double ansLat = genQuestion.getDbRow().getLat();
						double andLng = genQuestion.getDbRow().getLng();
						double distance = HelperFunctions.distance(userLat, userLng, ansLat, andLng, 'K');
//						Log.e("Distance", "Distance between: " + distance);
						if(!reGeoData.isHasError())
						{
							
							String answerColumn = genQuestion.getXml().getAnswer();
							try {
								markerTitle = genQuestion.getDbRow().getDataByColumnName(genQuestion.getXml().getMarker());
							} catch (QuestionModuleException e) {
								Toast.makeText(getApplicationContext(), "Invalid entry in \"marker\" in Xml", Toast.LENGTH_SHORT).show();
								e.printStackTrace();
							}
							LatLng ansPosition = new LatLng(ansLat, andLng);
							MarkerOptions markerOption;
							try {
								markerOption = new MarkerOptions().draggable(false).position(ansPosition).flat(true).title(genQuestion.getDbRow().getDataByColumnName(genQuestion.getXml().getMarker())).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
							} catch (QuestionModuleException e) {
								markerOption = new MarkerOptions().draggable(false).position(ansPosition).flat(true).title(genQuestion.getDbRow().getName()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
								e.printStackTrace();
							}

							if(answerColumn.equals("location"))
							{
								if(distance < 600)
								{
									mpCorrect.start();
									mIsCorrect = true;
									marker.setPosition(new LatLng(ansLat, andLng));
									marker.setTitle(markerTitle);
									marker.showInfoWindow();
									mTimer.setText("Your answer is correct but more accurate answer is shown below");
									marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
								}
								else
								{
									mpWrong.start();
									mTimer.setText("Wrong answer!\nCorrect answer is shown below");
									mapView.addMarker(markerOption).showInfoWindow();;
								}
								CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(ansLat, andLng), 3);
								getMaps().animateCamera(cameraUpdate);
							}
							else if(answerColumn.equals("state"))
							{
								if(reGeoData.getState().equals(genQuestion.getDbRow().getState()))
								{
									mpCorrect.start();
									mIsCorrect = true;
									marker.setTitle(markerTitle);
									marker.showInfoWindow();
									mTimer.setText("That's the correct answer!");
									marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
								}
								else if(distance < 600)
								{
									mpCorrect.start();
									mIsCorrect = true;
									marker.setPosition(new LatLng(ansLat, andLng));
									marker.setTitle(markerTitle);
									marker.showInfoWindow();
									marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
									mTimer.setText("Your answer is correct but more accurate answer is shown below");
									CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(ansLat, andLng), 3);
									getMaps().animateCamera(cameraUpdate);
								}
								else
								{
									mpWrong.start();
									mTimer.setText("Wrong answer!\nCorrect answer is shown below");
									mapView.addMarker(markerOption).showInfoWindow();;
									marker.setTitle(reGeoData.getState());
									CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(ansLat, andLng), 3);
									getMaps().animateCamera(cameraUpdate);
								}
							}
							else if(answerColumn.equals("country"))
							{
								if(reGeoData.getCountry().equals(genQuestion.getDbRow().getCountry()))
								{
									mpCorrect.start();
									mIsCorrect = true;
									marker.setTitle(markerTitle);
									marker.showInfoWindow();
									mTimer.setText("That's the correct answer!");
									marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
								}
								else if(distance < 600)
								{
									mpCorrect.start();
									mIsCorrect = true;
									marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
									marker.setPosition(new LatLng(ansLat, andLng));
									marker.setTitle(markerTitle);
									marker.showInfoWindow();
									mTimer.setText("Your answer is correct but more accurate answer is shown below");
									CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(ansLat, andLng), 3);
									getMaps().animateCamera(cameraUpdate);
								}
								else
								{
									mpWrong.start();
									mTimer.setText("Wrong answer!\nCorrect answer is shown below");
									marker.setTitle(reGeoData.getCountry());
									mapView.addMarker(markerOption).showInfoWindow();;
									CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(ansLat, andLng), 3);
									getMaps().animateCamera(cameraUpdate);
								}
							}
							else
							{
								Toast.makeText(getApplicationContext(), "Invalid entry in \"answer\" in Xml: " + genQuestion.getAnswer(), Toast.LENGTH_SHORT).show();
							}
						}
						else
						{
							LatLng ansPosition = new LatLng(ansLat, andLng);
							MarkerOptions markerOption = new MarkerOptions().draggable(false).position(ansPosition).flat(true).title(genQuestion.getDbRow().getName()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
							if(distance < 600)
							{
								mpCorrect.start();
								mIsCorrect = true;
								marker.setPosition(new LatLng(ansLat, andLng));
								marker.setTitle(markerTitle);
								marker.showInfoWindow();
								mTimer.setText("Your answer is correct but more accurate answer is shown below");
								marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
							}
							else
							{
								mpWrong.start();
								mTimer.setText("Wrong answer!\nCorrect answer is shown below");
								mapView.addMarker(markerOption).showInfoWindow();;
							}
							CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(ansLat, andLng), 3);
							getMaps().animateCamera(cameraUpdate);
						}

					}
						},
						new Response.ErrorListener() {
							@Override
							public void onErrorResponse(VolleyError error) {
								button.setVisibility(View.VISIBLE);
								progress.setVisibility(View.INVISIBLE);
//								Log.d("VOLLEY ERROR", error.getMessage() + "");
								double ansLat = genQuestion.getDbRow().getLat();
								double andLng = genQuestion.getDbRow().getLng();
								LatLng ansPosition = new LatLng(ansLat, andLng);
								String markerTitle = "";
								try {
									markerTitle = genQuestion.getDbRow().getDataByColumnName(genQuestion.getXml().getMarker());
								} catch (QuestionModuleException e) {
									Toast.makeText(getApplicationContext(), "Invalid entry in \"marker\" in Xml", Toast.LENGTH_SHORT).show();
									e.printStackTrace();
								}
								double userLat = marker.getPosition().latitude;
								double userLng = marker.getPosition().longitude;
								double distance = HelperFunctions.distance(userLat, userLng, ansLat, andLng, 'K');
//								Log.e("Distance", "Distance between: " + distance);
								MarkerOptions markerOption = new MarkerOptions().draggable(false).position(ansPosition).flat(true).title(genQuestion.getDbRow().getName()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
								if(distance < 600)
								{
									mpCorrect.start();
									mIsCorrect = true;
									marker.setPosition(new LatLng(ansLat, andLng));
									marker.setTitle(markerTitle);
									marker.showInfoWindow();
									mTimer.setText("Your answer is correct but more accurate answer is shown below");
									marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
								}
								else
								{
									mpWrong.start();
									mTimer.setText("Wrong answer!\nCorrect answer is shown below");
									mapView.addMarker(markerOption).showInfoWindow();
								}
								CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(ansLat, andLng), 3);
								getMaps().animateCamera(cameraUpdate);
								Toast.makeText(getApplicationContext(), "Network Error", Toast.LENGTH_SHORT).show();
//								Log.e("Volley Error", "Error :" + error.getMessage());
							}
						});
				RequestQueue mQueue = Volley.newRequestQueue(this);
				mQueue.add(myReq);

			}
			button.setText("Next");
		}
		else
		{
			loadNextQuestion();
		}
	}

	/**
	 * Loads the next question
	 */
	public void loadNextQuestion()
	{

		if(mQuestion.get(mQuestionCounter-1).getType() == Type.Pin)
		{
			android.support.v4.app.Fragment fragment = (getSupportFragmentManager().findFragmentById(R.id.mapFragment));  
			if(fragment != null)
			{
				FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
				ft.remove(getSupportFragmentManager().findFragmentById(R.id.mapFragment)).commit();
				getSupportFragmentManager().popBackStackImmediate();
				mMain.removeAllViews();	
			}	
		}
		loadQuestion();
	}

	/**
	 * Handles the UI changes whenever any option is clicked in MCQ type questions.
	 * 
	 * @param v
	 */
	@SuppressLint("NewApi") 
	public void onOptionClick(View v)
	{
		switch (v.getId()) {
		case R.id.mcq_option1:
			if(mSdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
				mOption1.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_click));
				mOption2.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
				mOption4.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
				mOption3.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));

			} else {
				mOption1.setBackground(getResources().getDrawable(R.drawable.button_click));
				mOption2.setBackground(getResources().getDrawable(R.drawable.border_white));
				mOption3.setBackground(getResources().getDrawable(R.drawable.border_white));
				mOption4.setBackground(getResources().getDrawable(R.drawable.border_white));
			}
			mSelectedOption = 0;
			break;
		case R.id.mcq_option2:
			if(mSdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
				mOption2.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_click));
				mOption1.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
				mOption3.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
				mOption4.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
			} else {
				mOption2.setBackground(getResources().getDrawable(R.drawable.button_click));
				mOption1.setBackground(getResources().getDrawable(R.drawable.border_white));
				mOption3.setBackground(getResources().getDrawable(R.drawable.border_white));
				mOption4.setBackground(getResources().getDrawable(R.drawable.border_white));
			}		
			mSelectedOption = 1;
			break;
		case R.id.mcq_option3:
			if(mSdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
				mOption3.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_click));
				mOption2.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
				mOption1.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
				mOption4.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
			} else {
				mOption3.setBackground(getResources().getDrawable(R.drawable.button_click));
				mOption2.setBackground(getResources().getDrawable(R.drawable.border_white));
				mOption1.setBackground(getResources().getDrawable(R.drawable.border_white));
				mOption4.setBackground(getResources().getDrawable(R.drawable.border_white));
			}
			mSelectedOption = 2;
			break;
		case R.id.mcq_option4:
			if(mSdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
				mOption4.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_click));
				mOption2.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
				mOption3.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
				mOption1.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
			} else {
				mOption4.setBackground(getResources().getDrawable(R.drawable.button_click));
				mOption2.setBackground(getResources().getDrawable(R.drawable.border_white));
				mOption3.setBackground(getResources().getDrawable(R.drawable.border_white));
				mOption1.setBackground(getResources().getDrawable(R.drawable.border_white));
			}
			mSelectedOption = 3;
			break;
		default:
			break;
		}
		mIsAnswered = true;

	}

	@SuppressWarnings("unchecked")
	public void loadQuestion()
	{
		if(mQuestionCounter > 0)
		{
			String question = genQuestion.getQuestion();
			String answer = genQuestion.getAnswer();
			UserAnsweredData userAnswerData;
			if(genQuestion.getType() == Type.Fill)
			{
				EditText fillAnswer = (EditText)findViewById(R.id.fill_answer);
				String userAnswer  = fillAnswer.getText().toString();
				userAnswerData = new UserAnsweredData(getApplicationContext(), question, answer, userAnswer, genQuestion.getType(), genQuestion.getXml().getAnswer());

			}
			else if(genQuestion.getType() == Type.Mcq)
			{
				String userAnswer;
				if(mIsAnswered)
				{
					userAnswer = options[mSelectedOption];
				}
				else
				{
					userAnswer = "";
				}
				userAnswerData = new UserAnsweredData(question, answer, userAnswer, genQuestion.getType(), genQuestion.getXml().getAnswer(), options, mIsAnswered);

			}
			else
			{
				LatLng postion = getPosition();
				String userAnswer;
				if(postion != null)
				{
					userAnswer = postion.latitude + "," + postion.longitude;
				}
				else
				{
					userAnswer = "";
				}
				userAnswerData = new UserAnsweredData(getApplicationContext(), question, answer, userAnswer, genQuestion.getType(), genQuestion.getXml().getAnswer());
				userAnswerData.evaluatePin(mIsCorrect);
				HelperFunctions.updateStats(getApplicationContext(), mIsCorrect, genQuestion.getDbRow().getCountry());
			}
			mAnsweredList.add(userAnswerData);

		}
		if(mQuestionCounter == QUESTION_COUNT)
		{
			Intent intent = new Intent(getApplicationContext(), ScoreActivity.class);
			intent.putParcelableArrayListExtra("SCORE_DATA", (ArrayList<? extends Parcelable>) mAnsweredList);
			startActivity(intent);
			finish();
			return;
		}
		genQuestion = mQuestion.get(mQuestionCounter++);
		if(genQuestion.getType() == Type.Fill)
		{
			mView = getLayoutInflater().inflate(R.layout.layout_fill, mMain,false);
			mMain.removeAllViews();
			mMain.addView(mView);
			mDisplayQuestion = (TextViewPlus)findViewById(R.id.sett_tutorial);
			mDisplayQuestion.setText(genQuestion.getQuestion());
			startTimer(60000);

		}
		else if(genQuestion.getType() == Type.Mcq)
		{
			mIsAnswered = false;
			mView = getLayoutInflater().inflate(R.layout.activity_mcq, mMain,false);
			mMain.removeAllViews();
			mMain.addView(mView);
			mDisplayQuestion = (TextViewPlus)findViewById(R.id.sett_tutorial);
			mDisplayQuestion.setText(genQuestion.getQuestion());
			mOption1 = (TextViewPlus)findViewById(R.id.mcq_option1);
			mOption2 = (TextViewPlus)findViewById(R.id.mcq_option2);
			mOption3 = (TextViewPlus)findViewById(R.id.mcq_option3);
			mOption4 = (TextViewPlus)findViewById(R.id.mcq_option4);
			String[] temp = genQuestion.getOption();
			String[] _options = {temp[0], temp[1], temp[2], genQuestion.getAnswer()};
			options = _options;
			HelperFunctions.ShuffleArray(options);
			mOption1.setText(options[0]);
			mOption2.setText(options[1]);
			mOption3.setText(options[2]);
			mOption4.setText(options[3]);
			startTimer(60000);
		}
		else
		{
			mView = getLayoutInflater().inflate(R.layout.activity_map, mMain,false);
			mMain.removeAllViews();
			mMain.addView(mView);
			mDisplayQuestion = (TextViewPlus)findViewById(R.id.sett_tutorial);
			mDisplayQuestion.setText(genQuestion.getQuestion());
			new Handler().post(new Runnable() {

				@Override
				public void run() {
					getMapView((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.mapFragment), mMapLocation);

				}
			});	
		}

		getSupportActionBar().setTitle("Question " + mQuestionCounter + " of " + QUESTION_COUNT);

	}

	@Override
	protected void onPause() {
		if(mCountTimer != null)
		{
			mCountTimer.cancel();
		}
		super.onPause();

	}

	@Override
	protected void onResume() {
		if(mCountTimer != null)
		{
			startTimer((int)timeLeft);
		}
		super.onResume();
	}

	@Override
	protected void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		savedInstanceState.putLong("TIME", timeLeft);
		if(mCountTimer != null)
		{
			mCountTimer.cancel();
		}
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		timeLeft = savedInstanceState.getLong("TIME");
	}

	/**
	 * Starts a new timer for every question.
	 * 
	 * @param timer
	 */
	private void startTimer(int timer)
	{
		mTimer = (TextViewPlus)findViewById(R.id.timer);
		mCountTimer = new CountDownTimer(timer, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {
				mTimer.setText("Time remaining: " + millisUntilFinished / 1000);
				timeLeft = millisUntilFinished;
			}
			@Override
			public void onFinish() {
				nextQuestion(null);
			}
		}.start();
	}

	/* (non-Javadoc)
	 * @see org.buildmlearn.learnfrommap.Helper#onMapReady()
	 */
	@Override
	public void onMapReady() {
		super.onMapReady();
		ProgressBar loading = (ProgressBar)findViewById(R.id.map_progress);
		loading.setVisibility(View.GONE);
		startTimer(90000);	
		userMarker = marker;
		userMarker.setDraggable(false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			CustomDialog.AboutDialog(GameActivity.this);
			return true;
		}
		else if(id == android.R.id.home)
		{
			showConfirmDialog();
			return true;

		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * Called on back button pressed
	 */
	public void customBackPressed()
	{
		super.onBackPressed();
	}

	/**
	 * Shows a confirmation dialog box whenever the user tries to leave the current challenge.
	 */
	protected void showConfirmDialog() {
		dialog = new Dialog(GameActivity.this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		dialog.setContentView(R.layout.dialog_confirm);   
		dialog.show();
		TextViewPlus yes = (TextViewPlus)dialog.findViewById(R.id.confirm_yes);
		yes.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
				customBackPressed();
			}
		});
		TextViewPlus no = (TextViewPlus)dialog.findViewById(R.id.confirm_no);
		no.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
	}

	public void Confirm(View v)
	{
		if(v.getId() == R.id.confirm_yes)
		{
			super.onBackPressed();
		}   
		else
		{
			dialog.dismiss();
		}
	}

	@Override
	public void onBackPressed() {
		showConfirmDialog();
	}

	@Override
	public void onPreExecute() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProgressUpdate(final int percent) {
		mProgressBar.setProgress(percent);

		runOnUiThread(new Runnable() {
			public void run() {
				mLoadingText.setText("Loading question " + percent + " of " + QUESTION_COUNT);
			}
		});
	}

	@Override
	public void onCancelled() {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public void onPostExecute(Object question) {
		if(question == null)
		{
			Toast.makeText(getApplicationContext(), "Currently " + mValue + " is not supported", Toast.LENGTH_LONG).show();
			finish();
		}
		mQuestion = (ArrayList<GeneratedQuestion>)question;
		mMain.removeAllViews();
		mMain.addView(mView);
		TextViewPlus selection = (TextViewPlus)findViewById(R.id.play_selection);
		selection.setText(mDisplatMsg);
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.remove(getSupportFragmentManager().findFragmentByTag(TAG_TASK_FRAGMENT)).commit();
		getSupportFragmentManager().popBackStackImmediate();

	}

}
