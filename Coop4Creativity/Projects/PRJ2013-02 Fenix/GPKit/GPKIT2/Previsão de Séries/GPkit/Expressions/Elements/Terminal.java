/* -------------------------------------------------------------------------- */
/* Classe    : Terminal                                                       */
/* Descri��o : Classe abstracta utilizada para representar o comportamento das*/
/*             fun��es nas express�es.                                        */
/* -------------------------------------------------------------------------- */
package GPkit.Expressions.Elements;

import Aplication.Classes.Value.*;
import Aplication.Classes.Fitness.*;
import Aplication.Classes.Aritmetic.*;
import Aplication.Classes.FitnessCase.*;


public abstract class Terminal extends Symbol
{

/* ------------------------- vari�veis de inst�n�ia ------------------------- */

  /* informa��o que est� no terminal */
  private Value value = null;
  /* informa��o relativa ao facto de um terminal introduzir um 
     valor especifico quando for introduzido na �rvore. */  
  private boolean specific = false;
     
/* ----------------------- m�todos selectores (get/set) --------------------- */

public Value getValue()
  {  return(this.value);  }

public void setValue(Value o)
  {  this.value = (Value) o.clone();  }

public boolean getSpecific()
  {  return(this.specific);  }

public void setSpecific(boolean s)
  {  this.specific = s;  }
  
/* ----------------------- m�todos de inst�n�ia ----------------------------- */

public Value evaluate(Value[] args)
  {  return(this.getValue());  }

public abstract Terminal InsertSpecificTerminal();

/* ----------------------- m�todos das interfaces --------------------------- */

public abstract String toString();
public abstract boolean equals(Object o);
public abstract Object clone();

}

/* -------------------------------------------------------------------------- */