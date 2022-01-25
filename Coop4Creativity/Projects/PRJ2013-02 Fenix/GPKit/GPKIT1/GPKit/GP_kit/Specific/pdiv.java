/* -------------------------------------------------------------------------- */
/* Classe    : pdiv                                                           */
/* Descrição : divisão protegida                                              */
/* -------------------------------------------------------------------------- */
package Specific;

import Expressions.Elements.*;

public class pdiv extends Function
{

/* ----------------------------- constructores ------------------------------ */

public pdiv()
  {  this.setSymbolName("/");
     this.setArity(2);  }

/* ----------------------- métodos de instânçia ----------------------------- */

public Value evaluate(Value[] args)
  { if (args[1].getValue() == 0) return new Value(1.0); 
    return (new Value(args[0].getValue() / args[1].getValue()));  }
  
/* ----------------------- métodos das interfaces --------------------------- */

public String toString()
  {  return (this.getSymbolName());  }

public boolean equals(Object o)
  {  return (o instanceof pdiv);  }

public Object clone()
  {  return (new pdiv());  }
  

}

/* -------------------------------------------------------------------------- */