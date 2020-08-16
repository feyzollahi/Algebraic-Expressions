package Expression;

public class AddExpression extends Expression {
    private Expression firstExp;
    private Expression secondExp;
    public AddExpression(Expression expression){
        firstExp = expression;
    }

    public void setAddExpression(Expression exp){
        secondExp = exp;
    }
    @Override
    public Expression derivate(Variable var){
        return firstExp.derivate(var).add(secondExp.derivate(var));//(f + g)' = f' + g'
    }

    @Override
    public boolean isConstant() {
        return firstExp.isConstant() && secondExp.isConstant();
    }

    @Override
    public Expression replaceVariable(Variable src, Variable dst) {
        return firstExp.replaceVariable(src, dst).add(secondExp.replaceVariable(src, dst));
    }

    public Expression getFirstExp() {
        return firstExp;
    }

    public Expression getSecondExp() {
        return secondExp;
    }

    @Override
    public Expression eval(Variable var, double val){
        AddExpression exp = (AddExpression) firstExp.eval(var, val).add(secondExp.eval(var, val));
        if(exp.isConstant()){
            return new Constant(((Constant)exp.getFirstExp()).eval() + ((Constant)exp.getSecondExp()).eval());
        }
        else{
            return exp;
        }
    }

    @Override
    public String toString(){
        return "(" + firstExp.toString() + ")" + " + " + "(" + secondExp.toString() + ")";
    }



}
