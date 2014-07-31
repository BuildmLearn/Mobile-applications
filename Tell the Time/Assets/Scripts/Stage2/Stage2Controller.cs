using UnityEngine;
using System.Collections;

public class Stage2Controller : MonoBehaviour {
	public GameObject longHand,shortHand;	
	int time;
	int minute;
	int nWin=0;
	int currentRound=0;
	string answer = "";
	public string hourOneInput,hourTwoInput,minuteOneInput,minuteTwoInput;
	public GUIStyle textStyle;
	public bool touchEnabled = true;

	public GameObject cross,circle;
	// Use this for initialization
	void Start () {
		cross.active = false;
		circle.active = false;
		time = (int)Random.Range (1.0F, 11.0F);
		minute = 0;
		//shortHand.transform.Rotate (0, 0, time*-30);
		shortHand.transform.eulerAngles = new Vector3(0,0,time*-30); 

	}
	
	// Update is called once per frame
	void Update () {
		if (Input.GetKeyDown(KeyCode.Escape)) { Application.LoadLevel("StageSelect"); }
		Debug.Log ("answer " + answer);
		Debug.Log (convertIntToTimeStr (time));
	}

	string convertIntToTimeStr(int time)
	{
		string timeStr;
		if (time < 10) {
			timeStr = "0"+time+"00"	;	
		}
		else 
		{
			timeStr = time+"00";
		}
		return timeStr;
	}

	void OnGUI() {
		hourOneInput = GUI.TextField(new Rect(Screen.width/4, Screen.height/7, 60, 100), hourOneInput, 1,textStyle);
		hourTwoInput = GUI.TextField(new Rect(Screen.width/4+100, Screen.height/7, 60, 100), hourTwoInput, 1,textStyle);
		minuteOneInput = GUI.TextField(new Rect(Screen.width/4+200, Screen.height/7, 60, 100), minuteOneInput, 1,textStyle);
		minuteTwoInput = GUI.TextField(new Rect(Screen.width/4+300, Screen.height/7, 60, 100), minuteTwoInput, 1,textStyle);
		answer = hourOneInput + hourTwoInput + minuteOneInput + minuteTwoInput;
		if (GUI.Button (new Rect (Screen.width/2.0f - (Screen.width/6) , (6.8f / 9f) * Screen.height, Screen.width / 3, Screen.height / 12), "<color=white><size=45>" + "Submit" + "</size></color>")&&touchEnabled==true) {
			touchEnabled = false;
			GUI.Label (new Rect ( Screen.width / 2, Screen.height / 2, Screen.width / 3, Screen.height / 12), "<color=black><size=80>" + hourOneInput+hourTwoInput+minuteOneInput+minuteTwoInput  + "</size></color>");
			StartCoroutine(checkWin (answer));
			
		}

	}

	IEnumerator checkWin(string answer){
		if(convertIntToTimeStr(time).Equals(answer))
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
			if(PlayerPrefs.GetInt("Stage2HighScore")<nWin)
			{
				PlayerPrefs.SetInt("Stage2HighScore",nWin);
			}
			Application.LoadLevel("ScoreScene");
		}
		else{
			circle.active = false;
			cross.active = false;
		}
		hourOneInput = "";
		hourTwoInput = "";
		minuteOneInput = "";
		minuteTwoInput = "";
		//new round
		time = (int)Random.Range (1.0F, 11.0F);
		minute = 0;
		shortHand.transform.eulerAngles = new Vector3(0,0,time*-30); 
		//shortHand.transform.Rotate (0, 0, time*-30);

		touchEnabled = true;
	}
	
	IEnumerator waitTime()
	{
		yield return new WaitForSeconds(2);
	}

}
