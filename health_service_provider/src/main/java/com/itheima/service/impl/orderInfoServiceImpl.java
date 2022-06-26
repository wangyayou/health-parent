package com.itheima.service.impl;

import com.itcast.service.orderInfoService;
import com.itheima.mapper.orderInfoMapper;
import com.itheima.mapper.setMealMapper;
import com.itheima.pojo.Setmeal;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@Service
public class orderInfoServiceImpl implements orderInfoService {

    @Autowired
    private orderInfoMapper orderInfoMapper;

   @Autowired
    private setMealMapper setMealMapper;

    @Override
    public Setmeal findSetMealById(Integer id) {
        return  setMealMapper.findSetMealDetail(id);
    }

    @Override
    public Map<String, String> findOrderInfoByOrderId(String orderId) {
        return orderInfoMapper.findOrderInfoByOrderId(orderId);
    }
}
