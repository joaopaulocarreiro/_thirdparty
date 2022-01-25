/* -------------------------------------------------------------------------- */
/* Classe    : geneticOperator                                                */
/* Descri��o : Classe abstracta para a implementa��o dos operadores gen�ticos */
/* -------------------------------------------------------------------------- */
package GPkit.GeneticOperators;

import GPkit.Misc.*;
import GPkit.Population.*;
import GPkit.GeneratorFunctions.*;
import GPkit.Parameters.*;
import java.util.Random;

public abstract class geneticOperator implements Cloneable,
                                                 Standard
{

/* -------------------------- vari�veis de classe --------------------------- */

  /* gerador de numeros aleat�rios */
  protected Random generator;
  
/* ------------------------- vari�veis de inst�n�ia ------------------------- */
  
  /* nome do operador gen�tico */
  private String name;
  /* numero de individuos que o operador produz. */
  private int num_return_ind;
  /* limites das express�es */
  private paramLimits limits;

/* ----------------------- m�todos selectores (get/set) --------------------- */
  
public String getName()
  {  return (name);  }
  
public int getNumReturnInd()
  {  return (this.num_return_ind);  }
  
public paramLimits getLimits()
  {  return(this.limits);  }

protected void setName(String name)
  {  this.name = new String(name);  }
  
protected void setNumReturnInd(int nri)
  {  this.num_return_ind = nri;  }

public void setLimits(paramLimits l)
  {  this.limits = (paramLimits) l;  }  

public void setGenerator(Random r)
  {  this.generator = r;  }
  
/* ---------------------------- m�todos abstractos -------------------------- */

/**
 * M�todo     : doGeneticOperation
 * Descri��o  : Este m�todo � respons�vel pela implementa��o do operador gen�-
 *              -tico.
 * Parametros : Generation g - gera��o pela qual s�o selecionados os individuos
 *
 * Retorna    : Um array de individuos geneticamente produzidos pelo operador.
 */
public abstract Individual[] doOperation(Generation g);

/* ----------------------- m�todos das interfaces --------------------------- */
   
public abstract String toString();
public abstract boolean equals(Object o);
public abstract Object clone();

}

/* -------------------------------------------------------------------------- */