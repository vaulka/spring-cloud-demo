package com.pongsky.cloud.utils.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * jackson 工具类
 *
 * @author pengsenhao
 * @create 2021-02-14
 */
public class JacksonUtils {

    /**
     * JSON MAPPER
     */
    public static final ObjectMapper JSON_MAPPER = new ObjectMapper()
            // 反序列化忽略 Json 对象在实体类中没有的字段
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            // 序列化忽略为 null 字段
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

}
