package com.itcast.service;

import com.itcast.entity.QueryPageBean;
import com.itcast.expection.myException;
import com.itheima.pojo.CheckGroup;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface checkGroupService {

    void addGroup( int[] checkitemIds,CheckGroup checkGroup) throws myException;

    Map<String, Object> findCheckGroup(QueryPageBean queryPageBean);

    List<Integer> findCheckItemByGroupId(Integer id);

    void updateCheckGroupAndItem(int[] checkitemIds, CheckGroup checkGroup) throws myException;

    void deleteCheckGroupById(Integer id);

    List<CheckGroup> findAllGroup();

}
