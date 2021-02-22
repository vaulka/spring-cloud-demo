package com.pongsky.cloud.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * 替换 HttpServletRequest，实现 body 数据多次读取
 *
 * @author pengsenhao
 * @create 2021-02-11
 */
@Slf4j
@Configuration
public class ReplaceStreamFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    /**
     * 上传文件请求头
     */
    private static final List<ContentType> UPLOAD_FILE_CONTENT_TYPE = List.of(
            ContentType.IMAGE_BMP,
            ContentType.IMAGE_GIF,
            ContentType.IMAGE_JPEG,
            ContentType.IMAGE_PNG,
            ContentType.IMAGE_SVG,
            ContentType.IMAGE_TIFF,
            ContentType.IMAGE_WEBP,
            ContentType.MULTIPART_FORM_DATA
    );

    /**
     * 判断是否切换 request
     *
     * @param request request
     * @return 判断是否切换 request
     */
    public boolean isCheckRequest(ServletRequest request) {
        if (request.getContentType() == null) {
            return false;
        }
        return UPLOAD_FILE_CONTENT_TYPE.stream()
                .filter(contentType -> request.getContentType().contains(contentType.getMimeType()))
                .findAny().isEmpty();
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        if (isCheckRequest(request)) {
            chain.doFilter(new RequestWrapper((HttpServletRequest) request), response);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }

}
