/* -------------------------------------------------------------------------- */
/* Classe    : Terminal                                                       */
/* Descrição : Classe abstracta utilizada para representar o comportamento das*/
/*             funções nas expressões.                                        */
/* -------------------------------------------------------------------------- */
package GPkit.Expressions.Elements;

import Aplication.Classes.Value.*;
import Aplication.Classes.Fitness.*;
import Aplication.Classes.Aritmetic.*;
import Aplication.Classes.FitnessCase.*;


public abstract class Terminal extends Symbol
{

/* ------------------------- variáveis de instânçia ------------------------- */

  /* informação que está no terminal */
  private Value value = null;
  /* informação relativa ao facto de um terminal introduzir um 
     valor especifico quando for introduzido na árvore. */  
  private boolean specific = false;
     
/* ----------------------- métodos selectores (get/set) --------------------- */

public Value getValue()
  {  return(this.value);  }

public void setValue(Value o)
  {  this.value = (Value) o.clone();  }

public boolean getSpecific()
  {  return(this.specific);  }

public void setSpecific(boolean s)
  {  this.specific = s;  }
  
/* ----------------------- métodos de instânçia ----------------------------- */

public Value evaluate(Value[] args)
  {  return(this.getValue());  }

public abstract Terminal InsertSpecificTerminal();

/* ----------------------- métodos das interfaces --------------------------- */

public abstract String toString();
public abstract boolean equals(Object o);
public abstract Object clone();

}

/* -------------------------------------------------------------------------- */