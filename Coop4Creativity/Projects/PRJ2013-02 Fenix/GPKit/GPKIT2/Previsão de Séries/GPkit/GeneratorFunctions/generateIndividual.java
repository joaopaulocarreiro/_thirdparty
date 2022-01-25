/* -------------------------------------------------------------------------- */
/* Classe    : generateIndividual                                             */
/* Descrição : Classe abstracta que implementa a estrutura duma classe gera-  */
/*             -dora de um indíviduo genético.                                */
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

/* ------------------------- variáveis de instânçia ------------------------- */

  /* nome da função geradora */
  private String generate_function_name;
  
/* ----------------------- métodos selectores (get/set) --------------------- */

protected String getGenerateFunctionName()
  {  return(this.generate_function_name);  }

protected void setGenerateFunctionName(String n)
  {  this.generate_function_name = new String(n);  }
  
/* ----------------------- métodos de instânçia ----------------------------- */

public abstract Individual generate(Function[] functions,
                                    Terminal[] terminals,
                                    int depth,
                                    Random ngenerator);

/* ----------------------- métodos das interfaces --------------------------- */

public abstract String toString();
public abstract boolean equals(Object o);
public abstract Object clone();
}

/* -------------------------------------------------------------------------- */