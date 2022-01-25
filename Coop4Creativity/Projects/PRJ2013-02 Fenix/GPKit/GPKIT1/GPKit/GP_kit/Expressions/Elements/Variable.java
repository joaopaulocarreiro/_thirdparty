/* -------------------------------------------------------------------------- */
/* Classe    : Variable                                                       */
/* Descrição : Classe abstracta utilizada para representar o comportamento das*/
/*             Variaveis.                                                     */
/* -------------------------------------------------------------------------- */
package Expressions.Elements;

import Specific.*;

public class Variable extends Terminal
{

/* ----------------------------- constructores ------------------------------ */

public Variable(String name)
  {  this.setSymbolName(name); 
     this.setValue(new Value());  }
  
/* ----------------------- métodos de instânçia ----------------------------- */

public Terminal InsertSpecificTerminal()
  {  return null;  }

/* ----------------------- métodos das interfaces --------------------------- */

public String toString()
  {  return (this.getSymbolName());  }
 
public boolean equals(Object o)
  {  boolean equal = false;
     Variable var_aux;  
         
     if (o instanceof Variable)
       {  var_aux = (Variable) o;
          equal = this.getSymbolName().equals(var_aux.getSymbolName());  }
     return (equal);
  }     
                        
public Object clone()
  {  return (new Variable(this.getSymbolName()));  }

}

/* -------------------------------------------------------------------------- */