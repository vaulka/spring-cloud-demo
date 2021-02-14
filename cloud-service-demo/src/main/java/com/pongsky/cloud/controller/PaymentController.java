package com.pongsky.cloud.controller;

import com.pongsky.cloud.entity.user.dto.UserDto;
import com.pongsky.cloud.entity.user.vo.UserVo;
import com.pongsky.cloud.feign.PaymentFeign;
import com.pongsky.cloud.response.annotation.ResponseResult;
import com.pongsky.cloud.validator.SearchGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Payment 模块
 *
 * @author pengsenhao
 * @create 2021-02-14
 */
@ResponseResult
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/payment", produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentController {

    private final PaymentFeign paymentFeign;

    /**
     * 登录
     *
     * @param userDto 登录信息
     * @return 登录
     */
    @RequestMapping("/login")
    public UserVo login(@Validated({SearchGroup.class}) @RequestBody UserDto userDto) {
        return paymentFeign.login(userDto).getData();
    }

    /**
     * 获取 Payment 随机 UUID
     *
     * @return 获取 Payment 随机 UUID
     */
    @RequestMapping("/uid")
    public String uid() {
        return paymentFeign.uid().getData();
    }

    /**
     * 异常
     *
     * @return 异常
     */
    @RequestMapping("/exception")
    public Object exception() {
        return paymentFeign.exception().getData();
    }

}
