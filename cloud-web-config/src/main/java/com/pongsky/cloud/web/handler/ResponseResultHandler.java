package com.pongsky.cloud.web.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pongsky.cloud.response.ErrorResult;
import com.pongsky.cloud.response.SuccessResult;
import com.pongsky.cloud.response.annotation.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 接口响应体处理器
 *
 * @author pengsenhao
 * @create 2021-02-11
 */
@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {

    private final ObjectMapper jsonMapper;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        HttpServletRequest httpServletRequest =
                ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        // 判断是否是错误
        if (body instanceof ErrorResult) {
            return body;
        }
        // 判断是否全局响应数据
        if (httpServletRequest.getAttribute(ResponseResult.class.getSimpleName()) != null) {
            if (body instanceof String) {
                try {
                    return jsonMapper.writeValueAsString(new SuccessResult(body));
                } catch (JsonProcessingException e) {
                    log.error(e.getLocalizedMessage());
                    return new SuccessResult(body);
                }
            } else {
                return new SuccessResult(body);
            }
        }
        return body;
    }

}