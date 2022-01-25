/* -------------------------------------------------------------------------- */
/* Classe    : quad                                                           */
/* Descri��o : quadiplica��o                                                  */
/* -------------------------------------------------------------------------- */
package Specific;

import Expressions.Elements.*;

public class quad extends Function
{

/* ----------------------------- constructores ------------------------------ */

public quad()
  {  this.setSymbolName("#");
     this.setArity(4);  }

/* ----------------------- m�todos de inst�n�ia ----------------------------- */

public Value evaluate(Value[] args)
  {  return (new Value(args[0].getValue() * args[1].getValue() *
                       args[2].getValue() * args[3].getValue()));  }
  
/* ----------------------- m�todos das interfaces --------------------------- */

public String toString()
  {  return (this.getSymbolName());  }

public boolean equals(Object o)
  {  return (o instanceof quad);  }

public Object clone()
  {  return (new quad());  }
  

}

/* -------------------------------------------------------------------------- */