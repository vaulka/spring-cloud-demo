package com.pongsky.cloud;

import com.pongsky.cloud.config.DatabaseConfig;
import com.pongsky.cloud.config.JacksonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

/**
 * @author pengsenhao
 * @create 2021-02-09
 */
@EnableFeignClients
@EnableCircuitBreaker
@SpringBootApplication
@Import({
        JacksonConfig.class,
        DatabaseConfig.class
})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
