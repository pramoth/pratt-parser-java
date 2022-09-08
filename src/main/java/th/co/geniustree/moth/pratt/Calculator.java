package th.co.geniustree.moth.pratt;

public class Calculator implements Visitor<Double> {
    @Override
    public Double visitMinus(Expression.Minus exp) {
        return eval(exp.left()) - eval(exp.right());
    }

    private Double eval(Expression exp) {
        return (Double)exp.accept(this);
    }

    @Override
    public Double visitMull(Expression.Mul exp) {
        return eval(exp.left()) * eval(exp.right());
    }

    @Override
    public Double visitNegative(Expression.Negative exp) {
        return -eval(exp.right());
    }

    @Override
    public Double visitNumber(Expression.Number exp) {
        return Double.parseDouble(exp.value().value());
    }

    @Override
    public Double visitDivide(Expression.Divide exp) {
        return eval(exp.left()) / eval(exp.right());
    }

    @Override
    public Double visitAdd(Expression.Add exp) {
        return eval(exp.left()) + eval(exp.right());
    }

    @Override
    public Double visitMod(Expression.Mod exp) {
        return eval(exp.left()) % eval(exp.right());
    }
}
