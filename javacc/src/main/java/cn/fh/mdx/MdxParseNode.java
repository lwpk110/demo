package cn.fh.mdx;

import org.apache.calcite.sql.*;
import org.apache.calcite.sql.parser.SqlParserPos;

import java.util.List;

public class MdxParseNode extends SqlCall {
    private final String mdxStr;
    protected MdxParseNode(SqlParserPos pos, String mdxStr) {
        super(pos);
        this.mdxStr = mdxStr;
    }

    @Override
    public void unparse(SqlWriter writer, int leftPrec, int rightPrec) {
        super.unparse(writer, leftPrec, rightPrec);
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

    public String getMdxStr() {
        return mdxStr;
    }
}
