package gplib;

public abstract class Terminal extends Symbol {

    /* data for terminal node.  */
    private Value value = null;
    
    /* does this terminal insert specific information in a genetic code tree. */
    private boolean specific = false;

    public Value getValue() {
        return (this.value);
    }

    public void setValue(Value o) {
        this.value = (Value) o.clone();
    }

    public boolean getSpecific() {
        return (this.specific);
    }

    public void setSpecific(boolean s) {
        this.specific = s;
    }

    @Override
    public Value evaluate(Value[] args) {
        return (this.getValue());
    }

    public abstract Terminal InsertSpecificTerminal();
}