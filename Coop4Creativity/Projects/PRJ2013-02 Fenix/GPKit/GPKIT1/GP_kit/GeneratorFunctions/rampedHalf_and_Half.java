/* -------------------------------------------------------------------------- */
/* Classe    : rampedHalf_and_Half                                            */
/* Descri��o : m�todo Ramped Half-and-Half para gera��o aleatoria de indiv�-  */
/*             -duos. Este m�todo � intencionado para melhorar a diversidade  */
/*             duma popula��o gerada.                                         */
/*                                                                            */
/* M�todo                                                                     */
/* ------                                                                     */
/*  Supondo que temos uma depthRamp de [a-b], entao a dimensao da popula��o   */
/* � dividida igualmente entre individuos a serem inicializados com �rvores   */
/* de profundidade a,a+1.a+2,...,b. Para cada grupo de profundidade, metade   */
/* das �rvores s�o inicializadas com o m�todo full e a outra metade com o m�- */
/* -to grow.                                                                  */
/*                                                                            */
/* -------------------------------------------------------------------------- */
package GeneratorFunctions;

import Expressions.*;
import Expressions.Elements.*;
import Parameters.*;
import Population.*;
import Misc.*;
import Specific.*;
import java.util.Random;


public class rampedHalf_and_Half extends generatePopulation
                                                     implements Cloneable
{

/* ------------------------- constantes de classe --------------------------- */

  private static final depthRamp DEFAULT_depthRamp = new depthRamp(2,6);
  private static final int DEFAULT_random_attemps = 100;  

/* ----------------------------- constructores ------------------------------ */

public rampedHalf_and_Half(depthRamp idr, int ra)
  {  this.setDepthRamp(idr);
     this.setRandomAttemps(ra);
     this.setName("ramped_Half_&_Half");  }

public rampedHalf_and_Half()
  {  this(DEFAULT_depthRamp,DEFAULT_random_attemps);  }

/* ----------------------- m�todos de inst�n�ia ----------------------------- */

public Population generate(int pop_size,
                           Function[] fset, 
                           Terminal[] tset) 
                           throws RandomAttempsExpiredException
  {  
    Population generated_population = new Population(pop_size);  
    generateGROW Grow = new generateGROW();
    generateFULL Full = new generateFULL();    
    int start_depth = this.getDepthRamp().getInferiorLimit();
    int[] depth_sequence = this.getDepthRamp().computeDepthSequence(pop_size);
    int num_depths = this.getDepthRamp().computeNumberDepths();
   
      
    for (int i=0; i<num_depths; i++)
      {
          // calcula respectivamente o numero de individuos a gerar pelos      
          // m�todos grow e full.
          int n_grow;
          int n_full;

          if (start_depth % 2 == 0)
            { n_grow = depth_sequence[i] / 2;
              n_full = depth_sequence[i] - n_grow;  }
          else
            { n_full = depth_sequence[i] / 2;
              n_grow = depth_sequence[i] - n_full;  }
            
                    
          // gerar e inserir os individuos gerados com grow.
          for (int j=0; j<n_grow; j++)
            { 
              boolean endwhile = false;
              int timestotry = this.getRandomAttemps();
              Individual ind;
              
              // s� insere um individuo que n�o esteja j� na popula��o
              while ( (!endwhile) && (timestotry > 0) )
                { ind = Grow.generate(fset,tset,start_depth,this.generator);
                  try
                    {  generated_population.addIndividual(ind);
                       endwhile = true;  }
                  catch (PopulationRepeatedIndividualException e1)
                    {  timestotry--;  }
                }    
              // se ap�s timestotry tentativas n�o for possivel inserir o
              // individuo, inserimos um qualquer, mesmo que seja igual,
              // fazemos isso porque sen�o o processo poderia durar para
              // sempre.
              if (timestotry == 0)    
                {  ind = Grow.generate(fset,tset,start_depth,this.generator);
                   generated_population.addInconditionalIndividual(ind);  }
            }

          // gerar e inserir os individuos gerados com full.
          for (int j=0; j<n_full; j++)
            {
              boolean endwhile = false;
              int timestotry = this.getRandomAttemps();
              Individual ind;
         
              // s� insere um individuo que n�o esteja j� na popula��o
              while ((!endwhile) && (timestotry > 0))
                { ind = Full.generate(fset,tset,start_depth,this.generator);
                  try
                    {  generated_population.addIndividual(ind);
                       endwhile = true;  }
                  catch (PopulationRepeatedIndividualException e2)
                    {  timestotry--;  }
                }    
              // se ap�s timestotry tentativas n�o for possivel inserir o
              // individuo, inserimos um qualquer, mesmo que seja igual,
              // fazemos isso porque sen�o o processo poderia durar para
              // sempre.    
              if (timestotry == 0)    
                {  ind = Grow.generate(fset,tset,start_depth,this.generator);
                   generated_population.addInconditionalIndividual(ind);  }
            }
            
          start_depth++;    
      }
    
    return (generated_population);
  }

/* ----------------------- m�todos das interfaces --------------------------- */

public String toString()
  {  return(this.getName());  }

public boolean equals(Object o)
  {  return (o instanceof rampedHalf_and_Half);  }
  
public Object clone()
  {  return (new rampedHalf_and_Half(this.getDepthRamp(),
                                     this.getRandomAttemps()));  }
}

/* -------------------------------------------------------------------------- */