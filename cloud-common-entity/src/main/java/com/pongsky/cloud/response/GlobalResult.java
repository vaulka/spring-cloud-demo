package com.pongsky.cloud.response;

import com.pongsky.cloud.response.enums.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 正确响应数据体
 *
 * @author pengsenhao
 * @create 2021-02-10
 */
@Data
@NoArgsConstructor
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
