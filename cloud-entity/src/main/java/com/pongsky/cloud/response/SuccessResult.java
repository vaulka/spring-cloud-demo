package com.pongsky.cloud.response;

import com.pongsky.cloud.response.enums.ResultCode;
import lombok.Data;

/**
 * 正确响应数据体
 *
 * @author pengsenhao
 * @create 2021-02-10
 */
@Data
public class SuccessResult {

    public SuccessResult(Object data) {
        this.code = ResultCode.Success.getCode();
        this.message = ResultCode.Success.getMessage();
        this.data = data;
    }

    /**
     * 接口响应结果标识码
     */
    private Integer code;

    /**
     * 接口响应结果信息
     */
    private String message;

    /**
     * 接口响应数据体
     */
    private Object data;

}
