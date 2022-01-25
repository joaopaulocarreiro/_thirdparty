/* -------------------------------------------------------------------------- */
/* Classe    : functionNode                                                   */
/* Descrição : Classe que representa um nodo tipo de função                   */
/* -------------------------------------------------------------------------- */
package GPkit.Expressions;

import GPkit.Expressions.Elements.*;
import java.lang.Math;
import java.util.*;
import Aplication.Classes.Value.*;
import Aplication.Classes.Fitness.*;
import Aplication.Classes.Aritmetic.*;
import Aplication.Classes.FitnessCase.*;

public class functionNode extends expressionTree
{

/* ----------------------------- constructores ------------------------------ */

public functionNode(Function f)
  {  this.args = new expressionTree [f.getArity()];
     this.setArity(f.getArity());
     this.setFunction(f);  }
  
/* ------------------------- variáveis de instânçia ------------------------- */
  
  /* nº de argumentos */
  private int arity;
  /* os argumentos da função */
  private expressionTree[] args;
  /* a funçao propriamente dita */
  private Function function_instance;
  
/* ----------------------- métodos selectores (get/set) --------------------- */

public int getArity()
  {  return (this.arity);  }

public expressionTree getArgument(int index)
  {  return ( (expressionTree) args[index] );}

public expressionTree[] getAllArguments()
  {  return (this.args);  }   
  
public Function getFunction()
  {  return (this.function_instance);  }
  
public void setArity(int a)
  {  this.arity = a;  }

public void setArgument(int index, expressionTree a)
  {  args[index] = (expressionTree) a.clone();  }

public void setFunction(Function f)
  {  this.function_instance = (Function) f.clone();  }

/* ----------------------- métodos de instânçia ----------------------------- */

public int depth()
{  int l = 0;

   for(int i= 0; i < arity; i++)
     l = Math.max(l, this.getArgument(i).depth());
   return(l + 1);  }

public int size()
{  int s = 0;
   
   for(int i= 0; i < arity; i++)
     s += this.getArgument(i).size();
   return(s + 1);  }

public int leaves()
{  int l= 0;
   for(int i= 0; i < arity; i++)
     l += this.getArgument(i).leaves();
   return(l);  }

public expressionTree subTree(int n)
{
  if(n == 0)
    return (expressionTree) this;
  else
  {  for(int i= 0; i < arity; i++)
	   {  if (n > this.getArgument(i).size())
            n -= this.getArgument(i).size();
          else
            return( this.getArgument(i).subTree(n - 1));
       }
  }
  return null;
}

public void replaceTree(int n, expressionTree tree)
  {
  for(int i= 0; i < arity; i++)
    {  if(n == 0)
         {  this.setArgument(i,tree);
            return;
         }
       else 
         if(n >= this.getArgument(i).size())
           {  n -= this.getArgument(i).size();  }
         else 
           if(n < this.getArgument(i).size())
					{  this.getArgument(i).replaceTree(n - 1, tree);
                       return;  }
    }
  }

public Value evaluate()
  {  int nargs = this.getArity();
     Value[] evaluated_args = new Value[nargs];
     Value result;
     
     for(int i = 0; i < nargs; i++)
       evaluated_args[i] = this.getArgument(i).evaluate();
     
     result = this.getFunction().evaluate(evaluated_args);
     return (result);
  }

public Value evaluate(Object vars[], Value[] values)
  {  int nargs = this.getArity();
     Value[] evaluated_args = new Value[nargs];
     Value result;
     
     for(int i = 0; i < nargs; i++)
       evaluated_args[i] = this.getArgument(i).evaluate(vars,values);
     
     result = this.getFunction().evaluate(evaluated_args);
     return (result);
  }
	
/**
* Método     : insertArgument
* Descrição  : Este método insere um elemento na expressão, dum modo 
*              que uma determinada profundidade (altura) não seja ultrapassada.
*              A inserção começa sempre mais á esquerda possivel. Quando forem 
*              inseridos outros elementos a árvore de expressão vai ficando mais
*              composta. Sendo o objectivo principal construir uma árvore de 
*              expressão completa. De notar que se o elemento a inserir for uma 
*		       instânçia de Terminal então não poderão ser inseridos mais      
*              elementos a partir deste terminal para baixo.
* Parametros : (int) maxdepth -> Profundade máxima pretendida.
*              (expressionTree)     a -> Elemento a inserir.  
* Retorna    : 1 se a inserção foi executada com exito, ou 0 se não foi possivel
*              inserir o elemento.
*			   Existem básicamente duas razões para que o elemento não seja 
*              inserido :
*			    - A árvore está completa, ou seja á profundidade requerida por 
*				  maxdepth não existem mais nodos por preencher.
*				- Apesar da árvore não se encontrar completa, todas as folhas   
*                 são instâncias de Terminal.
*/
public int insertArgument(int maxdepth, expressionTree a)
{  int result = 0;
   int i = 0;
   int n_arg = this.getNArguments();
		
   if (maxdepth == -1)
   {  result = 0;  }
   else
     if (n_arg < this.getArity())
       {  setArgument(n_arg,a);
          result = 1;  }
     else
       {
         while ((result == 0) && (i < arity))
           {
             if (this.getArgument(i) instanceof functionNode)
               result = this.getArgument(i).insertArgument(maxdepth - 1,a);
             i++;
           }
       }
   return(result);
}
	
/* --------------------------- métodos privados ----------------------------- */

private int getNArguments() 
{  for (int i=0; i<this.args.length; i++)
    if (this.args[i] == null) return(i);
    return (this.arity);  }

private boolean equalfunctionNode(functionNode twin)
{  boolean equal;
   int n_arg = this.getNArguments();
   
   equal = (this.getArity() == twin.getArity()) &&
           (this.getFunction().equals(twin.getFunction()));
		
   if (equal)
   {
     for (int i = 0; i < n_arg && equal; i++)
       if(this.getArgument(i) != null)
         equal = this.getArgument(i).equals(twin.getArgument(i));
   }			
   return (equal);
}

protected void cloneArguments(functionNode twin)
  {  int n_arg = this.getNArguments();
   
   for(int i= 0; i < n_arg; i++)
     if(this.getArgument(i) != null)
       {
         if(this.getArgument(i) instanceof terminalNode)
           twin.setArgument(i, this.getArgument(i));
         else
           twin.setArgument(i, (expressionTree) (this.getArgument(i)).clone());
       }
  }

/* ----------------------- métodos das interfaces --------------------------- */

public String toString()
{  String str_aux;

   str_aux = this.getFunction().toString() + "(";
   for(int i= 0; i < this.getArity() - 1; i++)
     str_aux +=  this.getArgument(i).toString() + ",";
   str_aux += this.getArgument(this.getArity()-1).toString();	
   str_aux += ")";

   return (str_aux);
}

public boolean equals(Object o)
  {  boolean equal = false;
     functionNode func_node;  
     
     if (o instanceof functionNode)
      {  func_node = (functionNode) o;
         equal = this.equalfunctionNode(func_node);  }
            
     return (equal);  
  }

public Object clone()
  {  functionNode func_aux = new functionNode(this.getFunction());
     this.cloneArguments(func_aux); 
     return ( (Object) func_aux);  }
}

/* -------------------------------------------------------------------------- */