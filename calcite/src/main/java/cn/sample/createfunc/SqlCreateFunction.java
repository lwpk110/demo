package cn.sample.createfunc;

import com.google.protobuf.WireFormat;
import org.apache.calcite.sql.*;
import org.apache.calcite.sql.parser.SqlParserPos;

import java.util.List;

@SuppressWarnings("all")
public class SqlCreateFunction extends SqlCall {
    private SqlNode functionName;
    private String className;
    private SqlNodeList properties;
    private String methodName;
    private String comment;

    public SqlCreateFunction(SqlParserPos pos, SqlNode functionName, String className, String methodName,String comment,
                             SqlNodeList properties) {
        super(pos);
        this.functionName = functionName;
        this.className = className;
        this.properties = properties;
        this.methodName = methodName;
        this.comment = comment;
    }

    @Override
    public void unparse(SqlWriter writer, int leftPrec, int rightPrec) {
        writer.keyword("CREATE");
        writer.keyword("FUNCTION");
        functionName.unparse(writer, leftPrec, rightPrec);
        writer.keyword("AS");
        writer.print("'" + methodName + "'");
        if(methodName != null){
            writer.newlineAndIndent();
            writer.keyword("METHOD");
            writer.keyword(methodName);
        }
        if(properties != null){
            writer.newlineAndIndent();
            writer.keyword("PROPERTY");
            SqlWriter.Frame propertyFrame = writer.startList("(", ")");
            for (SqlNode property : properties) {
                writer.sep(",", false);
                writer.newlineAndIndent();
                writer.print("  ");
                property.unparse(writer, leftPrec, rightPrec);
            }
            writer.newlineAndIndent();
            writer.endList(propertyFrame);
        }
    }

    @Override
    public SqlKind getKind() {
        return SqlKind.CREATE_FUNCTION;
    }

    @Override
    public SqlOperator getOperator() {
        return null;
    }

    @Override
    public List<SqlNode> getOperandList() {
        return null;
    }

    public SqlNode getFunctionName() {
        return functionName;
    }

    public String getClassName() {
        return className;
    }

    public SqlNodeList getProperties() {
        return properties;
    }

    public String getComment() {
        return comment;
    }
}
