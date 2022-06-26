package com.itheima.mapper;

import com.itheima.pojo.hotSetmeal;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ReportMapper {
    @Select("select count(1) from t_member where t_member.REGTIME = #{date}")
    int findtodayNewMember(@Param("date")String date);

    @Select("select count(1) from t_member")
    int findTotalMember();

    @Select("select count(1) from t_member where t_member.REGTIME > #{date}")
    int findthisWeekNewMember(@Param("date")String date);

    @Select("select count(1) from t_member where t_member.REGTIME > #{date}")
    int findthisMonthNewMember(@Param("date")String date);

    @Select("select  t_ordersetting.RESERVATIONS from  t_ordersetting where  t_ordersetting.ORDERDATE = #{date}")
    int findtodayOrderNumber(@Param("date")String date);

    @Select("select count(1) from t_order where t_order.ORDERDATE = #{date} and t_order.ORDERSTATUS = '1' ")
    int findtodayVisitsNumber(@Param("date")String date);
    //   thisWeekOrderNumber :0,//本周预约数
    @Select(" select sum(RESERVATIONS) from t_ordersetting where ORDERDATE > #{date}")
    int findthisWeekOrderNumber(@Param("date")String date);

    //  thisWeekVisitsNumber :0,//本周到诊数
    @Select("select count(1) from t_order where t_order.ORDERDATE >= #{date} and t_order.ORDERSTATUS = '0' ")
    int findthisWeekVisitsNumber(@Param("date")String date);
    // thisMonthOrderNumber :0,//本月预约数
    @Select(" select sum(RESERVATIONS) from t_ordersetting where ORDERDATE >= #{s} and ORDERDATE <= #{s1}")
    int findthisMonthOrderNumber(@Param("s")String s,@Param("s1")String s1);
    //  thisMonthVisitsNumber :0,//本月到诊数
    @Select("select count(1) from t_order where t_order.ORDERDATE >= #{s} and ORDERDATE <= #{s1} and t_order.ORDERSTATUS = '0' ")
    int findthisMonthVisitsNumber(@Param("s")String s,@Param("s1")String s1);

    // 找出四个最畅销的套餐
    @Select(" select tsl.NAME name, count(1) setmeal_count, count(1)/(select count(1) from t_order ) proportion from t_order tor,t_setmeal tsl where tor.SETMEAL_ID = tsl.ID group by tsl.NAME order by count(1) desc limit 0,4 ")
    List<hotSetmeal> findhotSetmeal_four();

}
