package gplib;

public class sub extends Function {

    public sub() {
        this.setSymbolName("-");
        this.setArity(2);
    }

    @Override
    public Value evaluate(Value[] args) {
        return (new Value(args[0].getValue() - args[1].getValue()));
    }

    @Override
    public String toString() {
        return (this.getSymbolName());
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof sub);
    }

    @Override
    public Object clone() {
        return (new sub());
    }
}

/* -------------------------------------------------------------------------- */