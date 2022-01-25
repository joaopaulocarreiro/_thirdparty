using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class World : MonoBehaviour
{
    public const double R = 6371;
    public static World Instance { get; set; }

    public Point CurrentLocation { get; set; }
    public Level CurrentLevel { get; set; }
    private double _Distance { get; set; }
    private double _Angle { get; set; }

    public GameObject Camera;
    public GameObject HeadingIndicator;

    public GameObject Portas;
    public GameObject Royal;
    public GameObject SM;
    public GameObject UM;
    public GameObject Cantinho;

    public Text Tip;
    public Text Title;

    public Text Location;
    public Text Target;
    public TMPro.TextMeshProUGUI Distance;

    // Use this for initialization
    void Start()
    {
        Instance = this;
        DontDestroyOnLoad(gameObject);
        CurrentLocation = new Point { Latitude = 0, Longitude = 0 };
        CurrentLevel = Settings.GetCurrentLevel();
        Title.text = string.Empty;//CurrentLevel.Name;

        Portas.SetActive(false);
        Royal.SetActive(false);
        SM.SetActive(false);
        UM.SetActive(false);
        Cantinho.SetActive(false);

        switch (CurrentLevel.Number)
        {
            case 1:
                {
                    Portas.SetActive(true);
                    break;
                }
            case 2:
                {
                    Royal.SetActive(true);
                    break;
                }
            case 3:
                {
                    SM.SetActive(true);
                    break;
                }
            case 4:
                {
                    UM.SetActive(true);
                    break;
                }
            case 5:
                {
                    Cantinho.SetActive(true);
                    break;
                }
        }
    }

    void Update()
    {
        _Distance = CalculateDistance();
        _Angle = CalculateAngle();
        float _CameraRotation = (Camera.transform.rotation.eulerAngles.y + Gyro.Instance._calibrationYAngle) % 360;

        Location.text = string.Format("Location: {0}, {1}", CurrentLocation.Latitude, CurrentLocation.Longitude);
        Target.text = string.Format("Target: {0}, {1}", CurrentLevel.Target.Latitude, CurrentLevel.Target.Longitude);
        Distance.text = string.Format("{0}m", Math.Round(_Distance));
        Tip.text = _Distance <= 30 ? CurrentLevel.Tip : string.Empty;

        var diff = Math.Abs(_CameraRotation - _Angle);

        Color color = new Color();

        if (diff > 35)
        {
            color = new Color(1, 0, 0);
        }
        if (diff <= 35)
        {
            color = new Color(1, 1, 0);
        }
        if (diff <= 15)
        {
            color = new Color(0, 1, 0);
        }

        HeadingIndicator.GetComponent<Renderer>().material.SetColor("_Color", color);
    }

    public static double Radians(double degrees)
    {
        return degrees * Math.PI / 180;
    }

    private static double Degrees(double rads)
    {
        return rads * (180.0 / Math.PI);
    }

    public double CalculateDistance()
    {
        double delataSigma = Math.Acos(Math.Sin(Radians(CurrentLocation.Latitude)) * Math.Sin(Radians(CurrentLevel.Target.Latitude)) +
                Math.Cos(Radians(CurrentLocation.Latitude)) * Math.Cos(Radians(CurrentLevel.Target.Latitude)) * Math.Cos(Math.Abs(Radians(CurrentLevel.Target.Longitude) - Radians(CurrentLocation.Longitude))));

        double distance = R * delataSigma;

        return distance * 1000;
    }

    private double CalculateAngle()
    {
        double dLon = (CurrentLevel.Target.Longitude - CurrentLocation.Longitude);

        double y = Math.Sin(dLon) * Math.Cos(CurrentLevel.Target.Latitude);
        double x = Math.Cos(CurrentLocation.Latitude) * Math.Sin(CurrentLevel.Target.Latitude) - Math.Sin(CurrentLocation.Latitude)
                * Math.Cos(CurrentLevel.Target.Latitude) * Math.Cos(dLon);

        double brng = Math.Atan2(y, x);

        brng = Degrees(brng);
        brng = (brng + 360) % 360;

        return brng;
    }
}
