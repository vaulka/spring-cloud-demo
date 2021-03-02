package com.pongsky.cloud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 系统配置文件
 *
 * @author pengsenhao
 * @create 2021-02-11
 */
@Component
public class SystemConfig {

    /**
     * 环境
     */
    private static String active;

    @Value("${spring.profiles.active}")
    public void setActive(String active) {
        SystemConfig.active = active;
    }

    public static String getActive() {
        return active;
    }


    /**
     * 应用名称
     */
    private static String applicationName;

    @Value("${application.name}")
    public void setApplicationName(String applicationName) {
        SystemConfig.applicationName = applicationName;
    }

    public static String getApplicationName() {
        return applicationName;
    }

}

