package com.buildmlearn.labeldiagram.helper;

import java.util.ArrayList;
import java.util.List;

import com.example.labelthediagram.R;



/**
 * @author Akilaz
 * This helper class is for separating the place holders which are on
 * the left side and right side of a particular diagram in order to 
 * determine the tag dropping behavior in the DiagramPlay Activity
 */
public class PlaceHolderContainer {


	private Integer[] leftPlaceHolderList;
	private Integer[] rightPlaceHolderList;
	private List<Integer[]> listHolder;

	public PlaceHolderContainer() {
		
		this.leftPlaceHolderList=new Integer[]{};
		this.rightPlaceHolderList=new Integer[]{};
		this.listHolder=new ArrayList<Integer[]>();

	}

	// Switching the helper method according to the diagram name
	public List<Integer[]> diagramCaller(String diagramName) {
		if (!diagramName.equals(null)) {
			switch (diagramName) {

				case "HumanEye":
					getHumanEyeMarkerList();
					break;
				case "HumanEar":
					getHumanEarMarkerList();
					break;
				// TODO The other diagrams goes here

			}
			return listHolder;
		}else {
			return null;
		}
	}

	/*
	 * Return list of arrays containing left placeholder Ids and right placeholder Ids
	 * of HumanEar diagram
	 */
	private List<Integer[]> getHumanEarMarkerList() {
		// TODO Human Ear place holder list goes here
		return null;
	}

	/*
	 * Return list of arrays containing left placeholder Ids and right placeholder Ids
	 * of HumanEye diagram
	 */
	private List<Integer[]> getHumanEyeMarkerList() {

		leftPlaceHolderList = new Integer[] { 
				R.id.corneaBlb, R.id.ciliaryBodyBlb,
				R.id.irisBlb,R.id.pupilBlb,R.id.lensBlb,R.id.vitreousBlb
		};
		
		rightPlaceHolderList = new Integer[]{
				R.id.foveaBlb,R.id.retinaBlb,R.id.opticNerveBlb,R.id.blindSpotBlb
		};
		
		listHolder.add(leftPlaceHolderList);
		listHolder.add(rightPlaceHolderList);
		
		return listHolder;

	}

}
