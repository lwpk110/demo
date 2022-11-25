package cn.deep.sample.base;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringFormat {


    /**
     * escape '%' in {@link String#format(String, Object...)} by double percentage char - %%'
     * @see <a href="https://stackoverflow.com/questions/5011932/how-to-escape-in-string-format">How to escape % in String.Format?</a>
     *
     */
    @Test
    void stringFormat_escape() throws Exception {
        String formatted = "select %s FROM %s dimension WHERE (NAME LIKE concat('%%', upper('%s'), '%%') " +
                " OR %s LIKE concat('%%', upper('%s'), '%%'))";
        String actualFormatted = String.format(formatted, "name", "tb","searchVal", "level", "low");
        assertEquals("select name FROM tb dimension WHERE (NAME LIKE concat('%', upper('searchVal'), '%')  OR level LIKE concat('%', upper('low'), '%'))",
                actualFormatted);

        String formatted2 = " and (DATE_FORMAT(CONCAT('%s','-','%s','-','01'),'%%Y-%%m') BETWEEN " +
                " DATE_FORMAT(CONCAT(ifnull(start_year,'%s'),'-','01','-','01'),'%%Y-%%m')" +
                " AND DATE_FORMAT(CONCAT(ifnull(end_year,#{end_year}),'-','12','-','01'),'%%Y-%%m'))";
        String actualFormatted2 = String.format(formatted2, "2012", "01", "2000");
        assertEquals(" and (DATE_FORMAT(CONCAT('2012','-','01','-','01'),'%Y-%m') BETWEEN  DATE_FORMAT(CONCAT(ifnull(start_year,'2000'),'-','01','-','01'),'%Y-%m') AND DATE_FORMAT(CONCAT(ifnull(end_year,#{end_year}),'-','12','-','01'),'%Y-%m'))",
                actualFormatted2);
    }
}
