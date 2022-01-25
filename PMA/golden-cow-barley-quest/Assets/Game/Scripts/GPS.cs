using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class GPS : MonoBehaviour
{
    public static string GPS_NOT_ENABLED = "GPS NOT ENABLED";
    public static string GPS_TIMED_OUT = "GPS TIMED OUT";
    public static string GPS_FAILED = "UNABLE TO DETERMINE GPS LOCATION";
    public static int TIME_OUT = 20;

    public static GPS Instance { get; set; }

    private bool hasStartedLocationServices = false;

    private void Start()
    {
        Instance = this;
        DontDestroyOnLoad(gameObject);
        StartCoroutine(StartLocationService());
    }

    private IEnumerator StartLocationService()
    {
        if (!Input.location.isEnabledByUser)
        {
            Debug.Log(GPS_NOT_ENABLED);
            yield break;
        }

        Input.location.Start(1f, 1f);

        int maxWait = TIME_OUT;
        while (Input.location.status == LocationServiceStatus.Initializing && maxWait > 0)
        {
            yield return new WaitForSeconds(1);
            maxWait--;
        }

        if (maxWait <= 0)
        {
            Debug.Log(GPS_TIMED_OUT);
            yield break;
        }

        if (Input.location.status == LocationServiceStatus.Failed)
        {
            Debug.Log(GPS_FAILED);
            yield break;
        }

        hasStartedLocationServices = true;
        Debug.Log("GPS STARTED");
        setCoordinates();

        yield break;
    }

    void setCoordinates()
    {
        if (hasStartedLocationServices)
        {
            World.Instance.CurrentLocation = new Point { Latitude = Input.location.lastData.latitude, Longitude = Input.location.lastData.longitude };
            Debug.Log("GPS UPDATED");
        }
    }

    void Update()
    {
        setCoordinates();
    }
}
