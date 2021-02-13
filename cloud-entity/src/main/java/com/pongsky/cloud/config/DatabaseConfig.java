package com.pongsky.cloud.config;

import com.pongsky.cloud.utils.snowflake.SnowFlakeUtils;
import org.springframework.context.annotation.Bean;

/**
 * 数据库配置
 *
 * @author pengsenhao
 * @create 2021-02-13
 */
public class DatabaseConfig {

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
