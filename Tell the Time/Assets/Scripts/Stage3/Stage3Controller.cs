using UnityEngine;
using System.Collections;

public class Stage3Controller : MonoBehaviour {
	int day;
	int month;
	int year;

	int nWin=0;
	int currentRound=0;
	public GUIStyle textStyle;
	public string dayInput,monthInput,yearInput;
	public bool touchEnabled = true;
	public GameObject cross,circle;

	TouchScreenKeyboard numKeyboard;

	// Use this for initialization
	void Start () {
		cross.active = false;
		circle.active = false;

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
		dayInput = GUI.TextField(new Rect(8*Screen.width/10-90, 11*Screen.height/20, 100, 100), dayInput, 2,textStyle);
		monthInput = GUI.TextField(new Rect(2*Screen.width/10-25, 11*Screen.height/20, 140, 100), monthInput, 3,textStyle);
		yearInput = GUI.TextField(new Rect(Screen.width/2-95, Screen.height/5+70, 200, 100), yearInput, 4,textStyle);
		if (GUI.Button (new Rect (Screen.width/2.0f - (Screen.width/6) , (6.8f / 9f) * Screen.height, Screen.width / 3, Screen.height / 12), "<color=white><size=45>" + "Submit" + "</size></color>")&&touchEnabled==true) {
			touchEnabled = false;
			//GUI.Label (new Rect ( Screen.width / 2, Screen.height / 2, Screen.width / 3, Screen.height / 12), "<color=black><size=80>" + hourOneInput+hourTwoInput+minuteOneInput+minuteTwoInput  + "</size></color>");
			StartCoroutine(checkWin (dayInput,monthInput,yearInput));
			
		}
	}

	IEnumerator checkWin(string day,string month, string year)
	{
		Vector3 spawnPosition = new Vector3(0,0,-12);

		if(convertMonthStrToInt(month)==this.month&&day.Equals((this.day).ToString())&&(year.Equals((this.year).ToString())))
		{
			Debug.Log ("Win");
			circle.active = true;
			Instantiate(circle,spawnPosition,Quaternion.identity);
			nWin++;
			//Instantiate(circle, spawnPosition, Quaternion.identity);
		}
		else{
			cross.active = true;
			Instantiate(cross,spawnPosition,Quaternion.identity);

		}
		currentRound++;
		yield return new WaitForSeconds(1);
		if(currentRound==5)
		{
			PlayerPrefs.SetInt("CurrentScore", nWin);
			if(PlayerPrefs.GetInt("Stage3HighScore")<nWin)
			{
				PlayerPrefs.SetInt("Stage3HighScore",nWin);
			}
			Application.LoadLevel("ScoreScene");
		}
		else{
			circle.active = false;
			cross.active = false;
		}
		monthInput = "";
		dayInput = "";
		yearInput = "";
		//new round
		randomDate();
		
		touchEnabled = true;
	}

	void randomDate()
	{
			day = (int)Random.Range(1,28);
		    month = (int)Random.Range (1, 13);
			year = (int)Random.Range (2000, 2030);
	}

	int convertMonthStrToInt(string month)
	{
		string monthStr = month.ToLowerInvariant ();
		int answer = 0;
		switch (month) {
			case "jan":
				answer = 1;
				break;
			case "feb":
			answer = 2;
			break;
		case "mar":
			answer = 3;
			break;
		case "apr":
				answer = 4;
			break;
		case "may":
			answer = 5;
			break;
		case "jun":
			answer = 6;
			break;
		case "jul":
				answer = 7;
			break;
		case "aug":
			answer = 8;
			break;
		case "sep":
			answer = 9;
			break;
		case "oct":
			answer = 10;
			break;
		case "nov":
			answer = 11;
			break;
		case "dec":
			answer = 12;
			break;
		}
		return answer;
	}
}
