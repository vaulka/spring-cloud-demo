package com.pongsky.cloud.feign;

import com.pongsky.cloud.config.ServiceConfig;
import com.pongsky.cloud.entity.user.dto.RefreshTokenLoginDto;
import com.pongsky.cloud.entity.user.dto.UserDto;
import com.pongsky.cloud.entity.user.vo.UserVo;
import com.pongsky.cloud.feign.impl.AuthFeignServiceImpl;
import com.pongsky.cloud.response.GlobalResult;
import com.pongsky.cloud.validator.CreateGroup;
import com.pongsky.cloud.validator.SearchGroup;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author pengsenhao
 * @create 2021-02-19
 */
@FeignClient(value = ServiceConfig.AUTH_SERVICE, fallback = AuthFeignServiceImpl.class)
public interface AuthFeignService {

    /**
     * URL 前缀
     */
    String URL_PREFIX = "/feign/user/login";

    /**
     * 注册
     *
     * @param userDto 注册信息
     * @return 注册
     */
    @PostMapping(URL_PREFIX + "/registered")
    GlobalResult<UserVo> registered(@Validated({CreateGroup.class}) @RequestBody UserDto userDto);

    /**
     * 登录
     *
     * @param userDto 登录信息
     * @return 登录
     */
    @PostMapping(URL_PREFIX)
    GlobalResult<UserVo> login(@Validated({SearchGroup.class}) @RequestBody UserDto userDto);

    /**
     * refresh 登录
     *
     * @param refreshTokenLoginDto 登录信息
     * @return refresh 登录
     */
    @PostMapping(URL_PREFIX + "/refresh")
    GlobalResult<UserVo> refreshLogin(@Validated({SearchGroup.class}) @RequestBody RefreshTokenLoginDto refreshTokenLoginDto);

}
