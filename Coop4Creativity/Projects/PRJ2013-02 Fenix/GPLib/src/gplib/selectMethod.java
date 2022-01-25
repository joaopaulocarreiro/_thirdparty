/* -------------------------------------------------------------------------- */
/* Classe    : selectMethod                                                   */
/* Descri��o : Classe abstracta para a implementa��o dos m�todos de selec��o. */
/* -------------------------------------------------------------------------- */
package GPkit.Selection;

import GPkit.Misc.*;
import GPkit.Population.*;
import java.util.Random;

public abstract class selectMethod implements Cloneable,
                                              Standard
{

/* -------------------------- vari�veis de classe --------------------------- */

  /* gerador de numeros aleat�rios */
  protected Random generator;
  
/* ------------------------- vari�veis de inst�n�ia ------------------------- */

  /* nome do m�todo de selec��o */
  protected String name;

/* ----------------------- m�todos selectores (get/set) --------------------- */
  
public String getName()
  {  return (this.name);  }

protected void setName(String n)
  {  this.name = new String(n);  }

public void setGenerator(Random r)
  {  this.generator = r;  }

/* ----------------------- m�todos de inst�n�ia ----------------------------- */
  
/**
 * M�todo     : doSelection
 * Descri��o  : Este m�todo ser� implementado pelas subclasses para implementar
 *              o m�todo de selec��o.
 * Parametros : Generation g - uma determinada gera��o.
 * Retorna    : o individuo seleccionado.
 */  
public abstract Individual doSelection(Generation g);

/* ----------------------- m�todos das interfaces --------------------------- */

public abstract String toString();
public abstract boolean equals(Object o);
public abstract Object clone();
  
}

/* -------------------------------------------------------------------------- */