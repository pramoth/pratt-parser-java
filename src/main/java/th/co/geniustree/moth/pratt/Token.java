package th.co.geniustree.moth.pratt;

public record Token(TokenType type,int line,String value) {
    @Override
    public String toString() {
        return "Token{" + "type=" + type + ", value=" + value +'}';
    }
}
