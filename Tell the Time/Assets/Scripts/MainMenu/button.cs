using UnityEngine;
using System.Collections;

public class button : MonoBehaviour {

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
				if (Input.touches.Length <= 0) {
						// if no touches
				} else {

						for (int i=0; i<Input.touchCount; i++) {
								//execute current touch on screen
								if (this.guiTexture.HitTest (Input.GetTouch (i).position)) {
										//if current touch hit our guitexture run this code
										if (Input.GetTouch (i).phase == TouchPhase.Began) {
												switch (this.name) {
												case "PlayButton":
														Application.LoadLevel ("StageSelect");
														break;
												case "StatButton":
														break;
												case "ConfigButton":
														break;
												}
												//Debug.Log("The touch is down " + this.name);
					
										}

								}
						}
				}
		}
}
