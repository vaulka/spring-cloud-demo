package com.pongsky.cloud.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pongsky.cloud.utils.jackson.JacksonUtils;
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
        return JacksonUtils.JSON_MAPPER;
    }

}
