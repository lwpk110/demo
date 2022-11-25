package cn.deep.sample.annotation.bind;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <h3>sql 注入方法级别注解</h3>
 * 在 bean 的方法级别拦截参数的正确性。
 * <p>
 *     <ul>
 *         <li>{@link #fields()} - 指定需要验证的参数名称。</li>
 *         <li>{@link #handlers()} 指定使用的验证器的类型。</li>
 *     </ul>
 * </p>
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SqlIAble {

    /**
     * fields 的值必须和方法里面的参数名称一致
     * <p>
     * 可验证参数类型:
     * <ul>
     *     <li>字符串</li>
     *     <li>Collection 集合 </li>
     *     <li> dto 对象 </li>
     * </ul>
     * </p>
     *
     * <p>
     *     fields 为空则对整个方法的参数都进行验证。
     * </p>
     *
     */
    String[] fields() default {};

    Class<? extends SqlInjectionHandler>[] handlers() default {BlackListKeywordValidator.class};
}
