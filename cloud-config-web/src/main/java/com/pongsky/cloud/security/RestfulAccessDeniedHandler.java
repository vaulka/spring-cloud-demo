package com.pongsky.cloud.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pongsky.cloud.response.GlobalResult;
import com.pongsky.cloud.response.enums.ResultCode;
import com.pongsky.cloud.utils.ip.IpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author pengsenhao
 * @create 2021-03-02
 */
@Component
@RequiredArgsConstructor
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper jsonMapper;

    /**
     * 接口响应结果信息
     */
    private static final String MESSAGE = "没有访问权限";

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e)
            throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        GlobalResult<Object> result = new GlobalResult<>(IpUtils.getIp(request), ResultCode.AccessDeniedException,
                request.getRequestURI(), e.getClass().getName()).setMessage(MESSAGE);
        InputStream inputStream = new ByteArrayInputStream(jsonMapper.writeValueAsString(result).getBytes(StandardCharsets.UTF_8));
        inputStream.transferTo(response.getOutputStream());
    }

}