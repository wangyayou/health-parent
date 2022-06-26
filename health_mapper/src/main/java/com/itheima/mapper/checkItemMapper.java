package com.itheima.mapper;

import com.itheima.pojo.CheckItem;
import com.itheima.sqlMapper.sqlProduct;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface checkItemMapper {

    int add(CheckItem checkItem);

    List<CheckItem> findPage(@Param("queryString") String queryString);

    @Update("update t_checkitem set is_delete = 1 where id = #{id}")
    int deleteItem(@Param("id") Integer id);

    @SelectProvider(type= sqlProduct.class,method = "updateItem")
    void updateItem(CheckItem checkItem);

    @Select("select * from t_checkitem ")
    List<CheckItem> findAllCheckItem();
}
