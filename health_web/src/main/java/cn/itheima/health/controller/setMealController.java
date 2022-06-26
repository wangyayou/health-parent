package cn.itheima.health.controller;

import com.itcast.entity.QueryPageBean;
import com.itcast.entity.Result;
import com.itcast.resources.MessageConstant;
import com.itcast.resources.RedisConstant;
import com.itcast.service.setMealService;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.Setmeal;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/setMeal")
public class setMealController {

   /* @Autowired
    private JedisPool jedisPool;*/

    @Reference
    private setMealService setMealService;

    @RequestMapping("/addSetMeal.do")
    public Result addSetMeal(@RequestParam("checkgroupIds") int[] checkgroupIds , @RequestBody Setmeal setmeal){
        try {
            setMealService.addSetMeal(checkgroupIds,setmeal);
            /*Jedis resource = jedisPool.getResource();
            resource.sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,setmeal.getImg());
            resource.close();*/
            return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_SETMEAL_FAIL);

        }

    }

    @RequestMapping("/findAllMeals.do")
    public Result findAllMeals(@RequestBody QueryPageBean queryPageBean){
        try {
           Map<String,Object> map = setMealService.findAllMeals(queryPageBean);
            return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS,map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, MessageConstant.QUERY_SETMEAL_FAIL);
        }

    }

    // findCheckGroupBySid
    @RequestMapping("/findCheckGroupBySid.do")
    public Result findCheckGroupBySid(@RequestParam("id") Integer id){
        try {
            List<Integer> list = setMealService.findCheckGroupBySid(id);
            return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, MessageConstant.QUERY_SETMEAL_FAIL);
        }

    }
// UpdateSetMeals

    @RequestMapping("/UpdateSetMeals.do")
    public Result UpdateSetMeals(@RequestParam("checkitemIds") int[] checkitemIds, @RequestBody Setmeal setmeal){
        try {
            setMealService.UpdateSetMeals(checkitemIds,setmeal);
            return   new Result(true,MessageConstant.EDIT_SETMEAL_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return   new Result(false,MessageConstant.EDIT_SETMEAL_FAIL);
        }
    }
    //delSetMealsByID
    @RequestMapping("/delSetMealsByID.do")
    public Result delSetMealsByID(@RequestParam("id") Integer id){
        try {
            setMealService.delSetMealsByID(id);
            return new Result(true, MessageConstant.DELETE_SETMEAL_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, MessageConstant.DELETE_SETMEAL_FAIL);
        }

    }

    @RequestMapping("/findSetMealReport.do")
    public Result findSetMealReport(){
        try {
         Map  map = setMealService.findSetMealReport();
            return new Result(true, MessageConstant.GET_SETMEAL_COUNT_REPORT_SUCCESS,map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_SETMEAL_COUNT_REPORT_FAIL);
        }

    }
}
