/* -------------------------------------------------------------------------- */
/* Classe    : paramLimits                                                    */
/* Descri��o : Classe que comporta a informa��o sobre os limites do tamanho/n�*/
/*             de nodos das �rvores de express�o.                             */
/* -------------------------------------------------------------------------- */
package Parameters;

import Misc.*;

public class paramLimits implements Cloneable,
                                    Standard
{

/* ------------------------- constantes de classe --------------------------- */

  static final int DEFAULT_max_nodes = -1;					
  static final int DEFAULT_max_depth = -1;

/* ----------------------------- constructores ------------------------------ */

public paramLimits()
  {  this.setMaxNodes(DEFAULT_max_nodes);
     this.setMaxDepth(DEFAULT_max_depth);  }

public paramLimits(int mn, int md)
  {  this.setMaxNodes(mn);
     this.setMaxDepth(md);  }

/* ------------------------- vari�veis de inst�n�ia ------------------------- */

  /* n� m�ximo de nodos das �rvores de express�o */
  private int max_nodes;
  /* profundidade m�xima das �rvores de express�o */
  private int max_depth;

/* ----------------------- m�todos selectores (get/set) --------------------- */

public int getMaxNodes()
  {  return(this.max_nodes);  }

public int getMaxDepth()
  {  return(this.max_depth);  }

public void setMaxNodes(int mn)
  {  this.max_nodes = mn;  }

public void setMaxDepth(int md)
  {  this.max_depth = md;  }

/* ----------------------- m�todos das interfaces --------------------------- */

public String toString()
  { String max_n;
    String max_d;
    String str_aux;

    if (this.getMaxNodes() <= 0) 
      max_n = "not defined";
    else
      max_n = "" + this.getMaxNodes();

    if (this.getMaxDepth() <= 0)
      max_d = "not defined";
    else
      max_d = "" + this.getMaxDepth();

    str_aux = "limits.max_nodes=" + max_n + "\n" +
              "limits.max_depth=" + max_d + "\n";
    return(str_aux);
  }

public boolean equals(Object o)
  { paramLimits param_aux;
    boolean equal = false;
    
    if (o instanceof paramLimits)
      {  param_aux = (paramLimits) o;param_aux = (paramLimits) o;
         equal = (this.getMaxNodes() == param_aux.getMaxNodes() ) && 
         (this.getMaxDepth() == param_aux.getMaxDepth() );
      }
    return(equal);
  }

public Object clone()
  {  return (new paramLimits(this.getMaxNodes(),
                             this.getMaxDepth()));
  }
}

/* -------------------------------------------------------------------------- */