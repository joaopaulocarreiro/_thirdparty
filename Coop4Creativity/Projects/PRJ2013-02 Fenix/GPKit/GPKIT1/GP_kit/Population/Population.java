/* -------------------------------------------------------------------------- */
/* Classe    : Population                                                     */
/* Descrição : Classe que implementa uma população de individuos genéticos.   */
/* -------------------------------------------------------------------------- */

package Population;
import Misc.*;
import java.util.*;

public class Population implements Cloneable,
                                   Standard
{

/* ----------------------------- constructores ------------------------------ */

public Population (int ps)
  {  this.population = new Vector(ps);  }

/* ------------------------- variáveis de instânçia ------------------------- */

  /* conjunto de individuos, a própria população. */
  private Vector population;
  
/* ----------------------- métodos selectores (get/set) --------------------- */

public int getPopSize()
  {  return (this.population.size());  }

public Individual getIndividual(int index) 
                                throws ArrayIndexOutOfBoundsException
  {  return ( (Individual) this.population.get(index));  }

public Vector getAllIndividuals()
  {  return this.population;  }
  
/* ----------------------- métodos de instânçia ----------------------------- */

public void addIndividual(Individual ind) throws 
                                           PopulationRepeatedIndividualException
  {  if (!this.existIndividual(ind))
       {  this.addInconditionalIndividual(ind);  }  
     else
       throw new PopulationRepeatedIndividualException();     
  }

public void addIndividual(Individual[] ind,int q) throws
                                           PopulationRepeatedIndividualException
  {  if (q <= ind.length)
       {
          for (int i=0; i<q; i++)
            this.addIndividual(ind[i]);  
       }
  }   
  
public void addInconditionalIndividual(Individual ind)
  {  this.population.addElement(ind);  }  

public boolean existIndividual(Individual ind)
  {  return (this.population.contains(ind));  }    

public Enumeration elements()
  {  return (this.population.elements());  }
  
/* ----------------------- métodos das interfaces --------------------------- */

public String toString() 
  {  String str_aux;

     str_aux = this.getPopSize() + " individuals : \n";
     for (Enumeration e = this.elements(); e.hasMoreElements();)
       str_aux += ( (Individual) e.nextElement() ).toString() + "\n";         
                
     return (str_aux);
  }

public boolean equals(Object o)
  {  boolean equal = false;
     Population pop_aux;
     
     if (o instanceof Population)
       {  pop_aux = (Population) o;
          if (this.getPopSize() == pop_aux.getPopSize())
            {  equal = true;
               for (int i=0; i<this.getPopSize() && equal; i++)
                 equal = this.getIndividual(i).equals(pop_aux.getIndividual(i));
            }
       }    
     return(equal);  
  }
  
public Object clone()
  {  Population p = new Population (this.getPopSize()); 

     for (Enumeration e = this.elements(); e.hasMoreElements();)
       p.addInconditionalIndividual( (Individual) e.nextElement());
  
     return (p);  }
}

/* -------------------------------------------------------------------------- */

