/* -------------------------------------------------------------------------- */
/* Classe    : Individual                                                     */
/* Descrição : Classe que implementa um individuo genético.                   */
/* -------------------------------------------------------------------------- */
package GPkit.Population;

import GPkit.Misc.*;
import GPkit.Expressions.*;
import GPkit.Expressions.Elements.*;
import Aplication.Classes.Value.*;
import Aplication.Classes.Fitness.*;
import Aplication.Classes.Aritmetic.*;
import Aplication.Classes.FitnessCase.*;

public class Individual implements Cloneable,
                                   Standard
{

/* ----------------------------- constructores ------------------------------ */

public Individual(expressionTree e)
  {  this.setGeneticCode(e);
     this.setRawFitness(null);
     this.setStandardFitness(0.0);
     this.setAdjustedFitness(0.0);
     this.setProportionateFitness(0.0);  }

/* ------------------------- variáveis de instânçia ------------------------- */

  /* código genético do individuo */
  expressionTree genetic_code;
  /* raw fitness do individuo */
  private Object raw_fitness = null;
  /* standard fitness do individuo */
  private double standard_fitness = -1;
  /* adjusted fitness do individuo */
  private double adjusted_fitness = -1;
  /* proportional fitness do individuo */
  private double proportionate_fitness = -1;
  
/* ----------------------- métodos selectores (get/set) --------------------- */

public expressionTree getGeneticCode()
  {  return(this.genetic_code);  }

public Object getRawFitness()
  {  return(this.raw_fitness);  }
  
public double getStandardFitness()
  {  return(this.standard_fitness);  }
  
public double getAdjustedFitness()
  {  return(this.adjusted_fitness);  }
  
public double getProportionateFitness()
  {  return(this.proportionate_fitness);  }
  
public void setGeneticCode (expressionTree e)
  {  this.genetic_code = (expressionTree) e.clone();  }
  
public void setRawFitness(Object r)
  {  this.raw_fitness = r;  }
  
public void setStandardFitness(double s)
  {  this.standard_fitness = s;  }
  
public void setAdjustedFitness(double a)
  {  this.adjusted_fitness = a;  }
  
public void setProportionateFitness(double p)
  {  this.proportionate_fitness = p;  }
  

/* ----------------------- métodos de instânçia ----------------------------- */

public int depth()
  {  return (this.getGeneticCode().depth()); }
  
public int size()
  {  return (this.getGeneticCode().size()); }
  
public int leaves()
  {  return (this.getGeneticCode().leaves());  }
  
public Value evaluate()
  {  return (this.getGeneticCode().evaluate());  }

public Value evaluate(Object[] vars, Value[] values)
  {  return (this.getGeneticCode().evaluate(vars,values));  }
  
/* --------------------------- métodos privados ----------------------------- */

/* ----------------------- métodos das interfaces --------------------------- */

public String toString()
  { String str_aux;
    
    str_aux = "(" +
              "depth=" + this.depth() + ", " + 
              "size=" + this.size() + ", " + 
              "raw=" + this.getRawFitness() + ", " +
              "std=" + this.getStandardFitness() + ", " +           
              "adj=" + this.getAdjustedFitness() + ", " +
              "ppl=" + this.getProportionateFitness() + ", " +           
              this.getGeneticCode().toString() + ")";  
    
    return (str_aux);          
  }

public boolean equals(Object o)
  {  boolean equal = false;
     Individual ind_aux;
    
     if (o instanceof Individual)
       { ind_aux = (Individual) o;
         equal = this.getGeneticCode().equals(ind_aux.getGeneticCode());  }
     return (equal);
  }
    
public Object clone()
  {  return (new Individual(this.getGeneticCode()));  }

  
}

/* -------------------------------------------------------------------------- */