package com.itheima.mapper;

import com.itheima.pojo.Member;
import com.itheima.pojo.Order;
import com.itheima.pojo.User;
import org.apache.ibatis.annotations.*;

public interface MemberMapper {

    @Select("select * from t_member where IDCARD = #{idCard}")
    Member findUserByIdCard(@Param("idCard") String idCard);

    @Options(useGeneratedKeys = true,keyProperty = "id" ,keyColumn = "id")
    @Insert("insert into t_member values(null,null,#{name},#{sex},#{idCard},#{phoneNumber},#{regTime},null,null,null,null)")
    void AddUMember(Member member);

    @Select("select count(1) from t_ordersetting where RESERVATIONS < NUMBER  and ORDERDATE =#{orderDate} ")
    int CurrentAndreservedReservationCompareByDate(@Param("orderDate")String orderDate);

    @Options(useGeneratedKeys = true,keyProperty = "id" ,keyColumn = "id")
    @Insert("insert into t_order values(null,#{memberId},#{orderDate},#{orderType},#{orderStatus},#{setmealId}) ")
    void AddOrder(Order order);

    @Update("update t_ordersetting set RESERVATIONS = RESERVATIONS+1 where ID = #{sermealId} ")
    void updateSetMealReservationBySermealId(@Param("sermealId")String sermealId);

    @Select("select * from t_order where SETMEAL_ID =#{sermealId} and ORDERDATE= #{orderDate} and MEMBER_ID =#{orderId} ")
    Order findOrderBySetmealIdAndOrderId(@Param("sermealId")String sermealId,@Param("orderId") Integer orderId,@Param("orderDate") String orderDate);

    @Select(" select count(1) from t_member where REGTIME between #{startDate} and #{endDate}  ")
    int getMemberReport(@Param("startDate")String startDate,@Param("endDate") String endDate);

    @Select("select count(1) from t_member where t_member.REGTIME between  #{startDate} and #{endDate}")
    int findMemberCountByDate(@Param("startDate")String startDate,@Param("endDate") String endDate);
}
