package com.pongsky.cloud.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;

/**
 * jackson 配置
 *
 * @author pengsenhao
 * @create 2021-02-13
 */
public class JacksonConfig {

    @Bean
    public ObjectMapper jsonMapper() {
        return new ObjectMapper()
                // 反序列化忽略 Json 对象在实体类中没有的字段
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                // 序列化忽略为 null 字段
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

}
