/* -------------------------------------------------------------------------- */
/* Classe    : errorFitness                                                   */
/* Descrição : Classe abstracta que implementa a raw-fitness do problema espe-*/
/*             -cifico.                                                       */
/* -------------------------------------------------------------------------- */
package Specific;

import Fitness.*;
import Misc.*;
import Population.*;
import Specific.*;
import Expressions.Elements.*;
import java.util.*;

public class errorFitness extends rawFitness implements Cloneable,
                                                        Standard
{

/* ----------------------------- constructores ------------------------------ */

public errorFitness(Variable v)
  {  this.setName("error_Fitness_Function");
     this.setVariable(v);  }
  
/* ------------------------- variáveis de instânçia ------------------------- */
  
  /* variavel a ser avaliada */
  private Variable variable;
  
/* ----------------------- métodos selectores (get/set) --------------------- */

public Variable getVariable()
  {  return (this.variable);  }
  
public void setVariable(Variable v)
  {  this.variable = (Variable) v;  }
  
/* ----------------------- métodos de instânçia ----------------------------- */

public void compute(Population p, fitnessCase[] test_cases)
  { Value pi;
    double ei;
    double fp = 0.0;
    
    for (Enumeration e = p.elements(); e.hasMoreElements();)
      {
        fp = 0.0;
        Individual ind = (Individual) e.nextElement(); 
        for (int i=0; i<test_cases.length; i++)
          {
             Object[] vars  = {this.getVariable()};
             Value[] values = {new Value(test_cases[i].getX())};
             pi = ind.evaluate(vars,values);    
             ei = pi.getValue() - test_cases[i].getY();
             ei = Math.abs(ei);
             fp = fp + ei; 
          }
        ind.setRawFitness(new Double(fp));
      }
  }
                                         
/* ----------------------- métodos das interfaces --------------------------- */

public String toString()
  { return (this.getName()); }

public boolean equals(Object o)
  { return (o instanceof errorFitness); }
  
public Object clone()
  { return (new errorFitness(this.getVariable())); }

}

/* -------------------------------------------------------------------------- */