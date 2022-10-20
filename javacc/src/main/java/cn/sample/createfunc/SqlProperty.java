package cn.sample.createfunc;

import org.apache.calcite.sql.*;
import org.apache.calcite.sql.parser.SqlParserPos;

import java.util.List;

@SuppressWarnings("all")
public class SqlProperty extends SqlCall {

    private SqlNode key;
    private SqlNode value;

    public SqlProperty(SqlParserPos pos, SqlNode key, SqlNode value) {
        super(pos);
        this.key = key;
        this.value = value;
    }

    @Override
    public void unparse(SqlWriter writer, int leftPrec, int rightPrec) {
        key.unparse(writer, leftPrec, rightPrec);
        writer.keyword("=");
        value.unparse(writer, leftPrec, rightPrec);
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
}
