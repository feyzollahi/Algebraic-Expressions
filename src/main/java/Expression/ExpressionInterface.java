package Expression;

public interface ExpressionInterface {

    Expression add(Expression expressionInterface);
    Expression subtract(Expression expressionInterface);
    Expression exponent(Expression expressionInterface);
    Expression divide(Expression expressionInterface);
    Expression multiply(Expression expressionInterface);
    Expression sin();
    Expression cos();
    Expression makeNegative();
    boolean isConstant();
    Expression replaceVariable(Variable src, Variable dst);
    Expression derivate(Variable var);
    Expression ln();
    Expression eval(Variable var, double val);
    @Override
    String toString();
}
