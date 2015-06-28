package org.buildmlearn.practicehandwriting.helper;

import java.util.ArrayList;

public class TimeTrialResult {
    private String mPracticeString;
    private ArrayList<ArrayList<Integer>> mTouches;

    public TimeTrialResult(String practiceString, ArrayList<ArrayList<Integer>> touches) {
        mPracticeString = practiceString;
        mTouches = touches;
    }

    public String getPracticeString() {
        return mPracticeString;
    }

    public ArrayList<ArrayList<Integer>> getTouches() {
        return mTouches;
    }
}
