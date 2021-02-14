package com.pongsky.cloud.controller.web.user;

import com.pongsky.cloud.entity.user.dto.UserDto;
import com.pongsky.cloud.entity.user.vo.UserVo;
import com.pongsky.cloud.response.annotation.ResponseResult;
import com.pongsky.cloud.service.UserService;
import com.pongsky.cloud.utils.jwt.enums.AuthRole;
import com.pongsky.cloud.validator.UpdateGroup;
import com.pongsky.cloud.web.request.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 账号信息模块
 *
 * @author pengsenhao
 * @create 2021-02-11
 */
@ResponseResult
@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('" + AuthRole.USER_ROLE + "')")
@RequestMapping(value = "/web/user/account", produces = MediaType.APPLICATION_JSON_VALUE)
public class WebUserAccountController {

    private final UserService userService;

    /**
     * 查看个人信息
     *
     * @param request request
     * @return 查看个人信息
     */
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
    @PutMapping("/info")
    public void modifyInfo(HttpServletRequest request,
                           @Validated({UpdateGroup.class}) @RequestBody UserDto userDto) {
        Long userId = AuthUtils.getAuthUserId(request);
        userService.existsByUserId(userId);
        userService.existsByPhoneAndRoleAndNotUserId(userDto.getUsername(), userId);
        userService.modifyInfo(userId, userDto);
    }

}
