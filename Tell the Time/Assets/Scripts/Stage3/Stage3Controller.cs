using UnityEngine;
using System.Collections;

public class Stage3Controller : MonoBehaviour {
	int day;
	int month;
	int year;
	// Use this for initialization
	void Start () {
		randomDate ();
	}
	
	// Update is called once per frame
	void Update () {
		if (Input.GetKeyDown(KeyCode.Escape)) 
		{ Application.LoadLevel("StageSelect"); }

	}
	void OnGUI()
	{
		GUI.Label (new Rect (Screen.width/6,Screen.height/20, Screen.width, Screen.height /6), "<color=white><size=90>" + day + "-" + month +"-"+ year + "</size></color>");

	}

	void randomDate()
	{
			day = (int)Random.Range(1,28);
		    month = (int)Random.Range (1, 13);
			year = (int)Random.Range (2000, 2030);
	}
}
