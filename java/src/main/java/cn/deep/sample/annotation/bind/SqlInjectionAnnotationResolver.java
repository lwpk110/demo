package cn.deep.sample.annotation.bind;


import com.fasterxml.jackson.databind.util.ClassUtil;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * {@link SqlIAble} 注释解析器，在方法执行之前对bean 的方法参数进行参数验证；
 *
 * @see SqlIAble
 * @see SqlIField
 * @see SqlInjectionHandler
 */
@Aspect
public class SqlInjectionAnnotationResolver {

    @Before("@annotation(sqlInjection)")
    public void handleSqlInjection(JoinPoint joinPoint, SqlIAble sqlInjection) {
        String[] fields = sqlInjection.fields();
        List<String> validateFieldNames = new ArrayList<>();
        if (ArrayUtils.isNotEmpty(fields)) {
            validateFieldNames = Arrays.asList(fields);
        }
        Class<? extends SqlInjectionHandler>[] handlers = sqlInjection.handlers();
        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        Map<String, Object> validateFields = new HashMap<>();
        if (signature != null) {
            this.getValidateFields(signature, validateFieldNames, validateFields, args);
            if (MapUtils.isNotEmpty(validateFields)) {
                this.handle(handlers, validateFields);
            }
        }
    }

    private void getValidateFields(Signature signature, List<String> validateFieldNames,
                                   Map<String, Object> validateFields, Object[] args) {
        MethodSignature ms = (MethodSignature) signature;
        String[] parameterNames = ms.getParameterNames();
        if (CollectionUtils.isEmpty(validateFieldNames)) {
            validateFields.putAll(zipAll(parameterNames, args));
        } else {
            validateFields.putAll(match(parameterNames,args,validateFieldNames));
        }
    }

    private void handle(Class<? extends SqlInjectionHandler>[] handlers, Map<String, Object> fields) {
        for (Class<? extends SqlInjectionHandler> handler : handlers) {
            SqlInjectionHandler instance = ClassUtil.createInstance(handler, true);
            instance.handler(fields);
        }
    }

    private Map<String, Object> zipAll(String[] parameters, Object[] values) {
        Map<String, Object> result = new HashMap<>(parameters.length);
        for (int i = 0; i < parameters.length; i++) {
            result.put(parameters[i], values[i]);
        }
        return result;
    }

    private Map<String,Object> match(String[] parameterNames, Object[] values,List<String> validateFieldNames){
        Map<String, Object> result = new HashMap<>(parameterNames.length);
        for (int i = 0; i < parameterNames.length; i++) {
            String parameterName = parameterNames[i];
            if (validateFieldNames.contains(parameterName)) {
                result.put(parameterName, values[i]);
            }
        }
        return result;
    }
}
