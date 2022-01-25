/* -------------------------------------------------------------------------- */
/* Classe    : depthRamp                                                      */
/* Descri��o : Classe que implementa o conceito de intervalo de profundidades */
/*             para gera��o de �rvores de express�o.                          */
/* -------------------------------------------------------------------------- */
package Misc;

import java.util.Random;
import java.lang.Math;


public class depthRamp implements Cloneable,
                                  Standard
{

/* ----------------------------- constructores ------------------------------ */

public depthRamp()
  {  this.setInferiorLimit(-1);
     this.sup_limit = -1;
  }

public depthRamp(int infl, int supl)
  {
    this();
    if ( (infl >= 0) && (supl >= 0) )
    { 
      if (supl > infl) 
        {  this.setInferiorLimit(infl);  
           this.setSuperiorLimit(supl);  }  
      else
        {  this.setInferiorLimit(supl);
           this.setSuperiorLimit(infl);  }
    }
  }

public depthRamp(int lim)
  {  this.setSuperiorLimit(lim);
     this.setInferiorLimit(lim);  }

/* ------------------------- vari�veis de inst�n�ia ------------------------- */

  /* limite inferior */
  private int inf_limit;
  /* limite superior */
  private int sup_limit;	

/* ----------------------- m�todos selectores (get/set) --------------------- */

public int getInferiorLimit()
  {  return(this.inf_limit);  }

public int getSuperiorLimit()
  {  return(this.sup_limit);  }

public void setInferiorLimit(int il)
  {  this.inf_limit = il;  }

public void setSuperiorLimit(int sl)
  {  this.sup_limit = sl;  }

/* ----------------------- m�todos de inst�n�ia ----------------------------- */

/**
* M�todo     : computeDepthSequence                                             
* Descri��o  : Este m�todo calcula que profundidades vao ter as �rvores da     
*              popula�ao inicial. O m�todo � o seguinte :   
*              Se a popula��o especificada for maior ou igual � distancia      
*              entre as duas profundidades (inclusive) ent�o o tamanho da      
*              popula��o � dividido pelo numero de profundidades entre olimite 
*              inferior e o limite superior mais um, depois � verificado se    
*              temos mais ou menos elementos do que a dimens�o da popula��o, se
*              tivermos mais elementos � retirado ao acaso a diferen�a, se     
*              tivermos a menos � acrescentado o que falta, de modo que no fim 
*              a lista devolvida, se forem somados os elementos todos temos que
*              ter pop_size (tamanho da popula��o).
*                Se a popula��o especificada for menor do que o numero de      
*              profundidades requeridas entao s�o escolhidas pop_size pro-     
*              fundidades (possivelmente repetidas) e � lhes acrescentado      
*              um elemento, perfazendo no fim pop_size profundidades.
* Parametros : pop_size - tamanho da popula��o.
* Retorna    : Um array em que em cada index corresponde ao numero de �rvo-
*              -res com a profundidade indicada no limite inferior.
*                 ex : se os limites forem 2 e 7, e o tamanho da popula��o     
*                      100 entao o array retornado ser� 
*
*     [17,  17,  16,  17,  17, 16]  
*       |   |     |             |
*       |   |     |             \- n� de �rvores com a profundidade 7. 
*       |   |     |          ..
*       |   |     \- etc..
*       |   \- ser� o n� de �rvores a gerar com a profundidade 3.
*        \- ser� o n� de �rvores a gerar com a profundidade 2. 
*
* Nota -> Se a depthRamp tiver definida s� com uma profundidade,natural-   
*         -mente todos os individuos ser�o sequenciados com essa mesma    
*         profundidade.
*             por ex : limite 7 e tamanho da popula��o 100 
*                      o array retornado ser� [100] ,que significa dizer   
*                      que a profundidade de todas as �rvores est� ligada �
*                      profundidade 7. 
*/

public int[] computeDepthSequence(int pop_size)
  {
    int n;
    int num_depths;
    int dif;
    int[] depth_sequence;

    if ( (this.getInferiorLimit() < 0) || (this.getSuperiorLimit() < 0) )
      { depth_sequence = new int[1];
        depth_sequence[0] = 0;  }
    else
    if (this.getInferiorLimit() == this.getSuperiorLimit())
      { depth_sequence = new int[1];
        depth_sequence[0] = pop_size;  }
    else
      {
        num_depths = this.getSuperiorLimit() - this.getInferiorLimit() + 1;	
        depth_sequence = new int[num_depths];

        if (pop_size >= num_depths)
          {
            n = pop_size / num_depths;
            for (int i=0; i<num_depths; i++)
              depth_sequence[i] = n; 

            dif = n * num_depths - pop_size;

            if (dif > 0)
              {  Random generator = new Random();
                 for (int i=0; i<dif; i++)	
                   depth_sequence[generator.nextInt(num_depths)]--;  }

            if (dif < 0)
              {  dif = Math.abs(dif);
                 dif = Math.abs(dif);
                 Random generator = new Random();
                 for (int i=0; i<dif; i++)
                   depth_sequence[generator.nextInt(num_depths)]++;  }
          }
        else
          {
            for (int i=0; i<num_depths; i++)
            depth_sequence[i] = 0;

            Random generator = new Random();
            for (int i=0; i<pop_size; i++)
              depth_sequence[generator.nextInt(num_depths)]++;  }
      }
  return (depth_sequence); 
  }

public int computeNumberDepths()
  {  return(this.getSuperiorLimit() - this.getInferiorLimit() + 1);  }
  
/* ----------------------- m�todos das interfaces --------------------------- */

public String toString()
  {
    String str_aux;

    if (this.getSuperiorLimit() < 0) 
      {
        if (this.getInferiorLimit() < 0)
          str_aux = "[not defined]";
        else	
          str_aux = "[" + this.getInferiorLimit() + "]";  }
    else
      {  str_aux = "[" +
                   this.getInferiorLimit() +
                   "-" +
                   this.getSuperiorLimit() +
                   "]";  }
    return(str_aux);
  }

public boolean equals(Object o)
  { depthRamp param_aux;
    boolean equal = false;
    
    if (o instanceof depthRamp)
      { param_aux = (depthRamp) o;
        equal = (this.getInferiorLimit() == param_aux.getInferiorLimit() ) && 
                (this.getSuperiorLimit() == param_aux.getSuperiorLimit() );
      }
    return(equal);
  }

public Object clone()
  {  return (new depthRamp(this.getInferiorLimit(),
                          this.getSuperiorLimit()));  }                        

}

/* -------------------------------------------------------------------------- */