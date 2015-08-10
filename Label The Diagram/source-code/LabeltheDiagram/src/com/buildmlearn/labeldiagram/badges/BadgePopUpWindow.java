package com.buildmlearn.labeldiagram.badges;

import com.buildmlearn.labeldiagram.database.Database;
import com.buildmlearn.labeldiagram.entity.Badge;
import com.example.labelthediagram.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class BadgePopUpWindow extends Activity implements OnClickListener {

	Database database;
	ImageView cancelView;
	ImageView badgeIcon;
	TextView badgeTitle;
	TextView badgeDescription;
	String badgeName;
	int badgeId;
	Badge badgeObj;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.popup_window_view);
		getIntentData();
		initViews();
		loadDatabase();
		getBadgeData();
		processBadgeData();

	}

	private void getIntentData() {
		badgeName = getIntent().getExtras().getString("BADGE_TITLE");
		badgeId = getIntent().getExtras().getInt("BADGE_ID");
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
			finish();
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
