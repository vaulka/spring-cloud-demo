package com.pongsky.cloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * feign 配置
 *
 * @author pengsenhao
 * @create 2021-02-14
 */
public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

//    @Bean
//    public ErrorDecoder errorDecoder() {
//        return new MyErrorDecoder();
//    }
//
//    public static class MyErrorDecoder implements ErrorDecoder {
//        @Override
//        public Exception decode(String methodKey, Response response) {
//            final StringWriter stringWriter = new StringWriter();
//            try {
//                IOUtils.copy(response.body().asInputStream(), stringWriter, String.valueOf(StandardCharsets.UTF_8));
//            } catch (IOException e) {
//                return RemoteCallException.errorStatus(methodKey, response);
//            }
//            return new com.pongsky.cloud.exception.RemoteCallException(stringWriter.toString());
//        }
//    }

}
