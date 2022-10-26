package cn.fh.mdx;

import org.apache.calcite.sql.*;
import org.apache.calcite.sql.parser.SqlParserPos;

import java.util.List;

public class ScopeNode extends SqlCall {

    private final SqlNodeList scopeBuckets;
    private final SqlNodeList calcExprs;

    public ScopeNode(SqlParserPos pos, SqlNodeList scopeBuckets, SqlNodeList calcExprs) {
        super(pos);
        this.scopeBuckets = scopeBuckets;
        this.calcExprs = calcExprs;
    }

    @Override
    public void unparse(SqlWriter writer, int leftPrec, int rightPrec) {
        writer.keyword("SCOPE");
        SqlWriter.Frame scopesFrame = writer.startList("(", ")");
        for (SqlNode scopeBucket : scopeBuckets) {
            writer.sep(",", false);
            scopeBucket.unparse(writer, leftPrec, rightPrec);
        }
        writer.endList(scopesFrame);
        writer.keyword(";");
        writer.newlineAndIndent();
        writer.keyword("  ");
        SqlWriter.Frame calcExprsFrame = writer.startList("  ", "  ");
        for (SqlNode calcExpr : calcExprs) {
            writer.sep(";",false);
            calcExpr.unparse(writer, leftPrec, rightPrec);
        }
        writer.endList(calcExprsFrame);
        writer.newlineAndIndent();
        writer.keyword("  END SCOPE;");
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

    public SqlNodeList getScopeBuckets() {
        return scopeBuckets;
    }

    public SqlNodeList getCalcExprs() {
        return calcExprs;
    }
}
