package com.buildmlearn.labeldiagram.helper;

import java.util.HashMap;

import com.buildmlearn.labeldiagram.DiagramPlayBacteria;
import com.buildmlearn.labeldiagram.DiagramPlayCarbonCycle;
import com.buildmlearn.labeldiagram.DiagramPlayHumanEar;
import com.buildmlearn.labeldiagram.DiagramPlayHumanEye;
import com.buildmlearn.labeldiagram.DiagramPlayHumanHeart;
import com.buildmlearn.labeldiagram.DiagramPlayPlantCell;
import com.buildmlearn.labeldiagram.DiagramPlayPlantFlower;
import com.buildmlearn.labeldiagram.DiagramPlayVirus;
import com.buildmlearn.labeldiagram.DiagramPlayWaterCycle;

public class ClassMapper {
	
	private HashMap<String, Class> map;
	private static ClassMapper classmapper;
	
	private ClassMapper(){
		map = new HashMap<String, Class>();
		map.put("Human Eye",DiagramPlayHumanEye.class);
		map.put("Human Ear", DiagramPlayHumanEar.class);
		map.put("Human Heart", DiagramPlayHumanHeart.class);
		map.put("Bacteria", DiagramPlayBacteria.class);
		map.put("Virus", DiagramPlayVirus.class);
		map.put("Plant Cell", DiagramPlayPlantCell.class);
		map.put("Plant Flower", DiagramPlayPlantFlower.class);
		map.put("Water Cycle", DiagramPlayWaterCycle.class);
		map.put("Carbon Cycle", DiagramPlayCarbonCycle.class);
	}

	public static ClassMapper getInstance(){
		
		if(classmapper == null){
			classmapper = new ClassMapper();
		}
		return classmapper;
		
	}
	
	public HashMap<String, Class> getMap() {
		return map;
	}

	public void setMap(HashMap<String, Class> map) {
		this.map = map;
	}
	
	
}
