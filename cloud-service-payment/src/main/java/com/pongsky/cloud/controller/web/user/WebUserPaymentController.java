package com.pongsky.cloud.controller.web.user;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.pongsky.cloud.entity.payment.dto.PaymentDto;
import com.pongsky.cloud.response.GlobalResult;
import com.pongsky.cloud.response.annotation.ResponseResult;
import com.pongsky.cloud.service.PaymentService;
import com.pongsky.cloud.utils.jwt.enums.AuthRole;
import com.pongsky.cloud.validator.CreateGroup;
import com.pongsky.cloud.web.request.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 支付信息模块
 *
 * @author pengsenhao
 * @create 2021-02-13
 */
@ResponseResult
@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('" + AuthRole.USER_ROLE + "')")
@RequestMapping(value = "/web/user/payment", produces = MediaType.APPLICATION_JSON_VALUE)
@DefaultProperties(
        defaultFallback = "circuitBreakerResult",
        ignoreExceptions = RuntimeException.class
)
public class WebUserPaymentController {

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
     * 保存支付信息
     *
     * @param request    request
     * @param paymentDto 支付信息
     */
    @HystrixCommand
    @PostMapping
    public Object save(HttpServletRequest request,
                       @Validated({CreateGroup.class}) @RequestBody PaymentDto paymentDto) {
        Long userId = AuthUtils.getAuthUserId(request);
        paymentService.existsBySerialAndNotUserId(paymentDto.getSerial(), null);
        paymentService.save(userId, paymentDto);
        return GlobalResult.SUCCESS;
    }

}
