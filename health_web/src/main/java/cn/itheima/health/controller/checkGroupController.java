package cn.itheima.health.controller;

import com.itcast.entity.QueryPageBean;
import com.itcast.entity.Result;
import com.itcast.resources.MessageConstant;
import com.itcast.service.checkGroupService;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/checkGroup")
public class checkGroupController {
    @Reference
    private checkGroupService checkGroupService;

    @RequestMapping("/addGroup.do")
    public Result addGroup(@RequestParam("checkitemIds") int[] checkitemIds, @RequestBody CheckGroup checkGroup){
        try {
            checkGroupService.addGroup(checkitemIds,checkGroup);
            return   new Result(true,MessageConstant.ADD_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return   new Result(false,MessageConstant.ADD_CHECKGROUP_FAIL);
        }
    }

    @RequestMapping("/findCheckGroup.do")
    public Result findCheckGroup(@RequestBody QueryPageBean queryPageBean){
        try {
            Map map = checkGroupService.findCheckGroup(queryPageBean);
            return   new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,map);
        } catch (Exception e) {
            e.printStackTrace();
            return   new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }
    // findCheckItemByGroupId
    @RequestMapping("/findCheckItemByGroupId.do")
    public Result findCheckItemByGroupId(@RequestParam("id") Integer id){
        try {
            List<Integer> list = checkGroupService.findCheckItemByGroupId(id);
            return   new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return   new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }
    //updateCheckGroupAndItem
    // deleteCheckGroupById
    @RequestMapping("/deleteCheckGroupById.do")
    public Result deleteCheckGroupById(@RequestParam("id") Integer id){
        try {
             checkGroupService.deleteCheckGroupById(id);
            return   new Result(true,MessageConstant.DELETE_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return   new Result(false,MessageConstant.DELETE_CHECKGROUP_FAIL);
        }
    }

    @RequestMapping("/updateCheckGroupAndItem.do")
    public Result updateCheckGroupAndItem(@RequestParam("checkitemIds") int[] checkitemIds, @RequestBody CheckGroup checkGroup){
        try {
            checkGroupService.updateCheckGroupAndItem(checkitemIds,checkGroup);
            return   new Result(true,MessageConstant.EDIT_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return   new Result(false,MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
    }
    @RequestMapping("/findAllGroup.do")
    public Result findAllGroup(){
        try {
           List<CheckGroup> list = checkGroupService.findAllGroup();
            return   new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return   new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    // findAllGroup
}
