package function;


import Expression.Expression;
import Expression.Variable;

public class Function {
    Expression expression;
    Variable x = new Variable("x");
    public Function(Expression expression){
        this.expression = expression;
    }
    public Expression apply(Variable var){
        return expression.replaceVariable(x, var);
    }
}
