/* -------------------------------------------------------------------------- */
/* Classe    : EndRun                                                         */
/* Descrição : Classe abstracta para a implementação dos critérios de paragem */
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