/* -------------------------------------------------------------------------- */
/* Classe    : GeneratorRandomAttempsExpiredException                         */
/* Excep��o                                                                   */
/* Descri��o : Excep��es relativas � gera��o de popula��es.                   */
/* -------------------------------------------------------------------------- */
package GPkit.GeneratorFunctions;

public class RandomAttempsExpiredException extends Exception
{
/* ----------------------------- constructores ------------------------------ */

public RandomAttempsExpiredException ()
  {  super();  }
public RandomAttempsExpiredException (String message)
  {  super(message);  }
}

/* -------------------------------------------------------------------------- */