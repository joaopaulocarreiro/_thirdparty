/* -------------------------------------------------------------------------- */
/* Classe    : Reproduction                                                   */
/* Descrição : Classe para a implementação do operador genético Reprodução.   */
/* -------------------------------------------------------------------------- */
package GeneticOperators;

import Misc.*;
import Selection.*;
import Population.*;

public class Reproduction extends geneticOperator
{

/* ----------------------------- constructores ------------------------------ */

public Reproduction (selectMethod s)
  {  this.setName("reproduction");
     this.setNumReturnInd(1);  
     this.setSelect(s);  }
  
/* ------------------------- variáveis de instânçia ------------------------- */

  /* método de selecção do individuo */  
  private selectMethod select;
  
/* ----------------------- métodos selectores (get/set) --------------------- */

public selectMethod getSelect()
  {  return (this.select);  }
  
public void setSelect(selectMethod s)
  {  this.select = (selectMethod) s.clone();  }

/* ----------------------- métodos de instânçia ----------------------------- */
  
public Individual[] doOperation(Generation g)
  { Individual[] i = new Individual[this.getNumReturnInd()];

    this.select.setGenerator(this.generator);  
    i[0] = this.select.doSelection(g);
    return (i);
  }

/* ----------------------- métodos das interfaces --------------------------- */
  
public String toString()
  { return ( "(" + this.getName() + "," + this.getSelect().toString() + ")"); }

public boolean equals(Object o)
  { boolean equal = false;
    Reproduction rep_aux;
    
    if (o instanceof Reproduction)
      { rep_aux = (Reproduction) o;
        equal = this.getName().equals(rep_aux.getName()) &&
                this.getSelect().equals(rep_aux.getSelect());  }
    return (equal);
  }
    
public Object clone()
  {  return (new Reproduction(this.getSelect()));  }
   
}

/* -------------------------------------------------------------------------- */