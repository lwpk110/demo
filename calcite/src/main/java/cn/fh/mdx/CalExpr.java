package cn.fh.mdx;

import org.apache.calcite.sql.*;
import org.apache.calcite.sql.parser.SqlParserPos;

import java.util.List;

public class CalExpr extends SqlCall {

    private  SqlNode left;
    private  SqlNode right;

    public CalExpr(SqlParserPos pos, SqlNode left, SqlNode right) {
        super(pos);
        this.left = left;
        this.right = right;
    }

    @Override
    public void unparse(SqlWriter writer, int leftPrec, int rightPrec) {
        left.unparse(writer, leftPrec, rightPrec);
        writer.keyword("=");
        right.unparse(writer, leftPrec, rightPrec);
        writer.keyword(";");
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

    public SqlNode getRight() {
        return right;
    }
}
