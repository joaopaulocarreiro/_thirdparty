/**
 * GPLib
 * Terminal
 */
package gplib;

import Aplication.Classes.Value.*;
import Aplication.Classes.Fitness.*;
import Aplication.Classes.Aritmetic.*;
import Aplication.Classes.FitnessCase.*;

public class Constant extends Terminal {

    /* ----------------------------- constructores ------------------------------ */
    public Constant(Value v) {
        this.setSymbolName(v.toString());
        this.setValue(v);
    }

    /* ----------------------- m�todos de inst�n�ia ----------------------------- */
    public Terminal InsertSpecificTerminal() {
        return null;
    }

    /* ----------------------- m�todos das interfaces --------------------------- */
    public String toString() {
        return (this.getSymbolName());
    }

    public boolean equals(Object o) {
        boolean equal = false;
        Constant var_aux;

        if (o instanceof Constant) {
            var_aux = (Constant) o;
            equal = this.getSymbolName().equals(var_aux.getSymbolName());
        }
        return (equal);
    }

    public Object clone() {
        return (new Constant(this.getValue()));
    }
}

/* -------------------------------------------------------------------------- */