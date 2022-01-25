/* -------------------------------------------------------------------------- */
/* Classe    : Symbol                                                         */
/* Descrição : Classe abstracta utilizada para representar o comportamento dos*/
/*             símbolos nas expressões.                                       */
/* -------------------------------------------------------------------------- */
package GPkit.Expressions.Elements;

import GPkit.Misc.*;
import Aplication.Classes.Value.*;

public abstract class Symbol implements Cloneable,
                                        Standard
{

/* ------------------------- variáveis de instânçia ------------------------- */

  /* nome do símbolo */  
  private String symbol_name;
  
  
/* ----------------------- métodos selectores (get/set) --------------------- */

public String getSymbolName()
  {  return (this.symbol_name);  }
  
public void setSymbolName(String n)
  {  this.symbol_name = new String(n);  }
  
/* ----------------------- métodos de instânçia ----------------------------- */

public abstract Value evaluate(Value[] args);

/* ----------------------- métodos das interfaces --------------------------- */

public abstract String toString();
public abstract boolean equals(Object o);
public abstract Object clone();

}

/* -------------------------------------------------------------------------- */