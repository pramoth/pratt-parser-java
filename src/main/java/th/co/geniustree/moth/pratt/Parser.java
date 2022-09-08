package th.co.geniustree.moth.pratt;

import java.util.List;

public class Parser {
    private List<Token> tokens;
    public int position = 0;
    int bps(TokenType type){
        switch (type){
            case NUMBER,RIGHT_PARENT : return 0;
            case PLUS,MINUS:return 20;
            case STAR,SLASH,MOD:return 40;
            case LEFT_PARENT:return 60;
            default:{
                throw new RuntimeException("not support");
            }
        }
    }

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    private Token peek() {
        return tokens.get(position);
    }

    private Token next() {
        return tokens.get(position++);
    }

    public boolean isEnd() {
        return tokens.size() == position;
    }

    public Expression parse() {
        return parse(0);
    }

    public Expression parse(Integer rightBp) {
        Token next = next();
        Expression left = nud(next);
        while (!isEnd() && bps(peek().type()) > rightBp) {
            left = led(left, next());
        }
        return left;
    }

    private Expression led(Expression left, Token token) {
        switch (token.type()) {
            case MINUS:
                return new Expression.Minus(left, token, parse(bps(token.type())));
            case PLUS:
                return new Expression.Add(left, token, parse(bps(token.type())));
            case STAR:
                return new Expression.Mul(left, token, parse(bps(token.type())));
            case SLASH:
                return new Expression.Divide(left, token, parse(bps(token.type())));
            case MOD:
                return new Expression.Mod(left, token, parse(bps(token.type())));
        }
        return left;
    }

    private Expression nud(Token token) {
        switch (token.type()) {
            case MINUS:
                return new Expression.Negative(token, parse(bps(token.type())));
            case NUMBER:
                return new Expression.Number(token);
            case LEFT_PARENT: {
                Expression exp = parse();
                expect(TokenType.RIGHT_PARENT);
                next();
                return exp;
            }
        }
        throw new RuntimeException();
    }

    private void expect(TokenType type) {
        if (isEnd() || peek().type() != type) {
            throw new RuntimeException("expect " + type.name());
        }
    }
}
