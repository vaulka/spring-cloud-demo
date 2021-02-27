package com.pongsky.cloud.service;

import com.pongsky.cloud.entity.User;
import com.pongsky.cloud.entity.user.dos.UserDo;
import com.pongsky.cloud.entity.user.dto.UserDto;
import com.pongsky.cloud.entity.user.vo.UserVo;
import com.pongsky.cloud.exception.DoesNotExistException;
import com.pongsky.cloud.exception.InsertException;
import com.pongsky.cloud.exception.ValidationException;
import com.pongsky.cloud.mapper.UserMapper;
import com.pongsky.cloud.utils.jwt.JwtUtils;
import com.pongsky.cloud.utils.jwt.enums.AuthRole;
import com.pongsky.cloud.utils.snowflake.SnowFlakeUtils;
import com.pongsky.cloud.web.request.SystemConfigUtils;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author pengsenhao
 * @create 2021-02-27
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final MapperFacade mapperFacade;
    private final SnowFlakeUtils snowFlakeUtils;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 注册
     *
     * @param userDto 注册信息
     * @return 注册
     */
    @Transactional(rollbackFor = Exception.class)
    public UserVo registered(UserDto userDto) {
        User user = mapperFacade.map(userDto, User.class)
                .setId(snowFlakeUtils.getId())
                .setRole(AuthRole.USER)
                .setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()))
                .setIsDisable(0)
                .setDataVersion(0L)
                .setCreatedAt(LocalDateTime.now());
        InsertException.validation("用户信息保存失败", userMapper.save(user));
        return mapperFacade.map(user, UserVo.class);
    }

    /**
     * 登录
     *
     * @param userDto 登录信息
     * @return 登录
     */
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public UserVo login(UserDto userDto) {
        UserDo user = userMapper.findByUsername(userDto.getUsername())
                .orElseThrow(() -> new DoesNotExistException("用户不存在"));
        if (!bCryptPasswordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            throw new ValidationException("用户名或密码错误");
        }
        return getAuthorization(user);
    }

    /**
     * refresh 登录
     *
     * @param userId 用户ID
     * @return refresh 登录
     */
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public UserVo refreshLogin(Long userId) {
        UserDo user = userMapper.findById(userId)
                .orElseThrow(() -> new DoesNotExistException("用户不存在"));
        return getAuthorization(user);
    }

    /**
     * 获取访问凭证
     *
     * @param user 用户信息
     * @return 获取访问凭证
     */
    private UserVo getAuthorization(UserDo user) {
        return mapperFacade.map(user, UserVo.class)
                .setAuthorization(JwtUtils.createAccessToken(user.getId().toString(),
                        user.getRole().toString(), SystemConfigUtils.getActive(), SystemConfigUtils.getApplicationName()))
                .setRefreshToken(JwtUtils.createRefreshToken(user.getId().toString(),
                        SystemConfigUtils.getActive(), SystemConfigUtils.getApplicationName()));
    }

    /**
     * 根据用户名和用户ID检验是否存在
     *
     * @param username 用户名
     * @param userId   用户ID
     */
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public void existsByPhoneAndRoleAndNotUserId(String username, Long userId) {
        if (StringUtils.isBlank(username)) {
            return;
        }
        Integer count = userMapper.countByUsernameAndNotId(username, userId);
        if (count > 0) {
            throw new ValidationException("用户名 " + username + " 已存在，请更换其他手机号重试");
        }
    }

}
