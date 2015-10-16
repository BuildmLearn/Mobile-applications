package com.buildmlearn.labeldiagram.helper;

import java.util.ArrayList;
import java.util.List;

import com.example.labelthediagram.R;

/**
 * @author Akilaz This helper class is for separating the place holders which
 *         are on the left side and right side of a particular diagram in order
 *         to determine the tag dropping behavior in the DiagramPlay Activity
 */
public class PlaceHolderContainer {

	private Integer[] leftPlaceHolderList;
	private Integer[] rightPlaceHolderList;
	private List<Integer[]> listHolder;

	public PlaceHolderContainer() {

		this.leftPlaceHolderList = new Integer[] {};
		this.rightPlaceHolderList = new Integer[] {};
		this.listHolder = new ArrayList<Integer[]>();

	}

	// Switching the helper method according to the diagram name
	public List<Integer[]> diagramCaller(String diagramName) {
		if (!diagramName.equals(null)) {
			switch (diagramName) {

			case "HumanEye":
				getHumanEyeMarkerList();
				break;
			case "HumanHeart":
				getHumanHeartMarkerList();
				break;
			case "HumanEar":
				getHumanEarMarkerList();
				break;
			case "PlantCell":
				getPlantCellMarkerList();
				break;
			case "PlantFlower":
				getPlantFlowerMarkerList();
				break;
			case "Bacteria":
				getBacteriaMarkerList();
				break;
			case "Virus":
				getVirusMarkerList();
				break;
			case "WaterCycle":
				getWaterCycleMarkerList();
				break;
			case "CarbonCycle":
				getCarbonCycleMarkerList();
				break;
			case "Prism":
				getPrismMarkerList();
				break;
			case "Lens":
				getLensMarkerList();
				break;
			case "Motor":
				getMotorMarkerList();
				break;
			case "DryCell":
				getDryCellMarkerList();
				break;
			case "Circuit":
				getCircuitMarkerList();
				break;
			case "Spectrum":
				getSpectrumMarkerList();
				break;
			case "SolarSystem":
				getSolarSystemMarkerList();
				break;
			case "StarPatterns":
				getStarPatternsMarkerList();
				break;
			// TODO The other diagrams goes here

			}
			return listHolder;
		} else {
			return null;
		}
	}

	private List<Integer[]> getStarPatternsMarkerList() {

		leftPlaceHolderList = new Integer[] { R.id.libraBlb, R.id.orianBlb };

		rightPlaceHolderList = new Integer[] { R.id.scorpioBlb, R.id.leoBlb,
				R.id.little_dripperBlb, R.id.big_dripperBlb, R.id.geminiBlb,
				R.id.dracoBlb,

		};

		listHolder.add(leftPlaceHolderList);
		listHolder.add(rightPlaceHolderList);

		return listHolder;

	}

	private List<Integer[]> getSolarSystemMarkerList() {

		leftPlaceHolderList = new Integer[] { R.id.earthBlb, R.id.venusBlb };

		rightPlaceHolderList = new Integer[] { R.id.sunBlb, R.id.mercuryBlb,
				R.id.neptuneBlb, R.id.saturnBlb, R.id.jupiterBlb, R.id.marsBlb,
				R.id.uranusBlb

		};

		listHolder.add(leftPlaceHolderList);
		listHolder.add(rightPlaceHolderList);

		return listHolder;

	}

	private List<Integer[]> getSpectrumMarkerList() {

		leftPlaceHolderList = new Integer[] { R.id.radio_wavesBlb, R.id.irBlb

		};

		rightPlaceHolderList = new Integer[] { R.id.amBlb, R.id.fmBlb,
				R.id.uvBlb, R.id.xrayBlb, R.id.gammaBlb, R.id.visibleLightBlb,
				R.id.microwaveBlb

		};

		listHolder.add(leftPlaceHolderList);
		listHolder.add(rightPlaceHolderList);

		return listHolder;

	}

	private List<Integer[]> getCircuitMarkerList() {

		leftPlaceHolderList = new Integer[] { R.id.capacitorBlb,
				R.id.voltmeterBlb, R.id.loadBlb

		};

		rightPlaceHolderList = new Integer[] { R.id.batteryBlb,
				R.id.currentBlb, R.id.ameterBlb, R.id.resistorBlb,
				R.id.bulbBlb, R.id.switchBlb

		};

		listHolder.add(leftPlaceHolderList);
		listHolder.add(rightPlaceHolderList);

		return listHolder;

	}

	private List<Integer[]> getDryCellMarkerList() {

		leftPlaceHolderList = new Integer[] { R.id.posTerminalBlb,
				R.id.negTerminalBlb, R.id.airSpaceBlb, R.id.anodeBlb

		};

		rightPlaceHolderList = new Integer[] { R.id.chemPasteBlb,
				R.id.cathodeBlb, R.id.sealBlb, R.id.insulatorBlb,
				R.id.mJacketBlb

		};

		listHolder.add(leftPlaceHolderList);
		listHolder.add(rightPlaceHolderList);

		return listHolder;

	}

	private List<Integer[]> getMotorMarkerList() {

		leftPlaceHolderList = new Integer[] { R.id.northPoleBlb,
				R.id.southPoleBlb, R.id.magFieldBlb, R.id.forceBlb,
				R.id.axelBlb

		};

		rightPlaceHolderList = new Integer[] { R.id.brushBlb, R.id.batteryBlb,
				R.id.commutatorBlb, R.id.armatureBlb,

		};

		listHolder.add(leftPlaceHolderList);
		listHolder.add(rightPlaceHolderList);

		return listHolder;

	}

	private List<Integer[]> getLensMarkerList() {

		leftPlaceHolderList = new Integer[] { R.id.lensBlb, R.id.imageBlb

		};

		rightPlaceHolderList = new Integer[] { R.id.principalRayBlb,
				R.id.centralRayBlb, R.id.focalRayBlb, R.id.objectBlb,
				R.id.focusBlb, R.id.lensAxisBlb

		};

		listHolder.add(leftPlaceHolderList);
		listHolder.add(rightPlaceHolderList);

		return listHolder;
	}

	private List<Integer[]> getPrismMarkerList() {

		leftPlaceHolderList = new Integer[] { R.id.prismAngleBlb,
				R.id.refAngleBlb, R.id.emergeAngleBlb, R.id.emergeRayBlb

		};

		rightPlaceHolderList = new Integer[] { R.id.incAngleBlb,
				R.id.incRayBlb, R.id.devAngleBlb, R.id.refRayBlb

		};

		listHolder.add(leftPlaceHolderList);
		listHolder.add(rightPlaceHolderList);

		return listHolder;

	}

	private List<Integer[]> getCarbonCycleMarkerList() {

		leftPlaceHolderList = new Integer[] { R.id.organic_carbonBlb,
				R.id.dead_organismsBlb, R.id.fossilsBlb

		};

		rightPlaceHolderList = new Integer[] { R.id.sunlightBlb,
				R.id.vehicle_emissionsBlb, R.id.plant_respirationBlb,
				R.id.animal_respirationBlb, R.id.ocean_uptakeBlb,
				R.id.photosynthesisBlb

		};

		listHolder.add(leftPlaceHolderList);
		listHolder.add(rightPlaceHolderList);

		return listHolder;

	}

	private List<Integer[]> getWaterCycleMarkerList() {

		leftPlaceHolderList = new Integer[] { R.id.oceanBlb,
				R.id.infiltrationBlb, R.id.precipitationBlb,
				R.id.surface_runBlb, R.id.condensationBlb,
				R.id.transpirationBlb };

		rightPlaceHolderList = new Integer[] { R.id.eveporationBlb,
				R.id.underground_waterBlb };

		listHolder.add(leftPlaceHolderList);
		listHolder.add(rightPlaceHolderList);

		return listHolder;
	}

	private List<Integer[]> getVirusMarkerList() {

		leftPlaceHolderList = new Integer[] { R.id.headBlb, R.id.collarBlb,
				R.id.sheathBlb, R.id.tailpinsBlb };

		rightPlaceHolderList = new Integer[] { R.id.tailfibreBlb,
				R.id.baseplateBlb, R.id.dnaBlb, R.id.neckBlb };

		listHolder.add(leftPlaceHolderList);
		listHolder.add(rightPlaceHolderList);

		return listHolder;
	}

	private List<Integer[]> getBacteriaMarkerList() {

		leftPlaceHolderList = new Integer[] { R.id.piliBlb,
				R.id.food_granuleBlb, R.id.ribosomesBlb, R.id.chromosomeBlb };

		rightPlaceHolderList = new Integer[] { R.id.flagellumBlb,
				R.id.plasmidBlb, R.id.cytoplasmBlb, R.id.plasma_membraneBlb,
				R.id.cellwallBlb, R.id.capsuleBlb };

		listHolder.add(leftPlaceHolderList);
		listHolder.add(rightPlaceHolderList);

		return listHolder;
	}

	private List<Integer[]> getPlantFlowerMarkerList() {

		leftPlaceHolderList = new Integer[] { R.id.stigmaBlb, R.id.antherBlb,
				R.id.petalBlb, R.id.receptacleBlb, R.id.pedicelBlb };

		rightPlaceHolderList = new Integer[] { R.id.styleBlb, R.id.overyBlb,
				R.id.filamentBlb, R.id.sepalBlb, R.id.ovuleBlb };

		listHolder.add(leftPlaceHolderList);
		listHolder.add(rightPlaceHolderList);

		return listHolder;

	}

	private List<Integer[]> getPlantCellMarkerList() {

		leftPlaceHolderList = new Integer[] { R.id.ribosomesBlb,
				R.id.smootherBlb, R.id.rougherBlb, R.id.nucleusBlb,
				R.id.golgi_complexBlb };

		rightPlaceHolderList = new Integer[] { R.id.vacuoleTag,
				R.id.cellwallTag, R.id.cell_membraneTag, R.id.mitochondiumTag,
				R.id.chloroplastTag };

		listHolder.add(leftPlaceHolderList);
		listHolder.add(rightPlaceHolderList);

		return listHolder;
	}

	private List<Integer[]> getHumanHeartMarkerList() {

		leftPlaceHolderList = new Integer[] { R.id.sup_vena_cavaBlb,
				R.id.right_atriumBlb, R.id.inf_vena_cavaBlb };

		rightPlaceHolderList = new Integer[] { R.id.right_ventricleBlb,
				R.id.left_ventricleBlb, R.id.left_atriumBlb,
				R.id.pul_arteryBulb, R.id.aortaBlb, R.id.pul_veinBlb, };

		listHolder.add(leftPlaceHolderList);
		listHolder.add(rightPlaceHolderList);

		return listHolder;

	}

	/*
	 * Return list of arrays containing left placeholder Ids and right
	 * placeholder Ids of HumanEar diagram
	 */
	private List<Integer[]> getHumanEarMarkerList() {

		leftPlaceHolderList = new Integer[] { R.id.pinnaBlb, R.id.malleusBlb,
				R.id.outerearBlb };

		rightPlaceHolderList = new Integer[] { R.id.earcanalBlb,
				R.id.eardrumBlb, R.id.cochleaBlb, R.id.earnerveBlb,
				R.id.incusBlb, R.id.stapesBlb, R.id.canalsBlb };

		listHolder.add(leftPlaceHolderList);
		listHolder.add(rightPlaceHolderList);

		return listHolder;
	}

	/*
	 * Return list of arrays containing left placeholder Ids and right
	 * placeholder Ids of HumanEye diagram
	 */
	private List<Integer[]> getHumanEyeMarkerList() {

		leftPlaceHolderList = new Integer[] { R.id.corneaBlb,
				R.id.ciliaryBodyBlb, R.id.irisBlb, R.id.pupilBlb, R.id.lensBlb, };

		rightPlaceHolderList = new Integer[] { R.id.foveaBlb, R.id.retinaBlb,
				R.id.opticNerveBlb, R.id.blindSpotBlb, R.id.vitreousBlb };

		listHolder.add(leftPlaceHolderList);
		listHolder.add(rightPlaceHolderList);

		return listHolder;

	}

}
