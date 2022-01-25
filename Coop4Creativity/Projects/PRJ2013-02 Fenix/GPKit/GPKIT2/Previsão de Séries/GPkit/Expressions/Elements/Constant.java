/* -------------------------------------------------------------------------- */
/* Classe    : Constant                                                       */
/* Descrição : Classe abstracta utilizada para representar o comportamento das*/
/*             Constantes.                                                    */
/* -------------------------------------------------------------------------- */
package GPkit.Expressions.Elements;

import Aplication.Classes.Value.*;
import Aplication.Classes.Fitness.*;
import Aplication.Classes.Aritmetic.*;
import Aplication.Classes.FitnessCase.*;

public class Constant extends Terminal
{

/* ----------------------------- constructores ------------------------------ */

public Constant(Value v)
  {  this.setSymbolName(v.toString());
     this.setValue(v);  }
  
/* ----------------------- métodos de instânçia ----------------------------- */

public Terminal InsertSpecificTerminal()
  {  return null;  }
  
/* ----------------------- métodos das interfaces --------------------------- */

public String toString()
  {  return (this.getSymbolName());  }
  
 
public boolean equals(Object o)
  {  boolean equal = false;
     Constant var_aux;  
         
     if (o instanceof Constant)
       {  var_aux = (Constant) o;
          equal = this.getSymbolName().equals(var_aux.getSymbolName());  }
     return (equal);
  }     
                        
public Object clone()
  {  return (new Constant(this.getValue()));  }

}

/* -------------------------------------------------------------------------- */