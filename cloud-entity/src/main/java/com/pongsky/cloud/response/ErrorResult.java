package com.pongsky.cloud.response;

import com.pongsky.cloud.response.enums.ResultCode;
import lombok.Data;

/**
 * 错误响应数据体
 *
 * @author pengsenhao
 * @create 2021-02-10
 */
@Data
public class ErrorResult {

    public ErrorResult(String ip, ResultCode resultCode, String path, String exception) {
        this.ip = ip;
        this.details = resultCode.getDetails();
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.path = path;
        this.exception = exception;
    }

    /**
     * 客户端 IP 地址
     */
    private String ip;

    /**
     * 接口响应结果标识码
     */
    private Integer code;

    /**
     * 异常详细信息
     */
    private String details;

    /**
     * 接口响应结果信息
     */
    private String message;

    /**
     * 调用接口路径地址
     */
    private String path;

    /**
     * 异常类型信息
     */
    private String exception;

    /**
     * 时间戳
     */
    private long timestamp = System.currentTimeMillis();
}
