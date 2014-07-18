using UnityEngine;
using System.Collections;

public class AboutScript : MonoBehaviour {

	string content = "'Tell the Time' is an educative game that teaches kids about the basic concepts of time (both analog and digital). The application helps kids learn to say time much quickly and in a fun way by using an interactive analog clock";
	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
		if (Input.GetKeyDown(KeyCode.Escape)) 
		{ 
			Application.LoadLevel("mainMenu"); 
		}
	}
	
}
