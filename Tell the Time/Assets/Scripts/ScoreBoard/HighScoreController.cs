using UnityEngine;
using System.Collections;

public class HighScoreController : MonoBehaviour {
	int spacing=0;
	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
		if (Input.GetKeyDown(KeyCode.Escape)) { Application.LoadLevel("mainMenu"); }

	}

	void OnGUI()
	{
		string statement;
		string message;
		for (int i=0; i<10; i++) 
		{
			statement = "Stage" + i + "HighScore";
			if(PlayerPrefs.GetInt(statement)!=0)
			{
				message = "Stage " + (i+1) + " " + PlayerPrefs.GetInt(statement);  
				GUI.Label (new Rect (Screen.width/10, Screen.height/30+spacing, Screen.width/3,Screen.height/12),"<color=white><size=80>"+ message + "</size></color>");
				spacing = spacing +10;
			}
		}
	}
}
