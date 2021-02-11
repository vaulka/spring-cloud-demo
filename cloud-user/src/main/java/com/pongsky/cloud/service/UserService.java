package com.pongsky.cloud.service;

import com.pongsky.cloud.entity.User;
import com.pongsky.cloud.entity.user.dos.UserDo;
import com.pongsky.cloud.entity.user.dto.UserDto;
import com.pongsky.cloud.entity.user.vo.UserVo;
import com.pongsky.cloud.exception.DoesNotExistException;
import com.pongsky.cloud.exception.ValidationException;
import com.pongsky.cloud.mapper.UserMapper;
import com.pongsky.cloud.repository.UserRepository;
import com.pongsky.cloud.utils.jwt.enums.AuthRole;
import com.pongsky.cloud.utils.snowflake.SnowFlakeUtils;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author pengsenhao
 * @create 2021-02-11
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final MapperFacade mapperFacade;
    private final UserRepository userRepository;
    private final SnowFlakeUtils snowFlakeUtils;

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
                .setIsDisable(0)
                .setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
        return mapperFacade.map(user, UserVo.class);
    }

    /**
     * 查看个人信息
     *
     * @param userId 用户ID
     * @return 查看个人信息
     */
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public UserVo queryInfo(Long userId) {
        UserDo userDo = userMapper.findById(userId)
                .orElseThrow(() -> new DoesNotExistException("用户不存在"));
        return mapperFacade.map(userDo, UserVo.class);
    }

    /**
     * 修改个人信息
     *
     * @param userId  用户ID
     * @param userDto 修改后的用户信息
     */
    @Transactional(rollbackFor = Exception.class)
    public void modifyInfo(Long userId, UserDto userDto) {
        userMapper.updateById(userId, userDto);
    }

    /**
     * 查询用户是否存在
     *
     * @param userId 用户ID
     */
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public void existsByUserId(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new DoesNotExistException("用户不存在");
        }
    }

    /**
     * 根据手机号和角色和用户ID检验是否存在
     *
     * @param phone  手机号
     * @param role   角色
     * @param userId 用户ID
     */
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public void existsByPhoneAndRoleAndUserId(String phone, AuthRole role, Long userId) {
        if (StringUtils.isBlank(phone)) {
            throw new ValidationException("手机号不能为空");
        }
        long count = userMapper.countByPhoneAndRoleAndId(phone, role, userId);
        if (count > 0L) {
            throw new ValidationException("手机号 " + phone + " 已存在，请更换其他手机号重试");
        }
    }

}
