/* -------------------------------------------------------------------------- */
/* Classe    : EndRun                                                         */
/* Descri��o : Classe abstracta para a implementa��o dos crit�rios de paragem */
/*             dos runs                                                       */
/* -------------------------------------------------------------------------- */
package GPkit.EndRun;

import GPkit.Misc.*;
import GPkit.Population.*;

public abstract class EndRun 
{
  public abstract boolean stopCriteria (Generation g);
  public abstract String toString();
}

/* -------------------------------------------------------------------------- */