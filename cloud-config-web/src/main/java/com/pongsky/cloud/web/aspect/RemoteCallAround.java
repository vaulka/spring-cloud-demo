package com.pongsky.cloud.web.aspect;

import com.pongsky.cloud.exception.RemoteCallException;
import com.pongsky.cloud.response.GlobalResult;
import com.pongsky.cloud.response.enums.ResultCode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 校验远程调用响应数据
 *
 * @author pengsenhao
 * @create 2021-02-14
 */
@Aspect
@Component
public class RemoteCallAround {

    @Around("execution(public * com.pongsky.cloud.feign..*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes requestAttributes
                = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        }
        Object result = joinPoint.proceed();
        if (result instanceof GlobalResult) {
            GlobalResult<?> globalResult = (GlobalResult<?>) result;
            if (!globalResult.getCode().equals(ResultCode.Success.getCode())) {
                throw new RemoteCallException(globalResult);
            }
        }
        return result;
    }

}
