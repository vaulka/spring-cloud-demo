package com.pongsky.cloud.web.filter;

import lombok.extern.slf4j.Slf4j;
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
    private static final List<String> UPLOAD_FILE_CONTENT_TYPE = List.of(
            "image",
            "multipart/form-data"
    );

    /**
     * 判断是否切换request
     *
     * @param request request
     * @return 判断是否切换request
     */
    public boolean isCheckRequest(ServletRequest request) {
        if (request.getContentType() == null) {
            return false;
        }
        for (String type : UPLOAD_FILE_CONTENT_TYPE) {
            if (request.getContentType().startsWith(type)) {
                // binary 形式上传文件，不读取body数据，并且不切换request
                return false;
            }
        }
        return true;
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
