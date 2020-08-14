package Expression;

public class SinExpression extends Expression {
    @Override
    public Expression eval(Variable var, double val) {
        return null;
    }

    @Override
    public Expression derivate(Variable var) {
        return null;
    }

    @Override
    public boolean isConstant() {
        return false;
    }

    @Override
    public Expression replaceVariable(Variable src, Variable dst) {
        return null;
    }
}
