using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public enum TypeOfScreen {
	CLUE,
	NAVIGATION,
	PUZZLE,
	PRIZE
}

public class Step {

	public int Level { get ; set; }

	public TypeOfScreen Screen { get; set; }

}
