/* -------------------------------------------------------------------------- */
/* Classe    : generateFULL                                                   */
/* Descri��o : m�todo FULL para gera��o de individuos.                        */
/*                                                                            */
/* M�todo                                                                     */
/* ------                                                                     */
/*   Este m�todo produz �rvores completas, porque os nodos s�o escolhidos so- */
/* -mente do conjunto das fun��es, at� que um nodo esteja h� profundidade m�- */
/* -xima. Depois s� escolhe terminais para acabar de gerar a �rvore. O resul- */
/* -tado � que todos os ramos de �rvores chegam at� � profundidade m�xima.    */
/*   Claro que este m�todo respeita os limites impostos para a profundidade e */
/* numero de nodos das �rvores.                                               */
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
  
/* ----------------------- m�todos de inst�n�ia ----------------------------- */

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
  	 
    // Inicializar a estrutura da express�o.
    genetic_code = null;
    randomindex = ngenerator.nextInt(nfunctions);
    genetic_code = new functionNode(functions[randomindex]);
    n_symbols = functions[randomindex].getArity();

  	
    // se a profundidade for menor 1 que ent�o ser� 1.
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

      
  	// Escolher aleat�riamente os terminais e inserir-los.
  	for (symbolcounter = 0; symbolcounter < n_symbols; symbolcounter++)
  	{
  		randomindex = ngenerator.nextInt(nterminals);
        Terminal t_aux = terminals[randomindex];
        // Se este terminal tiver algum comportamento especifico antes
        // de ser introduzido na �rvore ent�o � executado.  
        if (t_aux.getSpecific())
              t_aux = t_aux.InsertSpecificTerminal();        
  		randomexpressionTree = new terminalNode(t_aux);
  		genetic_code.insertArgument(depth,randomexpressionTree);
  	}
  	
    return (new Individual(genetic_code));
    
  }

/* ----------------------- m�todos das interfaces --------------------------- */

public String toString()
  {  return(this.getGenerateFunctionName());  }

public boolean equals(Object o)
  {  return (o instanceof generateFULL);  }
  
public Object clone()
  {  return (new generateFULL());  }
}

/* -------------------------------------------------------------------------- */