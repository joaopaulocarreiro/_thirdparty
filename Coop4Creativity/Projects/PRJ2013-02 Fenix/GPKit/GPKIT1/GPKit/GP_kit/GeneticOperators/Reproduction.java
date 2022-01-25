/* -------------------------------------------------------------------------- */
/* Classe    : Reproduction                                                   */
/* Descri��o : Classe para a implementa��o do operador gen�tico Reprodu��o.   */
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
  
/* ------------------------- vari�veis de inst�n�ia ------------------------- */

  /* m�todo de selec��o do individuo */  
  private selectMethod select;
  
/* ----------------------- m�todos selectores (get/set) --------------------- */

public selectMethod getSelect()
  {  return (this.select);  }
  
public void setSelect(selectMethod s)
  {  this.select = (selectMethod) s.clone();  }

/* ----------------------- m�todos de inst�n�ia ----------------------------- */
  
public Individual[] doOperation(Generation g)
  { Individual[] i = new Individual[this.getNumReturnInd()];

    this.select.setGenerator(this.generator);  
    i[0] = this.select.doSelection(g);
    return (i);
  }

/* ----------------------- m�todos das interfaces --------------------------- */
  
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