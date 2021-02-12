package com.pongsky.cloud.controller.web.admin;

import com.pongsky.cloud.entity.user.dto.SearchUserDto;
import com.pongsky.cloud.entity.user.vo.UserVo;
import com.pongsky.cloud.model.dto.PageQuery;
import com.pongsky.cloud.model.vo.PageResponse;
import com.pongsky.cloud.response.annotation.ResponseResult;
import com.pongsky.cloud.service.UserService;
import com.pongsky.cloud.utils.jwt.enums.AuthRole;
import com.pongsky.cloud.validator.SearchGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户模块
 *
 * @author pengsenhao
 * @create 2021-02-12
 */
@ResponseResult
@RestController
@RequiredArgsConstructor
@RequestMapping("/web/admin/user")
@PreAuthorize("hasRole('" + AuthRole.ADMIN_ROLE + "')")
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
                                      @Validated(SearchGroup.class) SearchUserDto searchUserDto) {
        return userService.query(pageQuery, searchUserDto);
    }

}
