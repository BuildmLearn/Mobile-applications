package com.buildmlearn.labeldiagram.lessons;

import com.buildmlearn.labeldiagram.database.Database;
import com.example.labelthediagram.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class LessonHumanEye extends Activity{
	
	private Database db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.lesson_ear);
		
		Database db = new Database(this);
		String result = db.getLesson("HumanEye");
		
		if(result != null){
			Toast.makeText(this, result,Toast.LENGTH_LONG).show();
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		db.close();
	}

}
