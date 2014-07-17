using UnityEngine;
using System.Collections;

public class Stage5Controller : MonoBehaviour {
	string question;
	int day;
	// Use this for initialization
	void Start () {
		day = (int)Random.Range (1, 30);
		question = "Which Day is \nDay " + day;
	}
	
	// Update is called once per frame
	void Update () {
		if (Input.GetKeyDown(KeyCode.Escape)) { Application.LoadLevel("StageSelect"); }

	}

	void OnGUI()
	{
		GUI.Label (new Rect (Screen.width/6,Screen.height/20, Screen.width, Screen.height /6), "<color=white><size=60>" + question + "</size></color>");
	}
}
