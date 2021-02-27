package com.pongsky.cloud.feign.impl;

import com.pongsky.cloud.entity.user.dto.RefreshTokenLoginDto;
import com.pongsky.cloud.entity.user.dto.UserDto;
import com.pongsky.cloud.entity.user.vo.UserVo;
import com.pongsky.cloud.feign.AuthFeignService;
import com.pongsky.cloud.response.GlobalResult;
import org.springframework.stereotype.Component;

/**
 * @author pengsenhao
 * @create 2021-02-19
 */
@Component
public class AuthFeignServiceImpl implements AuthFeignService {


    @Override
    public GlobalResult<UserVo> registered(UserDto userDto) {
        return GlobalResult.remoteCallExceptionResult(UserVo.class);
    }

    @Override
    public GlobalResult<UserVo> login(UserDto userDto) {
        return GlobalResult.remoteCallExceptionResult(UserVo.class);
    }

    @Override
    public GlobalResult<UserVo> refreshLogin(RefreshTokenLoginDto refreshTokenLoginDto) {
        return GlobalResult.remoteCallExceptionResult(UserVo.class);
    }

}
