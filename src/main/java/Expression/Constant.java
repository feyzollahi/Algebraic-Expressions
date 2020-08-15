package Expression;

public class Constant extends Expression {
    double number;
    public Constant(double number){
        this.number = number;
    }
    @Override
    public Expression derivate(Variable var) {
        return new Constant(0);
    }

    @Override
    public boolean isConstant() {
        return true;
    }

    @Override
    public Expression replaceVariable(Variable src, Variable dst) {
        return this;
    }

    @Override
    public Expression eval(Variable var, double val){
        return this;
    }

    public double eval(){
        return number;
    }

    @Override
    public String toString(){
        return String.valueOf(number);
    }
}
