package cn.itheima.health.controller;

import com.itcast.entity.QueryPageBean;
import com.itcast.entity.Result;
import com.itcast.resources.MessageConstant;
import com.itcast.service.checkItemService;
import com.itheima.pojo.CheckItem;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/checkItem")
public class checkItemController {

    @Reference
    private checkItemService checkItemService;

    @RequestMapping("/add.do")
    public Result add(@RequestBody CheckItem checkItem){
        try {
            checkItemService.add(checkItem);
            return   new Result(true,MessageConstant.ADD_CHECKITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return   new Result(false,MessageConstant.ADD_CHECKITEM_FAIL);

        }
    }


    @RequestMapping("/findPage.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result  findPage(@RequestBody QueryPageBean queryPageBean){
        try {
            Map map =checkItemService.findPage(queryPageBean);
            System.out.println(map+"***********************************************");
            return   new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,map);
        } catch (Exception e) {
            e.printStackTrace();
            return   new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);

        }
    }

    @RequestMapping("/deleteItem.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result deleteItem(@RequestParam("id") Integer id){
        try {
            checkItemService.deleteItem(id);
            return   new Result(true,MessageConstant.DELETE_CHECKITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return   new Result(false,MessageConstant.DELETE_CHECKITEM_FAIL);

        }
    }

    @RequestMapping("/updateItem.do")
    public Result  updateItem(@RequestBody CheckItem checkItem){
        try {
            checkItemService.updateItem(checkItem);
            return   new Result(true,MessageConstant.EDIT_CHECKITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return   new Result(false,MessageConstant.EDIT_CHECKITEM_FAIL);

        }
    }


    @RequestMapping("/findAllCheckItem.do")
    public Result  findAllCheckItem(){
        try {
            Map map = checkItemService.findAllCheckItem();
            return   new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,map);
        } catch (Exception e) {
            e.printStackTrace();
            return   new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

}
