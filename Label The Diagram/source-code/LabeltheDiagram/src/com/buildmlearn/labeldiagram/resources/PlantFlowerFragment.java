package com.buildmlearn.labeldiagram.resources;

import com.buildmlearn.labeldiagram.DiagramPlayPlantFlower;
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

public class PlantFlowerFragment extends Fragment {

	private Typeface tfThin;
	private float score;

	
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

   		final View view = inflater.inflate(R.layout.plant_flower_view, container,
   				false);

   		TextView diagramTxt=(TextView) view.findViewById(R.id.txt_diagram);
   		TextView savedScoreTxt=(TextView) view.findViewById(R.id.score_saved);
   		
           Button startBtn=(Button) view.findViewById(R.id.go_diagram_btn);
           
           diagramTxt.setTypeface(tfThin);
           startBtn.setTypeface(tfThin);
           savedScoreTxt.setText((int)score+"% Sucess");	
           
           startBtn.setOnClickListener(new OnClickListener() {
   			
   			@Override
   			public void onClick(View v) {

   				Intent flowerIntent = new Intent(getActivity(), DiagramPlayPlantFlower.class);
   				flowerIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
   				flowerIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
   				flowerIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
   				//getActivity().finish();
   				startActivity(flowerIntent);
   			
   				
   			}
   		});

   		return view;
   	}


}
