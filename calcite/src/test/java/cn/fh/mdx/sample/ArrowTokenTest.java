package cn.fh.mdx.sample;

import cn.fh.mdx.javacc.MdxParser;
import org.apache.calcite.avatica.util.Casing;
import org.apache.calcite.avatica.util.Quoting;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.sql.validate.SqlConformanceEnum;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


class ArrowTokenTest {

  @Disabled("未完成")
  @Test
  void testArrowToken() throws Exception {
    SqlParser.Config config = SqlParser.configBuilder()
            .setParserFactory(MdxParser.FACTORY)
            .setCaseSensitive(false)
            .setQuoting(Quoting.BACK_TICK)
            .setQuotedCasing(Casing.TO_UPPER)
            .setUnquotedCasing(Casing.TO_UPPER)
            .setConformance(SqlConformanceEnum.ORACLE_12)
            .build();
    SqlParser parser = SqlParser.create("", config);
    String sql = "->hello world!!<-";
    SqlNode sqlNode = parser.parseQuery(sql);
    System.out.println(sqlNode.toString());
  }

}