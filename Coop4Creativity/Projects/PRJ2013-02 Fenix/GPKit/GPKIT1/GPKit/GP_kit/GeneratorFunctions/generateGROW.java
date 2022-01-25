/* -------------------------------------------------------------------------- */
/* Classe    : generateGROW                                                   */
/* Descrição : método GROW para geração de individuos.                        */
/*                                                                            */
/* Método                                                                     */
/* ------                                                                     */
/*   Este método produz árvores de forma irregular, porque os nodos são esco- */
/* -lhidos aleatóriamente dos conjuntos das funções e terminais para todos os */
/* nodos da árvore (excepto para o nodo raiz que utiliza somente o conjunto   */
/* das funções). Quando um ramo contêm um nodo terminal, esse ramo termina,   */
/* mesmo que a profundidade máxima não tenha sido atingida.                   */
/*                                                                            */
/* -------------------------------------------------------------------------- */
package GeneratorFunctions;

import Population.*;
import Expressions.*;
import Expressions.Elements.*;
import Misc.*;
import java.util.Random;


public class generateGROW extends generateIndividual
{

/* ------------------------- constantes de classe --------------------------- */

  private static final double DEFAULT_grow_function_probability = 0.5;
  
/* ----------------------------- constructores ------------------------------ */

public generateGROW(double funcprob)
  {  this.setFunctionProbability(funcprob);  
     this.setGenerateFunctionName("grow");  }

public generateGROW()
  {  this(DEFAULT_grow_function_probability); }

/* ------------------------- variáveis de instânçia ------------------------- */

  /* probabilidade de gerar uma função */
  private double function_probability;

/* ----------------------- métodos selectores (get/set) --------------------- */

public double getFunctionProbability()
  {  return(this.function_probability);  }

public void setFunctionProbability(double p)
  {  this.function_probability = p;  }
  
  
/* ----------------------- métodos de instânçia ----------------------------- */

public Individual generate(Function[] functions, 
                           Terminal[] terminals,
                           int depthmax,
                           Random ngenerator)
  { 
    int depth;
    int depthcounter;
    int symbolcounter;
    int n_symbols;
    int n_depthsymbols;
    int nfunctions;
    int nterminals;
    int randomindex;
    expressionTree randomexpressionTree;
    expressionTree genetic_code;

    nfunctions = functions.length;
    nterminals = terminals.length;
   
    // Inicializar a estrutura da expressão.
    genetic_code = null;
    randomindex = ngenerator.nextInt(nfunctions);
    genetic_code = new functionNode(functions[randomindex]);
    n_symbols = functions[randomindex].getArity();
  
    // se a profundidade for menor que então será 1.
    if (depthmax < 1) depth = 1;
    else
      depth = depthmax;

    // Escolher aleatoriamente funções ou terminais e inserir.
    for  (depthcounter = 0; depthcounter < depth - 1; depthcounter++)
      { n_depthsymbols = 0;	
        for (symbolcounter = 0; symbolcounter < n_symbols; symbolcounter++)
          {  // função
             if (ngenerator.nextDouble() < this.getFunctionProbability())   
             {  randomindex = ngenerator.nextInt(nfunctions);
                randomexpressionTree = new functionNode(functions[randomindex]);
                n_depthsymbols += functions[randomindex].getArity();  }
             // terminal	
             else							  
             { randomindex = ngenerator.nextInt(nterminals);
               Terminal t_aux = terminals[randomindex]; 
               // Se este terminal tiver algum comportamento especifico antes
               // de ser introduzido na árvore então é executado.  
               if (t_aux.getSpecific())
                   t_aux = t_aux.InsertSpecificTerminal();                      
               randomexpressionTree = new terminalNode(t_aux);
               genetic_code.insertArgument(depthcounter,randomexpressionTree); }
          }
  	     n_symbols = n_depthsymbols;
       }
      
      
    // Escolher aleatóriamente os terminais e inserir-los.
    for (symbolcounter = 0; symbolcounter < n_symbols; symbolcounter++)
      {
        randomindex = ngenerator.nextInt(nterminals);
        Terminal t_aux = terminals[randomindex]; 
        // Se este terminal tiver algum comportamento especifico antes
        // de ser introduzido na árvore então é executado.  
        if (t_aux.getSpecific())
              t_aux = t_aux.InsertSpecificTerminal();                   
        randomexpressionTree = new terminalNode(t_aux);
        genetic_code.insertArgument(depth,randomexpressionTree);
      }
  
  return (new Individual(genetic_code));
  
  
  }

/* ----------------------- métodos das interfaces --------------------------- */

public String toString()
  {  return(this.getGenerateFunctionName());  }

public boolean equals(Object o)
  {  return (o instanceof generateGROW);  }
  
public Object clone()
  {  return (new generateGROW(this.getFunctionProbability()));}
}

/* -------------------------------------------------------------------------- */