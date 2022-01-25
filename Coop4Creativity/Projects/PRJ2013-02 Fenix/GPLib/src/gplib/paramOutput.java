/* -------------------------------------------------------------------------- */
/* Classe    : paramOutput                                                    */
/* Descrição : Classe que comporta a informação necessária para produzir      */
/*             ficheiros de output                                            */
/* -------------------------------------------------------------------------- */
package GPkit.Parameters;

import GPkit.Misc.*;

public class paramOutput implements Cloneable,
                                    Standard
{

/* ------------------------- constantes de classe --------------------------- */

  static final int DEFAULT_bestn = 2;

/* ----------------------------- constructores ------------------------------ */

public paramOutput()
  {  this (DEFAULT_bestn);  }

public paramOutput(int bn)
  {  this.bestn = bn;	}

/* ------------------------- variáveis de instânçia ------------------------- */

  /* O nº de melhores indivíduos escritos no output */
  private int bestn;

/* ----------------------- métodos selectores (get/set) --------------------- */

public int getBestN()
  {  return(this.bestn);  }

/* ----------------------- métodos das interfaces --------------------------- */

public String toString()
  {  String str_aux;
     str_aux  = "output.bestn=" + this.getBestN() + "\n";
     return(str_aux);  }

public boolean equals(Object o)
  { paramOutput param_aux;
    boolean equal = false;

    if (o instanceof paramOutput)
      {  param_aux = (paramOutput) o;
         equal = (this.getBestN() == param_aux.getBestN());  }
    return(equal);
  }

public Object clone()
  {  return (new paramOutput(this.getBestN()));  }
}

/* -------------------------------------------------------------------------- */