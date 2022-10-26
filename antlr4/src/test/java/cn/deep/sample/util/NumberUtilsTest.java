package cn.deep.sample.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberUtilsTest {

    @Test
    void test(){
        assertAll("assert number type validate", () -> {
            assertTrue(NumberUtils.isInt(Integer.MAX_VALUE));
            assertTrue(NumberUtils.isInt(Long.MAX_VALUE));
            assertTrue(NumberUtils.isInt(Short.MAX_VALUE));
            assertTrue(NumberUtils.isInt(Byte.MAX_VALUE));

            assertTrue(NumberUtils.isFloat(Float.MAX_VALUE));
            assertTrue(NumberUtils.isFloat(Double.MAX_VALUE));

            assertFalse(NumberUtils.isInt(6.23));
            assertFalse(NumberUtils.isFloat(2));

            assertTrue(NumberUtils.isNumeric(11));
        });
    }

}