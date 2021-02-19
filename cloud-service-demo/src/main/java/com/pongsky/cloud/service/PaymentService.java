package com.pongsky.cloud.service;

import com.pongsky.cloud.feign.PaymentFeignService;
import com.pongsky.cloud.response.GlobalResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author pengsenhao
 * @create 2021-02-19
 */
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentFeignService paymentFeignService;

    /**
     * 获取 Payment 随机 UUID
     *
     * @return 获取 Payment 随机 UUID
     */
    public String uid() {
        return GlobalResult.validation(paymentFeignService.uid()).getData();
    }

    /**
     * 异常
     *
     * @return 异常
     */
    public String exception() {
        return GlobalResult.validation(paymentFeignService.exception()).getData();
    }

}
