/* -------------------------------------------------------------------------- */
/* Classe    : generatePopulation                                             */
/* Descrição : Classe abstracta que implementa a estrutura duma classe gera-  */
/*             -dora de uma população.                                        */
/* -------------------------------------------------------------------------- */
package GPkit.GeneratorFunctions;

import GPkit.Population.*;
import GPkit.Misc.*;
import GPkit.Expressions.*;
import GPkit.Expressions.Elements.*;
import GPkit.Parameters.*;
import java.util.*;

public abstract class generatePopulation implements Cloneable,
                                                    Standard
{

/* -------------------------- variáveis de classe --------------------------- */

  /* gerador de números aleatórios */
  protected Random generator; 

/* ------------------------- variáveis de instânçia ------------------------- */

  /* nome da função geradora */
  private String name;
  /* depth ramp */
  private depthRamp ramp;
  /* nº máximo de árvores rejeitadas consecutivamente que a função geradora 
     tolera antes de terminar com uma mensagem de erro  */
  private int random_attemps;
  /* limites das expressões */
  private paramLimits limits;
  
/* ----------------------- métodos selectores (get/set) --------------------- */

public String getName()
  {  return(this.name);  }

public depthRamp getDepthRamp()
  {  return(this.ramp);  }

public int getRandomAttemps()
  {  return(this.random_attemps);}

public paramLimits getLimits()
  {  return(this.limits);  }

public void setName(String n)
  {  this.name = new String(n);  }
  
public void setDepthRamp(depthRamp dr)
  {  this.ramp = (depthRamp) dr.clone();  }

public void setRandomAttemps(int ra)
  {  this.random_attemps = ra;  }
  
public void setLimits(paramLimits l)
  {  this.limits = (paramLimits) l;  }

public void setGenerator(Random r)
  {  this.generator = r;  }
  

/* ----------------------- métodos de instânçia ----------------------------- */

public abstract Population generate(int pop_size,
                                    Function[] functions, 
                                    Terminal[] terminals
                                    ) throws RandomAttempsExpiredException;

/* ----------------------- métodos das interfaces --------------------------- */

public abstract String toString();
public abstract boolean equals(Object o);
public abstract Object clone();
}

/* -------------------------------------------------------------------------- */