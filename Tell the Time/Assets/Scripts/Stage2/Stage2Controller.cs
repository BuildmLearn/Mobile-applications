using UnityEngine;
using System.Collections;

public class Stage2Controller : MonoBehaviour {
	public GameObject longHand,shortHand;	
	int time;
	int minute;
	int nWin=0;
	int currentRound=0;
	string answer = "";


	public string kBoardString = "";
	
	private string tempString = "";
	private TouchScreenKeyboard keyboard;
	private bool iskBoardOpen = false;


	private bool keyboard1 = false;
	private bool keyboard2 = false;
	private bool keyboard3 = false;
	private bool keyboard4 = false;


	private TouchScreenKeyboard hourOneKeyboard,hourTwoKeyboard,minuteOneKeyboard,minuteTwoKeyboard;
	public string hourOneInput = "";
	public string hourTwoInput = "";
	public string minuteOneInput = "";
	public string minuteTwoInput = "";
	public GUIStyle textStyle;
	public GUIStyle style;
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
		//kBoard0 ();
		/*defaultKeyboard = TouchScreenKeyboard.Open(hourOneInput, TouchScreenKeyboardType.Default);
		hourOneInput = GUI.TextField(new Rect(1*Screen.width/10, Screen.height/7, 60, 100), hourOneInput, 1,textStyle);
		numKeyboard = TouchScreenKeyboard.Open(hourTwoInput, TouchScreenKeyboardType.NumberPad);
		hourTwoInput = GUI.TextField(new Rect(3*Screen.width/10, Screen.height/7, 60, 100), hourTwoInput, 1,textStyle);
		minuteOneInput = GUI.TextField(new Rect(6*Screen.width/10, Screen.height/7, 60, 100), minuteOneInput, 1,textStyle);
		minuteTwoInput = GUI.TextField(new Rect(8*Screen.width/10, Screen.height/7, 60, 100), minuteTwoInput, 1,textStyle);*/
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

		if (GUI.Button (new Rect (1 * Screen.width / 10, Screen.height / 7, 60, 100), hourOneInput, textStyle)&&keyboard1==false) {
			hourOneKeyboard = TouchScreenKeyboard.Open (tempString, TouchScreenKeyboardType.NumberPad, false, false, false, false, "Enter Hour");
						keyboard1=true;
				}

		if (hourOneKeyboard != null && hourOneKeyboard.done&&keyboard1==true) {
			if(hourOneKeyboard.text.Length>1)
			hourOneInput = hourOneKeyboard.text.Substring(0,1);
			else
			hourOneInput = hourOneKeyboard.text;
			tempString = "";
			keyboard1=false;
		}

		if (GUI.Button (new Rect (3 * Screen.width / 10, Screen.height / 7, 60, 100), hourTwoInput, textStyle)&&keyboard2==false) {
			hourTwoKeyboard = TouchScreenKeyboard.Open (tempString, TouchScreenKeyboardType.NumberPad, false, false, false, false, "Enter Hour");
				keyboard2=true;
		}
		
		if (hourTwoKeyboard != null && hourTwoKeyboard.done&&keyboard2==true) {
			if(hourTwoKeyboard.text.Length>1)
			hourTwoInput = hourTwoKeyboard.text.Substring(0,1);	
			else
				hourTwoInput = hourTwoKeyboard.text;

			tempString = "";
			keyboard2 = false;
		}

		if (GUI.Button (new Rect (6 * Screen.width / 10, Screen.height / 7, 60, 100), minuteOneInput, textStyle)&&keyboard3==false) {
			minuteOneKeyboard = TouchScreenKeyboard.Open (tempString, TouchScreenKeyboardType.NumberPad, false, false, false, false, "Enter Minute");
						keyboard3 = true;
				}

		if (minuteOneKeyboard != null && minuteOneKeyboard.done && keyboard3==true) {
			if(minuteOneKeyboard.text.Length>1)
				minuteOneInput = minuteOneKeyboard.text.Substring(0,1);
			else
				minuteOneInput = minuteOneKeyboard.text;
			tempString="";
			keyboard3 = false;
		}
		if (GUI.Button (new Rect (8 * Screen.width / 10, Screen.height / 7, 60, 100), minuteTwoInput, textStyle)&&keyboard4==false) {
			minuteTwoKeyboard = TouchScreenKeyboard.Open (tempString, TouchScreenKeyboardType.NumberPad, false, false, false, false, "Enter Minute");
			keyboard4=true;
		}
		if (minuteTwoKeyboard != null && minuteTwoKeyboard.done && keyboard4==true) {
			if(minuteTwoKeyboard.text.Length>1)
				minuteTwoInput = minuteTwoKeyboard.text.Substring(0,1);
			else
				minuteTwoInput = minuteTwoKeyboard.text;

			tempString = "";	
			keyboard4 = false;
		}

		/*hourOneInput = GUI.TextArea(new Rect(1*Screen.width/10, Screen.height/7, 60, 100), hourOneInput, 1,textStyle);
		hourTwoInput = GUI.TextArea(new Rect(3*Screen.width/10, Screen.height/7, 60, 100), hourTwoInput, 1,textStyle);
		minuteOneInput = GUI.TextArea(new Rect(6*Screen.width/10, Screen.height/7, 60, 100), minuteOneInput, 1,textStyle);
		minuteTwoInput = GUI.TextArea(new Rect(8*Screen.width/10, Screen.height/7, 60, 100), minuteTwoInput, 1,textStyle);*/

		answer = hourOneInput + hourTwoInput + minuteOneInput + minuteTwoInput;

		GUI.Label (new Rect (Screen.width/2-30, Screen.height/7, 60, 100),":",style);

		if (GUI.Button (new Rect (Screen.width/2.0f - (Screen.width/6) , (6.8f / 9f) * Screen.height, Screen.width / 3, Screen.height / 12), "<color=white><size=45>" + "Submit" + "</size></color>")&&touchEnabled==true) {
			touchEnabled = false;
			//GUI.Label (new Rect ( Screen.width / 2, Screen.height / 2, Screen.width / 3, Screen.height / 12), "<color=black><size=80>" + hourOneInput+hourTwoInput+minuteOneInput+minuteTwoInput  + "</size></color>");
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

	void kBoard0()
	{
		// keyboard = iPhoneKeyboard.Open( tempName : String, iPhoneKeyboardType.Default, Auto-Correct, Multi-Line, Secure, Alert, "Text Placeholder" );
		
		if ( !iskBoardOpen ) 
		{
	        keyboard = TouchScreenKeyboard.Open( tempString, TouchScreenKeyboardType.PhonePad, false, false, false, false, "Default Keyboard" );
			iskBoardOpen = true;
		}
		
		if ( keyboard.done ) 
		  {
			kBoardString = keyboard.text;
			tempString = "";
			iskBoardOpen = false;
		}
		else
		{
			kBoardString = keyboard.text;
		}
	}

}
