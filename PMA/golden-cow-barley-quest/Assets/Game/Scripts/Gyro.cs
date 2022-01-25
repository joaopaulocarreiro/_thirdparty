using System.Collections;
using UnityEngine;
using UnityEngine.UI;

public class Gyro : MonoBehaviour
{
    // Singleton
    public static Gyro Instance { get; set; }

    // STATE
    private float _initialYAngle = 0f;
    private float _appliedGyroYAngle = 0f;
    public float _calibrationYAngle = 0f;
    private Transform _rawGyroRotation;
    private float _tempSmoothing;

    // The Camera Object
    public GameObject Camera;

    // SETTINGS
    [SerializeField] private float _smoothing = 0f;

    private IEnumerator Start()
    {
        Input.gyro.enabled = true;

        Instance = this;
        DontDestroyOnLoad(gameObject);
        _initialYAngle = Camera.transform.eulerAngles.y;

        _rawGyroRotation = new GameObject("GyroRaw").transform;
        _rawGyroRotation.position = Camera.transform.position;
        _rawGyroRotation.rotation = Camera.transform.rotation;

        // Wait until gyro is active, then calibrate to reset starting rotation.
        yield return new WaitForSeconds(1);

        StartCoroutine(CalibrateYAngle());
    }

    private void Update()
    {
        ApplyGyroRotation();
        ApplyCalibration();

        Camera.transform.rotation = Quaternion.Slerp(Camera.transform.rotation, _rawGyroRotation.rotation, _smoothing);
    }

    private IEnumerator CalibrateYAngle()
    {
        _tempSmoothing = _smoothing;
        _smoothing = 1;
        _calibrationYAngle = _appliedGyroYAngle - _initialYAngle; // Offsets the y angle in case it wasn't 0 at edit time.
        yield return null;
        _smoothing = _tempSmoothing;
    }

    private void ApplyGyroRotation()
    {
        _rawGyroRotation.rotation = Input.gyro.attitude;
        _rawGyroRotation.Rotate(0f, 0f, 180f, Space.Self); // Swap "handedness" of quaternion from gyro.
        _rawGyroRotation.Rotate(90f, 180f, 0f, Space.World); // Rotate to make sense as a camera pointing out the back of your device.
        _appliedGyroYAngle = _rawGyroRotation.eulerAngles.y; // Save the angle around y axis for use in calibration.
    }

    private void ApplyCalibration()
    {
        _rawGyroRotation.Rotate(0f, -_calibrationYAngle, 0f, Space.World); // Rotates y angle back however much it deviated when calibrationYAngle was saved.
    }

    public void SetEnabled(bool value)
    {
        enabled = true;
        StartCoroutine(CalibrateYAngle());
    }
}