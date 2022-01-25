/* -------------------------------------------------------------------------- */
/* Classe    : fitnessCase                                                    */
/* Descri��o : Classe que implementa o tipo de dados dos casos de fitness.    */
/* -------------------------------------------------------------------------- */
package Specific;

public class fitnessCase 
{

/* ----------------------------- constructores ------------------------------ */

public fitnessCase(double x,double y)
  {  this.setX(x);
     this.setY(y);  }

public fitnessCase()
  {  this.setX(0);
     this.setY(0);  }

/* ------------------------- vari�veis de inst�n�ia ------------------------- */

  /* valor num�rico de x */
  private double x;
  /* valor num�rico de y */
  private double y;
  
/* ----------------------- m�todos selectores (get/set) --------------------- */

public void setX(double x)
  {  this.x = x;  }

public void setY(double y)
  {  this.y = y;  }
  
public double getX()
  {  return(this.x);  }

public double getY()
  {  return(this.y);  }
  
/* ----------------------- m�todos das interfaces --------------------------- */

public String toString()
  {  return ("(" + this.x + "," + this.y + ")");  }

public boolean equals(Object o)
  {  boolean equal = false;
     fitnessCase fit_aux;  
         
     if (o instanceof fitnessCase)
       {  fit_aux = (fitnessCase) o;
          equal = (this.x == fit_aux.getX()) &&
                  (this.y == fit_aux.getY());  }
     return (equal);
  }     
                        
public Object clone()
  {  return (new fitnessCase(this.getX(),this.getY()));  }

}

/* -------------------------------------------------------------------------- */