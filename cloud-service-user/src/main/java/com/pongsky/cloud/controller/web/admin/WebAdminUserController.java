package com.pongsky.cloud.controller.web.admin;

import com.pongsky.cloud.entity.user.dto.SearchUserDto;
import com.pongsky.cloud.entity.user.dto.UserDisableDto;
import com.pongsky.cloud.entity.user.vo.UserVo;
import com.pongsky.cloud.exception.ValidationException;
import com.pongsky.cloud.model.dto.PageQuery;
import com.pongsky.cloud.model.vo.PageResponse;
import com.pongsky.cloud.response.annotation.ResponseResult;
import com.pongsky.cloud.service.UserService;
import com.pongsky.cloud.utils.jwt.enums.AuthRole;
import com.pongsky.cloud.validator.SearchGroup;
import com.pongsky.cloud.validator.UpdateGroup;
import com.pongsky.cloud.web.request.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户模块
 *
 * @author pengsenhao
 * @create 2021-02-12
 */
@ResponseResult
@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('" + AuthRole.ADMIN_ROLE + "')")
@RequestMapping(value = "/web/admin/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class WebAdminUserController {

    private final UserService userService;

    /**
     * 查看用户
     *
     * @param pageQuery     分页信息
     * @param searchUserDto 搜索信息
     * @return 查看用户
     */
    @GetMapping
    public PageResponse<UserVo> query(@Validated PageQuery pageQuery,
                                      @Validated({SearchGroup.class}) SearchUserDto searchUserDto) {
        return userService.query(pageQuery, searchUserDto);
    }

    /**
     * 禁用/启用用户
     *
     * @param userId         用户ID
     * @param userDisableDto 禁用/启用信息
     * @param request        request
     */
    @PutMapping("/{userId:[0-9]+}/disable")
    public void disable(@PathVariable Long userId,
                        @Validated({UpdateGroup.class}) @RequestBody UserDisableDto userDisableDto,
                        HttpServletRequest request) {
        if (userId.equals(AuthUtils.getAuthUserId(request))) {
            throw new ValidationException("不能禁用/启用自己");
        }
        userService.existsByUserId(userId);
        userService.disable(userId, userDisableDto.getIsDisable());
    }

}
