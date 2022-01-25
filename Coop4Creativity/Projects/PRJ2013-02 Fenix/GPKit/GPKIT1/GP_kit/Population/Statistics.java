/* -------------------------------------------------------------------------- */
/* Classe    : Statistics                                                     */
/* Descrição : Engloba a informação estatistica.                              */
/* -------------------------------------------------------------------------- */
package Population;

import java.util.*;

public class Statistics
{

/* ------------------------- variáveis de instânçia ------------------------- */

  /* média da fitness standard */
  private double mean_standardized_fitness = -1;
  /* fitness standard do melhor individuo */
  private double standardized_fitness_of_best = -1;
  /* fitness standard do pior individuo */
  private double standardized_fitness_of_worst = -1;
  /* média do tamanho(em nº nodos) das árvores dos individuos */
  private double mean_tree_size = -1;
  /* média das alturas das árvores dos individuos */
  private double mean_tree_depth = -1;
  /* nº de nodos do melhor individuo */
  private int tree_size_of_best = -1;
  /* altura do melhor individuo */
  private int tree_depth_of_best = -1;
  /* nº de nodos do pior individuo */
  private int tree_size_of_worst = -1;
  /* altura do pior individuo */
  private int tree_depth_of_worst = -1;
  
/* ----------------------- métodos selectores (get/set) --------------------- */


public double getMSF()
  {  return (this.mean_standardized_fitness);  }
public double getSFB()
  {  return (this.standardized_fitness_of_best);  }
public double getSFW()
  {  return (this.standardized_fitness_of_worst);  }
public double getMTS()
  {  return (this.mean_tree_size);  }
public double getMTD() 
  {  return (this.mean_tree_depth);  }
public int getTSB()
  {  return (this.tree_size_of_best);  }
public int getTDB()
  {  return (this.tree_depth_of_best);  }  
public int getTSW()
  {  return (this.tree_size_of_worst);  }
public int getTDW()
  {  return (this.tree_depth_of_worst);  }
  
/* ----------------------- métodos de instânçia ----------------------------- */

public void compute(Generation g)
  { Population p = g.getPopulation();
    Individual bestfit = null;
    Individual worstfit = null;  

    Enumeration e = p.elements();
    if (e.hasMoreElements())
      {  bestfit = (Individual) e.nextElement();    
         worstfit = bestfit;  }

    double tot_std_fitness = bestfit.getStandardFitness();  
    double tot_depth = bestfit.depth();
    double tot_size = bestfit.size();

    while (e.hasMoreElements())
     {  Individual i = (Individual) e.nextElement();
     
        tot_std_fitness += i.getStandardFitness();
        tot_depth += i.depth();
        tot_size += i.size();
        
        if (i.getStandardFitness() < bestfit.getStandardFitness())
          bestfit = i;              
        if (i.getStandardFitness() > worstfit.getStandardFitness())
          worstfit = i;              
     }
    
    this.mean_standardized_fitness = tot_std_fitness / p.getPopSize();
    this.mean_tree_size = tot_size / p.getPopSize();
    this.mean_tree_depth = tot_depth / p.getPopSize();
     
    standardized_fitness_of_best = bestfit.getStandardFitness();
    standardized_fitness_of_worst = worstfit.getStandardFitness();
    tree_size_of_best = bestfit.size();
    tree_depth_of_best = bestfit.depth();
    tree_size_of_worst = worstfit.size();
    tree_depth_of_worst = worstfit.depth();
 
  }
  
 
/* --------------------------- métodos privados ----------------------------- */
/* ----------------------- métodos das interfaces --------------------------- */

public String toString()
  {  return ( "Statistics\n" + "----------\n" +
               "msf :" + this.mean_standardized_fitness + "\n" + 
               "sfbi:" + this.standardized_fitness_of_best + "\n" +
               "sfwi:" + this.standardized_fitness_of_worst + "\n" +
               "mts :" + this.mean_tree_size + "\n" +
               "mtd :" + this.mean_tree_depth + "\n" +
               "sob :" + this.tree_size_of_best + "\n" +
               "dob :" + this.tree_depth_of_best + "\n" +
               "sow :" + this.tree_size_of_worst + "\n" +
               "dow :" + this.tree_depth_of_worst + "\n");
  }

public boolean equals(Object o)
  {  return (o instanceof Statistics); }

public Object clone()
  {  return (new Statistics());  }
}

/* -------------------------------------------------------------------------- */