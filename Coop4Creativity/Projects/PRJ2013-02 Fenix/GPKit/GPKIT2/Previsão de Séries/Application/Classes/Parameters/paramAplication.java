/* -------------------------------------------------------------------------- */
/* Classe    : paramAplication                                                */
/* Descrição : Classe que implementa os parametros de configuração especifi-  */
/*             -cos de cada problema (aplicação).                             */
/* -------------------------------------------------------------------------- */
package Aplication.Classes.Parameters;

import GPkit.Misc.Standard;

public class paramAplication implements Cloneable,
                                        Standard
{
/* ------------------------- constantes de classe --------------------------- */

  static final double DEFAULT_test_percentage = 0.80;
  static final double DEFAULT_window_size = 0.10;
  static final String DEFAULT_outputfile = "default.dat";  

/* ----------------------------- constructores ------------------------------ */

  public paramAplication ()
    {  this(DEFAULT_test_percentage, 
            DEFAULT_window_size,
            DEFAULT_outputfile);  }

  public paramAplication (double tp, double ws, String of)
    {  this.setTestPercentage(tp);
       this.setWindowSize(ws);
       this.setOutputFile(of);  }

/* ------------------------- variáveis de instânçia ------------------------- */

  /* percentagem usada para os testes */
  private double test_percentage;
  
  /* definir (em percentagem) a dimensão da janela */
  private double window_size;
  
  /* definir o nome do ficheiro de output */
  private String outputfile;
  
/* ----------------------- métodos selectores (get/set) --------------------- */

  public double getTestPercentage()
    {  return (this.test_percentage);  }
  public double getWindowSize()
    {  return (this.window_size);  }
  public String getOutputFile()
    {  return (this.outputfile);  }

  public void setTestPercentage(double tp)
    {  this.test_percentage = tp;  }
  public void setWindowSize(double ws)
    {  this.window_size = ws;  }
  public void setOutputFile(String of)
    {  this.outputfile = of;  }
        
    
/* ----------------------- métodos das interfaces --------------------------- */

  public String toString()
    { String str_aux;
       
      str_aux = "Aplication.test_percentage=" + getTestPercentage() + "\n" +
                "Aplication.window_size=" + getWindowSize() + "\n" +
                "Aplication.output_file=" + getOutputFile() + "\n";
                
      return(str_aux);
    }

  public boolean equals(Object o)
    { paramAplication param_aux;
      boolean equal = false;
      
      if (o instanceof paramAplication)
        {
          param_aux = (paramAplication) o;	
          equal = (this.getTestPercentage() == param_aux.getTestPercentage()) &&
                  (this.getWindowSize() == param_aux.getWindowSize()) &&
                  (this.getOutputFile().equals(param_aux.getOutputFile()));
        }        
      return(equal);
    }

  public Object clone()
    {  return (new paramAplication(this.getTestPercentage(),
                                   this.getWindowSize(),
                                   this.getOutputFile()));  } 

}

/* -------------------------------------------------------------------------- */