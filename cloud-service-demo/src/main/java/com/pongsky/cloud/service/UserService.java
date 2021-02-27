package com.pongsky.cloud.service;

import com.pongsky.cloud.entity.user.dto.RefreshTokenLoginDto;
import com.pongsky.cloud.entity.user.dto.UserDto;
import com.pongsky.cloud.entity.user.vo.UserVo;
import com.pongsky.cloud.feign.AuthFeignService;
import com.pongsky.cloud.response.GlobalResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author pengsenhao
 * @create 2021-02-19
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final AuthFeignService authFeignService;

    /**
     * 注册
     *
     * @param userDto 注册信息
     * @return 注册
     */
    public UserVo registered(UserDto userDto) {
        return GlobalResult.validation(authFeignService.registered(userDto)).getData();
    }

    /**
     * 登录
     *
     * @param userDto 登录信息
     * @return 登录
     */
    public UserVo login(UserDto userDto) {
        return GlobalResult.validation(authFeignService.login(userDto)).getData();
    }

    /**
     * refresh 登录
     *
     * @param refreshTokenLoginDto 登录信息
     * @return refresh 登录
     */
    public UserVo refreshLogin(RefreshTokenLoginDto refreshTokenLoginDto) {
        return GlobalResult.validation(authFeignService.refreshLogin(refreshTokenLoginDto)).getData();
    }

}
