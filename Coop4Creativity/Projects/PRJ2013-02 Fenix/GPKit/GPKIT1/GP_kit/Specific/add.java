/* -------------------------------------------------------------------------- */
/* Classe    : add                                                            */
/* Descrição : soma.                                                          */
/* -------------------------------------------------------------------------- */
package Specific;

import Expressions.Elements.*;

public class add extends Function
{

/* ----------------------------- constructores ------------------------------ */

public add()
  {  this.setSymbolName("+");
     this.setArity(2);  }

/* ----------------------- métodos de instânçia ----------------------------- */

public Value evaluate(Value[] args)
  {  return (new Value( args[0].getValue() + args[1].getValue() ));  }
  
/* ----------------------- métodos das interfaces --------------------------- */

public String toString()
  {  return (this.getSymbolName());  }

public boolean equals(Object o)
  {  return (o instanceof add);  }

public Object clone()
  {  return (new add());  }
  

}

/* -------------------------------------------------------------------------- */