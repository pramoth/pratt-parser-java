package th.co.geniustree.moth.pratt;

public class ToStringVisitor implements Visitor<String>{
    @Override
    public String visitMinus(Expression.Minus exp) {
        return "("+print(exp.left())+ " - "+print(exp.right())+")";
    }

    private String print(Expression left) {
        return (String)left.accept(this);
    }

    @Override
    public String visitMull(Expression.Mul exp) {
        return "("+print(exp.left())+ " * "+print(exp.right())+")";
    }

    @Override
    public String visitNegative(Expression.Negative negative) {
        return "(-"+print(negative.right())+")";
    }

    @Override
    public String visitNumber(Expression.Number number) {
        return number.value().value();
    }

    @Override
    public String visitDivide(Expression.Divide exp) {
        return "("+print(exp.left())+ " / "+print(exp.right())+")";
    }

    @Override
    public String visitAdd(Expression.Add exp) {
        return "("+print(exp.left())+ " + "+print(exp.right())+")";
    }

    @Override
    public String visitMod(Expression.Mod exp) {
        return "("+print(exp.left())+ " % "+print(exp.right())+")";
    }
}
