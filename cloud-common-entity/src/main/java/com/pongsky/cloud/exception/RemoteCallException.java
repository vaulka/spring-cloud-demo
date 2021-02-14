package com.pongsky.cloud.exception;

import com.pongsky.cloud.response.GlobalResult;

/**
 * 远程调用异常
 *
 * @author pengsenhao
 * @create 2021-02-14
 */
public class RemoteCallException extends RuntimeException {

    private final GlobalResult<?> result;

    public GlobalResult<?> getResult() {
        return result;
    }

    public RemoteCallException(GlobalResult<?> result) {
        super(result.getMessage());
        this.result = result;
    }

}
