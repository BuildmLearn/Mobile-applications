package com.buildmlearn.labeldiagram.resources;

import com.buildmlearn.labeldiagram.DiagramPlayPlantCell;
import com.example.labelthediagram.R;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class PlantCellFragment extends Fragment {
	
	private static final int GAME_SCORE = 100;
	private Typeface tfThin;
    private float score;
    
    
 // newInstance constructor for creating fragment with arguments
    public static Fragment getInstance(int id) {
    	
    	PlantCellFragment frag = new PlantCellFragment();
        return frag;
        
    }
    
 // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tfThin = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Thin.ttf");
        
        if(getArguments()!=null){
        	 score = getArguments().getFloat("SCORE_SAVED");
        }
       
        
    }
    
    @Override
   	public View onCreateView(LayoutInflater inflater, ViewGroup container,
   			Bundle savedInstanceState) {

   		final View view = inflater.inflate(R.layout.plant_cell_view, container,
   				false);

   		TextView diagramTxt=(TextView) view.findViewById(R.id.txt_diagram);
   		TextView savedScoreTxt=(TextView) view.findViewById(R.id.score_saved);
   		
           Button startBtn=(Button) view.findViewById(R.id.go_diagram_btn);
           
           diagramTxt.setTypeface(tfThin);
           startBtn.setTypeface(tfThin);
           savedScoreTxt.setText((int)((score / GAME_SCORE ) * 100) + "% Sucess");	
           
           startBtn.setOnClickListener(new OnClickListener() {
   			
   			@Override
   			public void onClick(View v) {

   				Intent cellIntent = new Intent(getActivity(), DiagramPlayPlantCell.class);
   				cellIntent.putExtra("SOURCE", "Fragment");
   				cellIntent.putExtra("TRY_CYCLE", 0);
   				cellIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
   				cellIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
   				cellIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
   				//getActivity().finish();
   				startActivity(cellIntent);
   			
   				
   			}
   		});

   		return view;
   	}

}
