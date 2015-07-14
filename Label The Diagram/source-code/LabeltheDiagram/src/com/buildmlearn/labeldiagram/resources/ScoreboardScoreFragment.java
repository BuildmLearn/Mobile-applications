package com.buildmlearn.labeldiagram.resources;

import com.buildmlearn.labeldiagram.database.DBAdapter;
import com.example.labelthediagram.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ScoreboardScoreFragment extends Fragment {
	
	DBAdapter diagramDb;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        openDB();
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.scoreboard_fragment_layout, container, false);
        TextView score = (TextView) v.findViewById(R.id.resultScore);
        TextView scoreTxt = (TextView) v.findViewById(R.id.resultScoreTxt);
        
        
        
        return v;
    }

    private void openDB() {
		diagramDb = new DBAdapter(getActivity());
		diagramDb.open();
	}

	private void closeDB() {
		diagramDb.close();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		closeDB();
	}
}
