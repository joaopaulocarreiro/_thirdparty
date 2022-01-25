/* -------------------------------------------------------------------------- */
/* Classe    : standardFitness                                                */
/* Descrição : Classe abstracta que implementa a estrutura duma função de     */
/*             standard fitness.                                              */
/* -------------------------------------------------------------------------- */
package Fitness;

import Misc.*;
import Population.*;

public abstract class standardFitness implements Cloneable,
                                                 Standard
{

/* ------------------------- variáveis de instânçia ------------------------- */

  /* nome do método de standard fitness */
  protected String name;
  
/* ----------------------- métodos selectores (get/set) --------------------- */

public String getName()
  {  return (this.name);  }

public void setName(String n)
  {  this.name = new String(n);  }
  
/* ----------------------- métodos de instânçia ----------------------------- */

/**
 * Método     : computeStandardFitness
 * Descrição  : Este método calcula a standard fitness de todos os individos da
 *              população, isto é inverte/transforma a raw fitness de modo que
 *              todas as fitnesses são não negativas, com 0 sendo a melhor fit-
 *              -ness possível.
 */
public abstract void compute(Population p);
                                         
/* ----------------------- métodos das interfaces --------------------------- */

public abstract String toString();
public abstract boolean equals(Object o);
public abstract Object clone();

}

/* -------------------------------------------------------------------------- */