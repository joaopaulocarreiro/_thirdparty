/* -------------------------------------------------------------------------- */
/* Classe    : errorFitness                                                   */
/* Descrição : Classe abstracta que implementa a raw-fitness do problema espe-*/
/*             -cifico.                                                       */
/* -------------------------------------------------------------------------- */
package Aplication.Classes.Fitness;

import Aplication.Classes.Value.*;
import Aplication.Classes.FitnessCase.*;
import GPkit.Fitness.*;
import GPkit.Misc.*;
import GPkit.Population.*;
import GPkit.Expressions.Elements.*;
import java.util.*;

public class errorFitness extends rawFitness implements Cloneable,
                                                        Standard
{

/* ----------------------------- constructores ------------------------------ */

  public errorFitness(Variable[] v)
    {  this.setName("error_Fitness_Function");
       this.setVariables(v);  }
  
/* ------------------------- variáveis de instânçia ------------------------- */
  
  /* variaveis a serem avaliadas */
  private Variable[] variables = null;
  
/* ----------------------- métodos selectores (get/set) --------------------- */

  public Variable[] getVariables()
    {  Variable[] vars = new Variable[ this.variables.length ];
       for (int i=0; i<this.variables.length; i++)  
          vars[i] = (Variable) this.variables[i].clone();
       return (vars);  }
  
  public void setVariables(Variable[] v)
    {  this.variables = new Variable[ v.length ];
       for (int i=0; i<v.length; i++)  
          this.variables[i] = (Variable) v[i].clone();  }
  
/* ----------------------- métodos de instânçia ----------------------------- */

  /* método para calcular a raw fitness especifica. */
  public void compute(Population p, fitnessCase[] test_cases)
    { int size_window = this.variables.length;
      int size_testcases = test_cases.length;
      int size_evaluate = size_testcases - size_window;
      int size_real = size_testcases - size_window;
      int end_position = size_testcases - 1 - size_window;
      Value[] values = new Value [ size_window ];
      
      double ei;
      double fp = 0.0;
    
      for (Enumeration e = p.elements(); e.hasMoreElements();)
        {
          fp = 0.0;
          Individual ind = (Individual) e.nextElement(); 
          for (int i=0; i<end_position; i++)
            {
               for (int j=0; j<size_window; j++)
                 values[j] = new Value(test_cases[i + j].getNum());
                 
               Value est = ind.evaluate(variables,values);                  
               Value real = new Value(test_cases[i + size_window].getNum());
               ei = Math.abs ( est.getValue() - real.getValue() );
               fp = + fp + ei;
            }
          ind.setRawFitness(new Double(fp));
        }
    }
                                         
/* ----------------------- métodos das interfaces --------------------------- */

  public String toString()
    {  return (this.getName());  }

  public boolean equals(Object o)
    {  return (o instanceof errorFitness);  }
  
  public Object clone()
    {  return (new errorFitness(this.getVariables()));  }

}

/* -------------------------------------------------------------------------- */