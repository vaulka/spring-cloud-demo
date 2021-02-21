package com.pongsky.cloud.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 鉴权
 *
 * @author pengsenhao
 * @create 2021-02-21
 */
@Component
public class AuthFilter implements GlobalFilter, Ordered {

    @Override
    public int getOrder() {
        return 3;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // TODO 鉴权
        return chain.filter(exchange);
    }

}
