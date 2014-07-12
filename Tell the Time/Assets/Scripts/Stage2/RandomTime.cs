using UnityEngine;
using System.Collections;

public class RandomTime : MonoBehaviour {
	public GameObject longHand,shortHand;	
	int time;
	// Use this for initialization
	void Start () {
		time = (int)Random.Range (1.0F, 11.0F);
		shortHand.transform.Rotate (0, 0, time*-30);
	}
	
	// Update is called once per frame
	void Update () {
	
	}
}
