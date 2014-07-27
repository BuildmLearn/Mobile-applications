package org.buildmlearn.learnfrommap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.buildmlearn.learnfrommap.databasehelper.Database;
import org.buildmlearn.learnfrommap.helper.CustomDialog;
import org.buildmlearn.learnfrommap.parser.XmlParser;
import org.buildmlearn.learnfrommap.questionmodule.DbRow;
import org.buildmlearn.learnfrommap.questionmodule.GeneratedQuestion;
import org.buildmlearn.learnfrommap.questionmodule.GeneratedQuestion.Type;
import org.buildmlearn.learnfrommap.questionmodule.QuestionModuleException;
import org.buildmlearn.learnfrommap.questionmodule.UserAnsweredData;
import org.buildmlearn.learnfrommap.questionmodule.XmlQuestion;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
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
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class GameActivity extends Helper {

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
	private String mDisplatMsg;
	protected long timeLeft;
	private Dialog dialog;
	private GoogleMap gmaps;
	private Marker userMarker;
	private String country;
	private String state;

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
		mDisplatMsg = intent.getStringExtra("DISPLAY");
		mSelection = intent.getStringExtra("SELECTION");

		mValue = intent.getStringExtra("VALUE");
		mLoadingText = (TextViewPlus)findViewById(R.id.question);
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
		GenerateQuestions genQues = new GenerateQuestions(mSelection, mValue);
		genQues.execute();
		mMain = (RelativeLayout)findViewById(R.id.main_layout);
		mView = getLayoutInflater().inflate(R.layout.layout_play_game, mMain,false);


	}

	static void shuffleArray(String[] ar)
	{
		Random rnd = new Random();
		for (int i = ar.length - 1; i > 0; i--)
		{
			int index = rnd.nextInt(i + 1);
			String a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
	}

	public void startGame(View v)
	{
		loadQuestion();

	}

	@SuppressLint("NewApi") 
	public void nextQuestion(View v)
	{
		TextViewPlus button = (TextViewPlus)findViewById(R.id.next_btn);
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
				}
				for(int i=0; i<4; i++)
				{

					options[i].setClickable(false);
					if(mIsAnswered && mSelectedOption == i)
					{
						if(options[i].getText().toString().equals(answer))
						{
							mTimer.setText("That's the correct answer!");
							isCorrect = true;
						}
						else
						{
							mTimer.setText("Sorry, wrong answer!");
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
					mTimer.setText("That's the correct answer!");
					isCorrect = true;
				}
				else
				{
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
				}
				else if(isCorrect != null && !isCorrect)
				{
					layout.setBackgroundColor(Color.argb(128, 204, 00, 00));
				}


			}
			else if(genQuestion.getType() ==  Type.Pin)
			{
				if(getPosition() != null)
				{
					double lat  = getPosition().latitude;
					double lng = getPosition().longitude;
					String googleurl = "https://maps.google.com/maps/api/geocode/json?key=AIzaSyACYVxd_d-49UnhqibCI6F9f7b5Gw1qTSc&";
					Log.v("HTTP" , "Latitude is: " + lat + "Longitude is:" + lng);
					StringBuilder sbuilder = new StringBuilder();
					sbuilder.append(googleurl);
					sbuilder.append("latlng=" + lat + "," + lng);
					sbuilder.append("&sensor=true");
					String url = sbuilder.toString();
					Log.v("URL", url);
					StringRequest myReq = new StringRequest(Method.GET, 
							url,
							new Response.Listener<String>() {


						@Override
						public void onResponse(String response) {
							//Log.d("VOLLEY", response);
							try {
								JSONObject main = new JSONObject(response);
								JSONArray array = main.getJSONArray("results");
								JSONObject obj = array.getJSONObject(0);
								array = obj.getJSONArray("address_components");
								for(int i=0; i<array.length(); i++)
								{
									obj = array.getJSONObject(i);
									JSONArray tempArray = obj.getJSONArray("types");
									if(tempArray.getString(0).equals("country"))
									{
										country = obj.getString("long_name");
										Log.e("Country", country);
									}
									if(tempArray.getString(0).equals("administrative_area_level_1"))
									{
										state = obj.getString("long_name");
										Log.e("State", state);

									}
								}

							} catch (JSONException e) {
								// TODO Auto-generated catch block
								Toast.makeText(getApplicationContext(), "There was some error fetching your location\nError: " + e.getMessage(), Toast.LENGTH_LONG).show();
								country = "";
								state = "";
								e.printStackTrace();
							}

						}
					},
					new Response.ErrorListener() {
						@Override
						public void onErrorResponse(VolleyError error) {
							Log.d("VOLLEY ERROR", error.getMessage());
							Toast.makeText(getApplicationContext(), "There was some error fetching your location\nError: " + error.getMessage(), Toast.LENGTH_LONG).show();
						}
					});
					RequestQueue mQueue = Volley.newRequestQueue(this);
					mQueue.add(myReq);

				}
				else
				{
					country = "";
					state = "";
				}
				LatLng newPostion = new LatLng(genQuestion.getDbRow().getLat(), genQuestion.getDbRow().getLng());
				MarkerOptions markerOption = new MarkerOptions().draggable(false).position(newPostion).flat(true).title("Correct Answer");
				marker = mapView.addMarker(markerOption);
				getMaps().addMarker(markerOption);
				CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(newPostion, 4);
				getMaps().animateCamera(cameraUpdate);
				mTimer.setText("Correct answer is pinned on the map");
				marker.showInfoWindow();

			}
			button.setText("Next");
		}
		else
		{
			loadNextQuestion();
		}
	}

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
				userAnswerData.setCountry(country);
				userAnswerData.setState(state);
				userAnswerData.evaluatePin();
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
			mDisplayQuestion = (TextViewPlus)findViewById(R.id.question);
			mDisplayQuestion.setText(genQuestion.getQuestion());
			EditText fillAnswer = (EditText)findViewById(R.id.fill_answer);
			fillAnswer.setText(genQuestion.getAnswer());
			startTimer(60000);

		}
		else if(genQuestion.getType() == Type.Mcq)
		{
			mIsAnswered = false;
			mView = getLayoutInflater().inflate(R.layout.activity_mcq, mMain,false);
			mMain.removeAllViews();
			mMain.addView(mView);
			mDisplayQuestion = (TextViewPlus)findViewById(R.id.question);
			mDisplayQuestion.setText(genQuestion.getQuestion());
			mOption1 = (TextViewPlus)findViewById(R.id.mcq_option1);
			mOption2 = (TextViewPlus)findViewById(R.id.mcq_option2);
			mOption3 = (TextViewPlus)findViewById(R.id.mcq_option3);
			mOption4 = (TextViewPlus)findViewById(R.id.mcq_option4);
			String[] temp = genQuestion.getOption();
			String[] _options = {temp[0], temp[1], temp[2], genQuestion.getAnswer()};
			options = _options;
			shuffleArray(options);
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
			mDisplayQuestion = (TextViewPlus)findViewById(R.id.question);
			mDisplayQuestion.setText(genQuestion.getQuestion());
			new Handler().post(new Runnable() {

				@Override
				public void run() {
					getMapView((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.mapFragment));

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
		// Save UI state changes to the savedInstanceState.
		// This bundle will be passed to onCreate if the process is
		// killed and restarted.
		savedInstanceState.putLong("TIME", timeLeft);
		if(mCountTimer != null)
		{
			mCountTimer.cancel();
		}

		// etc.
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		timeLeft = savedInstanceState.getLong("TIME");
		//startTimer((int)timeLeft);
	}

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

	@Override
	public void onMapReady() {
		super.onMapReady();
		ProgressBar loading = (ProgressBar)findViewById(R.id.map_progress);
		loading.setVisibility(View.GONE);
		startTimer(90000);	
		gmaps = mapView;
		userMarker = marker;
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

	public void customBackPressed()
	{
		super.onBackPressed();
	}

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

	public class GenerateQuestions extends AsyncTask<Void, Integer, String>
	{
		String selection;
		String value;
		Database db;
		ArrayList<DbRow> globalDbRow;

		@Override
		protected String doInBackground(Void... params) {

			globalDbRow = new ArrayList<DbRow>();
			db = new Database(getApplicationContext(), 1);
			XmlParser xmlParser = new XmlParser(getApplicationContext());
			ArrayList<XmlQuestion> questionRules = xmlParser.fetchQuestions();		
			Random random = new Random();
			ArrayList<Integer> blackListRules = new ArrayList<Integer>();

			//Where condition selection
			//Explore Mode
			String where = null;
			if(selection.equals("CONTINENT"))
			{
				where = "continent = " + value;
			}
			else if(selection.equals("COUNTRY"))
			{
				try {
					where = "country = " + db.getId("SELECT * FROM country WHERE name='" + value + "'");
				} catch (QuestionModuleException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			for(int i = 1; i<= QUESTION_COUNT; i++)
			{
				int randomNo = random.nextInt(questionRules.size());
				XmlQuestion questionRule = questionRules.get(randomNo);
				if(!blackListRules.contains(randomNo))
				{
					if(mode.equals("CLASSIC_MODE"))
					{
						if(questionRule.getAnswer().equals("country"))
						{
							blackListRules.add(randomNo);
							i--;
							continue;
						}
						if(questionRule.getCount().equals("unique"))
						{
							blackListRules.add(randomNo);
						}
					}
					questionRule.printRule();

					
					String tableName = questionRule.getCode();
					String query = "SELECT * FROM " + tableName + " WHERE "+ where +" LIMIT ";
					String countQuery = "SELECT COUNT(*) FROM " + tableName + " WHERE " + where;
					try {
						boolean isUnique = false;
						int tryCount = 0;
						DbRow row = null;
						row = db.rawSelect(query, countQuery);
//						while(!isUnique)
//						{
//							isUnique = true;
//
//							row = db.rawSelect(query, countQuery);
//							Log.e("Stat", row.getName() + " " + row.getCountry());
//							for(DbRow row1 : globalDbRow)
//							{
//								if(row1.isEqual(row))
//								{
//									
//									tryCount++;
//									isUnique = false;
//									Log.e("DUPLICATE", "");
//									if(tryCount == 10)
//									{
//										
//										//throw new NoDbRowException("No row present");
//									}
//									
//								}
//							}
//							if(isUnique)
//							{
//								globalDbRow.add(row);	
//							}
//						}
						
						String question = questionRule.getFormat().replace(":X:", row.getDataByColumnName(questionRule.getRelation()));
						String answer = row.getDataByColumnName(questionRule.getAnswer());
						GeneratedQuestion genQues;
						if(questionRule.getType() == XmlQuestion.Type.MultipleChoiceQuestion)
						{
							String[] options = db.createOptions(questionRule.getAnswer(), answer, questionRule.getCode());
							genQues = new GeneratedQuestion(row, questionRule, question, answer, options);
						}
						else if(questionRule.getType() == XmlQuestion.Type.FillBlanks)
						{
							genQues = new GeneratedQuestion(row, questionRule, question, answer, Type.Fill);
						}
						else
						{
							genQues = new GeneratedQuestion(row, questionRule, question, answer, Type.Pin);
						}
						mQuestion.add(genQues);
						publishProgress(i);
					} catch (QuestionModuleException e) {
						blackListRules.add(randomNo);
						i--;
						e.printStackTrace();
					}

//					} catch (NoDbRowException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//						cancel(true);
//					}
				}
				else
				{
					i--;
				}
			}
			return null;
		}

		public GenerateQuestions(String selection, String value) {
			super();
			this.selection = selection;
			this.value = value;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			db.closeReadableDatabase();
			db.close();
			mMain.removeAllViews();
			mMain.addView(mView);
			TextViewPlus selection = (TextViewPlus)findViewById(R.id.play_selection);
			selection.setText(mDisplatMsg);
		}

		@Override
		protected void onProgressUpdate(final Integer... values) {
			super.onProgressUpdate(values);
			mProgressBar.setProgress(values[0]);

			runOnUiThread(new Runnable() {
				public void run() {
					mLoadingText.setText("Loading question " + values[0] + " of " + QUESTION_COUNT);
				}
			});
		}
	}

}
