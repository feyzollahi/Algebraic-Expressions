package Expression;

public class DivideExpression extends Expression {
    private Expression firstExp;
    private Expression secondExp;
    public DivideExpression(Expression expression){
        firstExp = expression;
    }

    public void setDivideExpression(Expression exp){
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
        DivideExpression exp = (DivideExpression) firstExp.eval(var, val).divide(secondExp.eval(var, val));
        if(exp.isConstant()){
            return new Constant(((Constant)exp.getFirstExp()).eval() / ((Constant)exp.getSecondExp()).eval());
        }
        else{
            return exp;
        }
    }

    @Override
    public Expression derivate(Variable var) {
        Expression first = firstExp.derivate(var).multiply(secondExp);
        Expression second = firstExp.multiply(secondExp.derivate(var));
        Expression result = first.subtract(second).divide(second.exponent(new Constant(2)));
        return result;//(f/g)' = (f'*g - f*g')/g^2
    }

    @Override
    public boolean isConstant() {
        return firstExp.isConstant() && secondExp.isConstant();
    }

    @Override
    public Expression replaceVariable(Variable src, Variable dst) {
        return firstExp.replaceVariable(src, dst).divide(secondExp.replaceVariable(src, dst));
    }
}
