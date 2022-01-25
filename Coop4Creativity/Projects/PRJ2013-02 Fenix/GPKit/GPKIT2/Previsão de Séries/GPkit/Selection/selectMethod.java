/* -------------------------------------------------------------------------- */
/* Classe    : selectMethod                                                   */
/* Descrição : Classe abstracta para a implementação dos métodos de selecção. */
/* -------------------------------------------------------------------------- */
package GPkit.Selection;

import GPkit.Misc.*;
import GPkit.Population.*;
import java.util.Random;

public abstract class selectMethod implements Cloneable,
                                              Standard
{

/* -------------------------- variáveis de classe --------------------------- */

  /* gerador de numeros aleatórios */
  protected Random generator;
  
/* ------------------------- variáveis de instânçia ------------------------- */

  /* nome do método de selecção */
  protected String name;

/* ----------------------- métodos selectores (get/set) --------------------- */
  
public String getName()
  {  return (this.name);  }

protected void setName(String n)
  {  this.name = new String(n);  }

public void setGenerator(Random r)
  {  this.generator = r;  }

/* ----------------------- métodos de instânçia ----------------------------- */
  
/**
 * Método     : doSelection
 * Descrição  : Este método será implementado pelas subclasses para implementar
 *              o método de selecção.
 * Parametros : Generation g - uma determinada geração.
 * Retorna    : o individuo seleccionado.
 */  
public abstract Individual doSelection(Generation g);

/* ----------------------- métodos das interfaces --------------------------- */

public abstract String toString();
public abstract boolean equals(Object o);
public abstract Object clone();
  
}

/* -------------------------------------------------------------------------- */