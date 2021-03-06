package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itcast.entity.QueryPageBean;
import com.itcast.resources.RedisConstant;
import com.itcast.service.setMealService;
import com.itheima.mapper.setMealMapper;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;
import com.itheima.pojo.Setmeal;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class setMealServiceImpl implements setMealService {
    @Autowired
    private setMealMapper setMealMapper;
    @Autowired
    private JedisPool jedisPool;

    @Override
    public void addSetMeal(int[] checkgroupIds, Setmeal setmeal) {
        setMealMapper.addSetMealNoGroupIds(setmeal);
        Integer id = setmeal.getId();
        if (checkgroupIds.length > 0) {
            for (int checkgroupId : checkgroupIds) {
                setMealMapper.AddMealAndGroupIdsConnection(id, checkgroupId);
            }
        }
        if (setmeal.getImg() != null) {
            Jedis jedis = jedisPool.getResource();
            jedis.sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES, setmeal.getImg());
            jedis.close();
        }

    }

    @Override
    public Map<String, Object> findAllMeals(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        List<Setmeal> list = setMealMapper.findAllMeals(queryPageBean.getQueryString());
        PageInfo<Setmeal> info = new PageInfo<>(list);
        HashMap<String, Object> map = new HashMap<>();
        map.put("list", list);
        map.put("total", info.getTotal());
        return map;
    }

    @Override
    public List<Integer> findCheckGroupBySid(Integer id) {

        return setMealMapper.findCheckGroupBySid(id);
    }

    @Override
    public void delSetMealsByID(Integer id) {
        setMealMapper.delSetMealsByID(id);
    }

    @Override
    public void UpdateSetMeals(int[] CheckGroupId, Setmeal setmeal) {
        setMealMapper.UpdateSetMealsProperty(setmeal);
        Integer id = setmeal.getId();
        setMealMapper.delMealAndCheckGroupBySid(id);
        for (int checkGroupId : CheckGroupId) {
            setMealMapper.AddMealAndGroupIdsConnection(id, checkGroupId);
        }
    }

    @Override
    public Setmeal findSetMealDetail(Integer id) {
        /*
         ?????????????????????
            1??????xml ????????????????????????
            2??????????????????????????????
         */
        /*
         ?????????????????????
            ???service???????????????????????????
                1 ?????????ID????????????????????????????????????????????????
                2.????????????ID?????????????????????List<CheckGroup>???????????????????????????????????????????????????
                3.???????????????IDS???????????????????????????IDS?????????????????????????????????????????????????????????
         */
         Setmeal setmeal = setMealMapper.findSetMealDetail(id);
        List<CheckGroup> groupList = setMealMapper.findCheckGroupsBySid(setmeal.getId());
        for (CheckGroup group : groupList) {
            List<CheckItem> itemList = setMealMapper.findCheckItemByGid(group.getId());
            group.setCheckItems(itemList);
        }
        setmeal.setCheckGroups(groupList);
        return setmeal;
        /*Setmeal setmeal = setMealMapper.findSetMealDetail(id);
        System.out.println(setmeal+"****************************************************************");
        return setmeal;*/
      /*  Setmeal setmeal = setMealMapper.findSetMealDetailBySid(id);
        System.out.println(setmeal+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        List<Integer> groupIdList = setMealMapper.findCheckGroupIdsBySid(id);
        List<CheckGroup> groupList = setMealMapper.findCheckGroupByGids(groupIdList);
        for (CheckGroup checkGroup : groupList) {
            List<Integer> itemIDList = setMealMapper.findCheckitemIdsByGId(checkGroup.getId());
            List<CheckItem> checkItemList = setMealMapper.findCheckItemsByItemIds(itemIDList);
            checkGroup.setCheckItems(checkItemList);
            System.out.println(checkItemList+"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        }
        System.out.println(groupIdList+"**************************************************");
        setmeal.setCheckGroups(groupList);
        return setmeal;*/

    }

    @Override
    public Map findSetMealReport() {
     List<Setmeal> setmealList  =  setMealMapper.findAllMeals("");
        ArrayList<String> mealName = new ArrayList<>();
        for (Setmeal setmeal : setmealList) {
            mealName.add(setmeal.getName());
        }
        ArrayList<Map> mealCount   =  setMealMapper.findSailedMealNameAndCount();
        HashMap<String, List> map = new HashMap<>();
        map.put("mealCount",mealCount);
        map.put("mealName",mealName);
        return map;
    }


}
