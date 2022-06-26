package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itcast.entity.QueryPageBean;
import com.itcast.expection.myException;
import com.itcast.service.checkItemService;
import com.itheima.mapper.checkItemMapper;
import com.itheima.pojo.CheckItem;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class checkItemServiceImpl implements checkItemService {

    @Autowired
    private checkItemMapper checkItemMapper;

    @Override
    public void add(CheckItem checkItem) throws myException {
        int num = checkItemMapper.add(checkItem);
        if (num>0){
            System.out.println("数据天际成功");
        }else {
            throw new myException("添加数据失败");
        }
    }

    @Override
    public Map findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        List<CheckItem> list = checkItemMapper.findPage(queryPageBean.getQueryString());
        HashMap<String, Object> map = new HashMap<>();
        map.put("list",list);
        PageInfo<CheckItem> info = new PageInfo<>(list);
        map.put("total",info.getTotal());
        return map;
    }

    /**
     * @param id
     */
    @Override
    public void deleteItem (Integer id) throws myException  {
      int num =  checkItemMapper.deleteItem(id);
      if (num>0){

      }else {
          throw new myException("删除失败");
      }

    }

    @Override
    public void updateItem(CheckItem checkItem) throws myException {
     checkItemMapper.updateItem(checkItem);

    }

    @Override
    public Map findAllCheckItem() {
      List<CheckItem> list = checkItemMapper.findAllCheckItem();
        HashMap<String, Object> map = new HashMap<>();
        map.put("list",list);
        return map;
    }


}
