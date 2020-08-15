package Expression;

public class CosExpression extends Expression {
    Expression inside;

    public CosExpression(Expression inside) {
        this.inside = inside;
    }

    @Override
    public Expression eval(Variable var, double val) {
        CosExpression exp = new CosExpression(inside.eval(var, val));
        if(exp.isConstant()){
            return new Constant(Math.cos(((Constant)exp.inside).eval()));
        }
        else{
            return exp;
        }
    }

    @Override
    public Expression derivate(Variable var) {
        Expression exp = inside.derivate(var).multiply(new SinExpression(inside)).makeNegative();
        return exp;
    }

    @Override
    public boolean isConstant() {
        return inside.isConstant();
    }

    @Override
    public Expression replaceVariable(Variable src, Variable dst) {
        return new CosExpression(inside.replaceVariable(src, dst));
    }
}
