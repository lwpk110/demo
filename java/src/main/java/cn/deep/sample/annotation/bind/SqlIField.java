package com.proinnova.database.bind;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * sqli 字段级别验证标识，表示在所验证的dto中，标注该类型的字段需要参入sql注入验证。
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SqlIField {
}
