/* -------------------------------------------------------------------------- */
/* Classe    : bestInd                                                        */
/* Descrição : critério baseado na fitness do melhor indivíduo.               */
/* -------------------------------------------------------------------------- */
package Aplication.Classes.EndRun;

import GPkit.Population.*;
import GPkit.EndRun.*;

public class bestInd extends EndRun 
{

  public boolean stopCriteria (Generation g)
    { boolean stop = false;
    
      if (g.find_best().getStandardFitness() < 0.01) stop = true;
      return (stop);  }

  public String toString()
    {  return("margem de erro para a standard fitness 0.01");  }

}

/* -------------------------------------------------------------------------- */