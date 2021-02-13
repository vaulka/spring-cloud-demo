package com.pongsky.cloud.web.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 注解赋值
 *
 * @author pengsenhao
 * @create 2021-02-11
 */
@Aspect
@Component
public class AnnotationAround {

    @Before("execution(public * com.pongsky.cloud.controller..*.*(..))")
    public void before(JoinPoint point) {
        ServletRequestAttributes requestAttributes
                = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return;
        }
        HttpServletRequest request = requestAttributes.getRequest();
        MethodSignature sign = (MethodSignature) point.getSignature();
        Method method = sign.getMethod();
        for (Annotation annotation : method.getAnnotations()) {
            String simpleName = annotation.annotationType().getSimpleName();
            request.setAttribute(simpleName, simpleName);
        }
        for (Annotation annotation : point.getTarget().getClass().getAnnotations()) {
            String simpleName = annotation.annotationType().getSimpleName();
            request.setAttribute(simpleName, simpleName);
        }
    }

}
