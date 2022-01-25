/* -------------------------------------------------------------------------- */
/* Classe    : Symbol                                                         */
/* Descri��o : Classe abstracta utilizada para representar o comportamento dos*/
/*             s�mbolos nas express�es.                                       */
/* -------------------------------------------------------------------------- */
package GPkit.Expressions.Elements;

import GPkit.Misc.*;
import Aplication.Classes.Value.*;

public abstract class Symbol implements Cloneable,
                                        Standard
{

/* ------------------------- vari�veis de inst�n�ia ------------------------- */

  /* nome do s�mbolo */  
  private String symbol_name;
  
  
/* ----------------------- m�todos selectores (get/set) --------------------- */

public String getSymbolName()
  {  return (this.symbol_name);  }
  
public void setSymbolName(String n)
  {  this.symbol_name = new String(n);  }
  
/* ----------------------- m�todos de inst�n�ia ----------------------------- */

public abstract Value evaluate(Value[] args);

/* ----------------------- m�todos das interfaces --------------------------- */

public abstract String toString();
public abstract boolean equals(Object o);
public abstract Object clone();

}

/* -------------------------------------------------------------------------- */