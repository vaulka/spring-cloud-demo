package com.pongsky.cloud.web.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pongsky.cloud.utils.jwt.dto.AuthInfo;
import com.pongsky.cloud.web.request.AuthUtils;
import com.pongsky.cloud.web.request.IpUtils;
import com.pongsky.cloud.web.request.RequestUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

/**
 * 请求参数、响应数据 统一日志打印
 *
 * @author pengsenhao
 * @create 2021-02-11
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class RestControllerAround {

    private final ObjectMapper jsonMapper;

    /**
     * 需要记录的请求方法集合
     */
    private static final List<String> API_REQUEST_METHODS = List.of(
            PutMapping.class.getSimpleName(),
            PostMapping.class.getSimpleName(),
            DeleteMapping.class.getSimpleName()
    );

    @Around("execution(public * com.pongsky.cloud.controller..*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes requestAttributes
                = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        }
        HttpServletRequest request = requestAttributes.getRequest();
        AuthInfo authInfo = AuthUtils.getUser(request);
        String ip = IpUtils.getIp(request);
        String userAgent = request.getHeader("user-agent");
        String referer = request.getHeader("referer");
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long count = API_REQUEST_METHODS.stream()
                .filter(method -> request.getAttribute(method) != null)
                .count();
        if (count > 0) {
            log.info("");
            log.info("Started request");
            log.info("IP [{}] userAgent [{}] referer [{}]", ip, userAgent, referer);
            log.info("ID [{}] ROLE [{}] methodURL [{}] methodType [{}] params [{}] body [{}]",
                    authInfo.getId(),
                    authInfo.getRole(),
                    request.getRequestURI(),
                    request.getMethod(),
                    Optional.ofNullable(request.getQueryString()).orElse(""),
                    RequestUtils.getBody(request));
            log.info("response is [{}]", jsonMapper.writeValueAsString(Optional.ofNullable(result).orElse("")));
            log.info("cost [{}] ms",
                    System.currentTimeMillis() - start);
            log.info("Ended request");
            log.info("");
        }
        return result;
    }

}
