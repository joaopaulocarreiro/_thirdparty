/* -------------------------------------------------------------------------- */
/* Classe    : standardFitness                                                */
/* Descri��o : Classe abstracta que implementa a estrutura duma fun��o de     */
/*             standard fitness.                                              */
/* -------------------------------------------------------------------------- */
package Fitness;

import Misc.*;
import Population.*;

public abstract class standardFitness implements Cloneable,
                                                 Standard
{

/* ------------------------- vari�veis de inst�n�ia ------------------------- */

  /* nome do m�todo de standard fitness */
  protected String name;
  
/* ----------------------- m�todos selectores (get/set) --------------------- */

public String getName()
  {  return (this.name);  }

public void setName(String n)
  {  this.name = new String(n);  }
  
/* ----------------------- m�todos de inst�n�ia ----------------------------- */

/**
 * M�todo     : computeStandardFitness
 * Descri��o  : Este m�todo calcula a standard fitness de todos os individos da
 *              popula��o, isto � inverte/transforma a raw fitness de modo que
 *              todas as fitnesses s�o n�o negativas, com 0 sendo a melhor fit-
 *              -ness poss�vel.
 */
public abstract void compute(Population p);
                                         
/* ----------------------- m�todos das interfaces --------------------------- */

public abstract String toString();
public abstract boolean equals(Object o);
public abstract Object clone();

}

/* -------------------------------------------------------------------------- */