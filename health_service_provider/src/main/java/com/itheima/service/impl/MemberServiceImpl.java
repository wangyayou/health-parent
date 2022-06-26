package com.itheima.service.impl;

import com.itcast.service.MemberService;
import com.itcast.utils.DateUtils;
import com.itheima.mapper.MemberMapper;
import com.itheima.pojo.Member;
import com.itheima.pojo.Order;
import com.itheima.pojo.User;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Transactional
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;
    @Override
    public int  findUserByIdCard(String idCard) {
        Member member =  memberMapper.findUserByIdCard(idCard);
        if (member==null){
            return -1;
        }else {
            return member.getId();
        }
    }

    @Override
    public void AddUMember(Member member) {
        memberMapper.AddUMember(member);
    }

    @Override
    public int CurrentAndreservedReservationCompareByDate(String orderDate) {
        return memberMapper.CurrentAndreservedReservationCompareByDate(orderDate) ;
    }

    @Override
    public void AddOrder(Order order) {
        memberMapper.AddOrder(order);
    }

    @Override
    public void updateSetMealReservationBySermealId(String sermealId) {
        memberMapper.updateSetMealReservationBySermealId(sermealId);
    }

    @Override
    public Order findOrderBySetmealIdAndOrderId(String sermealId, Integer orderId, String orderDate) {

        return memberMapper.findOrderBySetmealIdAndOrderId(sermealId,orderId, orderDate);
    }

    @Override
    public Map<String, List> getMemberReport() {
        HashMap<String, List> map = new HashMap<>();
        ArrayList<Integer> member = new ArrayList<>();
        List<String> months = DateUtils.getLast12YearAndMonth();
        String startDate="-1";
        String endDate="-31";
        for (String s : months) {
         int count =  memberMapper.getMemberReport(s+startDate,s+endDate);
            member.add(count);
        }
        System.out.println("months = " + months);
        System.out.println("member = " + member);
        map.put("months",months);
        map.put("member",member);
        return map;
    }

    @Override
    public Map<String, List> getMemberReportX(String startTimex, String endTimex) throws Exception {
        List<String> monthBetween = DateUtils.getMonthBetween(startTimex, endTimex, "yyyy-MM");
        ArrayList<Integer> list = new ArrayList<>();
        HashMap<String, List> map = new HashMap<>();
        for (String date : monthBetween) {
            int count = memberMapper.findMemberCountByDate(date + "-1", date + "-31");
            list.add(count);
            map.put("month", monthBetween);
            map.put("count", list);
        }
        return map;
    }

    public static void main(String[] args) {
    }
}