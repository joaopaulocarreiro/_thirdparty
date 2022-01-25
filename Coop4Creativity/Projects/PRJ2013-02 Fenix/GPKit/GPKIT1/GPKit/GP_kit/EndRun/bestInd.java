/* -------------------------------------------------------------------------- */
/* Classe    : bestInd                                                        */
/* Descrição : critério baseado na fitness do melhor indivíduo.               */
/* -------------------------------------------------------------------------- */
package EndRun;

import Misc.*;
import Population.*;
import GeneratorFunctions.*;
import Parameters.*;
import java.util.Random;

public class bestInd extends EndRun {

public boolean stopCriteria (Generation g)
  { boolean stop = false;
    
    if (g.find_best().getStandardFitness() < 0.01) stop = true;
    return (stop);  }

public String toString()
  {  return("margem de erro para a standard fitness 0.01");  }

public boolean equals(Object o)
  {  return(o instanceof bestInd);  }

public Object clone()
  {  return (new bestInd());  }

}

/* -------------------------------------------------------------------------- */