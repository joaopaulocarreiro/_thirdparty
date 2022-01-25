/* -------------------------------------------------------------------------- */
/* Classe    : ERConstant                                                     */
/* Descrição : Classe utilizada para representar as ERC (ephemeral random     */
/*             constants).                                                    */
/* -------------------------------------------------------------------------- */
package Aplication.Classes.Aritmetic;

import GPkit.Expressions.Elements.*;
import Aplication.Classes.Value.*;
import Aplication.Classes.FitnessCase.*;
import java.util.*;

public class ERConstant extends Terminal
{

/* ------------------------- constantes de classe --------------------------- */

  private static final double DEFAULT_precision = 0.0001;
  
/* -------------------------- variáveis de classe --------------------------- */

  /* precisao */
  private double precision;
  /* gerador de numeros aleatórios */
  private Random ngenerator = new Random();		

/* ----------------------------- constructores ------------------------------ */

public ERConstant(char bi,int a, int b, char bs)
  { this.setSymbolName("ERC");
    if ( ((bi == '[') || (bi == ']')) && ( (bs == '[') || (bs == ']') ) )
      { this.bracketInf = bi;
        this.bracketSup = bs;  }
    else
      { this.bracketInf = '[';
        this.bracketSup = ']';  }
    this.set = 'N';    
    if ( a <= b )
      {
        this.infLimit = new Integer(a);
        this.supLimit = new Integer(b);
      }
    else
      {
        this.infLimit = new Integer(b);
        this.supLimit = new Integer(a);
      }    
    this.setSpecific(true);      
    this.setValue(new Value(0));       
  }

public ERConstant(char bi,double a, double b, char bs)
  {  this(bi,a,b,bs,DEFAULT_precision);  }

public ERConstant(char bi,double a, double b, char bs,double p)
  { this.setSymbolName("ERC");
    if ( ((bi == '[') || (bi == ']')) && ( (bs == '[') || (bs == ']') ) )
      { this.bracketInf = bi;
        this.bracketSup = bs;  }
    else
      { this.bracketInf = '[';
        this.bracketSup = ']';  }
    this.set = 'R';    
    if ( a <= b )
      {
        this.infLimit = new Double(a);
        this.supLimit = new Double(b);
      }
    else
      {
        this.infLimit = new Double(b);
        this.supLimit = new Double(a);
      }    
    this.setPrecision(p);
    this.setSpecific(true); 
    this.setValue(new Value(0)); 
  }
  
/* ------------------------- variáveis de instânçia ------------------------- */

  /* tipo de chaveta inferior */
  private char bracketInf;
  /* tipo de chaveta superior */  
  private char bracketSup;
  /* tipo de intervalo */  
  private char set;
  /* limite inferior */
  private Number infLimit;
  /* limite superior */
  private Number supLimit;
    
/* ----------------------- métodos selectores (get/set) --------------------- */

public char getBracketInf()
  {  return (this.bracketInf); }
  
public char getBracketSup()
  {  return (this.bracketSup); }
  
public char getSet()
  {  return (this.set); }
  
public Number getInfLimit()
  {  return (this.infLimit); }
  
public Number getSupLimit()
  {  return (this.supLimit); }

public void setPrecision(double p)
  {  if ((p > 0) && (p < 1)) 
       this.precision = p;
     else
       this.precision = DEFAULT_precision;  }

/* ----------------------- métodos de instânçia ----------------------------- */

public Value getERC()
  {  Value ret_value = new Value(0);
     
     if (this.set == 'N')
       {
         int inf = this.infLimit.intValue();
         int sup = this.supLimit.intValue();
         if (this.bracketInf == ']') inf++;
         if (this.bracketSup == '[') sup--;
         	
         int num = this.ngenerator.nextInt(sup - inf + 1);
         ret_value = new Value(inf + num);			
       }
     if (this.set == 'R')
       {
         double inf = this.infLimit.doubleValue();
         double sup = this.supLimit.doubleValue();

         if (this.bracketInf == ']') inf += this.precision;
         if (this.bracketSup == '[') sup -= this.precision;
		
         Double double_aux = new Double( (sup-inf) / this.precision);
         int num = this.ngenerator.nextInt(double_aux.intValue());
		 ret_value = new Value (inf + num * this.precision);			
       }
             
     return (ret_value);
  }

public Terminal InsertSpecificTerminal()         
  {  return (new Constant(this.getERC()));  }
  
/* ----------------------- métodos das interfaces --------------------------- */

public String toString()
  { String str_aux = "";
    
    if (this.set == 'N')
      {
        int inf = this.infLimit.intValue();
        int sup = this.supLimit.intValue();
        str_aux += "" + this.bracketInf + inf + "," + sup + this.bracketSup;
      }
    if (this.set == 'R')
      {
        double inf = this.infLimit.doubleValue();
        double sup = this.supLimit.doubleValue();
        str_aux += "" + this.bracketInf + inf + "," + sup + this.bracketSup;  
      }
    return (str_aux); 
  }
 
public boolean equals(Object o)
  {  boolean equals = false;
     if (o instanceof ERConstant)
       {  ERConstant e = (ERConstant) o;
          if (this.set == e.getSet())        
            {
              if (this.set == 'N')
              { int inf1 = this.infLimit.intValue();
                int sup1 = this.supLimit.intValue();
                int inf2 = e.getInfLimit().intValue();
                int sup2 = e.getSupLimit().intValue();
                equals = (inf1 == inf2) && (sup1 == sup2);  }
              if (this.set == 'R')
              { double inf1 = this.infLimit.doubleValue();
                double sup1 = this.supLimit.doubleValue();
                double inf2 = e.getInfLimit().doubleValue();
                double sup2 = e.getSupLimit().doubleValue();
                equals = (inf1 == inf2) && (sup1 == sup2);  }
            }  
       }
     return (equals);   
  }  
                     
                        
public Object clone()
  {  
     if (this.set == 'N')
       {
         int inf = this.infLimit.intValue();
         int sup = this.supLimit.intValue();
         ERConstant c = new ERConstant(this.bracketInf,inf,sup,this.bracketSup);
         c.setValue(this.getValue());
         return (c);
       }
   
     double inf = this.infLimit.doubleValue();
     double sup = this.supLimit.doubleValue();
     ERConstant c = (new ERConstant(this.bracketInf,
                                    inf,sup,
                                    this.bracketSup,
                                    this.precision));      
     c.setValue(this.getValue());                               
     return (c);
  }

}

/* -------------------------------------------------------------------------- */