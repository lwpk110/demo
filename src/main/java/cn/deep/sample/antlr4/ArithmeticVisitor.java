package cn.deep.sample.antlr4;

import cn.deep.antlr4.parser.ArithmeticBaseVisitor;
import cn.deep.antlr4.parser.ArithmeticParser;
import cn.deep.sample.util.NumberUtils;


public class ArithmeticVisitor extends ArithmeticBaseVisitor<Node<?>> {

    @Override
    public Node<?> visitCalEle(ArithmeticParser.CalEleContext ctx) {
        if (ctx.str_expr().NUMBER() != null) {
            return new Node<>(NumberUtils.valueOf(ctx.getText()));
        }
        return new Node<>(ctx.getText());
    }

    @Override
    public Node<?> visitMulDiv(ArithmeticParser.MulDivContext ctx) {
        Node<?> left = visit(ctx.expr(0));
        Node<?> right = visit(ctx.expr(1));
        int type = ctx.op.getType();
        System.out.printf("multiple and division section： left number is %s, right number is %s, operate type is %s",
                left, right, type);
        return cal(type, left, right);
    }

    @Override
    public Node<?> visitParens(ArithmeticParser.ParensContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Node<?> visitAddSub(ArithmeticParser.AddSubContext ctx) {
        Node<?> left = visit(ctx.expr(0));
        Node<?> right = visit(ctx.expr(1));
        int type = ctx.op.getType();
        System.out.printf("add and sub section: left number is %s, right number is %s, operate type is %s",
                left.getValue().toString(), right.getValue().toString(), type);
        return cal(type, left, right);
    }

    @Override
    public Node<?> visitProg(ArithmeticParser.ProgContext ctx) {
        return visit(ctx.expr());
    }

    private Node<?> cal(int type, Node<?> left, Node<?> right) {
        if (NumberUtils.isNumeric(left.getValue()) && NumberUtils.isNumeric(right.getValue())) {
            Number leftNum = left.getNumValue();
            Number rightNum = right.getNumValue();
            Number calResult = doNumCalculate(type, leftNum, rightNum);
            return new Node<>(calResult);
        }
        if (type == ArithmeticParser.ADD) {
            return new Node<>(left.getStrValue() + right.getStrValue());
        }
        throw new UnsupportedOperationException(String.format("node left: %s, node right:%s,unsupported operation type：%s",
                left.getValue().toString(), right.getValue().toString(), type));
    }

    private Number doNumCalculate(int type, Number left, Number right) {
        switch (type) {
            case ArithmeticParser.ADD:
                return NumberUtils.plus(left, right);
            case ArithmeticParser.SUB:
                return NumberUtils.minus(left, right);
            case ArithmeticParser.MUL:
                return NumberUtils.mul(left, right);
            case ArithmeticParser.DIV:
                return NumberUtils.div(left, right);
        }
        throw new IllegalStateException(String.format("unsupported operate type : %s", type));
    }
}