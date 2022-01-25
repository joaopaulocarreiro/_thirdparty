/* -------------------------------------------------------------------------- */
/* Classe    : Misc                                                           */
/* Descrição : Classe que implementa métodos uteis.                           */
/* -------------------------------------------------------------------------- */
package Misc;

import java.util.Random;
import java.lang.Math;
import java.util.Vector;
import java.util.Enumeration;

public final class Misc                           
                                             
{

/* ---------------------------------- métodos ------------------------------- */

public static void copyArray(Object[] from, Object[] to)
  { to = new Object[from.length];
    for (int i=0; i<from.length; i++ )
      { to[i] = from[i]; }
  }

public static String toStringArray(Object[] from)
  { String str = "{" + "null" + "}";

    if (from != null)
      {    
        str = "{";
        for (int i=0; i<from.length-1; i++ )
          str += from[i] + ","; 
    
        str += from[from.length - 1] + "}";  
      }
    return(str);  
  }

public static String toStringArray(char b1,Object[] from,char b2)
  { String str = "" + b1 + "null" + b2;
   
   if (from != null)
     {
       str = "" + b1;
       for (int i=0; i<from.length-1; i++ )
         str += from[i] + ","; 
    
       str += from[from.length - 1] + "" + b2;  
     }  
    return(str);  
  }

public static Object[] returnArrayUnRepeated(Object[] from)
  {  Vector vec_aux = new Vector();
     
     
     for (int i=0; i<from.length; i++ )
        if (!vec_aux.contains(from[i]))
          vec_aux.addElement(from[i]);
     
     return (vec_aux.toArray());
          
     //System.out.println("\n" +toStringArray(to));          
  }

}

/* -------------------------------------------------------------------------- */