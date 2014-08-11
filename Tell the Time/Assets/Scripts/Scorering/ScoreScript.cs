using UnityEngine;
using System.Collections;

public class ScoreScript : MonoBehaviour {
	int nWin=0;
	// Use this for initialization
	void Start () {
		nWin = PlayerPrefs.GetInt("CurrentScore");

	}
	
	// Update is called once per frame
	void Update () {
		if (Input.GetMouseButtonDown (0)) {
			Application.LoadLevel("StageSelect");		
		}
	}

	void OnGUI()
	{
		GUI.Label (new Rect (Screen.width/2, Screen.height/2, Screen.width/2,Screen.height/5),"<color=white><size=140>"+calculateScore(nWin)+"</size></color>");
	}

	string calculateScore(int nWin)
	{
		switch (nWin) {
		
		case 1: return "F";
			break;
		case 2: return "E";
			break;
		case 3: return "D";
			break;
		case 4: return "C";
			break;
		case 5: return "B";
			break;
		case 6: return "A";
			break;
		
		}
		return "F";
	}
}
