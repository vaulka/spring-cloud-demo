package com.pongsky.cloud.exception;

/**
 * 不存在异常
 *
 * @author pengsenhao
 * @create 2021-02-10
 */
public class DoesNotExistException extends RuntimeException {

    public DoesNotExistException(String message) {
        super(message);
    }

}
