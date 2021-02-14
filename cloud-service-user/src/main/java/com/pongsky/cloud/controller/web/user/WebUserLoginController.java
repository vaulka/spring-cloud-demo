package com.pongsky.cloud.controller.web.user;

import com.pongsky.cloud.entity.user.dto.RefreshTokenLoginDto;
import com.pongsky.cloud.entity.user.dto.UserDto;
import com.pongsky.cloud.entity.user.vo.UserVo;
import com.pongsky.cloud.response.annotation.ResponseResult;
import com.pongsky.cloud.service.UserService;
import com.pongsky.cloud.utils.jwt.JwtUtils;
import com.pongsky.cloud.validator.CreateGroup;
import com.pongsky.cloud.validator.SearchGroup;
import com.pongsky.cloud.web.request.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录模块
 *
 * @author pengsenhao
 * @create 2021-02-11
 */
@ResponseResult
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/web/user/login", produces = MediaType.APPLICATION_JSON_VALUE)
public class WebUserLoginController {

    private final UserService userService;

    /**
     * 注册
     *
     * @param userDto 注册信息
     * @return 注册
     */
    @PostMapping("/registered")
    public UserVo registered(@Validated({CreateGroup.class}) @RequestBody UserDto userDto) {
        userService.existsByPhoneAndRoleAndNotUserId(userDto.getUsername(), null);
        return userService.registered(userDto);
    }

    /**
     * 登录
     *
     * @param userDto 登录信息
     * @return 登录
     */
    @PostMapping
    public UserVo login(@Validated({SearchGroup.class}) @RequestBody UserDto userDto) {
        return userService.login(userDto);
    }

    /**
     * refresh 登录
     *
     * @param refreshTokenLoginDto 登录信息
     * @return refresh 登录
     */
    @PostMapping("/refresh")
    public UserVo refreshLogin(@Validated({SearchGroup.class}) @RequestBody RefreshTokenLoginDto refreshTokenLoginDto) {
        if (AuthUtils.verifyToken(refreshTokenLoginDto.getRefreshToken())) {
            throw new AccessDeniedException("refreshToken 已过期，请重新登录");
        }
        return userService.refreshLogin(JwtUtils.getId(refreshTokenLoginDto.getRefreshToken()));
    }

}
