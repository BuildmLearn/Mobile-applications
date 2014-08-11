using UnityEngine;
using System.Collections;

public class Stage4Controller : MonoBehaviour {
	int nDays = 0;
	int day =0;

	int answer = 0;
	int nWin = 0;
	int currentRound = 0;
	string answerInput ="";
	bool touchEnabled;

	public GUIStyle textStyle;
	public GameObject cross,circle;

	string question;
	// Use this for initialization
	void Start () {
		cross.active = false;
		circle.active = false;
		question = randomQuestion ();
		touchEnabled = true;
	}
	
	// Update is called once per frame
	void Update () {
		if (Input.GetKeyDown(KeyCode.Escape)) { Application.LoadLevel("StageSelect"); }


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
			if(PlayerPrefs.GetInt("Stage4HighScore")<nWin)
			{
				PlayerPrefs.SetInt("Stage4HighScore",nWin);
			}
			Application.LoadLevel("ScoreScene");
		}
		else{
			circle.active = false;
			cross.active = false;
		}

		//generate new question
		question = randomQuestion ();

		answerInput = "";

		//new round

		//shortHand.transform.Rotate (0, 0, time*-30);
		
		touchEnabled = true;
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

		answer = (nDays + day) % 7;
		return quest;


	}
}
