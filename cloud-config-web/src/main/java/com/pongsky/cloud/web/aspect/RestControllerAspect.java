package com.pongsky.cloud.web.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pongsky.cloud.utils.ip.IpUtils;
import com.pongsky.cloud.utils.jwt.dto.AuthInfo;
import com.pongsky.cloud.web.request.AuthUtils;
import com.pongsky.cloud.web.request.RequestUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

/**
 * controller 请求日志打印
 *
 * @author pengsenhao
 * @create 2021-02-11
 */
@Slf4j
@Aspect
@Component
@Order(value = 2)
@RequiredArgsConstructor
public class RestControllerAspect {

    private final ObjectMapper jsonMapper;

    @Around("execution(public * com.pongsky.cloud.controller..*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes requestAttributes
                = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return joinPoint.proceed();
        }
        HttpServletRequest request = requestAttributes.getRequest();
        AuthInfo authInfo = AuthUtils.getUser(request);
        String ip = IpUtils.getIp(request);
        String userAgent = Optional.ofNullable(request.getHeader("user-agent")).orElse("");
        String referer = Optional.ofNullable(request.getHeader("referer")).orElse("");
        switch (RequestMethod.valueOf(request.getMethod())) {
            case PUT:
            case POST:
            case DELETE:
                log.info("");
                log.info("Started request");
                log.info("authInfo: id [{}] role [{}]", authInfo.getId(), authInfo.getRole());
                log.info("request header: IP [{}] userAgent [{}] referer [{}]", ip, userAgent, referer);
                log.info("request: methodURL [{}] methodType [{}] params [{}] body [{}]",
                        request.getRequestURI(),
                        request.getMethod(),
                        Optional.ofNullable(request.getQueryString()).orElse(""),
                        Optional.ofNullable(RequestUtils.getBody(request)).orElse(""));
                break;
            default:
                break;

        }
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long cost = System.currentTimeMillis() - start;
        switch (RequestMethod.valueOf(request.getMethod())) {
            case PUT:
            case POST:
            case DELETE:
                log.info("response: [{}]", jsonMapper.writeValueAsString(Optional.ofNullable(result).orElse("")));
                log.info("cost: [{}] {}", cost(cost), costUnit(cost));
                log.info("Ended request");
                log.info("");
                break;
            default:
                break;
        }
        return result;
    }

    /**
     * 秒间隔
     */
    private static final long SECOND_INTERVAL = 1000L;

    /**
     * 秒间隔
     */
    private static final BigDecimal SECOND_INTERVAL_DECIMAL = BigDecimal.valueOf(SECOND_INTERVAL);

    /**
     * 获取耗时时间
     *
     * @param millisecond 耗时毫秒
     * @return 获取耗时时间
     */
    private String cost(Long millisecond) {
        if (SECOND_INTERVAL >= millisecond) {
            return millisecond.toString();
        }
        BigDecimal second = BigDecimal.valueOf(millisecond)
                .divide(SECOND_INTERVAL_DECIMAL, 2, RoundingMode.HALF_UP);
        return second.toString();
    }

    /**
     * 耗时单位
     */
    enum CostUnit {

        /**
         * 毫秒
         */
        ms,

        /**
         * 秒
         */
        s

    }

    /**
     * 获取耗时时间单位
     *
     * @param millisecond 耗时毫秒
     * @return 获取耗时时间单位
     */
    private CostUnit costUnit(long millisecond) {
        return SECOND_INTERVAL >= millisecond ? CostUnit.ms : CostUnit.s;
    }

}
