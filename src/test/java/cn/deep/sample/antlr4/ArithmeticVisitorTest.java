package cn.deep.sample.antlr4;

import cn.deep.antlr4.parser.ArithmeticLexer;
import cn.deep.antlr4.parser.ArithmeticParser;
import cn.deep.sample.antlr4.ArithmeticVisitor;
import cn.deep.sample.antlr4.Node;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;


class ArithmeticVisitorTest {
    @ParameterizedTest
    @CsvSource({
            "1+2,           3",
            "2*3,           6",
            "1+2*(3-4),     -1",
            "a+b,           ab",
            "1+a,           1a",
            "a+(b+3),       ab3",
    })
    void cal(String calExpr, String expect) {
        ArithmeticParser.ProgContext tree = buildExprContext(calExpr);
        ArithmeticVisitor visitor = new ArithmeticVisitor();

        Node<?> actualParse = visitor.visit(tree);

        Assertions.assertAll(() -> {
            Assertions.assertEquals(expect, actualParse.getValue().toString());
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"a-b", "a*b", "2*a", "1+2*a"})
    void cal_expectUnsupportedOperationException(String errExpr) {
        ArithmeticParser.ProgContext tree = buildExprContext(errExpr);
        ArithmeticVisitor visitor = new ArithmeticVisitor();

        assertThrows(
                UnsupportedOperationException.class,
                () -> visitor.visit(tree));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1%2"})
    void cal_expectIllegalStateException(String errExpr) {
        ArithmeticParser.ProgContext tree = buildExprContext(errExpr);
        ArithmeticVisitor visitor = new ArithmeticVisitor();
        assertThrows(
                IllegalStateException.class,
                () -> visitor.visit(tree));
    }

    ArithmeticParser.ProgContext buildExprContext(String expr) {
        CharStream input = CharStreams.fromString(expr);
        ArithmeticLexer lexer = new ArithmeticLexer(input);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        ArithmeticParser parser = new ArithmeticParser(tokenStream);
        return parser.prog();
    }
}