package gplib;

public abstract class Function extends Symbol {

    /* function arity. */
    private int arity = 0;

    public int getArity() {
        return (this.arity);
    }

    public void setArity(int a) {
        this.arity = a;
    }
}

/* -------------------------------------------------------------------------- */