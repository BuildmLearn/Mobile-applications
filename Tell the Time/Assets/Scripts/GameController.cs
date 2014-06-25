using UnityEngine;
using System.Collections;

public class GameController : MonoBehaviour {
	public GameObject longHand,shortHand;
	public GUIText gameText;
	public GUIText time;
	public GameObject circle;
	public GameObject cross;
	int x =  Screen.width/2;
	int y = Screen.height / 2;
	// Use this for initialization
	void Start () {
		circle.active = false;
		cross.active = false;
		//longHand = GameObject.Find("LongHand");
		//shortHand = GameObject.Find ("ShortHand");
	}


	// Update is called once per frame
	void Update () {

		Debug.Log ("Long Hand" +longHand.transform.rotation.eulerAngles.z);
		//Debug.Log ("Short Hand" +shortHand.transform.rotation.z);
		int currentTime = time.GetComponent<RandomClockTime>().time;

		float shortHandRotation = shortHand.transform.rotation.eulerAngles.z;
		Vector3 spawnPosition = new Vector3(x, y,-1);

		if (Input.touches.Length<=0) {
			if(getCurrentTimeWithRotation(shortHandRotation)==currentTime)
			{
				Debug.Log ("Win");
				gameText.text = "Win";
				circle.active = true;
				//Instantiate(circle, spawnPosition, Quaternion.identity);
			}

		}

		if (Input.GetKeyDown(KeyCode.Escape)) { Application.LoadLevel("StageSelect"); }



	}

	int getCurrentTimeWithRotation(float rotation)
	{
		if ((rotation <= 5 && rotation >= 0) || (rotation <= 360 && rotation >= 355)) {
						return 3;
				} else if ((rotation <= 35 && rotation >= 30) || (rotation <= 30 && rotation >= 25)) {
						return 2;
				} else if ((rotation <= 65 && rotation >= 60) || (rotation <= 60 && rotation >= 55)) {
						return 1;
				} else if ((rotation <= 95 && rotation >= 90) || (rotation <= 90 && rotation >= 85)) {
						return 12;
				} else if ((rotation <= 125 && rotation >= 120) || (rotation <= 120 && rotation >= 115)) {
						return 11;
				} else if ((rotation <= 155 && rotation >= 150) || (rotation <= 150 && rotation >= 145)) {
						return 10;
				} else if ((rotation <= 185 && rotation >= 180) || (rotation <= 180 && rotation >= 175)) {
						return 9;
				} else if ((rotation <= 215 && rotation >= 210) || (rotation <= 210 && rotation >= 205)) {
						return 8;
				} else if ((rotation <= 245 && rotation >= 240) || (rotation <= 240 && rotation >= 235)) {
						return 7;
				} else if ((rotation <= 275 && rotation >= 270) || (rotation <= 270 && rotation >= 265)) {
						return 6;
				} else if ((rotation <= 305 && rotation >= 300) || (rotation <= 300 && rotation >= 295)) {
						return 5;
				} else if ((rotation <= 335 && rotation >= 330) || (rotation <= 330 && rotation >= 325)) {
						return 4;
				} else
						return 0;

	}

	void OnGUI(){
		GUI.Label (new Rect (2*Screen.width/3, Screen.height/20, Screen.width/3,Screen.height/12),"<color=red><size=45>"+time.GetComponent<RandomClockTime>().time+".00"+"</size></color>");

		//GUI.Label(new Rect(Screen.width/2,Screen.height/4,50,50), "Label text");
	}


}
