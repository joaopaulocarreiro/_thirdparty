/* -------------------------------------------------------------------------- */
/* Classe    : terminalNode                                                   */
/* Descri��o : representa��o dos terminais nas �rvores de express�o.          */
/* -------------------------------------------------------------------------- */

package Expressions;
import Expressions.Elements.*;
import Specific.*;

public class terminalNode extends expressionTree implements Cloneable
{

/* ----------------------------- constructores ------------------------------ */

public terminalNode(Terminal t)
  {  this.setTerminal(t);  }
  
/* ------------------------- vari�veis de inst�n�ia ------------------------- */

  /* o pr�prio terminal */
  private Terminal terminal_instance;

/* ----------------------- m�todos selectores (get/set) --------------------- */

public Terminal getTerminal()  
  {  return(this.terminal_instance);  }

public void setTerminal(Terminal t)
  {   this.terminal_instance = (Terminal) t.clone();  }
  
/* ----------------------- m�todos de inst�n�ia ----------------------------- */

public int depth() 
  {  return (0);  }

public int size() 
  {  return (1);  }

public int leaves() 
  {  return (1);  }

public expressionTree subTree(int n)
  {  if(n == 0)
       return (expressionTree) this;
     else
       return (expressionTree) null;  }

public void replaceTree(int n, expressionTree tree)
  {}

public int insertArgument(int maxdepth, expressionTree a)
  {  return (0);  }

public Value evaluate()
  { return (this.getTerminal().getValue());  }

public Value evaluate(Object vars[],Value[] values)
  { Value ret_value = this.evaluate();
    boolean endwhile = false;

    for (int i=0; (i < vars.length) && (!endwhile) ; i++)  
      {  Terminal term_aux;
         if (this.getTerminal().equals(vars[i]))
           {  ret_value = (Value) values[i].clone();  
              endwhile = true;  } 
      }
    return (ret_value);  
  }  

/* ----------------------- m�todos das interfaces --------------------------- */

public String toString()
  {  return (this.getTerminal().toString());  }   

public boolean equals(Object o)
  {  boolean equal = false;
     terminalNode t_aux;
     
     if (o instanceof terminalNode)
       {  t_aux = (terminalNode) o;  
          equal = this.getTerminal().equals(t_aux.getTerminal());
       }
     return (equal);   
  }
  
public Object clone()
  {  return(new terminalNode(this.getTerminal()));  }
  
}

/* -------------------------------------------------------------------------- */
