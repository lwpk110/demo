package cn.util;

import org.apache.calcite.sql.parser.SqlParserUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SqlParserUtilTest {

    @Test
    void parseString() {
        String singleQuotes = "test";
        String actualParsed = SqlParserUtil.parseString(singleQuotes);
        System.out.println(actualParsed);
    }

    @Test
    void parseDecimal() {
    }

    @Test
    void parseInteger() {
    }

    @Test
    void parseDate() {
    }

    @Test
    void parseTime() {
    }

    @Test
    void parseTimestamp() {
    }

    @Test
    void parseDateLiteral() {
    }
}