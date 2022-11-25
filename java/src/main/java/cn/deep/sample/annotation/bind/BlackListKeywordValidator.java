package com.proinnova.database.bind;

import org.seeplnframework.exception.ValidationException;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * <h3>sql 黑名单验证器。</h3>
 *
 * <p>
 * 验证规则: 黑名单关键字 + " "(空格)
 * </p>
 */
public class BlackListKeywordValidator implements SqlInjectionHandler {

    private static final String inj_str = "'|and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|;|or|-|+|,";

    /**
     * 校验sql拼接变量时参数是否合法：避免sql注入问题
     */
    public static void sqlVarCheck(String var) {
        String[] inj_stra = inj_str.split("\\|");
        for (String s : inj_stra) {
            if (var.contains(s) && var.indexOf(" ") > 0) {
                throw new ValidationException(999800, String.format("参数%s非法", var));
            }
        }
    }

    @Override
    public void handler(Map<String, Object> fields) {
        for (Map.Entry<String, Object> entry : fields.entrySet()) {
            Object value = entry.getValue();
            this.checkObject(value);
        }
    }

    private void checkObject(Object value) {
        if (value instanceof String) {
            checkStr(String.valueOf(value));
        } else if (value instanceof Collection) {
            checkCollection(value);
        } else {
            checkDto(value);
        }
    }

    private void checkCollection(Object value) {
        Collection<?> cols = (Collection<?>) value;
        for (Object col : cols) {
            checkObject(col);
        }
    }

    private void checkDto(Object value) {
        Field[] dfs = value.getClass().getDeclaredFields();
        Map<String, Object> validateFields = new HashMap<>(dfs.length);
        for (Field df : dfs) {
            SqlIField annotation = df.getAnnotation(SqlIField.class);
            if (annotation != null) {
                df.setAccessible(true);
                try {
                    Object fv = df.get(value);
                    validateFields.put(df.getName(), fv);
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("access field failed", e);
                }
            }
        }
        if (!CollectionUtils.isEmpty(validateFields)) {
            this.handler(validateFields);
        }
    }

    private void checkStr(String str) {
        sqlVarCheck(str);
    }
}
