/* -------------------------------------------------------------------------- */
/* Classe    : mul                                                            */
/* Descrição : multiplicação                                                  */
/* -------------------------------------------------------------------------- */
package Specific;

import Expressions.Elements.*;

public class mul extends Function
{

/* ----------------------------- constructores ------------------------------ */

public mul()
  {  this.setSymbolName("*");
     this.setArity(2);  }

/* ----------------------- métodos de instânçia ----------------------------- */

public Value evaluate(Value[] args)
  {  return (new Value(args[0].getValue() * args[1].getValue()));  }
  
/* ----------------------- métodos das interfaces --------------------------- */

public String toString()
  {  return (this.getSymbolName());  }

public boolean equals(Object o)
  {  return (o instanceof mul);  }

public Object clone()
  {  return (new mul());  }

}

/* -------------------------------------------------------------------------- */