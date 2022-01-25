/* -------------------------------------------------------------------------- */
/* Classe    : FitnessProportionate                                           */
/* Descrição : Classe que implementa o método de selecção Fitness             */
/*             Proportionate.                                                 */
/* -------------------------------------------------------------------------- */
package Selection;

import Misc.*;
import Population.*;
import java.util.Random;

public class FitnessProportionate extends selectMethod
{

/* ----------------------------- constructores ------------------------------ */

public FitnessProportionate()
  {  this.setName("fitness_proportionate");  }


/* ----------------------- métodos de instânçia ----------------------------- */
  
public Individual doSelection(Generation g)
  {  Population p = (Population) g.getPopulation().clone();
     Individual selected_ind;
     int[] weigths = new int[p.getPopSize()];
     int random_index = 0;
     int ind_index = 0;
     int tot_weigth = 0;
     boolean endfor = false;
     
     for (int i=0; i<weigths.length; i++)
      {  double f = (p.getIndividual(i)).getProportionateFitness();
         Double double_aux = new Double (f * 1000000000);
         weigths[i]= double_aux.intValue();  } 
     
   
     random_index = this.generator.nextInt(1000000000);
         
     // seleciona o indice
     for (int i=0; (i<weigths.length) && (!endfor) ; i++)
      { tot_weigth += weigths[i];
        if (random_index < tot_weigth)
          {  ind_index = i;
             endfor = true;  }  
      }

     selected_ind = (Individual) p.getIndividual(ind_index).clone();     
    return (selected_ind);
  }

/* ----------------------- métodos das interfaces --------------------------- */

public String toString()
  {  return ("(" + this.getName() + ")" );  }
  
public boolean equals(Object o)
  { return (o instanceof FitnessProportionate);  }
    
public Object clone()
  {  return (new FitnessProportionate());  }
  
}

/* -------------------------------------------------------------------------- */