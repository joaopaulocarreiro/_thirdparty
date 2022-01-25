/* -------------------------------------------------------------------------- */
/* Classe    : Function                                                       */
/* Descri��o : Classe abstracta utilizada para representar o comportamento das*/
/*             fun��es nas express�es.                                        */
/* -------------------------------------------------------------------------- */
package GPkit.Expressions.Elements;

import Aplication.Classes.Value.*;

public abstract class Function extends Symbol
{

/* ------------------------- vari�veis de inst�n�ia ------------------------- */

  /* aridade da fun��o (n� de argumentos) */  
  private int arity = 0;
  
  
/* ----------------------- m�todos selectores (get/set) --------------------- */

public int getArity()
  {  return (this.arity);  }

public void setArity(int a)
  {  this.arity = a;  }
  
/* ----------------------- m�todos de inst�n�ia ----------------------------- */

public abstract Value evaluate(Value[] args);

/* ----------------------- m�todos das interfaces --------------------------- */

public abstract String toString();
public abstract boolean equals(Object o);
public abstract Object clone();

}

/* -------------------------------------------------------------------------- */