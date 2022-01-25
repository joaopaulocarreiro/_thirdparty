using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class LoadSceneOnClick : MonoBehaviour
{
    public static int NAVIGATION_INDEX = 16;

    public static int HOME_INDEX = 0;
    public static int STORY_INDEX = 17;

    public static Step[] Sequence = {

        // Level 1

        new Step() {
            Level = 1,
            Screen = TypeOfScreen.CLUE
        },
        new Step() {
            Level = 1,
            Screen = TypeOfScreen.NAVIGATION
        },
        new Step() {
            Level = 1,
            Screen = TypeOfScreen.PUZZLE
        },
        new Step() {
            Level = 1,
            Screen = TypeOfScreen.PRIZE
        },

        // Level 2

        new Step() {
            Level = 2,
            Screen = TypeOfScreen.CLUE
        },
        new Step() {
            Level = 2,
            Screen = TypeOfScreen.NAVIGATION
        },
        new Step() {
            Level = 2,
            Screen = TypeOfScreen.PUZZLE
        },
        new Step() {
            Level = 2,
            Screen = TypeOfScreen.PRIZE
        },

        // Level 3

        new Step() {
            Level = 3,
            Screen = TypeOfScreen.CLUE
        },
        new Step() {
            Level = 3,
            Screen = TypeOfScreen.NAVIGATION
        },
        new Step() {
            Level = 3,
            Screen = TypeOfScreen.PUZZLE
        },
        new Step() {
            Level = 3,
            Screen = TypeOfScreen.PRIZE
        },

        // Level 4

        new Step() {
            Level = 4,
            Screen = TypeOfScreen.CLUE
        },
        new Step() {
            Level = 4,
            Screen = TypeOfScreen.NAVIGATION
        },
        new Step() {
            Level = 4,
            Screen = TypeOfScreen.PUZZLE
        },
        new Step() {
            Level = 4,
            Screen = TypeOfScreen.PRIZE
        },

        // Level 5

        new Step() {
            Level = 5,
            Screen = TypeOfScreen.CLUE
        },
        new Step() {
            Level = 5,
            Screen = TypeOfScreen.NAVIGATION
        },
        new Step() {
            Level = 5,
            Screen = TypeOfScreen.PRIZE
        }
    };

    public static Dictionary<int, Level> LevelMap = new Dictionary<int, Level>()
    {
        { 1,
            new Level()
            {
                Number = 1,
                ClueSceneIndex = 1,
                PrizeSceneIndex = 6,
                PuzzleSceneIndex = 11,
                Name = "Portas da Cidade",
                TriggerName = "PortasPresent",
                Tip = "Look for Portas da Cidade",
                Target = new Point () { Latitude = 37.73938f, Longitude = -25.6683f }
            }
        },
        { 2,
            new Level()
            {
                Number = 2,
                ClueSceneIndex = 2,
                PrizeSceneIndex = 7,
                PuzzleSceneIndex = 12,
                Name = "Café Royal",
                TriggerName = "RoyalPresent",
                Tip = "Look for Café Royal's logo",
                Target = new Point () { Latitude = 37.7399f, Longitude = -25.66751f }
            }
        },
        { 3,
            new Level()
            {
                Number = 3,
                ClueSceneIndex = 3,
                PrizeSceneIndex = 8,
                PuzzleSceneIndex = 13,
                Name = "Sol-Mar",
                TriggerName = "SMPresent",
                Tip = "Look for Sol-Mar's Logo",
                Target = new Point () { Latitude = 37.74026f, Longitude = -25.66552f }
            }
        },
        { 4,
            new Level()
            {
                Number = 4,
                ClueSceneIndex = 4,
                PrizeSceneIndex = 9,
                PuzzleSceneIndex = 14,
                Name = "União Micaelense",
                TriggerName = "UMPresent",
                Tip = "Look for União Micaelense's Logo",
                Target = new Point () { Latitude = 37.74069f, Longitude = -25.6658f }
            }
        },
        { 5,
            new Level()
            {
                Number = 5,
                ClueSceneIndex = 5,
                PrizeSceneIndex = 10,
                PuzzleSceneIndex = 15,
                TriggerName = "11721_darboard_V4_L3",
                //TriggerName = "CantinhoPresent",
                Name = "Cantinho dos Anjos",
                Tip = "Look for Cantinho dos Anjos's Logo",
                Target = new Point () { Latitude = 37.74144f, Longitude = -25.66882f }
            }
        }
    };

    public void Home()
    {
        LoadByIndex(HOME_INDEX);
    }

    public void Story()
    {
        LoadByIndex(STORY_INDEX);
    }

    public void Next()
    {
        int currentStepIndex = Settings.GetStep();

        currentStepIndex++;

        if (currentStepIndex >= Sequence.Length)
        {
            Settings.SetStep(0);
            Home();
        }
        else
        {
            Step nextStep = Sequence[currentStepIndex];
            SetAndGetLevelByLevelNumber(nextStep.Level);
            Settings.SetStep(currentStepIndex);
            Play();
        }
    }

    public void Back()
    {
        int currentStepIndex = Settings.GetStep();

        currentStepIndex--;

        if (currentStepIndex < 0)
        {
            Settings.SetStep(0);
            Home();
        }
        else
        {
            Step nextStep = Sequence[currentStepIndex];
            SetAndGetLevelByLevelNumber(nextStep.Level);
            Settings.SetStep(currentStepIndex);
            Play();
        }
    }
    
    public void Play()
    {
        int currentStepIndex = Settings.GetStep();
        Step currentStep = Sequence[currentStepIndex];
        int currentLevel = currentStep.Level;
        switch (currentStep.Screen)
        {
            case TypeOfScreen.CLUE:
                {
                    PlayClue(currentLevel);
                }
                break;
            case TypeOfScreen.NAVIGATION:
                {
                    PlayNav();
                }
                break;
            case TypeOfScreen.PUZZLE:
                {
                    PlayPuzzle(currentLevel);
                }
                break;
            case TypeOfScreen.PRIZE:
                {
                    PlayPrize(currentLevel);
                }
                break;
        }
    }

    public void Restart()
    {
        int currentStep = 0;
        Settings.SetStep(currentStep);
        Play();
    }

    public void PlayWorkArround()
    {
        Level level = SetAndGetLevelByLevelNumber(1);

        PlayNav();
    }

    public void PlayClue(int levelNumber)
    {
        Level level = SetAndGetLevelByLevelNumber(levelNumber);

        LoadByIndex(level.ClueSceneIndex);
    }

    public void PlayNav()
    {
        LoadByIndex(NAVIGATION_INDEX);
    }

    public void PlayPuzzle(int levelNumber)
    {
        Level level = SetAndGetLevelByLevelNumber(levelNumber);

        LoadByIndex(level.PuzzleSceneIndex);
    }

    public void PlayPrize(int levelNumber)
    {
        Level level = SetAndGetLevelByLevelNumber(levelNumber);

        LoadByIndex(level.PrizeSceneIndex);
    }

    //
    // HELPERS
    //

    public Level SetAndGetLevelByLevelNumber(int levelNumber)
    {
        Level level = LevelMap[levelNumber];
        Settings.SetCurrentLevel(level);

        return level;
    }
    public void LoadByIndex(int sceneIndex)
    {
        SceneManager.LoadScene(sceneIndex);
        Screen.orientation = sceneIndex == STORY_INDEX ? ScreenOrientation.Landscape : ScreenOrientation.Portrait;
    }
}
