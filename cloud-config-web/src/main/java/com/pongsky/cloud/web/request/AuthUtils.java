package com.pongsky.cloud.web.request;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.pongsky.cloud.config.SystemConfig;
import com.pongsky.cloud.utils.jwt.JwtUtils;
import com.pongsky.cloud.utils.jwt.dto.AuthInfo;
import org.springframework.security.authentication.BadCredentialsException;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * 鉴权工具类
 *
 * @author pengsenhao
 * @create 2021-02-11
 */
public class AuthUtils {

    /**
     * 获取用户信息
     *
     * @param request request
     * @return 获取用户信息
     */
    public static AuthInfo getUser(HttpServletRequest request) {
        try {
            AuthInfo authInfo = getAuthUser(request);
            return verifyToken(request) ? AuthInfo.PUBLIC_INFO : authInfo;
        } catch (Exception e) {
            return AuthInfo.PUBLIC_INFO;
        }
    }

    /**
     * 获取通过鉴权的用户信息
     *
     * @param request request
     * @return 获取通过鉴权的用户信息
     */
    public static AuthInfo getAuthUser(HttpServletRequest request) {
        String authorization = getAuthorization(request);
        return new AuthInfo()
                .setId(JwtUtils.getId(authorization))
                .setRole(JwtUtils.getRole(authorization));
    }

    /**
     * 获取通过鉴权的用户ID
     *
     * @param request request
     * @return 获取通过鉴权的用户ID
     */
    public static Long getAuthUserId(HttpServletRequest request) {
        return getAuthUser(request).getId();
    }

    /**
     * 校验过期时间（过期将会报错）
     *
     * @param request request
     * @return 校验过期时间（过期将会报错）
     */
    public static boolean verifyToken(HttpServletRequest request) {
        try {
            JWT.require(JwtUtils.ALGORITHM).build().verify(getAuthorization(request));
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * 校验过期时间（过期将会报错）
     *
     * @param token token
     * @return 校验过期时间（过期将会报错）
     */
    public static boolean verifyToken(String token) {
        try {
            JWT.require(JwtUtils.ALGORITHM).build().verify(token.replace(JwtUtils.TOKEN_PREFIX, ""));
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * 获取 Token
     *
     * @param request request
     * @return Token
     */
    public static String getAuthorization(HttpServletRequest request) {
        String authorization = Optional.ofNullable(request.getHeader(JwtUtils.AUTHORIZATION))
                .orElseThrow(() -> new BadCredentialsException("缺少访问凭证，请重新登录"))
                .replace(JwtUtils.TOKEN_PREFIX, "");
        try {
            String active = JwtUtils.getActive(authorization);
            if (!active.equals(SystemConfig.getActive())) {
                throw new BadCredentialsException("访问凭证已失效，请重新登录：错误 ACTIVE");
            }
            String application = JwtUtils.getApplication(authorization);
            if (!application.equals(SystemConfig.getApplicationName())) {
                throw new BadCredentialsException("访问凭证已失效，请重新登录：错误 APPLICATION");
            }
        } catch (JWTDecodeException e) {
            throw new BadCredentialsException("访问凭证解析失败，请重新登录");
        }
        return authorization;
    }

}
