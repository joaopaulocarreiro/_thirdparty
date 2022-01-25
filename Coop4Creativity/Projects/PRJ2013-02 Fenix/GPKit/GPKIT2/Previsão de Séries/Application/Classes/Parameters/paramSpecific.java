/* -------------------------------------------------------------------------- */
/* Classe    : paramAplication                                                */
/* Descri��o : Classe que implementa os parametros de configura��o especifi-  */
/*             -cos de cada problema (aplica��o).                             */
/* -------------------------------------------------------------------------- */
package Aplication.Classes.Parameters;

import GPkit.Misc.Standard;

public class paramAplication implements Cloneable,
                                        Standard
{
/* ------------------------- constantes de classe --------------------------- */

  static final double DEFAULT_test_percentage = 0.80;
  static final double DEFAULT_window_size = 0.10;

/* ----------------------------- constructores ------------------------------ */

  public paramAplication ()
    {  this(DEFAULT_test_percentage, DEFAULT_window_size);  }

  public paramAplication (double tp, double ws)
    {  this.setTestPercentage(tp);
       this.setWindowSize(ws);  }

/* ------------------------- vari�veis de inst�n�ia ------------------------- */

  /* percentagem usada para os testes */
  private double test_percentage;
  
  /* definir (em percentagem) a dimens�o da janela */
  private double window_size;
  
/* ----------------------- m�todos selectores (get/set) --------------------- */

  public double getTestPercentage()
    {  return (this.test_percentage);  }
  public double getWindowSize()
    {  return (this.window_size);  }

  public void setTestPercentage(double tp)
    {  this.test_percentage = tp;  }
  public void setWindowSize(double ws)
    {  this.window_size = ws;  }
        
    
/* ----------------------- m�todos das interfaces --------------------------- */

  public String toString()
    { String str_aux;
       
      str_aux = "Aplication.test_percentage=" + getTestPercentage() + "\n" +
                "Aplication.window_size=" + getWindowSize() + "\n";
                
      return(str_aux);
    }

  public boolean equals(Object o)
    { paramAplication param_aux;
      boolean equal = false;
      
      if (o instanceof paramAplication)
        {
          param_aux = (paramAplication) o;	
          equal = (this.getTestPercentage() == param_aux.getTestPercentage()) &&
                  (this.getWindowSize() == param_aux.getWindowSize());
        }        
      return(equal);
    }

  public Object clone()
    {  return (new paramAplication(this.getTestPercentage(),
                                   this.getWindowSize()));  } 

}

/* -------------------------------------------------------------------------- */