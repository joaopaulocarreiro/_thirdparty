/* -------------------------------------------------------------------------- */
/* Classe    : Tournament                                                     */
/* Descrição : Classe que implementa o método de selecção Tournament.         */
/* -------------------------------------------------------------------------- */
package Selection;

import Misc.*;
import Population.*;
import java.util.Random;
import java.util.Vector;

public class Tournament extends selectMethod 
{
  
/* ------------------------- constantes de classe --------------------------- */

  static final int DEFAULT_tournament_size = 2;  
  
/* ----------------------------- constructores ------------------------------ */

public Tournament (int s)
  { this.setName("tournament");
    this.setSize(s);  }
    
public Tournament()
  {  this(DEFAULT_tournament_size);  }

/* ------------------------- variáveis de instânçia ------------------------- */

  /* dimensão do torneio */
  private int size;
  
/* ----------------------- métodos selectores (get/set) --------------------- */
  
public int getSize()
  {  return (this.size);  }

private void setSize(int s)
  { if (s > 0) this.size = s;
     else this.size = DEFAULT_tournament_size;  }

/* ----------------------- métodos de instânçia ----------------------------- */

public Individual doSelection(Generation g)
  { Population p = (Population) g.getPopulation().clone();
    Individual bestfit;
    int tournament_size = this.size;

    // verificar se o tamanho to torneio é maior ou menor do que a população
    if (this.size > p.getPopSize()) tournament_size = p.getPopSize();    
  
    Vector allIndividuals = p.getAllIndividuals();
    
    // escolher aleatóriamente os competidores
    Individual[] competitors = new Individual[tournament_size];
  
   
    // aqui é usado um vector auxiliar para que durante a selecção dos
    // indivíduos não sejam seleccionados individuos repetidos.
    for (int i=0; i < tournament_size; i++)    
      { int random_index = this.generator.nextInt(allIndividuals.size());
        competitors[i] = (Individual) allIndividuals.get(random_index);  
        allIndividuals.remove(random_index);  }
   
    // escolher dentre os competidores o melhor
    bestfit = competitors[0];
     
    for (int i=1; i < tournament_size; i++)
    {  if (competitors[i].getStandardFitness() < bestfit.getStandardFitness())
          bestfit = competitors[i];  }    
            
    return ((Individual) bestfit.clone());
  }

/* ----------------------- métodos das interfaces --------------------------- */
public String toString()
  { return ("(" + this.getName() + "," + "size=" + this.getSize() +")");  }  

public boolean equals(Object o)
  { boolean equal = false;
    Tournament tour_aux;
    
    if (o instanceof Tournament)
      { tour_aux = (Tournament) o;
        equal = (this.size == tour_aux.getSize());  }
    return (equal);
  }
    
public Object clone()
  {  return (new Tournament(this.size));  }
  
}

/* -------------------------------------------------------------------------- */