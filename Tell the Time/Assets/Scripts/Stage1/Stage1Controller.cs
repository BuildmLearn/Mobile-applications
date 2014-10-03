using UnityEngine;
using System.Collections;

public class Stage1Controller : MonoBehaviour {

	public GUIStyle answerButtonStyle;
	public GUIStyle answerBoxStyle;

	string[] clock = new string[4];

	public int numStage;	

	public Texture clockTexture;

	public GameObject answer1;
	public GameObject answer2;
	public GameObject answer3;
	public GameObject answer4;
	
	public GameObject sun_rise;
	public GameObject sun_set;
	public GameObject night_time;
	public GameObject afternoon;
	public GameObject circle;
	public GameObject cross;

	public GameObject pauseMenuBackground;

	public Texture homeButton;
	public Texture resumeButton;

	bool paused = false;
	bool touchEnabled = true;
	int currentRound = 0;
	int answerPos;
	int puzzle; 
	int nWin = 1;
		// Use this for initialization
	void Start () {
		if (Screen.width > 720) {
						answerButtonStyle.fontSize = 90;		
				} else if (Screen.width > 540) {
						answerButtonStyle.fontSize = 60;
				} else {
			answerButtonStyle.fontSize = 30;

				}
		circle.active = false;
		cross.active = false;
		answer1.active = false;
		answer2.active = false;
		answer3.active = false;
		answer4.active = false;
		createPuzzle ();
	}
	
	// Update is called once per frame
	void Update () {
		if (Input.GetKeyDown(KeyCode.Escape)) 
		{ 
			Application.LoadLevel("StageSelect"); 
		}



	}
	void OnGUI(){
		//GUI.backgroundColor = Color.clear;

		if (GUI.Button (new Rect (Screen.width/14, (4.3f / 6.5f) * Screen.height, Screen.width/2-Screen.width/7, Screen.height / 10), clock [0],answerButtonStyle)&&touchEnabled==true) {
			StartCoroutine(checkWin (0));
		}
		//GUI.Label (new Rect (Screen.width/2-Screen.width/2.5f, (4.3f/6.5f)*Screen.height, Screen.width/3,Screen.height/12),"<color=white><size=45>"+clock[0]+"</size></color>");

		if (GUI.Button (new Rect (Screen.width/2+Screen.width/14, (4.3f / 6.5f) * Screen.height, Screen.width / 2-Screen.width/7, Screen.height / 10), clock [1],answerButtonStyle)&&touchEnabled==true) {
			StartCoroutine(checkWin (1));
		}
		//GUI.Label (new Rect (Screen.width/2+Screen.width/11, (4.3f/6.5f)*Screen.height, Screen.width/3,Screen.height/12),"<color=white><size=45>"+clock[1]+"</size></color>");

		if (GUI.Button (new Rect (Screen.width/14, (5.7f / 6.5f) * Screen.height, Screen.width / 2-Screen.width/7, Screen.height / 10), clock [2],answerButtonStyle)&&touchEnabled==true) {
			StartCoroutine(checkWin (2));
		}
		//GUI.Label (new Rect (Screen.width/2-Screen.width/2.5f, (5.7f/6.5f)*Screen.height, Screen.width/3,Screen.height/12),"<color=white><size=45>"+clock[2]+"</size></color>");

		if (GUI.Button (new Rect (Screen.width / 2 +Screen.width/14, (5.7f / 6.5f) * Screen.height, Screen.width / 2-Screen.width/7, Screen.height / 10), clock [3],answerButtonStyle)&&touchEnabled==true) {
			StartCoroutine(checkWin (3));
		}

		/*if (GUI.Button (new Rect (Screen.width/4, Screen.height/2, Screen.width/2, Screen.width/4), homeBtnStyle)) {
						Application.LoadLevel ("StageSelect"); 
				}*/
		//GUILayout.Button ("", homeBtnStyle);

		/*if (paused == true) {
			// Make a background box

			GUI.Box (new Rect (Screen.width/6,Screen.height/2,Screen.width/3,Screen.height/3), "");
			//pauseMenuBackground.active = true;

			// Make the first button. If it is pressed, Application.Loadlevel (1) will be executed
			GUI.backgroundColor = Color.red;
			if (GUI.Button (new Rect (Screen.width/8, Screen.height/2, Screen.width/4, Screen.width/8),"<color=white><size=45>" + "HOME" + "</size></color>")) {
				Application.LoadLevel ("StageSelect"); 
			}
			// Make the second button.
	

		}*/
		//GUI.Label (new Rect (Screen.width/2+Screen.width/11, (5.7f/6.5f)*Screen.height, Screen.width/3,Screen.height/12),"<color=white><size=45>"+clock[3]+"</size></color>");

	}

	IEnumerator checkWin(int answer)
	{
		touchEnabled = false;
		if (answerPos == answer) 
		{
			circle.active = true;	
			nWin++;
		} 
		else 
		{
			cross.active = true;
			switch(answerPos)
			{
			case 0: answer1.active=true;
				//GUI.Button (new Rect (5, (4.3f / 6.5f) * Screen.height, Screen.width/2-10, Screen.height / 10), "",answerBoxStyle);
					break;
			case 1: answer2.active=true;
				//GUI.Button (new Rect (Screen.width/2+5, (4.3f / 6.5f) * Screen.height, Screen.width / 2-10, Screen.height / 10),"",answerBoxStyle);
				break;
			case 2: answer3.active=true;
				//GUI.Button (new Rect (5, (5.7f / 6.5f) * Screen.height, Screen.width / 2-10, Screen.height / 10), "",answerBoxStyle);
				break;
			case 3: answer4.active=true;
				//GUI.Button (new Rect (Screen.width / 2 +5, (5.7f / 6.5f) * Screen.height, Screen.width / 2-10, Screen.height / 10), "",answerBoxStyle);
				break;
		
			}
		}
		currentRound++;
		yield return new WaitForSeconds(1);

		if(currentRound==5)
		{
			PlayerPrefs.SetInt("CurrentScore", nWin);
			if(PlayerPrefs.GetInt("Stage1HighScore")<nWin)
			{
				PlayerPrefs.SetInt("Stage1HighScore",nWin);
			}
			Application.LoadLevel("ScoreScene");
		}
		else{
			circle.active = false;
			cross.active = false;
			answer1.active = false;
			answer2.active = false;
			answer3.active = false;
			answer4.active = false;

			createPuzzle ();

		}
		//currentTime = (int)Random.Range (1.0F, 11.0F);
		touchEnabled = true;
	}




	void createPuzzle()
	{
		puzzle = Random.Range (0, 4);
		answerPos = Random.Range (0, 4);
		int time;
		switch (puzzle) {
		case 0: //sun rise
			for(int i=0;i<4;i++)
			{
				if(i!=answerPos)
				{
					time = (int)Random.Range(1,24);
					while(time>5&&time<9)
					{
						time = (int)Random.Range(1,24);
					}
				}
				else 
				{
					time = (int)Random.Range(6,9);
				}
				clock[i] = time + ":00";
			}
			sun_rise.active = true;
			sun_set.active = false;
			night_time.active = false;
			afternoon.active = false;
			break;
		case 1://sun set
			for(int i=0;i<4;i++)
			{
				if(i!=answerPos)
				{
					time = (int)Random.Range(1,24);
					while(time>16&&time<20)
					{
						time = (int)Random.Range(1,24);
					}
				}
				else 
				{
					time = (int)Random.Range(17,20);
				}
				clock[i] = time + ":00";
			}
			sun_rise.active = false;
			sun_set.active = true;
			night_time.active = false;
			afternoon.active = false;
			break;
		case 2: //night time
			for(int i=0;i<4;i++)
			{
				if(i!=answerPos)
				{
					time = (int)Random.Range(6,20);
				}
				else 
				{
					time = (int)Random.Range(20,24);
				}
				clock[i] = time + ":00";
			}
			sun_rise.active = false;
			sun_set.active = false;
			night_time.active = true;
			afternoon.active = false;
			break;
		case 3://afternoon
			for(int i=0;i<4;i++)
			{
				if(i!=answerPos)
				{
					time = (int)Random.Range(1,24);
					while(time>8&&time<16)
					{
						time = (int)Random.Range(1,24);
					}
				}
				else 
				{
					time = (int)Random.Range(9,16);
				}
				clock[i] = time + ":00";
			}
			sun_rise.active = false;
			sun_set.active = false;
			night_time.active = false;
			afternoon.active = true;
			break;
		}
	}

}
