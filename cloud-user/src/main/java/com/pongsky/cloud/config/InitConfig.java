package com.pongsky.cloud.config;

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

}
