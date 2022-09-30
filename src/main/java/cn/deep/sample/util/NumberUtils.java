package cn.deep.sample.util;

import java.util.function.BiFunction;

public class NumberUtils {

    public static Number valueOf(String digStr) {
        if(digStr.contains(".")){
            return Float.parseFloat(digStr);
        }
        return Long.parseLong(digStr);
    }

    public static Number plus(Number left, Number right) {
        return doCal(left, right, Long::sum, Float::sum);
    }

    public static Number minus(Number left, Number right) {
        return doCal(left, right, (a, b) -> a - b, (c, d) -> c - d);
    }

    public static Number mul(Number left, Number right) {
        return doCal(left, right, (a, b) -> a * b, (c, d) -> c * d);
    }

    public static Number div(Number left, Number right) {
        return doCal(left, right, (a, b) -> a / b, (c, d) -> c / d);
    }

    public static boolean isInt(Number num) {
        return num instanceof Integer || num instanceof Long || num instanceof Short || num instanceof Byte;
    }

    public static boolean isFloat(Number num) {
        return num instanceof Float || num instanceof Double;
    }

    private static Number doCal(Number left, Number right, BiFunction<Long, Long, Long> longFunc,
                                BiFunction<Float, Float, Float> floatFunc) {
        if (isInt(left) && isInt(right)) {
            return longFunc.apply(left.longValue(), right.longValue());
        }
        if (isFloat(left) || isFloat(right)) {
            return floatFunc.apply(left.floatValue(), right.floatValue());
        }
        return 0;
    }

    private static long longCal(long left, long right, BiFunction<Long, Long, Long> function) {
        return function.apply(left, right);
    }

    private static Float floatCal(float left, float right, BiFunction<Float, Float, Float> function) {
        return function.apply(left, right);
    }

    public static <T> boolean isNumeric(T origin) {
        if (origin instanceof String) {
            return isNumeric((String) origin);
        }
        return isInt((Number) origin) || isFloat((Number) origin);
    }

    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
