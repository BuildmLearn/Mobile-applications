package com.buildmlearn.labeldiagram.helper;

import java.util.HashMap;

import com.buildmlearn.labeldiagram.DiagramPlayBacteria;
import com.buildmlearn.labeldiagram.DiagramPlayCarbonCycle;
import com.buildmlearn.labeldiagram.DiagramPlayHumanEar;
import com.buildmlearn.labeldiagram.DiagramPlayHumanEye;
import com.buildmlearn.labeldiagram.DiagramPlayHumanHeart;
import com.buildmlearn.labeldiagram.DiagramPlayPlantCell;
import com.buildmlearn.labeldiagram.DiagramPlayPlantFlower;
import com.buildmlearn.labeldiagram.DiagramPlayPrism;
import com.buildmlearn.labeldiagram.DiagramPlayVirus;
import com.buildmlearn.labeldiagram.DiagramPlayWaterCycle;

public class ClassMapper {
	
	private HashMap<String, Class<?>> classMap;
	private HashMap<String, String> tagMap;
	private static ClassMapper classmapper;
	
	private ClassMapper(){
		classMap = new HashMap<String, Class<?>>();
		tagMap = new HashMap<String, String>();
		createClassMap();
		//createTagMap();
	}

	private void createTagMap() {
		tagMap.put("Human Eye", "HumanEye");
		tagMap.put("Human Ear", "HumanEar");
		tagMap.put("Human Heart", "HumanHeart");
		tagMap.put("Bacteria", "Bacteria");
		tagMap.put("Virus", "Virus");
		tagMap.put("Plant Cell", "PlantCell");
		tagMap.put("Plant Flower", "PlantFlower");
		tagMap.put("Water Cycle", "WaterCycle");
		tagMap.put("Carbon Cycle", "CarbonCycle");
		tagMap.put("Light Spectrum", "LightSpectrum");
		tagMap.put("Prism Refraction", "HumanEye");
		tagMap.put("Lens Refraction", "HumanEye");
		
	}

	/**
	 * 
	 */
	private void createClassMap() {
		classMap.put("HumanEye",DiagramPlayHumanEye.class);
		classMap.put("HumanEar", DiagramPlayHumanEar.class);
		classMap.put("HumanHeart", DiagramPlayHumanHeart.class);
		classMap.put("Bacteria", DiagramPlayBacteria.class);
		classMap.put("Virus", DiagramPlayVirus.class);
		classMap.put("PlantCell", DiagramPlayPlantCell.class);
		classMap.put("PlantFlower", DiagramPlayPlantFlower.class);
		classMap.put("WaterCycle", DiagramPlayWaterCycle.class);
		classMap.put("CarbonCycle", DiagramPlayCarbonCycle.class);
		classMap.put("Prism", DiagramPlayPrism.class);
	}

	public static ClassMapper getInstance(){
		
		if(classmapper == null){
			classmapper = new ClassMapper();
		}
		return classmapper;
		
	}
	
	public HashMap<String, Class<?>> getMap() {
		return classMap;
	}

	public void setMap(HashMap<String, Class<?>> map) {
		this.classMap = map;
	}
	
	
}
