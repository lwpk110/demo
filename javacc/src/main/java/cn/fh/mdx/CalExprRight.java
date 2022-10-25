package cn.fh.mdx;

import org.apache.calcite.sql.*;
import org.apache.calcite.sql.parser.SqlParserPos;

import java.util.List;

public class CalExprRight extends SqlCall {

    private  SqlNode left;
    private  SqlOperator operand;
    private  SqlNode right;

    public CalExprRight(SqlParserPos pos, SqlNode left, SqlOperator operand, SqlNode right) {
        super(pos);
        this.left = left;
        this.operand = operand;
        this.right = right;
    }

    @Override
    public void unparse(SqlWriter writer, int leftPrec, int rightPrec) {
        SqlNodeList nodeList =SqlNodeList.of(left, right);
        operand.unparse(writer,operand.createCall(nodeList), leftPrec, rightPrec);
    }

    @Override
    public SqlKind getKind() {
        return SqlKind.OTHER;
    }

    @Override
    public SqlOperator getOperator() {
        return null;
    }

    @Override
    public List<SqlNode> getOperandList() {
        return null;
    }

    public SqlNode getLeft() {
        return left;
    }

    public SqlOperator getOperand() {
        return operand;
    }

    public SqlNode getRight() {
        return right;
    }
}
