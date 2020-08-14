package Expression;

public class Variable extends Expression {
    String sign;

    public Variable(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Expression derivate(Variable var) {
        if(var.getSign().equals(this.getSign())){
            return new Constant(1);
        }
        else{
            return new Constant(0);
        }
    }

    @Override
    public boolean isConstant() {
        return false;
    }

    @Override
    public Expression replaceVariable(Variable src, Variable dst) {
        if(sign.equals(src.getSign())){
            return new Variable(dst.getSign());
        }
        return this;
    }


    public Expression eval(Variable var, double val) {
        if(var.getSign().equals(this.getSign())){
            return new Constant(val);
        }
        else{
            return this;
        }
    }
}
