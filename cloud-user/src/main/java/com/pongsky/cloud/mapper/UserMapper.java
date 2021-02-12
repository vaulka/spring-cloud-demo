package com.pongsky.cloud.mapper;

import com.pongsky.cloud.entity.User;
import com.pongsky.cloud.entity.user.dos.UserDo;
import com.pongsky.cloud.entity.user.dto.UserDto;
import org.apache.ibatis.annotations.Insert;
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
     * 根据用户ID查看数据版本号
     *
     * @param userId 用户ID
     * @return 根据用户ID查看数据版本号
     */
    @Select("select u.data_version " +
            "from `user` u " +
            "where u.id = #{userId} ")
    Optional<Long> findDataVersionById(@Param("userId") Long userId);

    /**
     * 根据用户ID查看总数
     *
     * @param userId 用户ID
     * @return 根据用户ID查看总数
     */
    @Select("select count(u.id) " +
            "from `user` u " +
            "where u.id = #{userId} ")
    Integer countById(@Param("userId") Long userId);

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
     * 根据用户ID和数据版本号修改个人信息
     *
     * @param userId      用户ID
     * @param dataVersion 数据版本号
     * @param userDto     修改后的用户信息
     * @return 根据用户ID和数据版本号修改个人信息
     */
    @Update("<script>" +
            "update `user` " +
            "set updated_at = now() " +
            ",data_version = #{dataVersion} + 1 " +
            "<if test = 'data.username != null' >" +
            ",username = #{data.username} " +
            "</if>" +
            "<if test = 'data.password != null' >" +
            ",password = #{data.password} " +
            "</if>" +
            "<if test = 'data.name != null' >" +
            ",name = #{data.name} " +
            "</if>" +
            "<if test = 'data.phone != null' >" +
            ",phone = #{data.phone} " +
            "</if>" +
            "where id = #{userId} " +
            "and data_version = #{dataVersion} " +
            "</script>")
    Integer updateById(@Param("userId") Long userId,
                       @Param("dataVersion") Long dataVersion,
                       @Param("data") UserDto userDto);

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
