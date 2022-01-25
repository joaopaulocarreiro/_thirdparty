/* -------------------------------------------------------------------------- */
/* Classe    : generatePopulation                                             */
/* Descri��o : Classe abstracta que implementa a estrutura duma classe gera-  */
/*             -dora de uma popula��o.                                        */
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

/* -------------------------- vari�veis de classe --------------------------- */

  /* gerador de n�meros aleat�rios */
  protected Random generator; 

/* ------------------------- vari�veis de inst�n�ia ------------------------- */

  /* nome da fun��o geradora */
  private String name;
  /* depth ramp */
  private depthRamp ramp;
  /* n� m�ximo de �rvores rejeitadas consecutivamente que a fun��o geradora 
     tolera antes de terminar com uma mensagem de erro  */
  private int random_attemps;
  /* limites das express�es */
  private paramLimits limits;
  
/* ----------------------- m�todos selectores (get/set) --------------------- */

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
  

/* ----------------------- m�todos de inst�n�ia ----------------------------- */

public abstract Population generate(int pop_size,
                                    Function[] functions, 
                                    Terminal[] terminals
                                    ) throws RandomAttempsExpiredException;

/* ----------------------- m�todos das interfaces --------------------------- */

public abstract String toString();
public abstract boolean equals(Object o);
public abstract Object clone();
}

/* -------------------------------------------------------------------------- */