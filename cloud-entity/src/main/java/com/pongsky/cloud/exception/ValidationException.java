package com.pongsky.cloud.exception;

/**
 * 校验异常
 *
 * @author pengsenhao
 * @create 2021-02-11
 */
public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }

}
