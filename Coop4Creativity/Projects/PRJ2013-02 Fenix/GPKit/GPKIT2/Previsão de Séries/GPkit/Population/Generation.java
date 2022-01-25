/* -------------------------------------------------------------------------- */
/* Classe    : Generation                                                     */
/* Descrição : Esta classe comporta todas as informações duma geração de indi-*/
/*             -viduos desde a sua população, vários fitnesses, e as estatis- */
/*             ticas referentes a essa geração.                               */
/* -------------------------------------------------------------------------- */
package GPkit.Population;

import GPkit.Misc.*;
import GPkit.Parameters.*;
import GPkit.Fitness.*;
import java.util.*;
import Aplication.Classes.Value.*;
import Aplication.Classes.Fitness.*;
import Aplication.Classes.Aritmetic.*;
import Aplication.Classes.FitnessCase.*;

public class Generation
{
/* ------------------------- constantes de classe --------------------------- */
/* -------------------------- variáveis de classe --------------------------- */

  /* nº corrente da geração */
  private int generation_number;
  
/* --------------------------- métodos de classe ---------------------------- */

public int getGenerationNumber()
  { return (this.generation_number);  }
  
public void setGenerationNumber(int i)
  { this.generation_number = i;  }

public void incGenerationNumber()
  { this.generation_number++;  }
  
/* ----------------------------- constructores ------------------------------ */

public Generation(Population p,int n)
  {  this.setPopulation(p);
     this.setGenerationNumber(n);  
     this.stats = new Statistics();  }

public Generation(Population p)
  {  this(p,0);  }
  
/* ------------------------- variáveis de instânçia ------------------------- */

  /* populaçao */
  private Population population;
  /* estatísticas */  
  private Statistics stats;
  
/* ----------------------- métodos selectores (get/set) --------------------- */

public Population getPopulation()
  {  return (this.population);  }

public Statistics getStatistics()
  {  return (this.stats);  }

public void setPopulation(Population p)
  {  this.population = (Population) p.clone();  }

public void setStatistics(Statistics s)
  {  this.stats = (Statistics) s.clone();  }

 
/* ----------------------- métodos de instânçia ----------------------------- */

/* -- métodos relacionados com fitness -- */

public void computeRawFitness(rawFitness f, fitnessCase[] t)
  {  f.compute (this.population,t);  }
  
public void computeStandardFitness(standardFitness f)
  {  f.compute (this.population);  }

public void computeAdjustedFitness(adjustedFitness f)
  {  f.compute (this.population);  }
    
public void computeProportionateFitness(proportionateFitness f)
  {  f.compute (this.population);  }
    
public void computeStatistics()
  {  this.stats = new Statistics();
     this.stats.compute(this);  }

public Individual find_best()
  { Individual bestfit = null;
       
    Enumeration e = this.population.elements();
    if (e.hasMoreElements())
       bestfit = (Individual) e.nextElement();    

    while (e.hasMoreElements())
     {  Individual i = (Individual) e.nextElement();
        if (i.getStandardFitness() < bestfit.getStandardFitness())
          bestfit = i;                                              }
          
    return (bestfit);
  }

public Individual find_worst()
  { Individual worstfit = null;
       
    Enumeration e = this.population.elements();
    if (e.hasMoreElements())
       worstfit = (Individual) e.nextElement();    

    while (e.hasMoreElements())
     {  Individual i = (Individual) e.nextElement();
        if (i.getStandardFitness() > worstfit.getStandardFitness())
          worstfit = i;                                              }
          
    return (worstfit);
  }

/* --------------------------- métodos privados ----------------------------- */
/* ----------------------- métodos das interfaces --------------------------- */

public String toString() 
  {  return (  "Generation[" +this.generation_number + "]\n" + 
               this.population.toString() +  
               this.stats.toString());
  }

public boolean equals(Object o)
  {  boolean equal = false;
       
     if (o instanceof Generation)
       {  Generation gen_aux = (Generation) o;
          equal = this.population.equals(gen_aux.getPopulation()) &&
                  this.stats.equals(gen_aux.getStatistics());   
       }    
     return(equal);  
  }
  
public Object clone()
  {  Generation gen_aux = new Generation(this.population);
     gen_aux.setStatistics(this.stats);
     return (gen_aux);  }


}

/* -------------------------------------------------------------------------- */