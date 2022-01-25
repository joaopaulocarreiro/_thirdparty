/* -------------------------------------------------------------------------- */
/* Classe    : expressionTree                                                 */
/* Descri��o : Esta classe representa um qualquer elemento dum express�o, ou  */
/*             seja qualquer elemento das express�es ser� sempre uma instan�ia*/
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

/* ----------------------- m�todos de inst�n�ia ----------------------------- */

/** fun��o que calcula o valor da �rvore.	*/
abstract public Value evaluate();
abstract public Value evaluate(Object[] vars,Value[] v);
/** calcula o peso/altura/profundidade da sub�rvore deste nodo.	*/
abstract public int depth();
/** calcula o tamanho da sub�rvore deste nodo em n� de nodos.	*/
abstract public int size();
/** calcula o n� total de folhas de sub�rvore desse nodo.	*/
abstract public int leaves();
/** retorna a sub�rvore. */
abstract public expressionTree subTree(int n);
/** substitui uma sub�rvore por outra sub�rvore. */
abstract public void replaceTree(int n, expressionTree tree);
/** insere um elemento numa �rvore.	*/
abstract public int insertArgument(int maxdepth, expressionTree a); 

/* ----------------------- m�todos das interfaces --------------------------- */

public abstract String toString();
public abstract boolean equals(Object o);
public abstract Object clone();

}

/* -------------------------------------------------------------------------- */