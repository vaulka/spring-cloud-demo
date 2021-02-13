package com.pongsky.cloud.mapper;

import com.pongsky.cloud.entity.Payment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author pengsenhao
 * @create 2021-02-11
 */
@Mapper
public interface PaymentMapper {

    /**
     * 保存支付信息
     *
     * @param payment 支付信息
     * @return 保存支付信息
     */
    @Insert("insert `payment`(id,product,serial,data_version,created_at,user_id) " +
            "value(#{data.id},#{data.product},#{data.serial},#{data.dataVersion},#{data.createdAt},#{data.userId}) ")
    Integer save(@Param("data") Payment payment);

    /**
     * 根据序列号和用户ID查询总数
     *
     * @param serial 序列号
     * @param userId 用户ID
     * @return 根据序列号和用户ID查询总数
     */
    @Select("<script>" +
            "select count(p.id) " +
            "from `payment` p " +
            "where p.serial = #{serial} " +
            "<if test = 'userId != null' >" +
            "and p.user_id != #{userId} " +
            "</if>" +
            "</script>")
    Integer countBySerialAndNotUserId(@Param("serial") String serial,
                                      @Param("userId") Long userId);

}
