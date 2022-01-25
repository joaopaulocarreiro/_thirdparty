/* -------------------------------------------------------------------------- */
/* Classe    : paramOutput                                                    */
/* Descri��o : Classe que comporta a informa��o necess�ria para produzir      */
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

/* ------------------------- vari�veis de inst�n�ia ------------------------- */

  /* O n� de melhores indiv�duos escritos no output */
  private int bestn;

/* ----------------------- m�todos selectores (get/set) --------------------- */

public int getBestN()
  {  return(this.bestn);  }

/* ----------------------- m�todos das interfaces --------------------------- */

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