package com.buildmlearn.labeldiagram.lessons;

import java.lang.reflect.Type;

import com.buildmlearn.labeldiagram.database.Database;
import com.buildmlearn.labeldiagram.entity.Lesson;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.os.Bundle;

public abstract class LessonBase extends Activity{
	
	String diagramName;
	Database database;
	Lesson lessonObj;
	Gson gsonObj;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(getResourcesId());
		setDiagramName(getDiagramName());
		loadDatabase();
		getLessonData();
		
	}

	private Lesson getLessonData() {
		
		lessonObj = new Lesson();
		gsonObj = new Gson();
		String result = database.getLesson(diagramName);
		Type type = new TypeToken<Lesson>() {}.getType();
		lessonObj = gsonObj.fromJson(result, type);
		
		return lessonObj;
	}

	private void loadDatabase() {
		database = new Database(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		database.close();
	}
	
	public String getDiagramName() {
		return diagramName;
	}

	public void setDiagramName(String diagramName) {
		this.diagramName = diagramName;
	}
	
	protected abstract int getResourcesId();
}
