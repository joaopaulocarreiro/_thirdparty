/* -------------------------------------------------------------------------- */
/* Classe    : fitnessCase                                                    */
/* Descrição : Classe que implementa o tipo de dados dos casos de fitness.    */
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

/* ------------------------- variáveis de instânçia ------------------------- */

  /* valor numérico */
  private double num;
  
/* ----------------------- métodos selectores (get/set) --------------------- */

public void setNum(double num)
  {  this.num = num;  }

public double getNum()
  {  return(this.num);  }

/* ----------------------- métodos das interfaces --------------------------- */

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