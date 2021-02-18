package com.pongsky.cloud.response;

import com.pongsky.cloud.exception.CircuitBreakerException;
import com.pongsky.cloud.exception.RemoteCallException;
import com.pongsky.cloud.response.enums.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 响应数据体
 *
 * @author pengsenhao
 * @create 2021-02-10
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class GlobalResult<T> {

    /**
     * 【成功】构造方法
     *
     * @param data 接口响应数据体
     */
    public GlobalResult(T data) {
        this.code = ResultCode.Success.getCode();
        this.message = ResultCode.Success.getMessage();
        this.data = data;
    }

    /**
     * 【失败】构造方法
     *
     * @param ip         客户端 IP 地址
     * @param resultCode 响应数据枚举
     * @param path       调用接口路径地址
     * @param exception  异常类型信息
     */
    public GlobalResult(String ip, ResultCode resultCode, String path, String exception) {
        this.ip = ip;
        this.details = resultCode.getDetails();
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.path = path;
        this.exception = exception;
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * 远程调用异常 fallback 响应数据
     *
     * @param clazz clazz
     * @param <T>   <T>
     * @return 远程调用异常 fallback 响应数据
     */
    public static <T> GlobalResult<T> remoteCallExceptionResult(Class<T> clazz) {
        return new GlobalResult<>(null, ResultCode.RemoteCallException, null, RemoteCallException.class.getName());
    }

    /**
     * 熔断 响应数据
     *
     * @return 熔断 响应数据
     */
    public static GlobalResult<Void> circuitBreakerExceptionResult() {
        return new GlobalResult<>(null, ResultCode.CircuitBreakerException, null, CircuitBreakerException.class.getName());
    }

    /**
     * 校验响应数据体是否异常
     * WARN:feign 远程调用 必须调用此方法
     *
     * @param globalResult 响应数据体
     * @param <T>          <T>
     * @return 校验响应数据体是否异常
     */
    public static <T> GlobalResult<T> validation(GlobalResult<T> globalResult) {
        if (!globalResult.getCode().equals(ResultCode.Success.getCode())) {
            throw new RemoteCallException(globalResult);
        }
        return globalResult;
    }

    /**
     * 成功
     */
    public static final String SUCCESS = "SUCCESS";

    /**
     * 客户端 IP 地址
     * <p>
     * 有数据的情况：失败
     */
    private String ip;

    /**
     * 接口响应结果标识码
     * <p>
     * 有数据的情况：成功、失败
     */
    private Integer code;

    /**
     * 异常详细信息
     * <p>
     * 有数据的情况：失败
     */
    private String details;

    /**
     * 接口响应结果信息
     * <p>
     * 有数据的情况：成功、失败
     */
    private String message;

    /**
     * 接口响应数据体
     * <p>
     * 有数据的情况：成功
     */
    private T data = null;

    /**
     * 调用接口路径地址
     * <p>
     * 有数据的情况：失败
     */
    private String path;

    /**
     * 异常类型信息
     * <p>
     * 有数据的情况：失败
     */
    private String exception;

    /**
     * 时间戳
     * <p>
     * 有数据的情况：失败
     */
    private Long timestamp;

}
