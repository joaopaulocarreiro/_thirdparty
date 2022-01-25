/* -------------------------------------------------------------------------- */
/* Classe    : OverSelection                                                  */
/* Descrição : Classe que implementa o método de selecção OverSelection       */
/* -------------------------------------------------------------------------- */
package Selection;

import Misc.*;
import Population.*;
import java.util.Random;
import java.util.Arrays;
import java.util.Vector;
import java.util.Enumeration;

public class OverSelection extends selectMethod
{

/* ------------------------- constantes de classe --------------------------- */

  static final double DEFAULT_overSelection_proportion = 0.8;
  static final double DEFAULT_overSelection_cutoff = 0.32;  

/* ----------------------------- constructores ------------------------------ */

public OverSelection(double coff,double prop)
  { this.setName("overselection");
    this.setCutOff(coff);
    this.setProportion(prop);  }

public OverSelection()
  {  this (DEFAULT_overSelection_cutoff,DEFAULT_overSelection_proportion);  }

/* ------------------------- variáveis de instânçia ------------------------- */
  
  /* especifica que quantidade de fitness ajustada está no grupo I */
  private double cutoff;
  /* especifica a proporção de vezes que é escolhido um elemento do grupo I */
  private double proportion;

/* ----------------------- métodos selectores (get/set) --------------------- */

public double getCutOff()
  {  return (this.cutoff);  }
  
public double getProportion()
  {  return (this.proportion);  }

private void setCutOff(double coff)
  {  if ((coff > 0.0) && (coff < 1.0))
       this.cutoff = coff;
     else
       this.cutoff = DEFAULT_overSelection_cutoff;  }
  
private void setProportion(double p)
  {  if ((p >= 0.0) && (p <= 1.0) ) 
       this.proportion = p;
     else
       this.proportion = DEFAULT_overSelection_proportion;  }

/* ----------------------- métodos de instânçia ----------------------------- */
  
public Individual doSelection(Generation g)
  { Population p = (Population) g.getPopulation().clone();
    Individual selected_ind = null;  
    Vector GroupI = new Vector(p.getPopSize());
    Vector GroupII = new Vector(p.getPopSize());
    index_and_fitness[] sorted_fitness = new index_and_fitness[p.getPopSize()];

    // criar uma lista com os pares (fitness,indice) dos individuos
    for (int i=0; i<sorted_fitness.length; i++)
      sorted_fitness[i] = new index_and_fitness (
                            p.getIndividual(i).getProportionateFitness(),
                            i);

    // ordenar por ordem crescente.
    Arrays.sort(sorted_fitness);
    
    // criar o Grupo I e o Grupo II
    double tot_prop = 0.0;
    double tot_groupI = 0.0;
    double tot_groupII = 0.0;
    
    for (int i=0; i < sorted_fitness.length; i++)
      {  tot_prop += sorted_fitness[i].getFitness();
         if (tot_prop <= 1 - this.cutoff)
           { GroupII.addElement(sorted_fitness[i].clone());
             tot_groupII += sorted_fitness[i].getFitness();  } 
         else
           { GroupI.addElement(sorted_fitness[i].clone());
             tot_groupI += sorted_fitness[i].getFitness();   }
      }

    // reajustar as fitnesses proporcionais dentro dos Grupos e construir os
    // respectivos arrays

    index_and_fitness[] array_groupI = new index_and_fitness[GroupI.size()];
    index_and_fitness[] array_groupII = new index_and_fitness[GroupII.size()]; 
       
    
    int counter = 0;
    for (Enumeration e = GroupI.elements(); e.hasMoreElements();)
      { index_and_fitness el = (index_and_fitness) e.nextElement();
        el.setFitness(el.getFitness() / tot_groupI);  
        array_groupI[counter] = el;  
        counter++;   }

    counter = 0;      
    for (Enumeration e = GroupII.elements(); e.hasMoreElements();)
      { index_and_fitness el = (index_and_fitness) e.nextElement();
        el.setFitness(el.getFitness() / tot_groupII);  
        array_groupII[counter] = el;  
        counter++;  }  
    
    // escolher de qual dos grupos selecionar o individuo. baseado na
    // proporção. E depois seleccionar o indivíduo baseado na fitness
    // proporcional de cada grupo.
    if ((this.generator.nextDouble() < this.proportion) && (GroupI.size()) > 0)
      { int i = this.selectPropFitness(array_groupI);
        selected_ind = p.getIndividual(i);  }
    else
      {  if (GroupII.size() > 0)
         {  int i = this.selectPropFitness(array_groupII);
            selected_ind = p.getIndividual(i);  }
      }    


   
    return ( (Individual) selected_ind.clone());
  }

/* --------------------------- métodos privados ----------------------------- */

private int selectPropFitness (index_and_fitness[] prop_fitness)
  {  int[] weigths = new int[prop_fitness.length];
     int random_index = 0;
     int ind_index = 0;
     int tot_weigth = 0;
     boolean endfor = false;
     
     for (int i=0; i<weigths.length; i++)
       {  Double d = new Double (prop_fitness[i].getFitness() * 1000000000);
          weigths[i]= d.intValue();  } 
 
     
     random_index = this.generator.nextInt(1000000000);
         
     // seleciona o indice
     for (int i=0; (i<weigths.length) && (!endfor) ; i++)
      { tot_weigth += weigths[i];
        if (random_index < tot_weigth)
          {  ind_index = i;
             endfor = true;  }  
      }

    return (prop_fitness[ind_index].getIndex());
  }

/* ----------------------- métodos das interfaces --------------------------- */

public String toString()
  { return ( "(" + this.getName() + "," +
              "cutoff=" + this.cutoff + "," +
              "proportion=" + this.proportion +
              ")");  }  
  
public boolean equals(Object o)
  { boolean equal = false;
    OverSelection over_aux;
    
    if (o instanceof OverSelection)
      { over_aux = (OverSelection) o;
        equal = (this.cutoff == over_aux.getCutOff()) &&
                (this.proportion == over_aux.getProportion());  }

    return (equal);
  }
    
public Object clone()
  {  return (new OverSelection(this.cutoff,this.proportion));  }
  
}

/* -------------------------------------------------------------------------- */

/* -------------------------------------------------------------------------- */
/* Classe Auxiliar : index_and_fitness                                        */
/* Descrição       : Classe que implementa o par prop_fitness / indice        */
/* -------------------------------------------------------------------------- */

class index_and_fitness implements Cloneable,
                                   Standard,
                                   Comparable
{

/* ----------------------------- constructores ------------------------------ */

public index_and_fitness(double f, int i)
  { this.setFitness(f);
    this.setIndex(i);  }

public index_and_fitness(int i,double f)
  { this(f,i);  }

/* ------------------------- variáveis de instânçia ------------------------- */
  
  /* fitness */
  private double fitness;
  /* index */
  private int index;
  
/* ----------------------- métodos selectores (get/set) --------------------- */
  
public double getFitness()
  {  return (this.fitness);  }

public int getIndex()
  {  return (this.index);  }

public void setFitness(double fitness)
  {  this.fitness = fitness;  }

public void setIndex(int index)
  {  this.index = index;  }

/* ----------------------- métodos das interfaces --------------------------- */

public String toString()    
  { return  ("(" + this.fitness + "," +this.index + ")");  }

public boolean equals(Object o)
  { index_and_fitness aux;
    boolean equal = false;
    
    if (o instanceof index_and_fitness)
      { aux = (index_and_fitness) o;
        equal = (this.fitness == aux.getFitness()) &&
                (this.index == aux.getIndex()) ;  }
    return (equal);  
  }

public Object clone()
  {  return (new index_and_fitness(this.fitness,this.index));  }

public int compareTo (Object o)
  {  int ret_value = 0;
     
     index_and_fitness iaf = (index_and_fitness) o;      
     
     if (this.fitness < iaf.getFitness()) ret_value = -1;
     else
       if (this.fitness > iaf.getFitness()) ret_value = 1;
     
 
     return (ret_value);  
  }
  
}  

/* -------------------------------------------------------------------------- */