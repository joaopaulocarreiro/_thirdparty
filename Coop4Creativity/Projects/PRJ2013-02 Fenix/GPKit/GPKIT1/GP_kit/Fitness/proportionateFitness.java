/* -------------------------------------------------------------------------- */
/* Classe    : proportionateFitness                                           */
/* Descrição : Classe que implementa o método de calcular a fitness proporcio-*/
/*             nal de cada individuo.                                         */
/*                                                                            */
/* nota sobre a fitness-proporcional                                          */
/* ---------------------------------                                          */
/*                                                                            */
/* A fitness proporcional varia entre [0,1], com 1 sendo a melhor. Este tipo  */
/* de fitness representa o "peso" que cada indivíduo tem na população em geral*/
/* ou seja, a soma de todos os pesos sera 1 (sum [fp(i)] = 1).                */
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
package Fitness;

import Misc.*;
import Population.*;
import java.util.*;


public class proportionateFitness implements Cloneable,
                                             Standard
{

/* ----------------------------- constructores ------------------------------ */

public proportionateFitness()
  {  this.name = "proportionateFitness";  }
  
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
 * Método     : computeProportionateFitness
 * Descrição  : Este método calcula a fitness propocional de todos os 
 *              individuos.
 *              A formula para o cálculo da fitness proporcional é a seguinte : 
 *               para uma populaçao de n, a fitness proporcional do indivíduo i
 *               será :                                                        
 *
 *                                       fa(i)
 *                         fp(i) =  -------------- 
 *                                   sum [ fa(i) ] com {i = 1,..,n}
 *                         
 *               onde fa(i) representa a sua fitness ajustada e sum[ fa(i) ]
 *               o somatório das fitnesses ajustadas.
 *            
 * nota -> poderá acontecer que a soma das fitness proporcionais dos individuos
 *         nao seja 1 certo poderá ser mais ou menos, estamos a falar de desvios
 *         bastante pequenos, na ordem dos 2 * potencia10(-15), estes pequenos
 *         desvios são ignorados.                                             
 */
 
public void compute(Population p)
  {  double sumAdjusted = 0.0;

     for (Enumeration e = p.elements(); e.hasMoreElements();)
       { Individual i = (Individual) e.nextElement();
         sumAdjusted += i.getAdjustedFitness();  }

     for (Enumeration e = p.elements(); e.hasMoreElements();)
       { Individual i = (Individual) e.nextElement();
         i.setProportionateFitness(i.getAdjustedFitness() / sumAdjusted);  }
  }
  
                                         
/* ----------------------- métodos das interfaces --------------------------- */

public String toString()
  {  return (this.name);  }

public boolean equals(Object o)
  {  return (o instanceof proportionateFitness);  }
  
public Object clone()
  {  return (new proportionateFitness());  }

}

/* -------------------------------------------------------------------------- */