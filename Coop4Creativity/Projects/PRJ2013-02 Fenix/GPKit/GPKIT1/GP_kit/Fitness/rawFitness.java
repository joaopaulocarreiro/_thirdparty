/* -------------------------------------------------------------------------- */
/* Classe    : rawFitness                                                     */
/* Descrição : Classe abstracta que implementa a estrutura duma função de     */
/*             raw fitness.                                                   */
/*                                                                            */
/* nota sobre a raw-fitness                                                   */
/* ------------------------                                                   */
/*                                                                            */
/* A raw fitness dum indivíduo é uma medida directa, baseada no próprio pro-  */
/* -blema, de progresso alcançado em resolver o problema. Frequentemente esta */
/* medida é baseada em alguma comparação a casos de fitness. Geralmente é usa-*/
/* do um conjunto de treino, também usado em outras aplicações como por ex:   */
/* o treino de uma rede neuronal, aplicamos cada um dos casos de fitness ao   */
/* programa-GP (indivíduo) e a soma do performance deste programa-GP aplicado */
/* a todos os casos é a raw-fitness.                                          */
/*                                                                            */
/* -------------------------------------------------------------------------- */
package Fitness;

import Misc.*;
import Population.*;
import Specific.*;

public abstract class rawFitness implements Cloneable,
                                            Standard
{

/* ------------------------- variáveis de instânçia ------------------------- */

  /* nome do método de raw_fitness */
  private String name;
  
/* ----------------------- métodos selectores (get/set) --------------------- */

public String getName()
  {  return (this.name);  }

public void setName(String n)
  {  this.name = new String(n);  }
  
/* ----------------------- métodos de instânçia ----------------------------- */

/**
 * Método     : computeRawFitness
 * Descrição  : Este método calcula a raw fitness de todos os individuos duma
 *              população, isto é conseguido através do calculo das árvores de
 *              expressão de cada individuo.
 */
public abstract void compute(Population p, fitnessCase[] tcases);
                                         
/* ----------------------- métodos das interfaces --------------------------- */

public abstract String toString();
public abstract boolean equals(Object o);
public abstract Object clone();

}

/* -------------------------------------------------------------------------- */