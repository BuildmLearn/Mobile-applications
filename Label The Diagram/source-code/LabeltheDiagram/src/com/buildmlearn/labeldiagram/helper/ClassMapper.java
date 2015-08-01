package com.buildmlearn.labeldiagram.helper;

import java.util.HashMap;

import com.buildmlearn.labeldiagram.DiagramPlayBacteria;
import com.buildmlearn.labeldiagram.DiagramPlayCarbonCycle;
import com.buildmlearn.labeldiagram.DiagramPlayCircuit;
import com.buildmlearn.labeldiagram.DiagramPlayDryCell;
import com.buildmlearn.labeldiagram.DiagramPlayHumanEar;
import com.buildmlearn.labeldiagram.DiagramPlayHumanEye;
import com.buildmlearn.labeldiagram.DiagramPlayHumanHeart;
import com.buildmlearn.labeldiagram.DiagramPlayLens;
import com.buildmlearn.labeldiagram.DiagramPlayMotor;
import com.buildmlearn.labeldiagram.DiagramPlayPlantCell;
import com.buildmlearn.labeldiagram.DiagramPlayPlantFlower;
import com.buildmlearn.labeldiagram.DiagramPlayPrism;
import com.buildmlearn.labeldiagram.DiagramPlaySolarSystem;
import com.buildmlearn.labeldiagram.DiagramPlaySpectrum;
import com.buildmlearn.labeldiagram.DiagramPlayStarPatterns;
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
		createTagMap();
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
		tagMap.put("Light Spectrum", "Spectrum");
		tagMap.put("Prism Refraction", "Prism");
		tagMap.put("Lens Refraction", "Lens");
		tagMap.put("Electric Motor", "Motor");
		tagMap.put("Dry Cell", "DryCell");
		tagMap.put("Electric Circuit", "Circuit");
		tagMap.put("Solar System", "SolarSystem");
		tagMap.put("Star Patterns", "Star Patterns");
		
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
		classMap.put("Lens", DiagramPlayLens.class);
		classMap.put("Spectrum", DiagramPlaySpectrum.class);
		classMap.put("Motor", DiagramPlayMotor.class);
		classMap.put("DryCell", DiagramPlayDryCell.class);
		classMap.put("Circuit", DiagramPlayCircuit.class);
		classMap.put("SolarSystem", DiagramPlaySolarSystem.class);
		classMap.put("StarPatterns", DiagramPlayStarPatterns.class);
		
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
	
	
	public HashMap<String, String> getTagMap() {
		return tagMap;
	}

	public void setTagMap(HashMap<String, String> tagMap) {
		this.tagMap = tagMap;
	}

	
	
}
