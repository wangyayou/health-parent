package com.itcast.service;

import com.itheima.pojo.Setmeal;

import java.util.Map;

public interface orderInfoService {
    Setmeal findSetMealById(Integer id);

    Map<String, String> findOrderInfoByOrderId(String orderId);
}
