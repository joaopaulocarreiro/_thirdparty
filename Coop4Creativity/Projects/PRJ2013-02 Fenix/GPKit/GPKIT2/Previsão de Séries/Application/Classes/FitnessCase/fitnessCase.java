/* -------------------------------------------------------------------------- */
/* Classe    : fitnessCase                                                    */
/* Descri��o : Classe que implementa o tipo de dados dos casos de fitness.    */
/* -------------------------------------------------------------------------- */
package Aplication.Classes.FitnessCase;

public class fitnessCase 
{

/* ----------------------------- constructores ------------------------------ */

  public fitnessCase(double num)
    {  this.setNum(num);  }

  public fitnessCase(fitnessCase f)
    {  this.setNum(f.getNum());  }

  public fitnessCase()
    {  this.setNum(0);  }

/* ------------------------- vari�veis de inst�n�ia ------------------------- */

  /* valor num�rico */
  private double num;
  
/* ----------------------- m�todos selectores (get/set) --------------------- */

public void setNum(double num)
  {  this.num = num;  }

public double getNum()
  {  return(this.num);  }

/* ----------------------- m�todos das interfaces --------------------------- */

public String toString()
  {  return ("" + this.num);  }

public boolean equals(Object o)
  {  boolean equal = false;
     fitnessCase fit_aux;  
         
     if (o instanceof fitnessCase)
       {  fit_aux = (fitnessCase) o;
          equal = (this.num == fit_aux.getNum());  }
     return (equal);
  }     
                        
public Object clone()
  {  return (new fitnessCase(this.num));  }

}

/* -------------------------------------------------------------------------- */