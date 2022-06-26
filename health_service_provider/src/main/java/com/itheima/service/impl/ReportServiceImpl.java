package com.itheima.service.impl;

import com.itcast.service.ReportService;

import com.itcast.utils.DateUtils;
import com.itheima.mapper.ReportMapper;
import com.itheima.pojo.hotSetmeal;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportMapper reportMapper;
    @Override
    public Map findExcelDatabase() {
        HashMap<String, Object> map = new HashMap<>();
        //  reportDate:null,//报告日期
        map.put("reportDate",new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(DateUtils.getToday()));
        // todayNewMember :0,//今日新增会员数
        int todayNewMember =   reportMapper.findtodayNewMember(new SimpleDateFormat("yyyy-MM-dd").format(DateUtils.getToday()));
        //  totalMember :0,//总会员数
        map.put("todayNewMember",todayNewMember);
        int totalMember = reportMapper.findTotalMember();
        map.put("totalMember",totalMember);
        // thisWeekNewMember :0,//本周新增会员数
        int thisWeekNewMember = reportMapper.findthisWeekNewMember(new SimpleDateFormat("yyyy-MM-dd").format(DateUtils.geLastWeekMonday(new Date())));
        map.put("thisWeekNewMember",thisWeekNewMember);
        // thisMonthNewMember :0,//本月新增会员数
        int thisMonthNewMember = reportMapper.findthisMonthNewMember(new SimpleDateFormat("yyyy-MM-dd").format(DateUtils.getLastMonth(new Date())));
        map.put("thisMonthNewMember",thisMonthNewMember);
        // todayOrderNumber :0,//今日预约数
        int todayOrderNumber = reportMapper.findtodayOrderNumber(DateUtils.DateFormat(DateUtils.getToday()));
        map.put("todayOrderNumber",todayOrderNumber);
        //  todayVisitsNumber :0,//今日到诊数
        int todayVisitsNumber = reportMapper.findtodayVisitsNumber(DateUtils.DateFormat(DateUtils.getToday()));
        map.put("todayVisitsNumber",todayVisitsNumber);
        //   thisWeekOrderNumber :0周,//本预约数
        int thisWeekOrderNumber = reportMapper.findthisWeekOrderNumber(DateUtils.DateFormat(DateUtils.getThisWeekMonday()));
        map.put("thisWeekOrderNumber",thisWeekOrderNumber);
        //  thisWeekVisitsNumber :0,//本周到诊数
        int thisWeekVisitsNumber = reportMapper.findthisWeekVisitsNumber(DateUtils.DateFormat(DateUtils.getThisWeekMonday()));
        map.put("thisWeekVisitsNumber",thisWeekVisitsNumber);
        //  thisMonthOrderNumber :0,//本月预约数
        int thisMonthOrderNumber = reportMapper.findthisMonthOrderNumber(new SimpleDateFormat("yyyy-MM").format(new Date())+"-1",new SimpleDateFormat("yyyy-MM").format(new Date())+"-31");
        map.put("thisMonthOrderNumber",thisMonthOrderNumber);
        //  thisMonthVisitsNumber :0,//本月到诊数
        int thisMonthVisitsNumber = reportMapper.findthisMonthVisitsNumber(new SimpleDateFormat("yyyy-MM").format(new Date())+"-1",new SimpleDateFormat("yyyy-MM").format(new Date())+"-31");
        map.put("thisMonthVisitsNumber",thisMonthVisitsNumber);
        // hotSetmeal :[//显示4个热门套餐
       List<hotSetmeal> hotSetmeal =  reportMapper.findhotSetmeal_four();
       map.put("hotSetmeal",hotSetmeal);
       return map;
    }



    public static void main(String[] args) {
        Date date = DateUtils.geLastWeekMonday(new Date());
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DAY_OF_YEAR,-7);
        Date time = instance.getTime();
        System.out.println("time = " + time);
        System.out.println(DateUtils.getLastDayOfWeek(new Date()));
        String format = new SimpleDateFormat("yyyy/MM/dd").format((DateUtils.getLastDayOfWeek(new Date())));
        System.out.println("format = " + format);
    }
}
