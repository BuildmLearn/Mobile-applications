using UnityEngine;
using System.Collections;

public class StageSelectButtonControl : MonoBehaviour {
	bool touched = false;
	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
		if (Input.GetKeyDown(KeyCode.Escape)) { Application.LoadLevel("mainMenu"); }

		Vector3 pos = Camera.main.ScreenToWorldPoint (Input.GetTouch(0).position);
		//Debug.Log (Input.mousePosition);
		RaycastHit2D hit = Physics2D.Raycast(pos, Vector2.zero);
		if (hit != null && hit.collider != null) 
		{
			switch(hit.collider.name)
			{
			case "Stage0":
				hit.collider.name = "";
				Debug.Log ("hit stage0");
				Application.LoadLevel ("stage0_loading");
				break;
			case "Stage1":
				hit.collider.name = "";
				Debug.Log ("hit stage1");
				Application.LoadLevel ("stage1_loading");
				break;
			}
		}
		if (Input.GetKeyDown(KeyCode.Escape)) { 
			hit.collider.name = "";
			Application.LoadLevel("mainMenu"); }

	}
}
