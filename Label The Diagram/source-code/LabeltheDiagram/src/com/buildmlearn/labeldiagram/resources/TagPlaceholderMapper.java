package com.buildmlearn.labeldiagram.resources;

import com.example.labelthediagram.R;
import android.util.SparseIntArray;

/**
 * This class is to map dragging textView tags to relevant 
 * imageView place holders so that to identify that the user
 * has correctly labeled the diagram in DiagramPlay activity
 */
public class TagPlaceholderMapper {
	
	private SparseIntArray tagMapper;
		
	public TagPlaceholderMapper(){
		
		tagMapper = new SparseIntArray();
		
	}
	
	// Call the correct method according to the diagram name
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
	
	/*
	 * Map correct draggable Tags(TextViews) to droppable place holders(ImageViews
	 * in HumanEye diagram
	 */
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

	/*
	 * Map correct draggable Tags(TextViews) to droppable place holders(ImageViews
	 * in HumanHeart diagram
	 */
	private void getHumanHeartTagMap() {
		// TODO Here goes the tag and place holder mapping of Human heart
		
	}

	/*
	 * Map correct draggable Tags(TextViews) to droppable place holders(ImageViews
	 * in HumanEar diagram
	 */
	private void getHumanEarTagMap() {
		// TODO Here goes the tag and place holder mapping of Human Ear
		
	}

	

}
