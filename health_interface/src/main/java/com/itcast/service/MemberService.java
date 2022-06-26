package com.itcast.service;

import com.itheima.pojo.Member;
import com.itheima.pojo.Order;

import java.util.List;
import java.util.Map;

public interface MemberService {
    int findUserByIdCard(String idCard);

    void AddUMember(Member member);

    int CurrentAndreservedReservationCompareByDate(String orderDate);

    void AddOrder(Order order);

    void updateSetMealReservationBySermealId(String sermealId);

    Order findOrderBySetmealIdAndOrderId(String sermealId, Integer orderId, String orderDate);

    Map<String, List> getMemberReport();

    Map<String, List> getMemberReportX(String startTimex, String endTimex) throws Exception;
}
