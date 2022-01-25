/* -------------------------------------------------------------------------- */
/* Classe    : mul                                                            */
/* Descri��o : multiplica��o                                                  */
/* -------------------------------------------------------------------------- */
package Aplication.Classes.Aritmetic;

import GPkit.Expressions.Elements.*;
import Aplication.Classes.Value.*;
import Aplication.Classes.FitnessCase.*;

public class mul extends Function
{

/* ----------------------------- constructores ------------------------------ */

public mul()
  {  this.setSymbolName("*");
     this.setArity(2);  }

/* ----------------------- m�todos de inst�n�ia ----------------------------- */

public Value evaluate(Value[] args)
  {  return (new Value(args[0].getValue() * args[1].getValue()));  }
  
/* ----------------------- m�todos das interfaces --------------------------- */

public String toString()
  {  return (this.getSymbolName());  }

public boolean equals(Object o)
  {  return (o instanceof mul);  }

public Object clone()
  {  return (new mul());  }

}

/* -------------------------------------------------------------------------- */