package com.itcast.service;

import com.itcast.entity.QueryPageBean;
import com.itcast.expection.myException;
import com.itheima.pojo.CheckItem;

import java.util.Map;

public interface checkItemService {

    void add(CheckItem checkItem) throws myException;

    Map findPage(QueryPageBean queryPageBean);

   void deleteItem(Integer id) throws myException;

    void updateItem(CheckItem checkItem) throws myException;

    Map findAllCheckItem();


}
