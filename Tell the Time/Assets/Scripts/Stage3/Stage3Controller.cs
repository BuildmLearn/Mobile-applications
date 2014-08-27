using UnityEngine;
using System.Collections;

public class Stage3Controller : MonoBehaviour {
	int day;
	int month;
	int year;

	int nWin=0;
	int currentRound=0;
	public GUIStyle submitButtonStyle;
	public GUIStyle textStyle;


	public GUIStyle DayStyle;
	public GUIStyle MonthStyle;
	public GUIStyle YearStyle;


	public GUIStyle questionStyle;
	public string dayInput,monthInput,yearInput;
	public bool touchEnabled = true;
	public GameObject cross,circle;



	private TouchScreenKeyboard keyboard;
	private bool keyboard1 = false;
	private bool keyboard2 = false;
	private bool keyboard3 = false;
	// Use this for initialization
	void Start () {
		if (Screen.width > 720) {
						questionStyle.fontSize = 120;
						submitButtonStyle.fontSize = 90;
						DayStyle.fontSize = 90;
						MonthStyle.fontSize = 90;
						YearStyle.fontSize = 90;

				} else if (Screen.width > 540) {
						questionStyle.fontSize = 90;
						submitButtonStyle.fontSize = 60;
						DayStyle.fontSize = 60;
						MonthStyle.fontSize = 60;
						YearStyle.fontSize = 60;
				} else {
					questionStyle.fontSize = 50;
					submitButtonStyle.fontSize = 40;
					DayStyle.fontSize = 40;
					MonthStyle.fontSize = 40;
					YearStyle.fontSize = 40;
				}
		
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
		//GUI.Label (new Rect (Screen.width/6,Screen.height/20, Screen.width, Screen.height /6), "<color=white><size=90>" + day + "-" + month +"-"+ year + "</size></color>");
		GUI.Label (new Rect (0,Screen.height/100, Screen.width, Screen.height /10), day + "-" + month +"-"+ year,questionStyle);
		/*dayInput = GUI.TextField(new Rect(8*Screen.width/10-90, 11*Screen.height/20, 100, 100), dayInput, 2,textStyle);
		monthInput = GUI.TextField(new Rect(2*Screen.width/10-25, 11*Screen.height/20, 140, 100), monthInput, 3,textStyle);
		yearInput = GUI.TextField(new Rect(Screen.width/2-95, Screen.height/5+70, 200, 100), yearInput, 4,textStyle);*/

		/*if (GUI.Button (new Rect(8*Screen.width/10-90, 13*Screen.height/20, 100, 100), dayInput,textStyle)&&keyboard1==false) {
			keyboard = TouchScreenKeyboard.Open (dayInput, TouchScreenKeyboardType.NumberPad, false, false, false, false, "Enter Day");
			keyboard1=true;
		}

		if (keyboard != null && keyboard.done&&keyboard1==true) {
			if(keyboard.text.Length>2)
				dayInput = keyboard.text.Substring(0,2);
			else
				dayInput = keyboard.text;
			keyboard1=false;
		}

		if (GUI.Button (new Rect(2*Screen.width/10-25, 13*Screen.height/20, 140, 100), monthInput,textStyle)&&keyboard1==false) {
			keyboard = TouchScreenKeyboard.Open (monthInput, TouchScreenKeyboardType.ASCIICapable, false, false, false, false, "Enter Month: eg. Jan");
			keyboard2=true;
		}
		
		if (keyboard != null && keyboard.done&&keyboard2==true) {
			if(keyboard.text.Length>2)
				monthInput = keyboard.text.Substring(0,3).ToLower();
			else
				monthInput = keyboard.text;
			keyboard2=false;
		}

		if (GUI.Button (new Rect(Screen.width/2-100, Screen.height/5+80, 200, 100), yearInput,textStyle)&&keyboard1==false) {
			keyboard = TouchScreenKeyboard.Open (yearInput, TouchScreenKeyboardType.NumberPad, false, false, false, false, "Enter Year");
			keyboard3=true;
		}
		
		if (keyboard != null && keyboard.done&&keyboard3==true) {
			if(keyboard.text.Length>4)
				yearInput = keyboard.text.Substring(0,4);
			else
				yearInput = keyboard.text;
			keyboard3=false;
		}*/


		
		if (GUI.Button (new Rect(Screen.width/2+20, Screen.height/2+40, Screen.width/2-40, Screen.height/3), dayInput,DayStyle)&&keyboard1==false) {
			keyboard = TouchScreenKeyboard.Open (dayInput, TouchScreenKeyboardType.NumberPad, false, false, false, false, "Enter Day");
			keyboard1=true;
		}
		
		if (keyboard != null && keyboard.done&&keyboard1==true) {
			if(keyboard.text.Length>2)
				dayInput = keyboard.text.Substring(0,2);
			else
				dayInput = keyboard.text;
			keyboard1=false;
		}
		
		if (GUI.Button (new Rect(10, Screen.height/2+40, Screen.width/2-40, Screen.height/3), monthInput,MonthStyle)&&keyboard1==false) {
			keyboard = TouchScreenKeyboard.Open (monthInput, TouchScreenKeyboardType.ASCIICapable, false, false, false, false, "Enter Month: eg. Jan");
			keyboard2=true;
		}
		
		if (keyboard != null && keyboard.done&&keyboard2==true) {
			if(keyboard.text.Length>2)
				monthInput = keyboard.text.Substring(0,3).ToUpper();
			else
				monthInput = keyboard.text.ToUpper();
			keyboard2=false;
		}
		if (GUI.Button (new Rect(Screen.width/2-((Screen.width/2-40)/2), Screen.height/10+Screen.height/100, Screen.width/2-40, Screen.height/3), yearInput,YearStyle)&&keyboard1==false) {
			keyboard = TouchScreenKeyboard.Open (yearInput, TouchScreenKeyboardType.NumberPad, false, false, false, false, "Enter Year");
			keyboard3=true;
		}
		
		if (keyboard != null && keyboard.done&&keyboard3==true) {
			if(keyboard.text.Length>4)
				yearInput = keyboard.text.Substring(0,4);
			else
				yearInput = keyboard.text;
			keyboard3=false;
		}

		if (GUI.Button (new Rect (Screen.width/2.0f - (Screen.width/6) , (8f / 9f) * Screen.height, Screen.width / 3, Screen.height / 10),"Submit",submitButtonStyle)&&touchEnabled==true) {
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
			monthInput = "";
			dayInput = "";
			yearInput = "";
			//new round
			randomDate();
		}
	
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
		string monthStr = month;
		Debug.Log ("MonthString " + monthStr);
		int answer = 0;
		switch (month) {
		case "JAN":
			answer = 1;
			break;
		case "FEB":
			answer = 2;
			break;
		case "MAR":
			answer = 3;
			break;
		case "APR":
				answer = 4;
			break;
		case "MAY":
			answer = 5;
			break;
		case "JUN":
			answer = 6;
			break;
		case "JUL":
				answer = 7;
			break;
		case "AUG":
			answer = 8;
			break;
		case "SEP":
			answer = 9;
			break;
		case "OCT":
			answer = 10;
			break;
		case "NOV":
			answer = 11;
			break;
		case "DEC":
			answer = 12;
			break;
		}
		return answer;
	}
}
