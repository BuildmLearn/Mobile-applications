using UnityEngine;
using System.Collections;

public class Rotation : MonoBehaviour {

	public RaycastHit hit;
	public Ray ray;
	private Camera myCam;
	private Vector2 screenPos;
	private float   angleOffset;
	public GUIText test;
	  void Start () {
		myCam=Camera.main;
	}
	
	void Update () { 
		 //This fires only on the frame the button is clicked
		//if (Input.GetMouseButtonDown(0)) {
		if (Input.touchCount > 0)
		if (Input.GetTouch(0).phase == TouchPhase.Began) {			
			//RaycastHit2D hit = Physics2D.Raycast(Camera.main.ScreenToWorldPoint(Input.mousePosition), Vector2.zero);
			RaycastHit2D hit = Physics2D.Raycast(Camera.main.ScreenToWorldPoint(Input.GetTouch(0).position), Vector2.zero);

			if(hit.collider != null)
			{
				screenPos = myCam.WorldToScreenPoint (hit.collider.gameObject.transform.position);
				//Vector2 v3 = Input.mousePosition - screenPos;
				Vector2 v3 = Input.GetTouch(0).position - screenPos;

				angleOffset = (Mathf.Atan2 (hit.collider.gameObject.transform.right.y, hit.collider.gameObject.transform.right.x) - Mathf.Atan2 (v3.y, v3.x)) * Mathf.Rad2Deg;
			}

						
		}
		//This fires while the button is pressed down
		//if(Input.GetMouseButton(0)) {
		if (Input.touchCount > 0) //to detect if there is a touch to prevent getTouch from index out of bound
		if(Input.GetTouch(0).phase == TouchPhase.Moved) {

			//RaycastHit2D hit = Physics2D.Raycast(Camera.main.ScreenToWorldPoint(Input.mousePosition), Vector2.zero);
			RaycastHit2D hit = Physics2D.Raycast(Camera.main.ScreenToWorldPoint(Input.GetTouch(0).position), Vector2.zero);

			if(hit.collider != null)
			{
			//Vector2 v3 = Input.mousePosition - screenPos;
			Vector2 v3 = Input.GetTouch(0).position - screenPos;
			float angle = Mathf.Atan2(v3.y, v3.x) * Mathf.Rad2Deg;
			hit.collider.gameObject.transform.eulerAngles = new Vector3(0,0,angle+angleOffset); 
			}
		}
	}

	/*void OnMouseDown()
	{
		//test.text = "Hello";

		if(Input.GetMouseButtonDown(0)) {
			screenPos = myCam.WorldToScreenPoint (transform.position);
			Vector3 v3 = Input.mousePosition - screenPos;
			angleOffset = (Mathf.Atan2(transform.right.y, transform.right.x) - Mathf.Atan2(v3.y, v3.x))  * Mathf.Rad2Deg;
		}
		//This fires while the button is pressed down
		if(Input.GetMouseButton(0)) {
			Vector3 v3 = Input.mousePosition - screenPos;
			float angle = Mathf.Atan2(v3.y, v3.x) * Mathf.Rad2Deg;
			transform.eulerAngles = new Vector3(0,0,angle+angleOffset);  
		}


	}*/
}



