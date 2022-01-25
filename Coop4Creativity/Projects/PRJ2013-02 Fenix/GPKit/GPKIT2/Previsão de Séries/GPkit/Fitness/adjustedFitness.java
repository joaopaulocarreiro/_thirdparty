/* -------------------------------------------------------------------------- */
/* Classe    : adjustedFitness                                                */
/* Descrição : Classe que implementa o método de calcular a fitness ajustada  */
/*             de cada individuo.                                             */
/*                                                                            */
/* nota sobre a fitness-ajustada                                              */
/* -----------------------------                                              */
/*                                                                            */
/* A fitness ajustada varia entre [0,1], com 1 sendo a melhor. Tem também a   */
/* vantagem de exagerar pequenas diferenças á medida que as fitnesses dos in- */
/* dividuos aumentam, logo á medida que a solução está mais perto, melhor     */
/* feedback, de modo que as melhores soluções possam ser perseguidas.         */
/*                                                                            */
/* Processamento geral das várias fitnesses                                   */
/* ----------------------------------------                                   */
/*                                                                            */
/* O processamento das várias fitnesses dum determinado individuo deverá      */
/* seguir o seguinte encadeamento :                                           */
/*                                                                            */
/*              calculo da raw-fitness                                        */
/*                       |                                                    */
/*                       |                                                    */
/*                       v                                                    */
/*            calculo da standard-fitness                                     */
/*                       |                                                    */
/*                       |                                                    */
/*                       v                                                    */
/*            calculo da adjusted-fitness                                     */
/*                       |                                                    */
/*                       |                                                    */
/*                       v                                                    */
/*         calculo da proportionate-fitness                                   */
/*                                                                            */
/* -------------------------------------------------------------------------- */
package GPkit.Fitness;

import GPkit.Misc.*;
import GPkit.Population.*;
import java.util.*;


public class adjustedFitness implements Cloneable,
                                        Standard
{

/* ----------------------------- constructores ------------------------------ */

public adjustedFitness()
  {  this.name = "adjustedFitness";  }
  
/* ------------------------- variáveis de instânçia ------------------------- */

  /* nome da função */
  private String name;
  
/* ----------------------- métodos selectores (get/set) --------------------- */

public String getName()
  {  return (this.name);  }

public void setName(String n)
  {  this.name = new String(n);  }


/* ----------------------- métodos de instânçia ----------------------------- */

/**
 * Método     : computeAdjustedFitness
 * Descrição  : Este método calcula a fitness ajustada de todos os individuos.
 *              A formula para o cálculo da fitness ajustada é a seguinte :   
 *               Para cada indivíduo i, a sua fitness ajustada fa(i) será :
 *
 *                                       1
 *                         fa(i) =  ----------- 
 *                                   1 + fs(i)
 *                         
 *               onde fs(i) representa a sua fitness standardizada.
 *            
 */
public void compute(Population p)
  {  
    for (Enumeration e = p.elements(); e.hasMoreElements();)
      { Individual ind_aux = (Individual) e.nextElement();
        ind_aux.setAdjustedFitness(1 / (1 + ind_aux.getStandardFitness()));  }
  }
  
                                         
/* ----------------------- métodos das interfaces --------------------------- */

public String toString()
  {  return (this.name);  }

public boolean equals(Object o)
  {  return (o instanceof adjustedFitness);  }
  
public Object clone()
  {  return (new adjustedFitness());  }

}

/* -------------------------------------------------------------------------- */