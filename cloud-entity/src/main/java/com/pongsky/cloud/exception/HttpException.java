package com.pongsky.cloud.exception;

/**
 * HTTP 请求异常
 *
 * @author pengsenhao
 * @create 2021-02-11
 */
public class HttpException extends RuntimeException {

    public HttpException(String message, Exception e) {
        super(message, e);
    }

}