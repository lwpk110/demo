package cn.fh.mdx;

import org.apache.calcite.sql.*;
import org.apache.calcite.sql.parser.SqlParserPos;

import java.util.List;

public class ScopeBucket extends SqlCall {

    private SqlNode dimension;
    private SqlNode member;

    public ScopeBucket(SqlParserPos pos, SqlNode dimension, SqlNode member) {
        super(pos);
        this.dimension = dimension;
        this.member = member;
    }

    @Override
    public void unparse(SqlWriter writer, int leftPrec, int rightPrec) {
        writer.keyword("[");
        dimension.unparse(writer, leftPrec, rightPrec);
        writer.keyword("]");
        writer.keyword(".");
        writer.keyword("[");
        member.unparse(writer, leftPrec, rightPrec);
        writer.keyword("]");
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

    public SqlNode getDimension() {
        return dimension;
    }

    public void setDimension(SqlNode dimension) {
        this.dimension = dimension;
    }

    public SqlNode getMember() {
        return member;
    }

    public void setMember(SqlNode member) {
        this.member = member;
    }
}
