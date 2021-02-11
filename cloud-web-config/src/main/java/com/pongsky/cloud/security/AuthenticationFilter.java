package com.pongsky.cloud.security;

import com.pongsky.cloud.utils.jwt.dto.AuthInfo;
import com.pongsky.cloud.web.request.AuthUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * 鉴权拦截器
 *
 * @author pengsenhao
 * @create 2021-02-11
 */
@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return false;
    }

    /**
     * 鉴权前缀
     */
    private static final String ROLE_STARTS_WITH = "ROLE_";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        AuthInfo authInfo = AuthUtils.getUser(request);
        PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(
                authInfo,
                null,
                Collections.singleton(new SimpleGrantedAuthority(ROLE_STARTS_WITH + authInfo.getRole())));
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

}
