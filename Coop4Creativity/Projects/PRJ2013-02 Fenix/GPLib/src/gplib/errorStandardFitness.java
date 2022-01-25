/* -------------------------------------------------------------------------- */
/* Classe    : errorStandardFitness                                           */
/* Descrição : Classe abstracta que implementa a standard fitness problema.   */
/* -------------------------------------------------------------------------- */
package Aplication.Classes.Fitness;

import GPkit.Fitness.*;
import GPkit.Misc.*;
import GPkit.Population.*;
import Aplication.Classes.*;
import java.util.*;

public class errorStandardFitness extends standardFitness implements Cloneable,
                                                                     Standard
{

/* ----------------------------- constructores ------------------------------ */

public errorStandardFitness()
  {  this.setName("error_Standard_Fitness");  }
  
/* ----------------------- métodos de instânçia ----------------------------- */

public void compute(Population p)
  {   
    for (Enumeration e = p.elements(); e.hasMoreElements();)
      {  Individual i = (Individual) e.nextElement(); 
         i.setStandardFitness( ((Double) i.getRawFitness()).doubleValue() );  }
  }
                                         
/* ----------------------- métodos das interfaces --------------------------- */

public String toString()
  { return (this.getName()); }

public boolean equals(Object o)
  { return (o instanceof errorStandardFitness); }
  
public Object clone()
  { return (new errorStandardFitness()); }

}

/* -------------------------------------------------------------------------- */