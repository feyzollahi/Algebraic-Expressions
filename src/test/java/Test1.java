import Expression.AddExpression;
import Expression.Constant;
import Expression.SinExpression;
import Expression.CosExpression;
import static org.junit.Assert.*;
import Expression.LnExpression;

import Expression.Expression;
import Expression.Variable;
import function.Function;
import org.junit.Test;

public class Test1 {
    @Test
    public void simpleAdd(){// x + 2 ==> x=4 ==> 4 + 2 = 6
        Variable var = new Variable("x");
        AddExpression addExpression = new AddExpression(var);
        addExpression.setAddExpression(new Constant(2));
        Expression val = addExpression.eval(var, 4);
        assertEquals(6.0, ((Constant)val).eval(), 0.01);
    }

    @Test
    public void exponentTest(){// x^3 ==> derivate ==> 3*x^2 ==> x = 5 ==> 75
        Variable var = new Variable("x");
        Expression exp = var.exponent(new Constant(3));
        exp = exp.derivate(var);
        Expression val = exp.eval(var, 5);
        assertEquals(75.0, ((Constant)val).eval(), 0.01);
    }

    @Test
    public void cosSimple(){//2*(cos(3x) - 10) ==> derivate ==> -6*sin(3x) ==> x = PI/6 ==> -6
        Variable var = new Variable("x");
        Expression exp = var.multiply(new Constant(3)).cos().subtract(new Constant(10)).multiply(new Constant(2));
        Expression val = exp.eval(var, 0);
        exp = exp.derivate(var);
        Expression val2 = exp.eval(var, Math.PI/6);
        assertEquals(-6, ((Constant)val2).eval(), 0.001);
        assertEquals(-18, ((Constant)val).eval(), 0.001);
    }

    @Test
    public void sinSimple(){//(sin(x^2) + 5x^3)^2 ==> derivate ==> 2*(sin(x^2) + 5x^3)*(2xcos(x^2) + 15x^2) == > x =1 ==> 187.868770515
        Variable x = new Variable("x");
        Expression exp = x.exponent(new Constant(2)).sin().add(x.exponent(new Constant(3)).multiply(new Constant(5))).exponent(new Constant(2));
        exp = exp.derivate(x);
        exp = exp.eval(x, 1);
        assertEquals(187.868770515, ((Constant)exp).eval(), 0.001);

    }

    @Test
    public void functionTest(){
        Variable x = new Variable("x");
        Expression exp = x.exponent(new Constant(3));
        Function f = new Function(exp);
        Variable y = new Variable("y");
        Expression exp2 = f.apply(y);
        Expression exp3 = exp2.divide(y);//y^3 / y = y^2
        Expression val = exp3.eval(y, 3);//3^2 = 9
        Expression val2 = exp2.derivate(y).eval(y, 4);
        assertEquals(9.0, ((Constant)val).eval(), 0.001);
        assertEquals(48.0, ((Constant)val2).eval(), 0.001);
    }

    @Test
    public void tanDerivate(){//(tan(x))' = 1 + tan(x)^2 ==> x= PI/3 ==> 4
        Variable x = new Variable("x");
        Expression exp = (new SinExpression(x)).divide(new CosExpression(x));
        Expression exp2 = exp.derivate(x);//1 + tan(x)^2
        Expression exp3 = exp2.eval(x, Math.PI/3);//4
        assertEquals(4.0, ((Constant)exp3).eval(), 0.001);
    }

    @Test
    public void expDerivate(){//e^x ==> derivate ==> e^x ==> x = 10 ==> e^x = (e^x)' ==> They are equals in all x for example x = 20
        Variable x = new Variable("x");
        Expression exp = (new Constant(Math.E)).exponent(x);//e^x
        Expression exp2 = exp.derivate(x);
        Expression val = exp.eval(x, 20);
        Expression val2 = exp2.eval(x, 20);
        assertEquals(((Constant)val).eval(), ((Constant)val2).eval(), 0.001);
    }

    @Test
    public void multiVariableExpression(){//(y*cos(x))^z ==> x = 0, y = 10, z = 3 ==> 1000 && (y*cos(x))^z ==> derivate z ==> (y*cos(x))^z . Ln(y*cos(x))) ==> x = 0, y = 2, z = 5 ==> ln(2) * 32
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Variable z = new Variable("z");
        Expression exp = x.cos().multiply(y).exponent(z);//(y*cos(x))^z
        Expression val = exp.eval(x, 0).eval(y, 10).eval(z, 3);
        assertEquals(1000.0, ((Constant)val).eval(), 0.001);
        Expression val2 = exp.derivate(z).eval(x, 0).eval(y, 2).eval(z, 5);
        assertEquals(32*Math.log(2.0), ((Constant)val2).eval(), 0.001);
    }

    @Test
    public void lnTest(){//ln(x) ==> derivate ==> 1/x ==> x = 2 ==> 0.5
        Variable x = new Variable("x");
        Expression exp = new LnExpression(x);
        Expression val = exp.derivate(x).eval(x, 2);
        assertEquals(0.5, ((Constant)val).eval(), 0.001);
    }
}

