package cn.fh.mdx;

import cn.fh.mdx.javacc.MdxParser;
import org.apache.calcite.avatica.util.Casing;
import org.apache.calcite.avatica.util.Quoting;
import org.apache.calcite.config.Lex;
import org.apache.calcite.sql.SqlDialect;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.dialect.OracleSqlDialect;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.sql.parser.SqlParserPos;
import org.apache.calcite.sql.validate.SqlConformanceEnum;
import org.junit.jupiter.api.Test;

class StmtTest {

    @Test
    void stmtNodeList() throws SqlParseException {
        SqlParser.Config config = SqlParser.configBuilder()
                .setParserFactory(MdxParser.FACTORY)
                .setCaseSensitive(false)
                .setQuoting(Quoting.BACK_TICK)
                .setQuotedCasing(Casing.TO_UPPER)
                .setUnquotedCasing(Casing.TO_UPPER)
                .setConformance(SqlConformanceEnum.ORACLE_12)
                .build();
        SqlParser parser = SqlParser.create("", config);
        String sql = "use cube_name;" +
                "scope([Account].[c01], [Year].[2022]);" +
                "[Product].[02] = [Product].[01] * 0.8;" +
                "end scope;";
        SqlNode sqlNode = parser.parseQuery(sql);
        System.out.println(sqlNode.toString());
    }
}