package com.pongsky.cloud.web.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pongsky.cloud.exception.RemoteCallException;
import com.pongsky.cloud.response.GlobalResult;
import com.pongsky.cloud.response.enums.ResultCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 远程调用耗时打印、校验远程调用响应数据
 *
 * @author pengsenhao
 * @create 2021-02-14
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class RemoteCallAround {

    private final ObjectMapper jsonMapper;

    @Around("execution(public * com.pongsky.cloud.feign..*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes requestAttributes
                = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        }
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        log.info("Started remote call request");
        log.info("response is [{}]", jsonMapper.writeValueAsString(result));
        log.info("cost [{}] ms", System.currentTimeMillis() - start);
        log.info("Ended remote call request");
        if (result instanceof GlobalResult) {
            GlobalResult<?> globalResult = (GlobalResult<?>) result;
            if (!globalResult.getCode().equals(ResultCode.Success.getCode())) {
                throw new RemoteCallException(globalResult);
            }
        }
        return result;
    }

}
