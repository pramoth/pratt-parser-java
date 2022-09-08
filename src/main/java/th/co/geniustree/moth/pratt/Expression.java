package th.co.geniustree.moth.pratt;

public sealed interface Expression {
    <R> R accept(Visitor<R> visitor);
    public record Minus(Expression left,Token operator,Expression right) implements Expression{
        @Override
        public <R> R accept(Visitor<R> visitor) {
            return visitor.visitMinus(this);
        }
    }
    public record Mul(Expression left,Token operator,Expression right) implements Expression{
        @Override
        public <R> R accept(Visitor<R> visitor) {
            return visitor.visitMull(this);
        }
    }
    public record Negative(Token operator,Expression right) implements Expression{
        @Override
        public <R> R accept(Visitor<R> visitor) {
            return visitor.visitNegative(this);
        }
    }
    public record Number(Token value) implements Expression{
        @Override
        public <R> R accept(Visitor<R> visitor) {
            return visitor.visitNumber(this);
        }

    }

    public record Divide(Expression left, Token token, Expression right) implements Expression {
        @Override
        public <R> R accept(Visitor<R> visitor) {
            return visitor.visitDivide(this);
        }
    }

    public record Add(Expression left, Token token, Expression right) implements Expression {
        @Override
        public <R> R accept(Visitor<R> visitor) {
            return visitor.visitAdd(this);
        }
    }

    public record Mod(Expression left, Token token, Expression right) implements Expression {
        @Override
        public <R> R accept(Visitor<R> visitor) {
            return visitor.visitMod(this);
        }
    }
}
