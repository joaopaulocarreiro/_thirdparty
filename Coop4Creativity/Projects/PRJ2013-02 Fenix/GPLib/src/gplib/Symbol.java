package gplib;

public abstract class Symbol implements Cloneable, Standard {

    /* name for symbol. */
    private String symbol_name;

    public String getSymbolName() {
        return (this.symbol_name);
    }

    public void setSymbolName(String n) {
        this.symbol_name = n;
    }

    public abstract Value evaluate(Value[] args);

    @Override
    public abstract String toString();

    @Override
    public abstract boolean equals(Object o);

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (this.symbol_name != null
                ? this.symbol_name.hashCode() : 0);
        return hash;
    }

    @Override
    public abstract Object clone();
}
