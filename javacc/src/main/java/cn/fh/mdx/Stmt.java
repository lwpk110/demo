package cn.fh.mdx;

import org.apache.calcite.sql.*;
import org.apache.calcite.sql.parser.SqlParserPos;

import java.util.List;

public class Stmt extends SqlCall {

    private final SqlNode cubeName;
    private final SqlNode scopeNode;

    public Stmt(SqlParserPos pos, SqlNode cubeName, SqlNode scopeNode) {
        super(pos);
        this.cubeName = cubeName;
        this.scopeNode = scopeNode;
    }

    @Override
    public void unparse(SqlWriter writer, int leftPrec, int rightPrec) {
        writer.keyword("USE");
        cubeName.unparse(writer, leftPrec, rightPrec);
        writer.keyword(";");
        writer.newlineAndIndent();
        writer.keyword("  ");
        scopeNode.unparse(writer, leftPrec, rightPrec);
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

    public SqlNode getCubeName() {
        return cubeName;
    }

    public SqlNode getScopeNode() {
        return scopeNode;
    }
}
