/* -------------------------------------------------------------------------- */
/* Classe    : generateIndividual                                             */
/* Descri��o : Classe abstracta que implementa a estrutura duma classe gera-  */
/*             -dora de um ind�viduo gen�tico.                                */
/* -------------------------------------------------------------------------- */
package GPkit.GeneratorFunctions;

import GPkit.Population.*;
import GPkit.Expressions.*;
import GPkit.Expressions.Elements.*;
import GPkit.Misc.*;
import java.util.*;

public abstract class generateIndividual implements Cloneable,
                                                    Standard
{

/* ------------------------- vari�veis de inst�n�ia ------------------------- */

  /* nome da fun��o geradora */
  private String generate_function_name;
  
/* ----------------------- m�todos selectores (get/set) --------------------- */

protected String getGenerateFunctionName()
  {  return(this.generate_function_name);  }

protected void setGenerateFunctionName(String n)
  {  this.generate_function_name = new String(n);  }
  
/* ----------------------- m�todos de inst�n�ia ----------------------------- */

public abstract Individual generate(Function[] functions,
                                    Terminal[] terminals,
                                    int depth,
                                    Random ngenerator);

/* ----------------------- m�todos das interfaces --------------------------- */

public abstract String toString();
public abstract boolean equals(Object o);
public abstract Object clone();
}

/* -------------------------------------------------------------------------- */