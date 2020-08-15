package Expression;

public class SinExpression extends Expression {
    Expression inside;

    public SinExpression(Expression inside) {
        this.inside = inside;
    }

    @Override
    public Expression eval(Variable var, double val) {
        SinExpression exp = new SinExpression(inside.eval(var, val));
        if(exp.isConstant()){
            return new Constant(Math.sin(((Constant)exp.inside).eval()));
        }
        else{
            return exp;
        }
    }

    @Override
    public Expression derivate(Variable var) {
        Expression exp = inside.derivate(var).multiply(new CosExpression(inside));
        return exp;
    }

    @Override
    public boolean isConstant() {
        return inside.isConstant();
    }

    @Override
    public String toString() {
        return "Sin( " + inside.toString() + " )";
    }

    @Override
    public Expression replaceVariable(Variable src, Variable dst) {
        return new SinExpression(inside.replaceVariable(src, dst));
    }
}
