﻿using UnityEngine;
using System.Collections;

public class StageSelectButtonControl : MonoBehaviour {
	bool touched = false;
	public GUIText stage0text;
	public GUIText stage1text;
	public GUIText stage2text;
	public GUIText stage3text;
	public GUIText stage4text;
	public GUIText stage5text;
	public GUIText stage6text;
	public GUIText stage7text;
	public GUIText stage8text;
	public GUIText stage9text;

	public GameObject lock0;
	public GameObject lock1;
	public GameObject lock2;
	public GameObject lock3;
	public GameObject lock4;
	public GameObject lock5;	
	public GameObject lock6;
	public GameObject lock7;
	public GameObject lock8;
	public GameObject lock9;


	// Use this for initialization
	void Start () {

	}
	
	// Update is called once per frame
	void Update () {
		if (Input.GetKeyDown(KeyCode.Escape)) 
		{ Application.LoadLevel("mainMenu"); }

		updateScore ();
		//unlockLevel ();
		if (Input.touchCount > 0) {
						if (Input.GetTouch (0).phase == TouchPhase.Began) {			

								Vector3 pos = Camera.main.ScreenToWorldPoint (Input.GetTouch (0).position);
								//Debug.Log (Input.mousePosition);
								RaycastHit2D hit = Physics2D.Raycast (pos, Vector2.zero);
								if (hit != null && hit.collider != null) {
										switch (hit.collider.name) {
										case "Stage0":
												hit.collider.name = "";
												Debug.Log ("hit stage0");
												Application.LoadLevel ("stage0_loading");
												break;
										case "Stage1":
												hit.collider.name = "";
												Debug.Log ("hit stage1");				
												Application.LoadLevel ("stage1_loading");
				/*if (PlayerPrefs.GetInt ("Stage0HighScore") > 1)
				{
					Application.LoadLevel ("stage1_loading");
				}*/
												break;
										/*case "Stage2":
				hit.collider.name = "";
				Debug.Log ("hit stage2");
				if (PlayerPrefs.GetInt ("Stage1HighScore") > 1) 
				{
					Application.LoadLevel ("stage2_loading");
				}
				break;
			case "Stage3":
				hit.collider.name = "";
				Debug.Log ("hit stage3");
				if (PlayerPrefs.GetInt ("Stage2HighScore") > 1) 
				{
					Application.LoadLevel ("stage3_loading");
				}
				break;
			case "Stage4":
				hit.collider.name = "";
				Debug.Log ("hit stage4");
				if (PlayerPrefs.GetInt ("Stage3HighScore") > 1) 
				{
					Application.LoadLevel ("stage4_loading");
				}
				break;
			case "Stage5":
				hit.collider.name = "";
				Debug.Log ("hit stage5");
				if (PlayerPrefs.GetInt ("Stage4HighScore") > 1) 
				{
					Application.LoadLevel ("stage5_loading");
				}
				break;*/
										}

								}
						}
				}

	}

	void unlockLevel()
	{
		if (PlayerPrefs.GetInt ("Stage0HighScore") > 1) {
			lock1.active = false;
		}
		/*if (PlayerPrefs.GetInt ("Stage1HighScore") > 1) {
			lock2.active = false;
		}
		if (PlayerPrefs.GetInt ("Stage2HighScore") > 1) {
			lock3.active = false;
		}
		if (PlayerPrefs.GetInt ("Stage3HighScore") > 1) {
			lock4.active = false;
		}
		if (PlayerPrefs.GetInt ("Stage4HighScore") > 1) {
			lock5.active = false;
		}
		if (PlayerPrefs.GetInt ("Stage5HighScore") > 1) {
			lock6.active = false;
		}
		if (PlayerPrefs.GetInt ("Stage6HighScore") > 1) {
			lock7.active = false;
		}
		if (PlayerPrefs.GetInt ("Stage7HighScore") > 1) {
			lock8.active = false;
		}
		if (PlayerPrefs.GetInt ("Stage8HighScore") > 1) {
			lock9.active = false;
		}*/
	}

	void updateScore()
	{
		if (PlayerPrefs.GetInt ("Stage0HighScore") != 0) {
			stage0text.text = calculateScore(PlayerPrefs.GetInt ("Stage0HighScore")		);
				} else {
			stage0text.text = "";
				}
		if (PlayerPrefs.GetInt ("Stage1HighScore") != 0) {
			stage1text.text = calculateScore(PlayerPrefs.GetInt ("Stage1HighScore")		);
		}
		else {
			stage1text.text = "";
		}

		if (PlayerPrefs.GetInt ("Stage2HighScore") != 0) {
			stage2text.text = calculateScore(PlayerPrefs.GetInt ("Stage2HighScore")		);
		}
		else {
			stage2text.text = "";
		}
		if (PlayerPrefs.GetInt ("Stage3HighScore") != 0) {
			stage3text.text = calculateScore(PlayerPrefs.GetInt ("Stage3HighScore")	);
		}
		else {
			stage3text.text = "";
		}
		if (PlayerPrefs.GetInt ("Stage4HighScore") != 0) {
			stage4text.text = calculateScore(PlayerPrefs.GetInt ("Stage4HighScore")		);
		}
		else {
			stage4text.text = "";
		}
		if (PlayerPrefs.GetInt ("Stage5HighScore") != 0) {
			stage5text.text = calculateScore(PlayerPrefs.GetInt ("Stage5HighScore")		);
		}
		else {
			stage5text.text = "";
		}
		if (PlayerPrefs.GetInt ("Stage6HighScore") != 0) {
			stage6text.text = calculateScore(PlayerPrefs.GetInt ("Stage6HighScore")		);
		}
		else {
			stage6text.text = "";
		}
		if (PlayerPrefs.GetInt ("Stage7HighScore") != 0) {
			stage7text.text = calculateScore(PlayerPrefs.GetInt ("Stage7HighScore")		);
		}
		else {
			stage7text.text = "";
		}
		if (PlayerPrefs.GetInt ("Stage8HighScore") != 0) {
			stage8text.text = calculateScore(PlayerPrefs.GetInt ("Stage8HighScore")		);
		}
		else {
			stage8text.text = "";
		}
		if (PlayerPrefs.GetInt ("Stage9HighScore") != 0) {
			stage9text.text = calculateScore(PlayerPrefs.GetInt ("Stage9HighScore")		);
		}
		else {
			stage9text.text = "";
		}

	}
	string calculateScore(int nWin)
	{
		switch (nWin) {
			
		case 1: return "F";
			break;
		case 2: return "E";
			break;
		case 3: return "D";
			break;
		case 4: return "C";
			break;
		case 5: return "B";
			break;
		case 6: return "A";
			break;
			
		}
		return "F";
	}


}
