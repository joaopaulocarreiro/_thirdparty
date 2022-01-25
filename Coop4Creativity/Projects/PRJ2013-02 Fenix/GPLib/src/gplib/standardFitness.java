package gplib;

import GPkit.Misc.*;
import GPkit.Population.*;

public abstract class standardFitness implements Cloneable, Standard {

    /* nome do método de standard fitness */
    protected String name;

    public String getName() {
        return (this.name);
    }

    public void setName(String n) {
        this.name = n;
    }

    /**
     * Método     : computeStandardFitness
     * Descrição  : Este m�todo calcula a standard fitness de todos os individos da
     *              popula��o, isto � inverte/transforma a raw fitness de modo que
     *              todas as fitnesses s�o n�o negativas, com 0 sendo a melhor fit-
     *              -ness poss�vel.
     */
    public abstract void compute(Population p);

    public abstract String toString();

    public abstract boolean equals(Object o);

    public abstract Object clone();
}

/* -------------------------------------------------------------------------- */