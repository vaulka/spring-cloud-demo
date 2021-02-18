package com.pongsky.cloud.feign;

import com.pongsky.cloud.entity.user.dto.RefreshTokenLoginDto;
import com.pongsky.cloud.entity.user.dto.UserDto;
import com.pongsky.cloud.entity.user.vo.UserVo;
import com.pongsky.cloud.response.GlobalResult;
import org.springframework.stereotype.Component;

/**
 * @author pengsenhao
 * @create 2021-02-17
 */
@Component
public class PaymentFeignServiceImpl implements PaymentFeignService {

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

    @Override
    public GlobalResult<String> uid() {
        return GlobalResult.remoteCallExceptionResult(String.class);
    }

    @Override
    public GlobalResult<String> exception() {
        return GlobalResult.remoteCallExceptionResult(String.class);
    }

}
