using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Dart : MonoBehaviour {

	public Camera c;
	public GameObject dartRef;
	public GameObject life0;
	public GameObject life1;
	public GameObject life2;
	public GameObject life3;
	public GameObject life4;

	public float originalX = 3.98F;
	public float originalY = -0.31F;
	public float originalZ = 5.62F;
	public float speed = 0.1F;
	private Vector3 originPosition;
    public int count = 0;

    public GameObject dartboard;

	// Use this for initialization
	void Start () {
        count = 0;
        originalX = transform.position.x;
		originalY = transform.position.y;
		originalZ = transform.position.z;
        
    }
	
	bool readyToThrow;

	// Update is called once per frame
	void Update () {
        if (Input.touchCount > 0 && gameObject.tag != "done")
        {
			switch(Input.GetTouch(0).phase) {
				/*case TouchPhase.Moved: {
					if(readyToThrow) {
						shake();
					} else {
						// Get movement of the finger since last frame
						Vector2 touchDeltaPosition = Input.GetTouch(0).deltaPosition;
						float z = - touchDeltaPosition.y * speed;
						if(transform.position.z > 0 && z > 0 && !readyToThrow) {
							transform.Translate(0, 0, z);
							Debug.Log("Z = " + transform.position.z);
							if(transform.position.z <=0 ) {
								transform.position = new Vector3(transform.position.x, transform.position.y, 0);
								readyToThrow = true;
							}
						}
					}
				}
				break;
				case TouchPhase.Stationary: {
					if(readyToThrow) {
						shake();
					}
				}
				break;*/
				case TouchPhase.Ended: {
					throwDart();
				}
				break;
			}   
        }
    }

	public void resetGame() {
		//transform.position = new Vector3(originalX, originalY, originalZ);
		readyToThrow = false;
		originPosition = Vector3.zero;
	}

	public void setObjActive() {
		dartRef.SetActive(true);
		Debug.Log("SET ACTIVE TRUE");
	}
	public void setObjInactive() {
		dartRef.SetActive(false);
		Debug.Log("SET ACTIVE FALSE");
	}

	public float shake_intensity = .1f;

	private void shake() {	
		if(originPosition == Vector3.zero) {
			originPosition = transform.position;
		}
		Debug.Log("SHAKE");

		float step = shake_intensity * Time.deltaTime;
		transform.position = Vector3.MoveTowards(transform.position, originPosition + Random.insideUnitSphere, step);

		//transform.position = originPosition + Random.insideUnitSphere * shake_intensity;
	}

	private void throwDart() {
        
        if (arr.Count > 10) {
			var dartToRemove = arr[0];
			arr.RemoveAt(0);
			Destroy(dartToRemove);
		}

		Ray ray = c.ViewportPointToRay(new Vector3(0.5F, 0.5F, 0));
		RaycastHit hit;
		if (Physics.Raycast(ray, out hit)) {
			switch(count) {
				case 0: {
					life0.SetActive(false);
				}
				break;
				case 1: {
					life1.SetActive(false);
				}
				break;
				case 2: {
					life2.SetActive(false);
				}
				break;
				case 3: {
					life3.SetActive(false);
				}
				break;
				case 4: {
					life4.SetActive(false);
				}
				break;
			}
			count++;
			
			Vector3 hitPoint = hit.point;
            Quaternion rotation = dartRef.transform.rotation;
			GameObject newDart = Instantiate(gameObject, hit.point, rotation);
			newDart.transform.SetParent(dartboard.transform, true);
			newDart.transform.position = new Vector3(hitPoint.x, hitPoint.y + 2, hitPoint.z);
			float scale = 0.3F;
			newDart.transform.localScale = new Vector3(scale, scale, scale);
			newDart.gameObject.tag = "done";
			arr.Add(newDart);
			resetGame();
		}
		else
			Debug.Log("FAILED");

		if(count == 5)
        {
            new LoadSceneOnClick().Next();
			destroyDarts();
            return;
        }
	}

	private void destroyDarts() {
		arr.ForEach(dart => {
			Destroy(dart);
		});
		arr.Clear();
	}

	private List<GameObject> arr = new List<GameObject>();
}
