using UnityEngine;
using System.Collections;

public class Stage1Loading : MonoBehaviour {

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
		/*if (Input.GetMouseButtonDown (0)) {
			Application.LoadLevel("stage1");		
		}*/
		if (Input.touchCount > 0) {
			if (Input.GetTouch (0).phase == TouchPhase.Began) {	
				Application.LoadLevel("stage1");		
			}
		}
	}
}
