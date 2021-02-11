package com.pongsky.cloud.mapper;

import com.pongsky.cloud.entity.user.dos.UserDo;
import com.pongsky.cloud.entity.user.dto.UserDto;
import com.pongsky.cloud.utils.jwt.enums.AuthRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Optional;

/**
 * @author pengsenhao
 * @create 2021-02-11
 */
@Mapper
public interface UserMapper {

    /**
     * 根据用户ID查看个人信息
     *
     * @param userId 用户ID
     * @return 根据用户ID查看个人信息
     */
    @Select("select u.id,u.role,u.name,u.phone " +
            "from `user` u " +
            "where u.id = #{userId} ")
    Optional<UserDo> findById(@Param("userId") Long userId);

    /**
     * 根据用户ID修改个人信息
     *
     * @param userId  用户ID
     * @param userDto 修改后的用户信息
     */
    @Update("update `user` " +
            "set updated_at = now() " +
            "<if test = 'data.name != null' >" +
            ",name = #{data.name} " +
            "</if>" +
            "<if test = 'data.phone != null' >" +
            ",phone = #{data.phone} " +
            "</if>" +
            "where id = #{userId} ")
    void updateById(@Param("userId") Long userId,
                    @Param("data") UserDto userDto);

    /**
     * 根据手机号和角色和用户ID查询总数
     *
     * @param phone  手机号
     * @param role   角色
     * @param userId 用户ID
     * @return 根据手机号和角色和用户ID查询总数
     */
    @Select("select count(u.id) " +
            "from `user` u " +
            "where u.phone = #{phone} " +
            "and u.role = #{role} " +
            "<if test = 'userId != null' >" +
            "and u.id != #{userId} " +
            "</if>")
    long countByPhoneAndRoleAndId(@Param("phone") String phone,
                                  @Param("role") AuthRole role,
                                  @Param("userId") Long userId);

}
