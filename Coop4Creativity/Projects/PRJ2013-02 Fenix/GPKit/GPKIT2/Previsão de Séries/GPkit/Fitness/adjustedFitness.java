/* -------------------------------------------------------------------------- */
/* Classe    : adjustedFitness                                                */
/* Descri��o : Classe que implementa o m�todo de calcular a fitness ajustada  */
/*             de cada individuo.                                             */
/*                                                                            */
/* nota sobre a fitness-ajustada                                              */
/* -----------------------------                                              */
/*                                                                            */
/* A fitness ajustada varia entre [0,1], com 1 sendo a melhor. Tem tamb�m a   */
/* vantagem de exagerar pequenas diferen�as � medida que as fitnesses dos in- */
/* dividuos aumentam, logo � medida que a solu��o est� mais perto, melhor     */
/* feedback, de modo que as melhores solu��es possam ser perseguidas.         */
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
 * M�todo     : computeAdjustedFitness
 * Descri��o  : Este m�todo calcula a fitness ajustada de todos os individuos.
 *              A formula para o c�lculo da fitness ajustada � a seguinte :   
 *               Para cada indiv�duo i, a sua fitness ajustada fa(i) ser� :
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
  
                                         
/* ----------------------- m�todos das interfaces --------------------------- */

public String toString()
  {  return (this.name);  }

public boolean equals(Object o)
  {  return (o instanceof adjustedFitness);  }
  
public Object clone()
  {  return (new adjustedFitness());  }

}

/* -------------------------------------------------------------------------- */