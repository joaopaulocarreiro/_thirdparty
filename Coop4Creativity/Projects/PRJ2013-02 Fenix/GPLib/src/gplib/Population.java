package gplib;

import java.util.*;

public class Population implements Cloneable, Standard {

    /* ----------------------------- constructores ------------------------------ */
    public Population(int ps) {
        this.population = new Vector(ps);
    }

    /* ------------------------- vari�veis de inst�n�ia ------------------------- */

    /* conjunto de individuos, a pr�pria popula��o. */
    private Vector population;

    /* ----------------------- m�todos selectores (get/set) --------------------- */
    public int getPopSize() {
        return (this.population.size());
    }

    public Individual getIndividual(int index)
            throws ArrayIndexOutOfBoundsException {
        return ((Individual) this.population.get(index));
    }

    public Vector getAllIndividuals() {
        return this.population;
    }

    /* ----------------------- m�todos de inst�n�ia ----------------------------- */
    public void addIndividual(Individual ind) throws
            PopulationRepeatedIndividualException {
        if (!this.existIndividual(ind)) {
            this.addInconditionalIndividual(ind);
        } else {
            throw new PopulationRepeatedIndividualException();
        }
    }

    public void addIndividual(Individual[] ind, int q) throws
            PopulationRepeatedIndividualException {
        if (q <= ind.length) {
            for (int i = 0; i < q; i++) {
                this.addIndividual(ind[i]);
            }
        }
    }

    public void addInconditionalIndividual(Individual ind) {
        this.population.addElement(ind);
    }

    public boolean existIndividual(Individual ind) {
        return (this.population.contains(ind));
    }

    public Enumeration elements() {
        return (this.population.elements());
    }

    public String toString() {
        String str_aux;

        str_aux = this.getPopSize() + " individuals : \n";
        for (Enumeration e = this.elements(); e.hasMoreElements();) {
            str_aux += ((Individual) e.nextElement()).toString() + "\n";
        }

        return (str_aux);
    }

    public boolean equals(Object o) {
        boolean equal = false;
        Population pop_aux;

        if (o instanceof Population) {
            pop_aux = (Population) o;
            if (this.getPopSize() == pop_aux.getPopSize()) {
                equal = true;
                for (int i = 0; i < this.getPopSize() && equal; i++) {
                    equal = this.getIndividual(i).equals(pop_aux.getIndividual(i));
                }
            }
        }
        return (equal);
    }

    public Object clone() {
        Population p = new Population(this.getPopSize());

        for (Enumeration e = this.elements(); e.hasMoreElements();) {
            p.addInconditionalIndividual((Individual) e.nextElement());
        }

        return (p);
    }
}

/* -------------------------------------------------------------------------- */
