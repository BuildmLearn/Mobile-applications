package com.buildmlearn.labeldiagram;

import com.example.labelthediagram.R;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class DiagramFragment extends Fragment {
    // Store instance variables
    private int id;
    private Typeface tfThin;
    private Typeface tfLight;
    

    // newInstance constructor for creating fragment with arguments
    public static Fragment getInstance(int id) {
    	DiagramFragment frag = new DiagramFragment();
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
        
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.diagram_fragment_view, container, false);
        //populate layout here
        TextView diagramTxt=(TextView) view.findViewById(R.id.txt_diagram);
        Button startBtn=(Button) view.findViewById(R.id.go_diagram_btn);
        diagramTxt.setTypeface(tfThin);
        startBtn.setTypeface(tfThin);
        return view;
    }
}