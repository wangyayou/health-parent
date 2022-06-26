package com.itcast.service;

import com.itcast.entity.QueryPageBean;
import com.itheima.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface setMealService {
    void addSetMeal(int[] checkgroupIds, Setmeal setmeal);

    Map<String, Object> findAllMeals(QueryPageBean queryPageBean);

    List<Integer> findCheckGroupBySid(Integer id);

    void delSetMealsByID(Integer id);

    void UpdateSetMeals(int[] checkitemIds, Setmeal setmeal);

    Setmeal findSetMealDetail(Integer id);

    Map findSetMealReport();
}
