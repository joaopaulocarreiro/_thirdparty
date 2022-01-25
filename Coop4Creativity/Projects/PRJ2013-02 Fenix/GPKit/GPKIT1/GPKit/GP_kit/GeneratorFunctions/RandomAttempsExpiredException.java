/* -------------------------------------------------------------------------- */
/* Classe    : GeneratorRandomAttempsExpiredException                         */
/* Excepção                                                                   */
/* Descrição : Excepções relativas á geração de populações.                   */
/* -------------------------------------------------------------------------- */
package GeneratorFunctions;

public class RandomAttempsExpiredException extends Exception
{
/* ----------------------------- constructores ------------------------------ */

public RandomAttempsExpiredException ()
  {  super();  }
public RandomAttempsExpiredException (String message)
  {  super(message);  }
}

/* -------------------------------------------------------------------------- */