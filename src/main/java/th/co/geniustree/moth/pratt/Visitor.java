package th.co.geniustree.moth.pratt;

public interface Visitor<R> {
    public R visitMinus(Expression.Minus minus);

    R visitMull(Expression.Mul mul);

    R visitNegative(Expression.Negative negative);

    R visitNumber(Expression.Number number);

    R visitDivide(Expression.Divide divide);

    R visitAdd(Expression.Add add);

    R visitMod(Expression.Mod mod);
}
