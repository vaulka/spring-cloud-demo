package com.pongsky.cloud.controller.web.user;

import com.pongsky.cloud.entity.user.dto.UserDto;
import com.pongsky.cloud.entity.user.vo.UserVo;
import com.pongsky.cloud.response.annotation.ResponseResult;
import com.pongsky.cloud.service.UserService;
import com.pongsky.cloud.utils.jwt.enums.AuthRole;
import com.pongsky.cloud.validator.CreateGroup;
import com.pongsky.cloud.validator.SearchGroup;
import com.pongsky.cloud.validator.UpdateGroup;
import com.pongsky.cloud.web.request.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author pengsenhao
 * @create 2021-02-11
 */
@ResponseResult
@RestController
@RequiredArgsConstructor
@RequestMapping("/web/user")
public class WebUserController {

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
    @PostMapping("/login")
    public UserVo login(@Validated({SearchGroup.class}) @RequestBody UserDto userDto) {
        return userService.login(userDto);
    }

    /**
     * 查看个人信息
     *
     * @param request request
     * @return 查看个人信息
     */
    @PreAuthorize("hasRole('" + AuthRole.USER_ROLE + "')")
    @GetMapping("/info")
    public UserVo queryInfo(HttpServletRequest request) {
        Long userId = AuthUtils.getAuthUserId(request);
        return userService.queryInfo(userId);
    }

    /**
     * 修改个人信息
     *
     * @param request request
     * @param userDto 修改后的用户信息
     */
    @PreAuthorize("hasRole('" + AuthRole.USER_ROLE + "')")
    @PutMapping("/info")
    public void modifyInfo(HttpServletRequest request,
                           @Validated({UpdateGroup.class}) @RequestBody UserDto userDto) {
        Long userId = AuthUtils.getAuthUserId(request);
        userService.existsByUserId(userId);
        userService.existsByPhoneAndRoleAndNotUserId(userDto.getUsername(), userId);
        userService.modifyInfo(userId, userDto);
    }

}
