/* -------------------------------------------------------------------------- */
/* Classe    : paramGlobal                                                    */
/* Descrição : Classe que comporta a informação sobre parametros iniçiais para*/
/*             a geração da população inicial.                                */
/* -------------------------------------------------------------------------- */
package Parameters;

import Misc.*;

public class paramGlobal implements Cloneable,
                                    Standard
{

/* ------------------------- constantes de classe --------------------------- */

  static final int DEFAULT_max_generations = 1;
  static final int DEFAULT_population_size = 50;

/* ----------------------------- constructores ------------------------------ */

public paramGlobal()
  {  this.setMaxGenerations(DEFAULT_max_generations);
     this.setPopulationSize(DEFAULT_population_size);  }

public paramGlobal(int mg, int ps)
  {  this.setMaxGenerations(mg);
     this.setPopulationSize(ps);  }

/* ------------------------- variáveis de instânçia ------------------------- */

  /* nº máximo de gerações por run */
  private int max_generations;  
  /* tamanho da população */
  private int population_size;  

/* ----------------------- métodos selectores (get/set) --------------------- */

public int getMaxGenerations()
  {  return(this.max_generations);  }

public int getPopulationSize()
  {  return(this.population_size);  }

public void setMaxGenerations(int mg)
  {  this.max_generations = mg;  }

public void setPopulationSize(int ps)
  {  this.population_size = ps;  }

/* ----------------------- métodos das interfaces --------------------------- */

public String toString()
  { String str_aux;
    
    str_aux = "global.max_generations=" + this.getMaxGenerations() + "\n" +
              "global.population_size=" + this.getPopulationSize() + "\n";
              
    return(str_aux);
  }

public boolean equals(Object o)
  { paramGlobal param_aux;
    boolean equal = false;
    
    if (o instanceof paramGlobal)
      {
        param_aux = (paramGlobal) o;	param_aux = (paramGlobal) o;
        equal = (this.getMaxGenerations() == param_aux.getMaxGenerations()) &&
                (this.getPopulationSize() == param_aux.getPopulationSize());
      }        
    return(equal);
  }

public Object clone()
  {  return (new paramGlobal(this.getMaxGenerations(),
                             this.getPopulationSize()));  }

}

/* -------------------------------------------------------------------------- */