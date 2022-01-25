/* -------------------------------------------------------------------------- */
/* Classe    : paramOutput                                                    */
/* Descrição : Classe que comporta a informação necessária para produzir      */
/*             ficheiros de output                                            */
/* -------------------------------------------------------------------------- */

package Parameters;
import Misc.*;

public class paramOutput implements Cloneable,
                                    Standard
{

/* ------------------------- constantes de classe --------------------------- */

  static final String DEFAULT_basename = new String("gp");	
  static final int DEFAULT_bestn = 2;

/* ----------------------------- constructores ------------------------------ */

public paramOutput()
  {  this.basename = DEFAULT_basename;
     this.bestn = DEFAULT_bestn;
  }

public paramOutput(String basen, int bn)
  {  this.basename = basen;
     this.bestn = bn;	}

/* ------------------------- variáveis de instânçia ------------------------- */

  /* O nome do ficheiro base para os ficheiros de output */
  private String basename;
  /* O nº de melhores indivíduos escritos no output */
  private int bestn;

/* ----------------------- métodos selectores (get/set) --------------------- */

public String getBaseName()
  {  return(this.basename);  }

public int getBestN()
  {  return(this.bestn);  }

/* ----------------------- métodos das interfaces --------------------------- */

public String toString()
  { String str_aux;
   
    str_aux  = "output.basename=" + this.getBaseName() + "\n" +
               "output.bestn=" + this.getBestN() + "\n";
    return(str_aux);
  }

public boolean equals(Object o)
  { paramOutput param_aux;
    boolean equal = false;

    if (o instanceof paramOutput)
      {  param_aux = (paramOutput) o;param_aux = (paramOutput) o;
         equal = (this.getBaseName().equals(param_aux.getBaseName())) && 
                 (this.getBestN() == param_aux.getBestN());  }
    return(equal);
  }

public Object clone()
  {  return (new paramOutput(this.getBaseName(),
                             this.getBestN()));  }
}

/* -------------------------------------------------------------------------- */