package com.buildmlearn.labeldiagram.badges;

import com.buildmlearn.labeldiagram.DiagramResult;
import com.buildmlearn.labeldiagram.database.Database;
import com.buildmlearn.labeldiagram.entity.Badge;
import com.example.labelthediagram.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class BadgePopUpWindow extends Activity implements OnClickListener {

	Database database;
	ImageView cancelView;
	ImageView badgeIcon;
	TextView badgeTitle;
	TextView badgeDescription;
	Badge badgeObj;
	String badgeName;
	String source;
	boolean achievedBestScore;
	boolean isCompleted;
	boolean isMasterBadge;
	float score;
	int gameScore;
	int badgeId;
	int tryCycle;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		init(savedInstanceState);
		initViews();
		loadDatabase();
		getBadgeData();
		processBadgeData();

	}

	private void init(Bundle savedInstanceState) {
		
		getIntentData();
		setCustomTheme();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.popup_window_view);
		adjustDimOpacity();
		
	}

	private void setCustomTheme() {
		if(!source.equals("Adapter")){
			setTheme(R.style.DialogWithDimmedBack);
		}
	}

	private void adjustDimOpacity() {
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.dimAmount = 0.8f;
		getWindow().setAttributes(lp);
	}

	private void getIntentData() {
		badgeName = getIntent().getExtras().getString("BADGE_TITLE");
		badgeId = getIntent().getExtras().getInt("BADGE_ID");
		source = getIntent().getExtras().getString("SOURCE");
		score = getIntent().getExtras().getFloat("SCORE");
		gameScore = getIntent().getExtras().getInt("GAME_SCORE");
		achievedBestScore = getIntent().getExtras().getBoolean("BEST_SCORE");
		isCompleted = getIntent().getExtras().getBoolean("COMPLETED");
		isMasterBadge = getIntent().getExtras().getBoolean("MASTER_BADGE");
		tryCycle = getIntent().getExtras().getInt("TRY_CYCLE");
	}

	private void initViews() {
		cancelView = (ImageView) findViewById(R.id.cancel_btn);
		badgeTitle = (TextView) findViewById(R.id.badge_txt);
		badgeDescription = (TextView) findViewById(R.id.badge_desc);
		badgeIcon = (ImageView) findViewById(R.id.badge_icon);
		cancelView.setOnClickListener(this);
	}

	private void loadDatabase() {
		database = new Database(this);
	}

	private Badge getBadgeData() {
		
		badgeObj = new Badge();
		badgeObj = database.getBadge(badgeName);
		return badgeObj;
		
	}
	
	private void processBadgeData(){
		
		if(badgeObj != null){
			badgeTitle.setText(badgeObj.getTitle());
			badgeDescription.setText(badgeObj.getDescription());
			badgeIcon.setImageResource(badgeId);
		}
		
	}
	
	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.cancel_btn:
			
			if(!source.equals("Adapter")){
				
				if(isCompleted == true){
					
					boolean completed = false;
					badgeName = getResources().getString(R.string.badge_champion);
					badgeId =  R.drawable.champion;
					
					Intent intent = new Intent(getBaseContext(), BadgePopUpWindow.class);
					intent.putExtra("BADGE_TITLE", badgeName);
					intent.putExtra("BADGE_ID", badgeId);
					intent.putExtra("SCORE", score);
					intent.putExtra("GAME_SCORE", gameScore);
					intent.putExtra("SOURCE", source);
					intent.putExtra("BEST_SCORE", achievedBestScore);
					intent.putExtra("COMPLETED", completed);
					intent.putExtra("TRY_CYCLE", tryCycle);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
					finish();
					
				}else{
					Intent intent = new Intent(getBaseContext(), DiagramResult.class);
					intent.putExtra("SCORE", score);
					intent.putExtra("GAME_SCORE", gameScore);
					intent.putExtra("SOURCE", source);
					intent.putExtra("BEST_SCORE", achievedBestScore);
					intent.putExtra("TRY_CYCLE", tryCycle);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
					finish();
				}
			}else{
				finish();
			}
			
			
			break;
		default:
			break;
		}

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		database.close();
	}

}
