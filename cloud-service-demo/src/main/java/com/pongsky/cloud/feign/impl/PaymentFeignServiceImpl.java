package com.pongsky.cloud.feign.impl;

import com.pongsky.cloud.feign.PaymentFeignService;
import com.pongsky.cloud.response.GlobalResult;
import org.springframework.stereotype.Component;

/**
 * @author pengsenhao
 * @create 2021-02-17
 */
@Component
public class PaymentFeignServiceImpl implements PaymentFeignService {

    @Override
    public GlobalResult<String> uid() {
        return GlobalResult.remoteCallExceptionResult(String.class);
    }

    @Override
    public GlobalResult<String> exception() {
        return GlobalResult.remoteCallExceptionResult(String.class);
    }

}
