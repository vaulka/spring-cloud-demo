package com.pongsky.cloud.controller.feign;

import com.pongsky.cloud.exception.ValidationException;
import com.pongsky.cloud.response.annotation.ResponseResult;
import com.pongsky.cloud.utils.jwt.enums.AuthRole;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Demo 模块
 *
 * @author pengsenhao
 * @create 2021-02-14
 */
@ResponseResult
@RestController
@PreAuthorize("hasRole('" + AuthRole.FEIGN_ROLE + "')")
@RequestMapping(value = "/feign/demo", produces = MediaType.APPLICATION_JSON_VALUE)
public class FeignDemoController {

    /**
     * 随机 UUID
     */
    private static final String UID = UUID.randomUUID().toString();

    /**
     * 获取 随机 UUID
     *
     * @return 获取 随机 UUID
     */
    @RequestMapping("/uid")
    public String uid() {
        return UID;
    }

    /**
     * 异常
     *
     * @return 异常
     */
    @RequestMapping("/exception")
    public String exception() {
        if (true) {
            throw new ValidationException("这是一个普普通通的校验异常");
        }
        return UID;
    }

}
