/* -------------------------------------------------------------------------- */
/* Classe    : geneticOperator                                                */
/* Descrição : Classe abstracta para a implementação dos operadores genéticos */
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

/* -------------------------- variáveis de classe --------------------------- */

  /* gerador de numeros aleatórios */
  protected Random generator;
  
/* ------------------------- variáveis de instânçia ------------------------- */
  
  /* nome do operador genético */
  private String name;
  /* numero de individuos que o operador produz. */
  private int num_return_ind;
  /* limites das expressões */
  private paramLimits limits;

/* ----------------------- métodos selectores (get/set) --------------------- */
  
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
  
/* ---------------------------- métodos abstractos -------------------------- */

/**
 * Método     : doGeneticOperation
 * Descrição  : Este método é responsável pela implementação do operador gené-
 *              -tico.
 * Parametros : Generation g - geração pela qual são selecionados os individuos
 *
 * Retorna    : Um array de individuos geneticamente produzidos pelo operador.
 */
public abstract Individual[] doOperation(Generation g);

/* ----------------------- métodos das interfaces --------------------------- */
   
public abstract String toString();
public abstract boolean equals(Object o);
public abstract Object clone();

}

/* -------------------------------------------------------------------------- */