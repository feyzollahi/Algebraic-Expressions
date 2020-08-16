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

//    @Override
//    public Expression simplify() {
//        if(inside instanceof ExponentExpression){
//            Expression exponent = ((ExponentExpression) inside).getSecondExp();
//            MultiplyExpression multiplyExpression = new MultiplyExpression(exponent);
//            multiplyExpression.setMultiplyExpression(new LnExpression(((ExponentExpression) inside).getFirstExp()));
//            return multiplyExpression;
//        }
//        else{
//            return this;
//        }
//    }
}
