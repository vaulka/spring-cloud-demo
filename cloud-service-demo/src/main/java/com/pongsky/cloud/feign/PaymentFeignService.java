package com.pongsky.cloud.feign;

import com.pongsky.cloud.config.ServiceConfig;
import com.pongsky.cloud.feign.impl.PaymentFeignServiceImpl;
import com.pongsky.cloud.response.GlobalResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author pengsenhao
 * @create 2021-02-14
 */
@FeignClient(value = ServiceConfig.PAYMENT_SERVICE, fallback = PaymentFeignServiceImpl.class)
public interface PaymentFeignService {

    /**
     * URL 前缀
     */
    String URL_PREFIX = "/feign/demo";

    /**
     * 获取 随机 UUID
     *
     * @return 获取 随机 UUID
     */
    @RequestMapping(URL_PREFIX + "/uid")
    GlobalResult<String> uid();

    /**
     * 异常
     *
     * @return 异常
     */
    @RequestMapping(URL_PREFIX + "/exception")
    GlobalResult<String> exception();

}
