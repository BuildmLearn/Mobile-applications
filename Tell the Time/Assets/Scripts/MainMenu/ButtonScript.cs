using UnityEngine;
using System.Collections;

public class ButtonScript : MonoBehaviour {

	public Texture playButtonTexture;
	public Texture statButtonTexture;
	public Texture configButtonTexture;
	
	void OnGUI() {
		GUI.backgroundColor = Color.clear;

		if (!playButtonTexture) {
			Debug.LogError("Please assign a texture on the inspector");
			return;
		}
		Rect playBtnRect = new Rect(Screen.width/2-Screen.width/6, (4.0f/6.5f)*Screen.height, Screen.width/3,Screen.height/12);
		if(GUI.Button(playBtnRect,playButtonTexture))
			Application.LoadLevel ("StageSelect");

		Rect statBtnRect = new Rect(Screen.width/2-Screen.width/6, (4.0f/6.5f)*Screen.height+Screen.height/12+10, Screen.width/3,Screen.height/12);
		Rect configBtnRect = new Rect(Screen.width/2-Screen.width/6, (4.0f/6.5f)*Screen.height+2*Screen.height/12+20, Screen.width/3,Screen.height/12);

		GUI.Button (statBtnRect, statButtonTexture);
		GUI.Button (configBtnRect, configButtonTexture);

		// To quit the application.
		if (Input.GetKeyDown(KeyCode.Escape)) { Application.Quit(); }
		

		//GUI.DrawTexture (playBtnRect, playButtonTexture);

		//if(GUI.Button(playBtnRect,playButtonTexture))
		//	Application.LoadLevel ("StageSelect");

		
	}

}
