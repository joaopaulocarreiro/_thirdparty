/* -------------------------------------------------------------------------- */
/* Classe    : proportionateFitness                                           */
/* Descri��o : Classe que implementa o m�todo de calcular a fitness proporcio-*/
/*             nal de cada individuo.                                         */
/*                                                                            */
/* nota sobre a fitness-proporcional                                          */
/* ---------------------------------                                          */
/*                                                                            */
/* A fitness proporcional varia entre [0,1], com 1 sendo a melhor. Este tipo  */
/* de fitness representa o "peso" que cada indiv�duo tem na popula��o em geral*/
/* ou seja, a soma de todos os pesos sera 1 (sum [fp(i)] = 1).                */
/*                                                                            */
/* Processamento geral das v�rias fitnesses                                   */
/* ----------------------------------------                                   */
/*                                                                            */
/* O processamento das v�rias fitnesses dum determinado individuo dever�      */
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
  
/* ------------------------- vari�veis de inst�n�ia ------------------------- */

  /* nome da fun��o */
  private String name;
  
/* ----------------------- m�todos selectores (get/set) --------------------- */

public String getName()
  {  return (this.name);  }

public void setName(String n)
  {  this.name = new String(n);  }

/* ----------------------- m�todos de inst�n�ia ----------------------------- */

/**
 * M�todo     : computeProportionateFitness
 * Descri��o  : Este m�todo calcula a fitness propocional de todos os 
 *              individuos.
 *              A formula para o c�lculo da fitness proporcional � a seguinte : 
 *               para uma popula�ao de n, a fitness proporcional do indiv�duo i
 *               ser� :                                                        
 *
 *                                       fa(i)
 *                         fp(i) =  -------------- 
 *                                   sum [ fa(i) ] com {i = 1,..,n}
 *                         
 *               onde fa(i) representa a sua fitness ajustada e sum[ fa(i) ]
 *               o somat�rio das fitnesses ajustadas.
 *            
 * nota -> poder� acontecer que a soma das fitness proporcionais dos individuos
 *         nao seja 1 certo poder� ser mais ou menos, estamos a falar de desvios
 *         bastante pequenos, na ordem dos 2 * potencia10(-15), estes pequenos
 *         desvios s�o ignorados.                                             
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
  
                                         
/* ----------------------- m�todos das interfaces --------------------------- */

public String toString()
  {  return (this.name);  }

public boolean equals(Object o)
  {  return (o instanceof proportionateFitness);  }
  
public Object clone()
  {  return (new proportionateFitness());  }

}

/* -------------------------------------------------------------------------- */