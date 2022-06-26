package com.itheima.mapper;

import com.itheima.pojo.OrderSetting;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface orderSettingMapper {

    @Select("select * from t_ordersetting where ORDERDATE = #{orderDate} ")
    OrderSetting findOrderSettingByDate(@Param("orderDate") Date orderDate);

    @Update("update t_ordersetting set NUMBER = NUMBER + #{number} where ORDERDATE = #{orderDate}")
    void updateOrderSettingById(@Param("number") Integer number,@Param("orderDate") String OrderDate);

    @Update("update t_ordersetting set NUMBER = NUMBER + #{number} where ORDERDATE = #{orderDate}")
    void updateOrderSettingById2(@Param("number") Integer number,@Param("orderDate") Date OrderDate);

    @Insert("insert into t_ordersetting values(null,#{orderDate},#{number},0)")
    void AddOrderSetting(OrderSetting orderSetting);

    @Select("select * from t_ordersetting where ORDERDATE >= #{startDate} and ORDERDATE<= #{endDate}")
    List<OrderSetting> GetOneMonthOrderSettings(@Param("startDate")String startDate,@Param("endDate") String endDate);
}
