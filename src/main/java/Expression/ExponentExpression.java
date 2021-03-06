package Expression;

public class ExponentExpression extends Expression{
    private Expression firstExp;
    private Expression secondExp;
    public ExponentExpression(Expression expression){
        firstExp = expression;
    }

    public void setExponentExpression(Expression exp){
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
        Expression first = firstExp.eval(var, val);
        Expression second = secondExp.eval(var, val);
        ExponentExpression result = (ExponentExpression)first.exponent(second);
        if(result.isConstant()){
            return new Constant(Math.pow(((Constant)result.getFirstExp()).eval(), ((Constant)result.getSecondExp()).eval()));
        }
        else{
            return result;
        }
    }

    @Override
    public Expression derivate(Variable var) {
        if(secondExp.isConstant()){
            Constant c = (Constant) secondExp.eval(var, 0);// for cast to Constant
            Expression exp = firstExp.exponent(new Constant(c.eval() - 1)).multiply(c).multiply(firstExp.derivate(var));
            return exp;
        }
        return this.multiply((secondExp.multiply(firstExp.ln())).derivate(var));
    }

    @Override
    public boolean isConstant() {
        return firstExp.isConstant() && secondExp.isConstant();
    }

    @Override
    public Expression replaceVariable(Variable src, Variable dst) {
        return firstExp.replaceVariable(src, dst).exponent(secondExp.replaceVariable(src, dst));
    }
    @Override
    public String toString(){
        return "( " + firstExp.toString() + " )" + "^" + "( " + secondExp.toString() + " )";
    }
}
