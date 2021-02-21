package com.pongsky.cloud.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 限流
 *
 * @author pengsenhao
 * @create 2021-02-21
 */
@Component
public class LimitFilter implements GlobalFilter, Ordered {

    @Override
    public int getOrder() {
        return 2;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // TODO 限流
        return chain.filter(exchange);
    }

}
