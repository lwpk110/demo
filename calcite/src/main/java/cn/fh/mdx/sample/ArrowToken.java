package cn.fh.mdx.sample;

import org.apache.calcite.sql.SqlCall;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.SqlOperator;
import org.apache.calcite.sql.parser.SqlParserPos;

import java.util.List;

public class ArrowToken extends SqlCall {

  private SqlNode variable;

  protected ArrowToken(SqlParserPos pos, SqlNode variable) {
    super(pos);
    this.variable = variable;
  }

  @Override
  public SqlOperator getOperator() {
    return null;
  }

  @Override
  public List<SqlNode> getOperandList() {
    return null;
  }

  public SqlNode getVariable() {
    return variable;
  }
}
