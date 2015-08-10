package com.buildmlearn.labeldiagram.badges;

import com.example.labelthediagram.R;

import android.os.Bundle;

public class AchievementPopupWindow extends BadgePopUpWindow {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(R.style.DialogWithDimmedBack);
		super.onCreate(savedInstanceState);
		
	}

}
