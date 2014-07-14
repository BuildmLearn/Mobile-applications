using UnityEngine;
using System.Collections;

public class Stage4Controller : MonoBehaviour {
	int nDays = 0;
	int day =0;
	string question;
	// Use this for initialization
	void Start () {
		question = randomQuestion ();
	}
	
	// Update is called once per frame
	void Update () {
		if (Input.GetKeyDown(KeyCode.Escape)) { Application.LoadLevel("StageSelect"); }


	}
	void OnGUI()
	{
		GUI.Label (new Rect (Screen.width/6,Screen.height/20, Screen.width, Screen.height /6), "<color=white><size=60>" + question + "</size></color>");
	}

	string randomQuestion()
	{
		string quest = "";
		day = Random.Range (1, 8);
		while (nDays==0) 
		{
			nDays = Random.Range (-3, 4);
		}


		switch (nDays) {
		case -3:
			quest = "Three days before";
			break;
		case -2:
			quest = "Two days before";
			break;
		case -1:
			quest = "The day before";
			break;
		case 1:
			quest = "The day after";
			break;
		case 2:
			quest = "Two days after";
			break;
		case 3:
			quest = "Three days after";
			break;
		}

		switch (day)
		{
		case 1:
			quest = quest + "\nMonday";
			break;
		case 2:
			quest =  quest + "\nTuesday";
			break;
		case 3:
			quest = quest + "\nWednesday";
			break;
		case 4:
			quest = quest + "\nThursday";
			break;
		case 5:
			quest = quest + "\nFriday";
			break;
		case 6:
			quest =  quest + "\nSaturday";
			break;
		case 7: 
			quest = quest + "\nSunday";
			break;
		}
		return quest;
	}
}
