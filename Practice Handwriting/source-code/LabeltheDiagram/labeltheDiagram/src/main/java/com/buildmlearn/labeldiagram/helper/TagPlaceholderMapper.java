package com.buildmlearn.labeldiagram.helper;

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
			case "PlantCell":
				getPlantCellTagMap();
				break;
			case "PlantFlower":
				getPlantFlowerTagMap();
				break;
			case "Bacteria":
				getBacteriaTagMap();
				break;
			case "Virus":
				getVirusTagMap();
				break;
			case "WaterCycle":
				getWaterCycleTagMap();
				break;
			case "CarbonCycle":
				getCarbonCycleTagMap();
				break;
			case "Prism":
				getPrismTagMap();
				break;
			case "Lens":
				getLensTagMap();
				break;
			case "Motor":
				getMotorTagMap();
				break;
			case "DryCell":
				getDryCellTagMap();
				break;
			case "Circuit":
				getCircuitTagMap();
				break;
			case "Spectrum":
				getSpectrumTagMap();
				break;
			case "SolarSystem":
				getSolarSystemTagMap();
				break;
			case "StarPatterns":
				getStarPatternsTagMap();
				break;
			
			}
		}
		
		return tagMapper;
	}
	
	private SparseIntArray getStarPatternsTagMap() {
		
		tagMapper.put(R.id.libraBlb, R.id.libraTag);
		tagMapper.put(R.id.little_dripperBlb, R.id.little_dripperTag);
		tagMapper.put(R.id.big_dripperBlb, R.id.big_dripperTag);
		tagMapper.put(R.id.scorpioBlb, R.id.scorpioTag);
		tagMapper.put(R.id.orianBlb, R.id.orianTag);
		tagMapper.put(R.id.leoBlb, R.id.leoTag);
		tagMapper.put(R.id.geminiBlb, R.id.geminiTag);
		tagMapper.put(R.id.dracoBlb, R.id.dracoTag);
		
		return tagMapper;
		
	}

	private SparseIntArray getSolarSystemTagMap() {
		
		tagMapper.put(R.id.sunBlb, R.id.sunTag);
		tagMapper.put(R.id.mercuryBlb, R.id.mercuryTag);
		tagMapper.put(R.id.venusBlb, R.id.venusTag);
		tagMapper.put(R.id.earthBlb, R.id.earthTag);
		tagMapper.put(R.id.marsBlb, R.id.marsTag);
		tagMapper.put(R.id.jupiterBlb, R.id.jupiterTag);
		tagMapper.put(R.id.saturnBlb, R.id.saturnTag);
		tagMapper.put(R.id.uranusBlb, R.id.uranusTag);
		tagMapper.put(R.id.neptuneBlb, R.id.neptuneTag);
		
		return tagMapper;
		
	}

	private SparseIntArray getSpectrumTagMap() {

		tagMapper.put(R.id.amBlb, R.id.amTag);
		tagMapper.put(R.id.fmBlb, R.id.fmTag);
		tagMapper.put(R.id.microwaveBlb, R.id.microwaveTag);
		tagMapper.put(R.id.visibleLightBlb, R.id.visibleLightTag);
		tagMapper.put(R.id.radio_wavesBlb, R.id.radio_wavesTag);
		tagMapper.put(R.id.irBlb, R.id.irTag);
		tagMapper.put(R.id.uvBlb, R.id.uvTag);
		tagMapper.put(R.id.xrayBlb, R.id.xrayTag);
		tagMapper.put(R.id.gammaBlb, R.id.gammaTag);
		
		return tagMapper;
		
	}

	private SparseIntArray getCircuitTagMap() {
		
		tagMapper.put(R.id.capacitorBlb, R.id.capacitorTag);
		tagMapper.put(R.id.batteryBlb, R.id.batteryTag);
		tagMapper.put(R.id.currentBlb, R.id.currentTag);
		tagMapper.put(R.id.resistorBlb, R.id.resistorTag);
		tagMapper.put(R.id.switchBlb, R.id.switchTag);
		tagMapper.put(R.id.ameterBlb, R.id.ameterTag);
		tagMapper.put(R.id.loadBlb, R.id.loadTag);
		tagMapper.put(R.id.voltmeterBlb, R.id.voltmeterTag);
		tagMapper.put(R.id.bulbBlb, R.id.bulbTag);
		
		return tagMapper;
		
	}

	private SparseIntArray getDryCellTagMap() {
		
		tagMapper.put(R.id.posTerminalBlb, R.id.posTerminalTag);
		tagMapper.put(R.id.negTerminalBlb, R.id.negTerminalTag);
		tagMapper.put(R.id.chemPasteBlb, R.id.chemPasteTag);
		tagMapper.put(R.id.anodeBlb, R.id.anodeTag);
		tagMapper.put(R.id.cathodeBlb, R.id.cathodeTag);
		tagMapper.put(R.id.airSpaceBlb, R.id.airSpaceTag);
		tagMapper.put(R.id.sealBlb, R.id.sealTag);
		tagMapper.put(R.id.mJacketBlb, R.id.mJacketTag);
		tagMapper.put(R.id.insulatorBlb, R.id.insulatorTag);
		
		return tagMapper;
		
	}

	private SparseIntArray getMotorTagMap() {
		
		tagMapper.put(R.id.northPoleBlb, R.id.northPoleTag);
		tagMapper.put(R.id.southPoleBlb, R.id.southPoleTag);
		tagMapper.put(R.id.magFieldBlb, R.id.magFieldTag);
		tagMapper.put(R.id.forceBlb, R.id.forceTag);
		tagMapper.put(R.id.axelBlb, R.id.axelTag);
		tagMapper.put(R.id.batteryBlb, R.id.batteryTag);
		tagMapper.put(R.id.commutatorBlb, R.id.commutatorTag);
		tagMapper.put(R.id.brushBlb, R.id.brushTag);
		tagMapper.put(R.id.armatureBlb, R.id.armatureTag);
		
		return tagMapper;
		
	}

	private SparseIntArray getLensTagMap() {
		
		tagMapper.put(R.id.principalRayBlb, R.id.principalRayTag);
		tagMapper.put(R.id.centralRayBlb, R.id.centralRayTag);
		tagMapper.put(R.id.focalRayBlb, R.id.focalRayTag);
		tagMapper.put(R.id.focusBlb, R.id.focusTag);
		tagMapper.put(R.id.objectBlb, R.id.objectTag);
		tagMapper.put(R.id.imageBlb, R.id.imageTag);
		tagMapper.put(R.id.lensBlb, R.id.lensTag);
		tagMapper.put(R.id.lensAxisBlb, R.id.lensAxisTag);
		
		return tagMapper;
	}

	private SparseIntArray getPrismTagMap() {

		tagMapper.put(R.id.prismAngleBlb, R.id.prismAngleTag);
		tagMapper.put(R.id.refAngleBlb, R.id.refAngleTag);
		tagMapper.put(R.id.incAngleBlb, R.id.incAngleTag);
		tagMapper.put(R.id.incRayBlb, R.id.incRayTag);
		tagMapper.put(R.id.devAngleBlb, R.id.devAngleTag);
		tagMapper.put(R.id.refRayBlb, R.id.refRayTag);
		tagMapper.put(R.id.emergeAngleBlb, R.id.emergeAngleTag);
		tagMapper.put(R.id.emergeRayBlb, R.id.emergeRayTag);
		
		return tagMapper;
	}

	private SparseIntArray getCarbonCycleTagMap() {
		
		tagMapper.put(R.id.ocean_uptakeBlb, R.id.ocean_uptakeTag);
		tagMapper.put(R.id.plant_respirationBlb, R.id.plant_respirationTag);
		tagMapper.put(R.id.animal_respirationBlb, R.id.animal_respirationTag);
		tagMapper.put(R.id.dead_organismsBlb, R.id.dead_organismsTag);
		tagMapper.put(R.id.fossilsBlb, R.id.fossilsTag);
		tagMapper.put(R.id.sunlightBlb, R.id.sunlightTag);
		tagMapper.put(R.id.organic_carbonBlb, R.id.organic_carbonTag);
		tagMapper.put(R.id.vehicle_emissionsBlb, R.id.vehicle_emissionsTag);
		tagMapper.put(R.id.photosynthesisBlb, R.id.photosynthesisTag);
		
		return tagMapper;
	}

	/*
	 * Map correct draggable Tags(TextViews) to droppable place holders(ImageViews
	 * in Water Cycle diagram
	 */
	private SparseIntArray getWaterCycleTagMap() {
		
		tagMapper.put(R.id.oceanBlb, R.id.oceanTag);
		tagMapper.put(R.id.precipitationBlb, R.id.preciptationTag);
		tagMapper.put(R.id.infiltrationBlb, R.id.infiltratoinTag);
		tagMapper.put(R.id.condensationBlb, R.id.condensationTag);
		tagMapper.put(R.id.eveporationBlb, R.id.eveporationTag);
		tagMapper.put(R.id.transpirationBlb, R.id.transpirationTag);
		tagMapper.put(R.id.underground_waterBlb, R.id.underground_waterTag);
		tagMapper.put(R.id.surface_runBlb, R.id.surface_runTag);
		
		return tagMapper;
		
	}

	/*
	 * Map correct draggable Tags(TextViews) to droppable place holders(ImageViews
	 * in Virus diagram
	 */
	private SparseIntArray getVirusTagMap() {
		
		tagMapper.put(R.id.headBlb, R.id.headTag);
		tagMapper.put(R.id.collarBlb, R.id.collarTag);
		tagMapper.put(R.id.neckBlb, R.id.neckTag);
		tagMapper.put(R.id.sheathBlb, R.id.sheathTag);
		tagMapper.put(R.id.tailfibreBlb, R.id.tailfibreTag);
		tagMapper.put(R.id.tailpinsBlb, R.id.tailpinsTag);
		tagMapper.put(R.id.baseplateBlb, R.id.baseplateTag);
		tagMapper.put(R.id.dnaBlb, R.id.dnaTag);
		
		return tagMapper;
		
	}

	/*
	 * Map correct draggable Tags(TextViews) to droppable place holders(ImageViews
	 * in Bacteria diagram
	 */
	private SparseIntArray getBacteriaTagMap() {

		tagMapper.put(R.id.chromosomeBlb, R.id.chromosomeTag);
		tagMapper.put(R.id.ribosomesBlb, R.id.ribosomesTag);
		tagMapper.put(R.id.food_granuleBlb, R.id.food_granuleTag);
		tagMapper.put(R.id.piliBlb, R.id.piliTag);
		tagMapper.put(R.id.flagellumBlb, R.id.flagellumTag);
		tagMapper.put(R.id.plasmidBlb, R.id.plasmidTag);
		tagMapper.put(R.id.capsuleBlb, R.id.capsuleTag);
		tagMapper.put(R.id.cellwallBlb, R.id.cellwallTag);
		tagMapper.put(R.id.cytoplasmBlb, R.id.cytoplasmTag);
		tagMapper.put(R.id.plasma_membraneBlb, R.id.plasma_membraneTag);
		
		return tagMapper;

		
	}

	/*
	 * Map correct draggable Tags(TextViews) to droppable place holders(ImageViews
	 * in PlantFlower diagram
	 */
	private SparseIntArray getPlantFlowerTagMap() {

		tagMapper.put(R.id.antherBlb, R.id.antherTag);
		tagMapper.put(R.id.petalBlb, R.id.petalTag);
		tagMapper.put(R.id.receptacleBlb, R.id.receptacleTag);
		tagMapper.put(R.id.pedicelBlb, R.id.pedicelTag);
		tagMapper.put(R.id.stigmaBlb, R.id.stigmaTag);
		tagMapper.put(R.id.styleBlb, R.id.styleTag);
		tagMapper.put(R.id.overyBlb, R.id.overyTag);
		tagMapper.put(R.id.filamentBlb, R.id.filamentTag);
		tagMapper.put(R.id.sepalBlb, R.id.sepalTag);
		tagMapper.put(R.id.ovuleBlb, R.id.ovuleTag);
		
		return tagMapper;
		
	}

	/*
	 * Map correct draggable Tags(TextViews) to droppable place holders(ImageViews
	 * in PlantCell diagram
	 */
	private SparseIntArray getPlantCellTagMap() {

		tagMapper.put(R.id.ribosomesBlb, R.id.ribosomeTag);
		tagMapper.put(R.id.smootherBlb, R.id.smootherTag);
		tagMapper.put(R.id.rougherBlb, R.id.rougherTag);
		tagMapper.put(R.id.nucleusBlb, R.id.nucleusTag);
		tagMapper.put(R.id.vacuoleBlb, R.id.vacuoleTag);
		tagMapper.put(R.id.cellwallBlb, R.id.cellwallTag);
		tagMapper.put(R.id.cell_membraneBlb, R.id.cell_membraneTag);
		tagMapper.put(R.id.mitochondriumBlb, R.id.mitochondiumTag);
		tagMapper.put(R.id.chloroplastBlb, R.id.chloroplastTag);
		tagMapper.put(R.id.golgi_complexBlb, R.id.golgi_complexTag);
		
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
	private SparseIntArray getHumanHeartTagMap() {
		
		tagMapper.put(R.id.sup_vena_cavaBlb, R.id.sup_vena_cavaTag);
		tagMapper.put(R.id.right_atriumBlb, R.id.right_atriunTag);
		tagMapper.put(R.id.inf_vena_cavaBlb, R.id.inf_vena_cavaTag);
		tagMapper.put(R.id.right_ventricleBlb, R.id.right_ventricleTag);
		tagMapper.put(R.id.left_ventricleBlb, R.id.left_ventricleTag);
		tagMapper.put(R.id.left_atriumBlb, R.id.left_atriumTag);
		tagMapper.put(R.id.pul_arteryBulb, R.id.pul_arteryTag);
		tagMapper.put(R.id.aortaBlb, R.id.aortaTag);
		tagMapper.put(R.id.pul_veinBlb, R.id.pul_veinTag);
		
		return tagMapper;
	}

	/*
	 * Map correct draggable Tags(TextViews) to droppable place holders(ImageViews
	 * in HumanEar diagram
	 */
	private SparseIntArray getHumanEarTagMap() {

		tagMapper.put(R.id.pinnaBlb, R.id.pinnaTag);
		tagMapper.put(R.id.earcanalBlb, R.id.ear_canalTag);
		tagMapper.put(R.id.eardrumBlb, R.id.eardrumTag);
		tagMapper.put(R.id.cochleaBlb, R.id.cochleaTag);
		tagMapper.put(R.id.earnerveBlb, R.id.earnerveTag);
		tagMapper.put(R.id.malleusBlb, R.id.malleusTag);
		tagMapper.put(R.id.incusBlb, R.id.incusTag);
		tagMapper.put(R.id.outerearBlb, R.id.outerearTag);
		tagMapper.put(R.id.stapesBlb, R.id.stapesTag);
		tagMapper.put(R.id.canalsBlb, R.id.canalsTag);
		
		return tagMapper;
		
	}

	

}
