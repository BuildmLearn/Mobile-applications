using UnityEngine;
using System.Collections;

public class Loading : MonoBehaviour {

	// Use this for initialization
	void Start () {
		Time.timeScale = 1;
	}
	
	// Update is called once per frame
	void Update () {
		countDown ();
	}

	IEnumerator countDown()
	{
		yield  return new WaitForSeconds(1);
		Application.LoadLevel ("stage0");
	}
}
