package com.itheima.service.impl;

import com.itcast.service.orderSettingService;
import com.itheima.mapper.orderSettingMapper;
import com.itheima.pojo.OrderSetting;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Service
public class orderSettingServiceImpl implements orderSettingService {

    @Autowired
    private orderSettingMapper orderSettingMapper;

    @Override  // importOrderSettings
    public void importOrderSettings(ArrayList<OrderSetting> orderSettingList) {
        System.out.println("$$$$$$$$$$$$$$$$$$$$$"+orderSettingList);
        if (orderSettingList.size()>0){
            for (OrderSetting orderSetting : orderSettingList) {
                if (orderSettingMapper.findOrderSettingByDate(orderSetting.getOrderDate())!=null){
                    System.out.println("更新了数据************************************************88");
                    String date = (orderSetting.getOrderDate().getYear()+1900)+"-"+(orderSetting.getOrderDate().getMonth()+1)+"-"+orderSetting.getOrderDate().getDate();
                    System.out.println("date = " + date);
                    orderSettingMapper.updateOrderSettingById(orderSetting.getNumber(),date);
//                    orderSettingMapper.updateOrderSettingById2(orderSetting.getNumber(),orderSetting.getOrderDate());
                }else {
                    System.out.println("_____+++++____+++++更新了数据************************************************88");
                    orderSettingMapper.AddOrderSetting(orderSetting);
                }
            }
        }
    }

    @Override
    public List<Map<String, Object>> GetOneMonthOrderSettings(String startDate, String endDate) {
      List<OrderSetting> orderList = orderSettingMapper.GetOneMonthOrderSettings(startDate,endDate);
      ArrayList<Map<String, Object>> mapList = new ArrayList<>();
        for (OrderSetting orderSetting : orderList) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("date",orderSetting.getOrderDate().getDate());
            map.put("number",orderSetting.getNumber());
            map.put("reservations",orderSetting.getReservations());
            mapList.add(map);
        }
        return mapList;
    }

    @Override
    public void AddOrderSettingReservations(Integer num, String dateParam) {
        orderSettingMapper.updateOrderSettingById(num,dateParam);
    }
}
