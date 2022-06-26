package com.mobile.health.controller;

import com.itcast.entity.QueryPageBean;
import com.itcast.entity.Result;
import com.itcast.resources.MessageConstant;
import com.itcast.service.setMealService;
import com.itheima.pojo.Setmeal;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/setmeal")
public class setMealsController {

    @Reference
    private setMealService setMealsService;

    @RequestMapping("/findAllSetmeal.do")
    public Result findAllSetmeal(@RequestBody QueryPageBean queryPageBean){
        try {
            Map<String, Object> map = setMealsService.findAllMeals(queryPageBean);
            return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS,map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_SETMEAL_FAIL);
        }

    }


    @RequestMapping("/findSetMealDetail.do")
    public Result findSetMealDetail(@RequestParam("id") Integer id){
        try {
            System.out.println(id+"************************************************");
            Setmeal setmeal = setMealsService.findSetMealDetail(id);
            System.out.println(setmeal+"**********************************************************" );
            return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS,setmeal);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_SETMEAL_FAIL);
        }

    }

}
