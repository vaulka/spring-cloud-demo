package com.pongsky.cloud.feign;

import com.pongsky.cloud.config.ServiceConfig;
import com.pongsky.cloud.entity.user.dto.RefreshTokenLoginDto;
import com.pongsky.cloud.entity.user.dto.UserDto;
import com.pongsky.cloud.entity.user.vo.UserVo;
import com.pongsky.cloud.response.GlobalResult;
import com.pongsky.cloud.validator.CreateGroup;
import com.pongsky.cloud.validator.SearchGroup;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author pengsenhao
 * @create 2021-02-14
 */
@FeignClient(value = ServiceConfig.PAYMENT_SERVICE)
public interface PaymentFeign {

    /**
     * 注册
     *
     * @param userDto 注册信息
     * @return 注册
     */
    @PostMapping("/web/user/login/registered")
    GlobalResult<UserVo> registered(@Validated({CreateGroup.class}) @RequestBody UserDto userDto);

    /**
     * 登录
     *
     * @param userDto 登录信息
     * @return 登录
     */
    @PostMapping("/web/user/login")
    GlobalResult<UserVo> login(@Validated({SearchGroup.class}) @RequestBody UserDto userDto);

    /**
     * refresh 登录
     *
     * @param refreshTokenLoginDto 登录信息
     * @return refresh 登录
     */
    @PostMapping("/web/user/login/refresh")
    GlobalResult<UserVo> refreshLogin(@Validated({SearchGroup.class}) @RequestBody RefreshTokenLoginDto refreshTokenLoginDto);

    /**
     * 获取 随机 UUID
     *
     * @return 获取 随机 UUID
     */
    @RequestMapping("/demo/uid")
    GlobalResult<String> uid();

}
