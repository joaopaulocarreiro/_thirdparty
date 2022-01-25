/* -------------------------------------------------------------------------- */
/* Classe    : Value                                                          */
/* Descrição : Classe que implementa o tipo de dados dum problema GP especifi-*/
/*             co.                                                            */
/* -------------------------------------------------------------------------- */
package Specific;

public class Value 
{

/* ----------------------------- constructores ------------------------------ */

public Value(double v)
  {  this.setValue(v);  }

public Value()
  {  this.setValue(0);  }

/* ------------------------- variáveis de instânçia ------------------------- */

  /* valor numérico */
  private double num;
  
/* ----------------------- métodos selectores (get/set) --------------------- */

public double getValue()    
  {  return (this.num);  }
  
public void setValue(double n)    
  {  this.num = n;  }
  
/* ----------------------- métodos das interfaces --------------------------- */

public String toString()
  {  return ("" + this.getValue());  }

public boolean equals(Object o)
  {  boolean equal = false;
     Value val_aux;  
         
     if (o instanceof Value)
       {  val_aux = (Value) o;
          equal = this.getValue() == val_aux.getValue();  }
     return (equal);
  }     
                        
public Object clone()
  {  return (new Value(this.getValue()));  }

}

/* -------------------------------------------------------------------------- */