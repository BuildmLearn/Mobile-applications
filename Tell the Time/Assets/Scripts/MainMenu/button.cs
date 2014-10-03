using UnityEngine;
using System.Collections;

public class button : MonoBehaviour {

	// Use this for initialization
	void Start () {
		GUI.backgroundColor = new Color(0,0,0,0);

	}
	
	// Update is called once per frame
	void Update () {
		if (Input.GetKeyDown(KeyCode.Escape)) 
			Application.Quit(); 


		if (Input.touchCount > 0) {
						if (Input.GetTouch (0).phase == TouchPhase.Began) {			
				
								Vector3 pos = Camera.main.ScreenToWorldPoint (Input.GetTouch (0).position);
								//Debug.Log (Input.mousePosition);
								RaycastHit2D hit = Physics2D.Raycast (pos, Vector2.zero);
								if (hit != null && hit.collider != null) {
										switch (hit.collider.name) {
										case "PlayButton":
												hit.collider.name = "";
												Application.LoadLevel ("StageSelect");
												break;
										case "AboutButton":
												hit.collider.name = "";
												Application.LoadLevel ("About");
												break;
										/*case "RankButton":
				hit.collider.name = "";
				Application.LoadLevel ("highScore");
				break;
			case "ConfigButton":
				hit.collider.name = "";
				//Application.LoadLevel ("configScene");
				break;*/
										}
								}
						}
				}
		}
}
