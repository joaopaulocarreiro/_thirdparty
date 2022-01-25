/* -------------------------------------------------------------------------- */
/* Classe    : EndRun                                                         */
/* Descrição : Classe abstracta para a implementação dos critérios de paragem */
/*             dos runs                                                       */
/* -------------------------------------------------------------------------- */
package EndRun;

import Misc.*;
import Population.*;
import GeneratorFunctions.*;
import Parameters.*;
import java.util.Random;

public abstract class EndRun implements Cloneable,
                                        Standard {

public abstract boolean stopCriteria (Generation g);

public abstract String toString();
public abstract boolean equals(Object o);
public abstract Object clone();


}

/* -------------------------------------------------------------------------- */