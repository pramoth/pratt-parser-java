package th.co.geniustree.moth.pratt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ScannerTest {
    @Test
    public void fail1() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            String src = " ( 1 - 2 + 4";
            Scanner scanner = new Scanner();
            List<Token> tokens = scanner.scan(src);
            Parser parser = new Parser(tokens);
            Expression x = parser.parse();
            System.out.println(x);
            ToStringVisitor printer = new ToStringVisitor();
            System.out.println(x.accept(printer));
            Calculator calculator = new Calculator();
            System.out.println(x.accept(calculator));
                }
        );


    }

    @Test
    public void test() {
        String src = " 25 + 3 * 5 - ( 1 - 2 ) % 3";
        Scanner scanner = new Scanner();
        List<Token> tokens = scanner.scan(src);
        System.out.println(tokens);
        Parser parser = new Parser(tokens);
        Expression x = parser.parse();
        System.out.println(x);
        ToStringVisitor printer = new ToStringVisitor();
        Object result = x.accept(printer);
        Assertions.assertEquals("((25 + (3 * 5)) - ((1 - 2) % 3))",result);
        System.out.println(result);
        Calculator calculator = new Calculator();
        System.out.println(x.accept(calculator));
    }

    @Test
    public void testParse1() {
        String src = " -5 - 4 * 3 - 2";
        Scanner scanner = new Scanner();
        List<Token> tokens = scanner.scan(src);
        Parser parser = new Parser(tokens);
        System.out.println(tokens);
        Expression x = parser.parse();
        System.out.println(x);
        ToStringVisitor printer = new ToStringVisitor();
        Object result = x.accept(printer);
        Assertions.assertEquals("(((-5) - (4 * 3)) - 2)",result);
        System.out.println(x.accept(printer));
        Calculator calculator = new Calculator();
        System.out.println(x.accept(calculator));
    }

    @Test
    public void testParse2() {
        String src = " -5 - 4 * (3 - 2)";
        Scanner scanner = new Scanner();
        List<Token> tokens = scanner.scan(src);
        Parser parser = new Parser(tokens);
        System.out.println(tokens);
        Expression x = parser.parse();
        System.out.println(x);
        ToStringVisitor printer = new ToStringVisitor();
        Object result = x.accept(printer);
        Assertions.assertEquals("((-5) - (4 * (3 - 2)))",result);
        System.out.println(x.accept(printer));
        Calculator calculator = new Calculator();
        System.out.println(x.accept(calculator));
    }

    @Test
    public void testParse3() {
        String src = " -5 - 4 - 3 - 2 / 2 * 3";
        Scanner scanner = new Scanner();
        List<Token> tokens = scanner.scan(src);
        Parser parser = new Parser(tokens);
        System.out.println(tokens);
        Expression x = parser.parse();
        System.out.println(x);
        ToStringVisitor printer = new ToStringVisitor();
        Object result = x.accept(printer);
        Assertions.assertEquals("((((-5) - 4) - 3) - ((2 / 2) * 3))",result);
        System.out.println(x.accept(printer));
        Calculator calculator = new Calculator();
        System.out.println(x.accept(calculator));
    }
}
