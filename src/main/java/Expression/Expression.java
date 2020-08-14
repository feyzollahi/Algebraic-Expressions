package Expression;

public abstract class Expression implements ExpressionInterface {

    public Expression add(Expression expression) {
        AddExpression expressionAdd = new AddExpression(this);
        expressionAdd.setAddExpression(expression);
        return expressionAdd;
    }

    public Expression subtract(Expression expression) {
        Expression expression1 = expression.makeNegative();
        return this.add(expression1);
    }

    public Expression exponent(Expression expression) {
        ExponentExpression exponentExpression = new ExponentExpression(this);
        exponentExpression.setExponentExpression(expression);
        return exponentExpression;
    }

    public Expression divide(Expression expression) {
        DivideExpression divideExpression = new DivideExpression(this);
        divideExpression.setDivideExpression(expression);
        return divideExpression;
    }

    public Expression multiply(Expression expression) {
        MultiplyExpression multiplyExpression = new MultiplyExpression(this);
        multiplyExpression.setMultiplyExpression(expression);
        return multiplyExpression;
    }

    public Expression sin() {
        return null;
    }

    public Expression cos() {
        return null;
    }

    public Expression ln() {
        return new LnExpression(this);
    }

    public abstract Expression eval(Variable var, double val);
    public abstract Expression derivate(Variable var);
    public abstract boolean isConstant();

    public Expression makeNegative(){
        Constant constant = new Constant(-1);
        MultiplyExpression multiplyExpression = new MultiplyExpression(constant);
        multiplyExpression.setMultiplyExpression(this);
        return multiplyExpression;
    }

    public abstract Expression replaceVariable(Variable src, Variable dst);
}
