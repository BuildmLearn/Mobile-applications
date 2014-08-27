using UnityEngine;
using System.Collections;

public class Stage4Controller : MonoBehaviour {
	public GUIStyle questionStyle;

	int nDays = 0;
	int day =0;
	string[] answerText = new string[4];
	int answerPos;
	int answer = 0;
	int nWin = 0;
	int currentRound = 0;
	bool touchEnabled = true;

	Hashtable questionHash;

	int[] answerArray = {0,1,2,3,4,5,6}; 

	public GUIStyle textStyle;
	public GameObject cross,circle;

	string question = "";
	// Use this for initialization
	void Start () {
		if (Screen.width > 720) {
						questionStyle.fontSize = 90;
						textStyle.fontSize = 60;
				} else if (Screen.width > 540) {
						questionStyle.fontSize = 60;
						textStyle.fontSize = 40;
				} else {
						questionStyle.fontSize = 40;
						textStyle.fontSize = 30;
				}
		questionHash = new Hashtable ();
		cross.active = false;
		circle.active = false;
		//while(question.Equals(""))
		question = randomQuestion ();
		//OnGUI ();

	}
	
	// Update is called once per frame
	void Update () {
		if (Input.GetKeyDown(KeyCode.Escape)) { Application.LoadLevel("StageSelect"); }


	}
	void OnGUI()
	{
		//GUI.Label (new Rect (Screen.width/6,Screen.height/20, Screen.width, Screen.height /6), "<color=black><size=60>" + question + "</size></color>");
		//question label
		GUI.Label (new Rect (10,10, Screen.width-10, Screen.height /6), question,questionStyle);

		/*answerInput = GUI.TextField(new Rect(Screen.width/2-200, Screen.height/2, 400, 100), answerInput, 8,textStyle);
	
		if (GUI.Button (new Rect (Screen.width/2.0f - (Screen.width/6) , (6.8f / 9f) * Screen.height, Screen.width / 3, Screen.height / 12), "<color=white><size=45>" + "Submit" + "</size></color>")&&touchEnabled==true) {
			touchEnabled = false;
			//GUI.Label (new Rect ( Screen.width / 2, Screen.height / 2, Screen.width / 3, Screen.height / 12), "<color=black><size=80>" + hourOneInput+hourTwoInput+minuteOneInput+minuteTwoInput  + "</size></color>");
			StartCoroutine(checkWin (answerInput));
			
		}*/

		if (GUI.Button (new Rect (10, (4.3f / 6.5f) * Screen.height, Screen.width / 2-40, Screen.height / 8), answerText[0],textStyle )&&touchEnabled==true) {
			StartCoroutine(checkWin (0));
		}
		//GUI.Label (new Rect (Screen.width/2-Screen.width/2.5f, (4.3f/6.5f)*Screen.height, Screen.width/3,Screen.height/12),"<color=white><size=45>"+clock[0]+"</size></color>");
		
		if (GUI.Button (new Rect (Screen.width / 2 + 20, (4.3f / 6.5f) * Screen.height, Screen.width / 2 -40, Screen.height / 8), answerText [1],textStyle )&&touchEnabled==true) {
			StartCoroutine(checkWin (1));
		}
		//GUI.Label (new Rect (Screen.width/2+Screen.width/11, (4.3f/6.5f)*Screen.height, Screen.width/3,Screen.height/12),"<color=white><size=45>"+clock[1]+"</size></color>");
		
		if (GUI.Button (new Rect (10, (5.3f / 6.5f) * Screen.height, Screen.width / 2-40, Screen.height / 8), answerText [2],textStyle )&&touchEnabled==true) {
			StartCoroutine(checkWin (2));
		}
		//GUI.Label (new Rect (Screen.width/2-Screen.width/2.5f, (5.7f/6.5f)*Screen.height, Screen.width/3,Screen.height/12),"<color=white><size=45>"+clock[2]+"</size></color>");
		
		if (GUI.Button (new Rect (Screen.width / 2 + 20, (5.3f / 6.5f) * Screen.height, Screen.width / 2 -40, Screen.height / 8), answerText [3],textStyle )&&touchEnabled==true) {
			StartCoroutine(checkWin (3));
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

	IEnumerator checkWin(int answerPos){
		touchEnabled = false;

		if(answerPos == this.answerPos)
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
			question = randomQuestion ();
		}

		//generate new question
	

		//new round

		//shortHand.transform.Rotate (0, 0, time*-30);

		touchEnabled = true;
	}




	string randomQuestion()
	{
		string quest = "";
		string part1 = "";
		string part2 = "";
		day = Random.Range (0, 7);
		nDays = Random.Range (-3, 4);
		while (nDays==0)
		{
			nDays = Random.Range (-3, 4);
		}
		int questionValue = day * 10 + nDays;
		while (questionHash.ContainsValue(questionValue)) {
			day = Random.Range (0, 7);
			nDays = Random.Range (-3, 4);		while (nDays==0)
			{
				nDays = Random.Range (-3, 4);
			}
			questionValue = day * 10 + nDays;
		}
		questionHash.Add ("q"+currentRound,questionValue);
	


		switch (nDays) {
		case -3:
			part1 = "Three days before";
			break;
		case -2:
			part1 = "Two days before";
			break;
		case -1:
			part1 = "The day before";
			break;
		case 1:
			part1 = "The day after";
			break;
		case 2:
			part1 = "Two days after";
			break;
		case 3:
			part1 = "Three days after";
			break;
		default:
			part1 = "Error";
			break;
		}

		switch (day)
		{
		case 1:
			part2 = "\nMonday";
			break;
		case 2:
			part2 =  "\nTuesday";
			break;
		case 3:
			part2 = "\nWednesday";
			break;
		case 4:
			part2 = "\nThursday";
			break;
		case 5:
			part2 = "\nFriday";
			break;
		case 6:
			part2 = "\nSaturday";
			break;
		case 0: 
			part2 = "\nSunday";
			break;
		default:
			part2 = "\nError2";
			break;
		}
		quest = part1 + part2;
		answer = mod(nDays+day,7);
		Debug.Log (answer);
		setAnswerLocation (answer);
		return quest;
	}

	int mod(int dividend, int divisor) {
		return (((dividend) % divisor) + divisor) % divisor;
	}

	void setAnswerLocation(int answer)
	{

		Debug.Log ("ndays " + nDays + "day " + day+"Answer " + answer);
		int temp;
		answerPos = Random.Range (0, 4);
		answerText [answerPos] = convertIntAnswerToString (answer);
		answerArray [answer] = 0;
		//set other answer choices
		for (int i=0; i<4; i++) {
			if(i!=answerPos)
			{
				 temp= answerArray[Random.Range(0,7)];
				while(temp==0)
				{
					temp= answerArray[Random.Range(0,7)];
				}

				answerText[i] = convertIntAnswerToString(temp);
				answerArray[temp]=0;
			}
		}

		//reset the array
		for (int k=0; k<7; k++) {
			answerArray[k] = k;		
		}
	}

	string convertIntAnswerToString(int answer)
	{
		string answerStr = "";
		switch (answer)
		{
		case 1:
			answerStr = "Monday";
			break;
		case 2:
			answerStr = "Tuesday";
			break;
		case 3:
			answerStr = "Wednesday";
			break;
		case 4:
			answerStr = "Thursday";
			break;
		case 5:
			answerStr = "Friday";
			break;
		case 6:
			answerStr = "Saturday";
			break;
		case 0: 
			answerStr = "Sunday";
			break;
		default:
			break;
		}	
		return answerStr;
	}
}
