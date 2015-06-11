package com.buildmlearn.labeldiagram.resources;

import java.util.HashMap;

import com.example.labelthediagram.R;

import android.util.SparseIntArray;

public class TagPlaceholderMapper {
	
	private SparseIntArray tagMapper;
	
	
	
	public TagPlaceholderMapper(){
		
		tagMapper = new SparseIntArray();
		
	}
	
	public SparseIntArray diagramMapper(String diagramName){
		
		if(!diagramName.equals(null)){
			switch(diagramName){
			
			case "HumanEye":
				getHumanEyeTagMap();
				break;
			case "HumanEar":
				getHumanEarTagMap();
				break;
			case "HumanHeart":
				getHumanHeartTagMap();
				break;
			
			}
		}
		
		return tagMapper;
	}
	
	private SparseIntArray getHumanEyeTagMap() {
		
		tagMapper.put(R.id.ciliaryBodyBlb, R.id.ciliaryTag);
		tagMapper.put(R.id.irisBlb, R.id.irisTag);
		tagMapper.put(R.id.pupilBlb, R.id.pupilTag);
		tagMapper.put(R.id.lensBlb, R.id.lensTag);
		tagMapper.put(R.id.corneaBlb, R.id.corneaTag);
		tagMapper.put(R.id.vitreousBlb, R.id.vitreousTag);
		tagMapper.put(R.id.opticNerveBlb, R.id.nerveTag);
		tagMapper.put(R.id.blindSpotBlb, R.id.opticdiskTag);
		tagMapper.put(R.id.foveaBlb, R.id.foveaTag);
		tagMapper.put(R.id.retinaBlb, R.id.retinaTag);
		
		return tagMapper;
		
	}

	private void getHumanHeartTagMap() {
		// TODO Here goes the tag and place holder mapping of Human heart
		
	}

	private void getHumanEarTagMap() {
		// TODO Here goes the tag and place holder mapping of Human Ear
		
	}

	

}
