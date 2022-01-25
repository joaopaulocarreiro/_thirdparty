/* -------------------------------------------------------------------------- */
/* Classe    : errorStandardFitness                                           */
/* Descrição : Classe abstracta que implementa a standard fitness problema.   */
/* -------------------------------------------------------------------------- */

package Specific;
import Fitness.*;
import Misc.*;
import Population.*;
import Specific.*;
import java.util.*;

public class sizeStandardFitness extends standardFitness implements Cloneable,
                                                                     Standard
{

/* ----------------------------- constructores ------------------------------ */

public sizeStandardFitness()
  {  this.setName("size_Standard_Fitness");  }
  
/* ----------------------- métodos de instânçia ----------------------------- */

public void compute(Population p)
  {   
    for (Enumeration e = p.elements(); e.hasMoreElements();)
      {  Individual i = (Individual) e.nextElement();
         double raw_fitness = ((Double) i.getRawFitness()).doubleValue();  
         i.setStandardFitness( 
                              ((raw_fitness / 100) * 90) +
                              ((i.size() / 100) * 10) 
                                );  }
  }
                                         
/* ----------------------- métodos das interfaces --------------------------- */

public String toString()
  { return (this.getName()); }

public boolean equals(Object o)
  { return (o instanceof sizeStandardFitness); }
  
public Object clone()
  { return (new sizeStandardFitness()); }

}

/* -------------------------------------------------------------------------- */