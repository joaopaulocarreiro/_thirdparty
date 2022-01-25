using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using System.Text.RegularExpressions;

public class PuzzleVerify : MonoBehaviour
{
    public InputField AnswerField;
	public Button SubmitButton;

    private static SortedDictionary<int, string> Solutions = new SortedDictionary<int, string>() {
        { 1, "Carlsberg" },  // Yeast
        { 2, "The Kalevala" }, // Hop
        { 3, "1 400 000 000" }, // Water
        { 4, "Endosperm" } // Barley
    };

    public void VerifyPuzzle(int puzzleID)
    {		
        string answer = AnswerField.text;

        bool gotIt = normalizeString(answer).Equals(normalizeString(Solutions[puzzleID]));

        if (gotIt)
        {
            LoadSceneOnClick nav = new LoadSceneOnClick();
            nav.Next();
        }
		else {
					
		}
    }

    private string normalizeString(string input)
    {
        string output = Regex.Replace(input, @"\s+", "");
        output = output.ToLower();
        return output;
    }
}
