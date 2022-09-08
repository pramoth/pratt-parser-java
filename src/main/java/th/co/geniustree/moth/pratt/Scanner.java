package th.co.geniustree.moth.pratt;

import java.util.ArrayList;
import java.util.List;

import static th.co.geniustree.moth.pratt.TokenType.*;

public class Scanner {
    private List<Token> tokens = new ArrayList<>();
    private int position = 0;
    private int line = 1;
    private char[] src;

    private char peek() {
        return src[position];
    }

    private char consume() {
        return src[position++];
    }

    private boolean isEnd() {
        return position == src.length;
    }

    public List<Token> scan(String _src) {
        this.src = _src.toCharArray();
        while (!isEnd()) {
            char chr = peek();
            switch (chr) {
                case '(':
                    tokens.add(new Token(LEFT_PARENT, line, null));
                    consume();
                    break;
                case ')':
                    tokens.add(new Token(RIGHT_PARENT, line, null));
                    consume();
                    break;
                case '*':
                    tokens.add(new Token(STAR, line, null));
                    consume();
                    break;
                case '/':
                    tokens.add(new Token(SLASH, line, null));
                    consume();
                    break;
                case '%':
                    tokens.add(new Token(MOD, line, null));
                    consume();
                    break;
                case '+':
                    tokens.add(new Token(PLUS, line, null));
                    consume();
                    break;
                case '-':
                    tokens.add(new Token(MINUS, line, null));
                    consume();
                    break;
                default: {
                    if (Character.isDigit(chr)) {
                        int start = position;
                        int size = 1;
                        consume();
                        while (!isEnd() && Character.isDigit(peek())) {
                            consume();
                            size++;
                        }
                        if (!isEnd() && peek() == '.') {
                            consume();
                            size++;
                            while (!isEnd() && Character.isDigit(peek())) {
                                consume();
                                size++;
                            }
                        }
                        tokens.add(new Token(NUMBER, line, new String(src, start, size)));
                    } else if (Character.isWhitespace(chr)) {
                        if (chr == '\n') {
                            line++;
                        }
                        consume();
                    }else{
                        throw new RuntimeException("not support "+peek());
                    }
                }
            }
        }
        return tokens;
    }
}
