package com.pongsky.cloud.mapper;

import com.pongsky.cloud.entity.User;
import com.pongsky.cloud.entity.user.dos.UserDo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

/**
 * @author pengsenhao
 * @create 2021-02-27
 */
@Mapper
public interface UserMapper {

    /**
     * 保存用户信息
     *
     * @param user 用户信息
     * @return 保存用户信息
     */
    @Insert("insert `user`(id,role,username,password,name,phone,is_disable,data_version,created_at) " +
            "value(#{data.id},#{data.role},#{data.username},#{data.password},#{data.name},#{data.phone},#{data.isDisable},#{data.dataVersion},#{data.createdAt})")
    Integer save(@Param("data") User user);

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 根据用户名查询用户信息
     */
    @Select("select u.id,u.role,u.username,u.password,u.name,u.phone " +
            "from `user` u " +
            "where u.username = #{username} ")
    Optional<UserDo> findByUsername(@Param("username") String username);

    /**
     * 根据用户ID查看个人信息
     *
     * @param userId 用户ID
     * @return 根据用户ID查看个人信息
     */
    @Select("select u.id,u.role,u.username,u.name,u.phone " +
            "from `user` u " +
            "where u.id = #{userId} ")
    Optional<UserDo> findById(@Param("userId") Long userId);

    /**
     * 根据用户名和用户ID查询总数
     *
     * @param username 用户名
     * @param userId   用户ID
     * @return 根据用户名和用户ID查询总数
     */
    @Select("<script>" +
            "select count(u.id) " +
            "from `user` u " +
            "where u.username = #{username} " +
            "<if test = 'userId != null' >" +
            "and u.id != #{userId} " +
            "</if>" +
            "</script>")
    Integer countByUsernameAndNotId(@Param("username") String username,
                                    @Param("userId") Long userId);

}
