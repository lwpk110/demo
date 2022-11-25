package cn.deep.sample.annotation.bind;

import java.util.Map;

/**
 * sqlInjection 处理器
 *
 * @see BlackListKeywordValidator
 */
public interface SqlInjectionHandler {
    /**
     *
     * @param fields 需要验证处理的字段名称和值。
     */
    void handler(Map<String,Object> fields);
}
