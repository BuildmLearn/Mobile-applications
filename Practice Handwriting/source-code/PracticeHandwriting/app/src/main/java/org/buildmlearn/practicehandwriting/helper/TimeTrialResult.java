package org.buildmlearn.practicehandwriting.helper;

import android.graphics.Point;

import java.util.ArrayList;

public class TimeTrialResult {
    private String mPracticeString;
    private ArrayList<ArrayList<Point>> mTouches;

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
