/* -------------------------------------------------------------------------- */
/* Classe    : rawFitness                                                     */
/* Descri��o : Classe abstracta que implementa a estrutura duma fun��o de     */
/*             raw fitness.                                                   */
/*                                                                            */
/* nota sobre a raw-fitness                                                   */
/* ------------------------                                                   */
/*                                                                            */
/* A raw fitness dum indiv�duo � uma medida directa, baseada no pr�prio pro-  */
/* -blema, de progresso alcan�ado em resolver o problema. Frequentemente esta */
/* medida � baseada em alguma compara��o a casos de fitness. Geralmente � usa-*/
/* do um conjunto de treino, tamb�m usado em outras aplica��es como por ex:   */
/* o treino de uma rede neuronal, aplicamos cada um dos casos de fitness ao   */
/* programa-GP (indiv�duo) e a soma do performance deste programa-GP aplicado */
/* a todos os casos � a raw-fitness.                                          */
/*                                                                            */
/* -------------------------------------------------------------------------- */
package Fitness;

import Misc.*;
import Population.*;
import Specific.*;

public abstract class rawFitness implements Cloneable,
                                            Standard
{

/* ------------------------- vari�veis de inst�n�ia ------------------------- */

  /* nome do m�todo de raw_fitness */
  private String name;
  
/* ----------------------- m�todos selectores (get/set) --------------------- */

public String getName()
  {  return (this.name);  }

public void setName(String n)
  {  this.name = new String(n);  }
  
/* ----------------------- m�todos de inst�n�ia ----------------------------- */

/**
 * M�todo     : computeRawFitness
 * Descri��o  : Este m�todo calcula a raw fitness de todos os individuos duma
 *              popula��o, isto � conseguido atrav�s do calculo das �rvores de
 *              express�o de cada individuo.
 */
public abstract void compute(Population p, fitnessCase[] tcases);
                                         
/* ----------------------- m�todos das interfaces --------------------------- */

public abstract String toString();
public abstract boolean equals(Object o);
public abstract Object clone();

}

/* -------------------------------------------------------------------------- */