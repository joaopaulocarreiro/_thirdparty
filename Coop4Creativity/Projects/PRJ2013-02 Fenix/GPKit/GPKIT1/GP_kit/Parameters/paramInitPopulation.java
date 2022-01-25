/* -------------------------------------------------------------------------- */
/* Classe    : paramInitPopulation                                            */
/* Descrição : Classe que comporta a informação sobre parametros iniçiais para*/
/*             a geração da população inicial;                                */
/* -------------------------------------------------------------------------- */
package Parameters;

import Misc.*;
import GeneratorFunctions.*;

public class paramInitPopulation implements Cloneable,
                                            Standard
{

/* ------------------------- constantes de classe --------------------------- */

  static final generatePopulation DEFAULT_method = new rampedHalf_and_Half();
  static final depthRamp DEFAULT_depth = new depthRamp(2,6);
  static final int DEFAULT_random_attemps = 100;

/* ----------------------------- constructores ------------------------------ */

public paramInitPopulation()
  {  this.setMethod(DEFAULT_method);
     this.setDepth(DEFAULT_depth);
     this.setRandomAttemps(DEFAULT_random_attemps);
  }

public paramInitPopulation(generatePopulation m, depthRamp dr, int r)
  {  this.setMethod(m);
     this.setDepth(dr);
     this.setRandomAttemps(r);
  }

/* ------------------------- variáveis de instânçia ------------------------- */

  /* método para geração da população inicial */
  private generatePopulation method;
  /* rampa que será usada para gerar a população inicial */
  private depthRamp depth;
  /* nº máximo de árvores rejeitas consecutivamente que o programa tolera */
  private int random_attemps;

/* ----------------------- métodos selectores (get/set) --------------------- */

public generatePopulation getMethod()
  {  return(this.method);  }

public depthRamp getDepth()
  {  return(this.depth);  }

public int getRandomAttemps()
  {  return(this.random_attemps);  }

public void setMethod(generatePopulation m)
  {  this.method = (generatePopulation) m.clone();  }

public void setDepth(depthRamp dr)
  {  this.depth = (depthRamp) dr.clone() ;  }

public void setRandomAttemps(int ra)
  {  this.random_attemps = ra;  }

/* ----------------------- métodos das interfaces --------------------------- */

public String toString()
  { String str_aux;
    
    str_aux = "init.method=" + this.getMethod().toString() + "\n" +
              "init.depth=" + this.getDepth().toString() + "\n" +
              "init.random_attemps=" + this.getRandomAttemps() + "\n";
    return(str_aux);
  }

public boolean equals(Object o)
  { paramInitPopulation param_aux;
    boolean equal = false;

    if (o instanceof paramInitPopulation)
      {  param_aux = (paramInitPopulation) o;
         equal = this.getMethod().equals(param_aux.getMethod())   && 
                 this.getDepth().equals(param_aux.getDepth()) &&
                 this.getRandomAttemps() == param_aux.getRandomAttemps();
      }
    return(equal);
  }

public Object clone()
  {  return (new paramInitPopulation(this.getMethod(),
                                     this.getDepth(),
                                     this.getRandomAttemps()));
  }
}

/* -------------------------------------------------------------------------- */