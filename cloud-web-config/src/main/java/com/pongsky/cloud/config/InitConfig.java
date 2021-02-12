package com.pongsky.cloud.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pongsky.cloud.utils.snowflake.SnowFlakeUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 初始化配置信息
 *
 * @author pengsenhao
 * @create 2021-02-11
 */
@Configuration
public class InitConfig {

    /**
     * 雪花算法工具类
     *
     * @return 雪花算法工具类
     */
    @Bean
    public SnowFlakeUtils snowFlakeUtils() {
        return new SnowFlakeUtils();
    }

    @Bean
    public ObjectMapper jsonMapper() {
        return new ObjectMapper()
                // 反序列化忽略 Json 对象在实体类中没有的字段
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                // 序列化忽略为 null 字段
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

}
