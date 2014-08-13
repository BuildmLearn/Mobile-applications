using UnityEngine;
using System.Collections;

public class Stage5Controller : MonoBehaviour {
	string question;
	int day;
	int startDay;
	int answer;
	public GUIStyle textStyle;

	int nWin = 0;
	int currentRound = 0;
	string answerInput ="";
	bool touchEnabled;

	public GameObject cross,circle;
	public GameObject calendar1,calendar2,calendar3,calendar4,calendar5;
	
	// Use this for initialization
	void Start () {
		day = (int)Random.Range (1, 30);
		cross.active = false;
		circle.active = false;
		touchEnabled = true;

		question = "Which Day is \nDay " + day;
		choosePuzzle ();
	}
	
	// Update is called once per frame
	void Update () {
		if (Input.GetKeyDown(KeyCode.Escape)) { Application.LoadLevel("StageSelect"); }
		if (GUI.Button (new Rect (Screen.width/2.0f - (Screen.width/6) , (6.8f / 9f) * Screen.height, Screen.width / 3, Screen.height / 12), "<color=white><size=45>" + "Submit" + "</size></color>")&&touchEnabled==true) {
			touchEnabled = false;
			//GUI.Label (new Rect ( Screen.width / 2, Screen.height / 2, Screen.width / 3, Screen.height / 12), "<color=black><size=80>" + hourOneInput+hourTwoInput+minuteOneInput+minuteTwoInput  + "</size></color>");
			StartCoroutine(checkWin (answerInput));
			
		}

	}

	IEnumerator checkWin(string answer){


		if(convertAnswerToInt(answer) == this.answer)
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
			if(PlayerPrefs.GetInt("Stage5HighScore")<nWin)
			{
				PlayerPrefs.SetInt("Stage5HighScore",nWin);
			}
			Application.LoadLevel("ScoreScene");
		}
		else{
			circle.active = false;
			cross.active = false;
		}
		
		//generate new question
		day = (int)Random.Range (1, 30);
		question = "Which Day is \nDay " + day;
		choosePuzzle ();
		answerInput = "";
		
		//new round
		
		//shortHand.transform.Rotate (0, 0, time*-30);
		
		touchEnabled = true;
	}

	int convertAnswerToInt(string answer)
	{
		string ans = answer.ToLower ();
		switch (ans) {
		case "monday":
			return 1;
			break;
		case "tuesday":
			return 2;
			break;
		case "wednesday":
			return 3;
			break;
		case "thursday":
			return 4;
			break;
		case "friday":
			return 5;
			break;
		case "saturday":
			return 6;
			break;
		case "sunday":
			return 0;
			break;
		default:
			return -1;
			break;
		}
	}
	void choosePuzzle()
	{
				int puzzle = (int)Random.Range (0, 5);
				switch (puzzle) {
				case 0:
						calendar1.active = true;
						calendar2.active = false;
						calendar3.active = false;
						calendar4.active = false;
						calendar5.active = false;
						startDay = 0;
						break;
				case 1:
						calendar1.active = false;
						calendar2.active = true;
						calendar3.active = false;
						calendar4.active = false;
						calendar5.active = false;
						startDay = 2;
						break;
				case 2:
						calendar1.active = false;
						calendar2.active = false;
						calendar3.active = true;
						calendar4.active = false;
						calendar5.active = false;
						startDay = 4;
						break;
				case 3:
						calendar1.active = false;
						calendar2.active = false;
						calendar3.active = false;
						calendar4.active = true;
						calendar5.active = false;
						startDay = 2;
						break;
				case 4:
						calendar1.active = false;
						calendar2.active = false;
						calendar3.active = false;
						calendar4.active = false;
						calendar5.active = true;
						startDay = 5;
						break;
			
				}
			int temp = (day - 1) % 7;
			this.answer = (startDay + temp) % 7;

	}

	void OnGUI()
	{
		GUI.Label (new Rect (Screen.width/6,Screen.height/20, Screen.width, Screen.height /6), "<color=white><size=60>" + question + "</size></color>");
		answerInput = GUI.TextField(new Rect(Screen.width/2-200, Screen.height/2, 400, 100), answerInput, 8,textStyle);
		
		if (GUI.Button (new Rect (Screen.width/2.0f - (Screen.width/6) , (6.8f / 9f) * Screen.height, Screen.width / 3, Screen.height / 12), "<color=white><size=45>" + "Submit" + "</size></color>")&&touchEnabled==true) {
			touchEnabled = false;
			//GUI.Label (new Rect ( Screen.width / 2, Screen.height / 2, Screen.width / 3, Screen.height / 12), "<color=black><size=80>" + hourOneInput+hourTwoInput+minuteOneInput+minuteTwoInput  + "</size></color>");
			StartCoroutine(checkWin (answerInput));
			
		}
	}
}
