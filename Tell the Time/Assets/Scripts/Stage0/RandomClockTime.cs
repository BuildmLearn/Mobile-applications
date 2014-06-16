using UnityEngine;
using System.Collections;

public class RandomClockTime : MonoBehaviour {

	public int time;
	public GUIText timeText;
	public GUIStyle myGUIStyle;
	// Use this for initialization
	void Start () {
		time = (int)Random.Range (1.0F, 11.0F);
		timeText.text = time + ".00";
	}
	
	// Update is called once per frame
	void Update () {

	}

}
