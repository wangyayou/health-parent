package com.itheima.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

public interface orderInfoMapper {

    @Select("select tm.name member ,ts.name setmeal ,tro.orderDate,tro.orderType from t_order tro ,t_setmeal ts , t_member tm where tro.id = #{orderId} and tro.MEMBER_ID = tm.id and tro.SETMEAL_ID=ts.id ")
    Map<String, String> findOrderInfoByOrderId(@Param("orderId")String orderId);
}
