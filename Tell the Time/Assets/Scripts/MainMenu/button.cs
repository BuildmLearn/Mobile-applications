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

		Vector3 pos = Camera.main.ScreenToWorldPoint (Input.GetTouch(0).position);
		RaycastHit2D hit = Physics2D.Raycast(pos, Vector2.zero);
		if (hit != null && hit.collider != null) 
		{
			switch(hit.collider.name)
			{
			case "PlayButton":
				hit.collider.name = "";
				Application.LoadLevel ("StageSelect");
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
