package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itcast.entity.QueryPageBean;
import com.itcast.service.checkGroupService;
import com.itheima.mapper.checkGroupMapper;
import com.itheima.pojo.CheckGroup;
import com.itcast.expection.myException;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class checkGroupServiceImpl implements checkGroupService {

    @Autowired
    private checkGroupMapper checkGroupMapper;

    @Override
    public void addGroup(int[] checkitemIds, CheckGroup checkGroup)throws myException {
      int num =  checkGroupMapper.addGroup(checkGroup);
       if (num==0){
           throw  new myException("出现异常了呢");
       }
        Integer id = checkGroup.getId();
        for (int checkitemId : checkitemIds) {
          int num1 =  checkGroupMapper.addCheckGroupAndCheckitem(id,checkitemId);
          if (num1==0){
           throw  new myException("出现异常了呢");
          }
        }

    }

    @Override
    public Map<String, Object> findCheckGroup(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        HashMap<String, Object> map = new HashMap<>();
        List<CheckGroup> list =  checkGroupMapper.findCheckGroup(queryPageBean.getQueryString());
        PageInfo<CheckGroup> info = new PageInfo<>(list);
        map.put("list",list);
        map.put("total",info.getTotal());
        return map;
    }

    @Override
    public List<Integer> findCheckItemByGroupId(Integer id) {
        return checkGroupMapper.findCheckItemByGroupId(id);
    }

    @Override
    public void updateCheckGroupAndItem(int[] checkitemIds, CheckGroup checkGroup) {
        checkGroupMapper.releveGroupAndItemConnection(checkGroup.getId());
         checkGroupMapper.updateCheckGroup(checkGroup);
        if (checkitemIds.length>0) {
            for (int checkitemId : checkitemIds) {
                checkGroupMapper.addCheckGroupAndCheckitem(checkGroup.getId(), checkitemId);
            }
        }

    }

    @Override
    public void deleteCheckGroupById(Integer id) {
        checkGroupMapper.deleteCheckGroupById(id);
    }

    @Override
    public List<CheckGroup> findAllGroup() {
        return checkGroupMapper.findAllGroup();
    }
}
