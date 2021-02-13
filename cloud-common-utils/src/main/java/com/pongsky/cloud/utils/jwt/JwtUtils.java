package com.pongsky.cloud.utils.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.pongsky.cloud.utils.jwt.enums.AuthData;
import com.pongsky.cloud.utils.jwt.enums.AuthRole;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

/**
 * 创建 Token 工具类
 *
 * @author pengsenhao
 * @create 2021-02-11
 */
public class JwtUtils {

    /**
     * 发行人名称
     */
    private static final String ISS = "彭森豪";

    /**
     * 盐值
     */
    public static final Algorithm ALGORITHM = Algorithm.HMAC256("PongSky");

    /**
     * 获取 AccessToken
     *
     * @param id              ID
     * @param role            角色
     * @param active          配置文件
     * @param applicationName 应用名称
     * @return AccessToken
     */
    public static String createAccessToken(String id, String role, String active, String applicationName) {
        return JWT.create()
                .withClaim(AuthData.ID.toString(), id)
                .withClaim(AuthData.ROLE.toString(), role)
                .withClaim(AuthData.ACTIVE.toString(), active)
                .withClaim(AuthData.APPLICATION.toString(), applicationName)
                .withIssuer(ISS)
                .withIssuedAt(new Date())
                .withExpiresAt(DateUtils.addHours(new Date(), 2))
                .sign(ALGORITHM);
    }

    /**
     * 获取 RefreshToken
     *
     * @param id              ID
     * @param active          配置文件
     * @param applicationName 应用名称
     * @return RefreshToken
     */
    public static String createRefreshToken(String id, String active, String applicationName) {
        return JWT.create()
                .withClaim(AuthData.ID.toString(), id)
                .withClaim(AuthData.ACTIVE.toString(), active)
                .withClaim(AuthData.APPLICATION.toString(), applicationName)
                .withIssuer(ISS)
                .withIssuedAt(new Date())
                .withExpiresAt(DateUtils.addDays(new Date(), 7))
                .sign(ALGORITHM);
    }

    /**
     * 直接获取载体（过期不会报错）
     *
     * @param token token
     * @return 载体
     */
    private static DecodedJWT getTokenBody(String token) {
        try {
            return JWT.decode(token);
        } catch (Exception e) {
            throw new JWTDecodeException(e.getLocalizedMessage(), e);
        }
    }

    /**
     * 获取ID
     *
     * @param token token
     * @return 获取ID
     */
    public static Long getId(String token) {
        Claim claim = getTokenBody(token).getClaim(AuthData.ID.toString());
        if (claim.isNull()) {
            throw new JWTDecodeException("访问凭证已失效，请重新登录：缺少 ID");
        }
        return Long.parseLong(claim.asString());
    }

    /**
     * 获取环境
     *
     * @param token token
     * @return 获取环境
     */
    public static String getActive(String token) {
        Claim claim = getTokenBody(token).getClaim(AuthData.ACTIVE.toString());
        if (claim.isNull()) {
            throw new JWTDecodeException("访问凭证已失效，请重新登录：缺少 ACTIVE");
        }
        return claim.asString();
    }

    /**
     * 获取应用
     *
     * @param token token
     * @return 获取应用
     */
    public static String getApplication(String token) {
        Claim claim = getTokenBody(token).getClaim(AuthData.APPLICATION.toString());
        if (claim.isNull()) {
            throw new JWTDecodeException("访问凭证已失效，请重新登录：缺少 APPLICATION");
        }
        return claim.asString();
    }

    /**
     * 获取role
     *
     * @param token token
     * @return 获取role
     */
    public static AuthRole getRole(String token) {
        Claim claim = getTokenBody(token).getClaim(AuthData.ROLE.toString());
        if (claim.isNull()) {
            throw new JWTDecodeException("访问凭证已失效，请重新登录：缺少 ROLE");
        }
        return AuthRole.valueOf(claim.asString());
    }

}
