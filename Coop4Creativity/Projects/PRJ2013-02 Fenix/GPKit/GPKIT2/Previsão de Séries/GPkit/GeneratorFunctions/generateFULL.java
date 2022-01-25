/* -------------------------------------------------------------------------- */
/* Classe    : generateFULL                                                   */
/* Descrição : método FULL para geração de individuos.                        */
/*                                                                            */
/* Método                                                                     */
/* ------                                                                     */
/*   Este método produz árvores completas, porque os nodos são escolhidos so- */
/* -mente do conjunto das funções, até que um nodo esteja há profundidade má- */
/* -xima. Depois só escolhe terminais para acabar de gerar a árvore. O resul- */
/* -tado é que todos os ramos de árvores chegam até á profundidade máxima.    */
/*   Claro que este método respeita os limites impostos para a profundidade e */
/* numero de nodos das árvores.                                               */
/*                                                                            */
/* -------------------------------------------------------------------------- */
package GPkit.GeneratorFunctions;

import GPkit.Population.*;
import GPkit.Expressions.*;
import GPkit.Expressions.Elements.*;
import GPkit.Misc.*;
import java.util.Random;

public class generateFULL extends generateIndividual
{

/* ----------------------------- constructores ------------------------------ */

public generateFULL()
  {  this.setGenerateFunctionName("full");  }
  
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

  	
    // se a profundidade for menor 1 que então será 1.
    if (depthmax < 1) depth = 1;
    else
        depth = depthmax;

    for  (depthcounter = 0; depthcounter < depth - 1; depthcounter++)
      { n_depthsymbols = 0;	
        for (symbolcounter = 0; symbolcounter < n_symbols; symbolcounter++)
          {
            randomindex = ngenerator.nextInt(nfunctions);
            randomexpressionTree = new functionNode(functions[randomindex]);
            n_depthsymbols += functions[randomindex].getArity();  
            genetic_code.insertArgument(depthcounter,randomexpressionTree);
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
  {  return (o instanceof generateFULL);  }
  
public Object clone()
  {  return (new generateFULL());  }
}

/* -------------------------------------------------------------------------- */