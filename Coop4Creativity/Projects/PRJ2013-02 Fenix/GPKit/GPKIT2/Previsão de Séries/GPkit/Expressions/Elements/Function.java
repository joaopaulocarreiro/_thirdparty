/* -------------------------------------------------------------------------- */
/* Classe    : Function                                                       */
/* Descrição : Classe abstracta utilizada para representar o comportamento das*/
/*             funções nas expressões.                                        */
/* -------------------------------------------------------------------------- */
package GPkit.Expressions.Elements;

import Aplication.Classes.Value.*;

public abstract class Function extends Symbol
{

/* ------------------------- variáveis de instânçia ------------------------- */

  /* aridade da função (nº de argumentos) */  
  private int arity = 0;
  
  
/* ----------------------- métodos selectores (get/set) --------------------- */

public int getArity()
  {  return (this.arity);  }

public void setArity(int a)
  {  this.arity = a;  }
  
/* ----------------------- métodos de instânçia ----------------------------- */

public abstract Value evaluate(Value[] args);

/* ----------------------- métodos das interfaces --------------------------- */

public abstract String toString();
public abstract boolean equals(Object o);
public abstract Object clone();

}

/* -------------------------------------------------------------------------- */