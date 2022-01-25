using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Settings : MonoBehaviour
{
    public static Settings Instance { get; set; }
    public static Level DefaultLevel = LoadSceneOnClick.LevelMap[1];
    private static string TARGET_LATITUDE = "targetLatitude";
    private static string TARGET_LONGITUDE = "targetLongitude";
    private static string LEVEL_NAME = "levelName";
    private static string LEVEL_TIP = "levelTip";
    private static string LEVEL_NUMBER = "levelNumber";
    private static string LEVEL_TRIGGER_NAME = "levelTriggerName";

    private static string LEVEL_CLUE_INDEX = "levelClueIndex";
    private static string LEVEL_PUZZLE_INDEX = "levelPuzzelIndex";
    private static string LEVEL_PRIZE_INDEX = "levelPrizeIndex";

    private static string STEP = "sequenceStep";

    void Start()
    {
        Instance = this;
        DontDestroyOnLoad(gameObject);
    }

    public static Level GetCurrentLevel()
    {        
        string levelName = PlayerPrefs.GetString(LEVEL_NAME, DefaultLevel.Name);

        string levelTip = PlayerPrefs.GetString(LEVEL_TIP, DefaultLevel.Tip);

        int levelNumber = PlayerPrefs.GetInt(LEVEL_NUMBER, DefaultLevel.Number);

        string levelTriggername = PlayerPrefs.GetString(LEVEL_TRIGGER_NAME, DefaultLevel.TriggerName);

        Point Target = new Point
        {
            Latitude = PlayerPrefs.GetFloat(TARGET_LATITUDE, DefaultLevel.Target.Latitude),
            Longitude = PlayerPrefs.GetFloat(TARGET_LONGITUDE, DefaultLevel.Target.Longitude)
        };

        int levelClueIndex = PlayerPrefs.GetInt(LEVEL_CLUE_INDEX, DefaultLevel.ClueSceneIndex);
        int levelPuzzleIndex = PlayerPrefs.GetInt(LEVEL_PUZZLE_INDEX, DefaultLevel.PuzzleSceneIndex);
        int levelPrizeIndex = PlayerPrefs.GetInt(LEVEL_PRIZE_INDEX, DefaultLevel.PrizeSceneIndex);

        Level level = new Level()
        {
            Number = levelNumber,
            Name = levelName,
            TriggerName = levelTriggername,
            Tip = levelTip,
            Target = Target,
            ClueSceneIndex = levelClueIndex,
            PuzzleSceneIndex = levelPuzzleIndex,
            PrizeSceneIndex = levelPrizeIndex
        };

        SetCurrentLevel(level);

        return level;
    }

    public static void SetCurrentLevel(Level level)
    {
        PlayerPrefs.SetString(LEVEL_NAME, level.Name);
        PlayerPrefs.SetString(LEVEL_TRIGGER_NAME, level.TriggerName);
        PlayerPrefs.SetString(LEVEL_TIP, level.Tip);
        PlayerPrefs.SetInt(LEVEL_NUMBER, level.Number);

        PlayerPrefs.SetInt(LEVEL_CLUE_INDEX, level.ClueSceneIndex);
        PlayerPrefs.SetInt(LEVEL_PUZZLE_INDEX, level.PuzzleSceneIndex);
        PlayerPrefs.SetInt(LEVEL_PRIZE_INDEX, level.PrizeSceneIndex);

        PlayerPrefs.SetFloat(TARGET_LATITUDE, level.Target.Latitude);
        PlayerPrefs.SetFloat(TARGET_LONGITUDE, level.Target.Longitude);
    }

    public static int GetStep()
    {
        return PlayerPrefs.GetInt(STEP, 0);
    }

    public static void SetStep(int step)
    {
        PlayerPrefs.SetInt(STEP, step);
    }
}
