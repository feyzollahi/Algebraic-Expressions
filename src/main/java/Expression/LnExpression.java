package Expression;

public class LnExpression extends Expression {
    Expression inside;
    public LnExpression(Expression expression){
        inside = expression;
    }
    @Override
    public Expression eval(Variable var, double val) {
        Expression expression = inside.eval(var, val);
        if(expression.isConstant()){
            return new Constant(Math.log(((Constant)expression).eval()));
        }
        else{
            return new LnExpression(expression);
        }
    }

    @Override
    public Expression derivate(Variable var) {
        return inside.derivate(var).divide(inside);
    }

    @Override
    public boolean isConstant() {
        return inside.isConstant();
    }

    @Override
    public Expression replaceVariable(Variable src, Variable dst) {
        return new LnExpression(inside.replaceVariable(src, dst));
    }

    @Override
    public String toString(){
        return "Ln( " + inside.toString() + " )";
    }
}
