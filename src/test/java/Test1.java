import Expression.AddExpression;
import Expression.Constant;
import Expression.Expression;
import Expression.Variable;
import org.junit.Test;

public class Test1 {
    @Test
    public void simpleAdd(){
        Variable var = new Variable("x");
        AddExpression addExpression = new AddExpression(var);
        addExpression.setAddExpression(new Constant(2));
        Expression val = addExpression.eval(var, 4);
        System.out.println("1");
    }

    @Test
    public void exponentTest(){
        Variable var = new Variable("x");
        Expression exp = var.exponent(new Constant(3));
        exp = exp.derivate(var);
        System.out.println("1");
    }
}
