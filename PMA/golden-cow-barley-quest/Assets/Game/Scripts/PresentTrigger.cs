using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PresentTrigger : MonoBehaviour {

    public Camera camera;
    public GameObject trigger;

	// Use this for initialization
	void PlayNextScene()
    {
        LoadSceneOnClick a = new LoadSceneOnClick();

        a.Next();
    }

    void Update()
    {
        if(Input.touchCount > 0 && Input.touches[0].phase == TouchPhase.Began)
        {
            Ray ray = camera.ScreenPointToRay(Input.GetTouch(0).position);
            RaycastHit Hit;

            if(Physics.Raycast(ray, out Hit)) {
                GameObject objHit = Hit.transform.gameObject;
                Level level = Settings.GetCurrentLevel();

                if (ReferenceEquals(objHit, trigger) && objHit.name.Equals(level.TriggerName))
                {
                    PlayNextScene();
                }
            }
        }
    }
}
