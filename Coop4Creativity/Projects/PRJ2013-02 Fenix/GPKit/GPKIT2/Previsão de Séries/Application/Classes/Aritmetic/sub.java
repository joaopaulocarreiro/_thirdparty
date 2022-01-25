/* -------------------------------------------------------------------------- */
/* Classe    : sub                                                            */
/* Descri��o : subtrac��o                                                     */
/* -------------------------------------------------------------------------- */
package Aplication.Classes.Aritmetic;

import GPkit.Expressions.Elements.*;
import Aplication.Classes.Value.*;
import Aplication.Classes.FitnessCase.*;


public class sub extends Function
{

/* ----------------------------- constructores ------------------------------ */

public sub()
  {  this.setSymbolName("-");
     this.setArity(2);  }

/* ----------------------- m�todos de inst�n�ia ----------------------------- */

public Value evaluate(Value[] args)
  {  return (new Value(args[0].getValue() - args[1].getValue()));  }
  
/* ----------------------- m�todos das interfaces --------------------------- */

public String toString()
  {  return (this.getSymbolName());  }

public boolean equals(Object o)
  {  return (o instanceof sub);  }

public Object clone()
  {  return (new sub());  }
  

}

/* -------------------------------------------------------------------------- */