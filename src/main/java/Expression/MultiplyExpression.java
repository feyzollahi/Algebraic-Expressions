package Expression;

public class MultiplyExpression extends Expression {
    private Expression firstExp;
    private Expression secondExp;
    public MultiplyExpression(Expression expression){
        firstExp = expression;
    }

    public void setMultiplyExpression(Expression exp){
        secondExp = exp;
    }

    public Expression getFirstExp() {
        return firstExp;
    }

    public Expression getSecondExp() {
        return secondExp;
    }

    @Override
    public Expression eval(Variable var, double val) {
        MultiplyExpression exp = (MultiplyExpression) firstExp.eval(var, val).multiply(secondExp.eval(var, val));
        if(exp.isConstant()){
            return new Constant(((Constant)exp.getFirstExp()).eval() * ((Constant)exp.getSecondExp()).eval());
        }
        else{
            return exp;
        }
    }

    @Override
    public Expression derivate(Variable var) {
        Expression first = firstExp.derivate(var).multiply(secondExp);
        Expression second = firstExp.multiply(secondExp.derivate(var));
        Expression result = first.add(second);
        return result;//(f*g)' = f'*g + f*g'
    }

    @Override
    public boolean isConstant() {
        return firstExp.isConstant() && secondExp.isConstant();
    }

    @Override
    public Expression replaceVariable(Variable src, Variable dst) {
        return firstExp.replaceVariable(src, dst).multiply(secondExp.replaceVariable(src, dst));
    }
}
