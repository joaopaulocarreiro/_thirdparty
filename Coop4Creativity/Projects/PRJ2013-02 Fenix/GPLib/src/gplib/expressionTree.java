/* -------------------------------------------------------------------------- */
/* Classe    : expressionTree                                                 */
/* Descrição : Esta classe representa um qualquer elemento dum expressão, ou  */
/*             seja qualquer elemento das expressões será sempre uma instançia*/
/*             duma subclasse de expressionTree.                              */
/* -------------------------------------------------------------------------- */
package GPkit.Expressions;

import GPkit.Misc.*;
import Aplication.Classes.Value.*;
import Aplication.Classes.Fitness.*;
import Aplication.Classes.Aritmetic.*;
import Aplication.Classes.FitnessCase.*;

public abstract class expressionTree implements Cloneable,
                                                Standard
{

/* ----------------------- métodos de instânçia ----------------------------- */

/** função que calcula o valor da árvore.	*/
abstract public Value evaluate();
abstract public Value evaluate(Object[] vars,Value[] v);
/** calcula o peso/altura/profundidade da subárvore deste nodo.	*/
abstract public int depth();
/** calcula o tamanho da subárvore deste nodo em nº de nodos.	*/
abstract public int size();
/** calcula o nº total de folhas de subárvore desse nodo.	*/
abstract public int leaves();
/** retorna a subárvore. */
abstract public expressionTree subTree(int n);
/** substitui uma subárvore por outra subárvore. */
abstract public void replaceTree(int n, expressionTree tree);
/** insere um elemento numa árvore.	*/
abstract public int insertArgument(int maxdepth, expressionTree a); 

/* ----------------------- métodos das interfaces --------------------------- */

public abstract String toString();
public abstract boolean equals(Object o);
public abstract Object clone();

}

/* -------------------------------------------------------------------------- */