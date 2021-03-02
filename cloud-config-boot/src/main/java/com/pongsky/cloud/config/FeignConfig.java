package com.pongsky.cloud.config;

import com.pongsky.cloud.utils.jwt.JwtUtils;
import com.pongsky.cloud.utils.jwt.enums.AuthRole;
import feign.RequestInterceptor;
import feign.RequestTemplate;

import java.math.BigInteger;

/**
 * @author pengsenhao
 * @create 2021-03-02
 */
public class FeignConfig implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        // feign 调用的接口全部加上 Authorization 请求头进行鉴权
        String authorization = JwtUtils.createAccessToken(BigInteger.ZERO.toString(),
                AuthRole.FEIGN_ROLE, SystemConfig.getActive(), SystemConfig.getApplicationName());
        requestTemplate.header(JwtUtils.AUTHORIZATION, JwtUtils.TOKEN_PREFIX + authorization);
    }

}