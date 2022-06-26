package com.itheima.service.impl;

import com.itcast.service.setMealService;
import com.itheima.pojo.Setmeal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:a*.xml")
public class setMealServiceImplTest {

    @Autowired
    private setMealService setMealService;

    @Test
    public void findSetMealDetail() {
        Setmeal setmeal = setMealService.findSetMealDetail(1);
        System.out.println("setmeal = " + setmeal);
    }
}