package gplib;

public class Value {

    private double num;

    public Value() {
        this(0);
    }

    public Value(double v) {
        this.num = v;
    }

    public double getValue() {
        return (this.num);
    }

    public void setValue(double n) {
        this.num = n;
    }

    @Override
    public String toString() {
        return "" + getValue();
    }

    @Override
    public boolean equals(Object o) {
        boolean equal = false;
        Value val_aux;

        if (o instanceof Value) {
            val_aux = (Value) o;
            equal = getValue() == val_aux.getValue();
        }
        return (equal);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.num)
                ^ (Double.doubleToLongBits(this.num) >>> 32));
        return hash;
    }

    @Override
    public Object clone() {
        return (new Value(getValue()));
    }
}