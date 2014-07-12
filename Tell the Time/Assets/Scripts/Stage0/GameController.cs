using UnityEngine;
using System.Collections;

public class GameController : MonoBehaviour {
	public GameObject longHand,shortHand;
	public GUIText time;
	public GameObject circle;
	public GameObject cross;
	public GameObject pauseMenu;
	public GameObject resumeBtn;
	public GameObject homeBtn;

	int nWin =1;
	int x =  Screen.width/2;
	int y = Screen.height / 2;
	float shortHandRotation;
	float longHandRotation;
	public bool touchEnabled = true;
	int currentTime;
	int currentMinute;
	int nRound = 5;
	int currentRound =0;
	// Use this for initialization
	void Start () {

		circle.active = false;
		cross.active = false;

		pauseMenu.active = false;
		homeBtn.active = false;
		resumeBtn.active = false;

		currentTime = (int)Random.Range (1.0F, 11.0F);
		currentMinute = (int)Random.Range(0,11)*5;
		//longHand = GameObject.Find("LongHand");
		//shortHand = GameObject.Find ("ShortHand");
	}


	// Update is called once per frame
	void Update () {

		if (Input.GetKeyDown(KeyCode.Escape))		{ 
			/*pauseMenu.active = true;
			homeBtn.active = true;
			resumeBtn.active = true;*/
			Application.LoadLevel("StageSelect"); 
		}

		Debug.Log ("Long Hand" +longHand.transform.rotation.eulerAngles.z);
		//Debug.Log ("Short Hand" +shortHand.transform.rotation.z);

		longHandRotation = longHand.transform.rotation.eulerAngles.z;
		shortHandRotation = shortHand.transform.rotation.eulerAngles.z;

	



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


	int getCurrentMinuteWithRotation(float rotation)
	{
		if ((rotation <= 5 && rotation >= 0) || (rotation <= 360 && rotation >= 355)) {
			return 15;
		} else if ((rotation <= 35 && rotation >= 30) || (rotation <= 30 && rotation >= 25)) {
			return 10;
		} else if ((rotation <= 65 && rotation >= 60) || (rotation <= 60 && rotation >= 55)) {
			return 5;
		} else if ((rotation <= 95 && rotation >= 90) || (rotation <= 90 && rotation >= 85)) {
			return 0;
		} else if ((rotation <= 125 && rotation >= 120) || (rotation <= 120 && rotation >= 115)) {
			return 55;
		} else if ((rotation <= 155 && rotation >= 150) || (rotation <= 150 && rotation >= 145)) {
			return 50;
		} else if ((rotation <= 185 && rotation >= 180) || (rotation <= 180 && rotation >= 175)) {
			return 45;
		} else if ((rotation <= 215 && rotation >= 210) || (rotation <= 210 && rotation >= 205)) {
			return 40;
		} else if ((rotation <= 245 && rotation >= 240) || (rotation <= 240 && rotation >= 235)) {
			return 35;
		} else if ((rotation <= 275 && rotation >= 270) || (rotation <= 270 && rotation >= 265)) {
			return 30;
		} else if ((rotation <= 305 && rotation >= 300) || (rotation <= 300 && rotation >= 295)) {
			return 25;
		} else if ((rotation <= 335 && rotation >= 330) || (rotation <= 330 && rotation >= 325)) {
			return 20;
		} else
			return 0;
	}

	void  OnGUI(){
		if (currentMinute == 0) 
		{
				GUI.Label (new Rect (2 * Screen.width / 3-20, Screen.height / 20, Screen.width / 3, Screen.height / 12), "<color=white><size=80>" + currentTime + ":00" + "</size></color>");
		} 
		else if (currentMinute == 5) 
		{
				GUI.Label (new Rect (2 * Screen.width / 3-20, Screen.height / 20, Screen.width / 3, Screen.height / 12), "<color=white><size=80>" + currentTime + ":05" + "</size></color>");
		} 
		else 
		{
				GUI.Label (new Rect (2 * Screen.width / 3-20, Screen.height / 20, Screen.width / 3, Screen.height / 12), "<color=white><size=80>" + currentTime + ":" + currentMinute + "</size></color>");
		}
		GUI.backgroundColor = new Color(0,0,0,0);
		if (GUI.Button (new Rect (Screen.width/2.0f - (Screen.width/6) , (6.8f / 9f) * Screen.height, Screen.width / 3, Screen.height / 12), "<color=white><size=45>" + "Submit" + "</size></color>")&&touchEnabled==true) {
			touchEnabled = false;
			StartCoroutine(checkWin ());

		}

		//GUI.Label(new Rect(Screen.width/2,Screen.height/4,50,50), "Label text");
	}

	IEnumerator checkWin(){
		if(getCurrentTimeWithRotation(shortHandRotation)==currentTime&&getCurrentMinuteWithRotation(longHandRotation)==currentMinute)
		{
			Debug.Log ("Win");
			circle.active = true;
			nWin++;
			//Instantiate(circle, spawnPosition, Quaternion.identity);
		}
		else{
			cross.active = true;
		}
		currentRound++;
		yield return new WaitForSeconds(1);
		if(currentRound==5)
		{
				PlayerPrefs.SetInt("CurrentScore", nWin);
				if(PlayerPrefs.GetInt("Stage0HighScore")<nWin)
				{
				PlayerPrefs.SetInt("Stage0HighScore",nWin);
				}
				Application.LoadLevel("ScoreScene");
		}
		else{
				circle.active = false;
				cross.active = false;
		}
		currentTime = (int)Random.Range (1.0F, 11.0F);
		currentMinute = (int)Random.Range(0,11)*5;;
		touchEnabled = true;
	}

	IEnumerator waitTime()
	{
		yield return new WaitForSeconds(2);
	}

}
