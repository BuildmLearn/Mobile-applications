package org.buildmlearn.practicehandwriting.helper;

import android.graphics.Point;

import java.util.ArrayList;

//Class to store the result of a trace in Time Trial mode
public class TimeTrialResult {
    private String mPracticeString; //The string that was practiced
    private ArrayList<ArrayList<Point>> mTouches;  //List of strokes. Each ArrayList<Point> is the touches from one MOTION_DOWN even to a MOTION_UP even

    public TimeTrialResult(String practiceString, ArrayList<ArrayList<Point>> touches) {
        mPracticeString = practiceString;
        mTouches = touches;
    }

    public String getPracticeString() {
        return mPracticeString;
    }

    public ArrayList<ArrayList<Point>> getTouches() {
        return mTouches;
    }
}
