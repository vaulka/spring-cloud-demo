package com.pongsky.cloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.pongsky.cloud.entity.user.dto.UserDto;
import com.pongsky.cloud.response.GlobalResult;
import com.pongsky.cloud.response.annotation.ResponseResult;
import com.pongsky.cloud.service.PaymentService;
import com.pongsky.cloud.service.UserService;
import com.pongsky.cloud.validator.SearchGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping(value = "/feignClient", produces = MediaType.APPLICATION_JSON_VALUE)
@DefaultProperties(
        defaultFallback = "circuitBreakerResult",
        ignoreExceptions = RuntimeException.class,
        commandProperties = {
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "60000")
        }
)
public class FeignClientController {

    private final UserService userService;
    private final PaymentService paymentService;

    /**
     * 服务降级 fallback 方法
     * WARN: controller 所有方法返回值必须为 Object
     *
     * @return 服务降级 fallback 方法
     */
    public GlobalResult<Void> circuitBreakerResult() {
        return GlobalResult.circuitBreakerExceptionResult();
    }

    /**
     * 登录
     *
     * @param userDto 登录信息
     * @return 登录
     */
    @HystrixCommand
    @RequestMapping("/login")
    public Object login(@Validated({SearchGroup.class}) @RequestBody UserDto userDto) {
        return userService.login(userDto);
    }

    /**
     * 获取 Payment 随机 UUID
     *
     * @return 获取 Payment 随机 UUID
     */
    @HystrixCommand
    @GetMapping("/uid")
    public Object uid() {
        return paymentService.uid();
    }

    /**
     * 异常
     *
     * @return 异常
     */
    @HystrixCommand
    @RequestMapping("/exception")
    public Object exception() {
        return paymentService.exception();
    }

}
