using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Level {

    public int Number { get; set; }
    public string Name { get; set; }
    public string TriggerName { get; set; }
    public string Tip { get; set; }
    public int ClueSceneIndex { get; set; }
    public int PuzzleSceneIndex { get; set; }
    public int PrizeSceneIndex { get; set; }
    public Point Target { get; set; }
	
}
