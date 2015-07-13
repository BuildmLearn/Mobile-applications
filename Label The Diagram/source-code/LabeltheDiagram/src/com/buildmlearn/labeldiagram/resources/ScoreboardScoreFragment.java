package com.buildmlearn.labeldiagram.resources;

import com.example.labelthediagram.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ScoreboardScoreFragment extends Fragment {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.scoreboard_fragment_layout, container, false);
        TextView score = (TextView) v.findViewById(R.id.resultScore);
        TextView scoreTxt = (TextView) v.findViewById(R.id.resultScoreTxt);
        
        return v;
    }

    @Override
    public void onDestroy() {
    	// TODO Auto-generated method stub
    	super.onDestroy();
    }
}
