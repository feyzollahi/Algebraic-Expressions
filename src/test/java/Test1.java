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
        System.out.println(addExpression);
    }

    @Test
    public void exponentTest(){
        Variable var = new Variable("x");
        Expression exp = var.exponent(new Constant(3));
        System.out.println(exp);
        exp = exp.derivate(var);
        System.out.println(exp);
    }

    @Test
    public void cosSimple(){
        Variable var = new Variable("x");
        Expression exp = var.multiply(new Constant(3)).cos().subtract(new Constant(10)).multiply(new Constant(2));
        Expression val = exp.eval(var, 0);
        exp = exp.derivate(var);
        Expression val2 = exp.eval(var, 0);
        System.out.println(exp.toString());
    }

    @Test
    public void sinSimple(){//(sin(x^2) + 5x^3)^2
        Variable x = new Variable("x");
        Expression exp = x.exponent(new Constant(2)).sin().add(x.exponent(new Constant(3)).multiply(new Constant(5))).exponent(new Constant(2));
        exp = exp.derivate(x);
        exp = exp.eval(x, 0);
        System.out.println("1");
    }
}
