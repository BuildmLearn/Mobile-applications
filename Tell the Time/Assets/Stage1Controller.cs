using UnityEngine;
using System.Collections;

public class Stage1Controller : MonoBehaviour {
	string[] clock = new string[4];

	public Texture clockTexture;
	public GameObject sun_rise;
	public GameObject sun_set;
	public GameObject night_time;
	public GameObject afternoon;

	// Use this for initialization
	void Start () {
		createPuzzle ();
	}
	
	// Update is called once per frame
	void Update () {
		if (Input.GetKeyDown(KeyCode.Escape)) { Application.LoadLevel("StageSelect"); }

	}
	void OnGUI(){
		GUI.backgroundColor = Color.clear;

		GUI.Button (new Rect (Screen.width/2-Screen.width/2.5f, (4.3f/6.5f)*Screen.height, Screen.width/3,Screen.height/12),"<color=white><size=45>"+clock[0]+"</size></color>");
		//GUI.Label (new Rect (Screen.width/2-Screen.width/2.5f, (4.3f/6.5f)*Screen.height, Screen.width/3,Screen.height/12),"<color=white><size=45>"+clock[0]+"</size></color>");

		GUI.Button (new Rect (Screen.width/2+Screen.width/11, (4.3f/6.5f)*Screen.height, Screen.width/3,Screen.height/12),"<color=white><size=45>"+clock[1]+"</size></color>");
		//GUI.Label (new Rect (Screen.width/2+Screen.width/11, (4.3f/6.5f)*Screen.height, Screen.width/3,Screen.height/12),"<color=white><size=45>"+clock[1]+"</size></color>");

		GUI.Button (new Rect (Screen.width/2-Screen.width/2.5f, (5.7f/6.5f)*Screen.height, Screen.width/3,Screen.height/12),"<color=white><size=45>"+clock[2]+"</size></color>");
		//GUI.Label (new Rect (Screen.width/2-Screen.width/2.5f, (5.7f/6.5f)*Screen.height, Screen.width/3,Screen.height/12),"<color=white><size=45>"+clock[2]+"</size></color>");

		GUI.Button (new Rect (Screen.width/2+Screen.width/11, (5.7f/6.5f)*Screen.height, Screen.width/3,Screen.height/12),"<color=white><size=45>"+clock[3]+"</size></color>");
		//GUI.Label (new Rect (Screen.width/2+Screen.width/11, (5.7f/6.5f)*Screen.height, Screen.width/3,Screen.height/12),"<color=white><size=45>"+clock[3]+"</size></color>");

	}

	void createPuzzle()
	{
		int puzzle = Random.Range (0, 4);
		int answerPos = Random.Range (0, 4);
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
				clock[i] = time + ".00";
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
				clock[i] = time + ".00";
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
				clock[i] = time + ".00";
			}
			sun_rise.active = false;
			sun_set.active = false;
			night_time.active = true;
			afternoon.active = false;
			break;
		case 3:
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
				clock[i] = time + ".00";
			}
			sun_rise.active = false;
			sun_set.active = false;
			night_time.active = false;
			afternoon.active = true;
			break;
		}
	}
}
