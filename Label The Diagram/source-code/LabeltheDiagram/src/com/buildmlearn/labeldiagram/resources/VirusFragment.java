package com.buildmlearn.labeldiagram.resources;

import com.buildmlearn.labeldiagram.DiagramPlayVirus;
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

public class VirusFragment extends Fragment {
	
	private Typeface tfThin;
    private Typeface tfLight;
    private float score;
    
 // newInstance constructor for creating fragment with arguments
    public static Fragment getInstance(int id) {
    	VirusFragment frag = new VirusFragment();
    	//insert data to passs
        //Bundle args = new Bundle();
        //args.putInt("diagram_id", id);
        //frag.setArguments(args);
        return frag;
    }
    
 // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //id = getArguments().getInt("diagram_id", 0);
        tfLight = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Light.ttf");
        tfThin = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Thin.ttf");
        
        if(getArguments()!=null){
        	 score = getArguments().getFloat("SCORE_SAVED");
        }
             
    }
    
    @Override
   	public View onCreateView(LayoutInflater inflater, ViewGroup container,
   			Bundle savedInstanceState) {

   		final View view = inflater.inflate(R.layout.virus_view, container,
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

   				Intent eyeIntent = new Intent(getActivity(), DiagramPlayVirus.class);
   				eyeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
   				eyeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
   				eyeIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
   				//getActivity().finish();
   				startActivity(eyeIntent);
   			
   				
   			}
   		});

   		return view;
   	}

}
