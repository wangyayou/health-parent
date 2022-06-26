package com.itheima.mapper;

import com.itheima.pojo.CheckGroup;
import com.itheima.sqlMapper.sqlProduct;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface checkGroupMapper {

    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    @Insert("insert into t_checkgroup values(null,#{code},#{name},#{helpCode},#{sex},#{remark},#{attention},#{is_delete}) ")
    int addGroup(CheckGroup checkGroup);

    @Insert("insert into t_checkgroup_checkitem values(#{id},#{checkitemId})")
    int addCheckGroupAndCheckitem(@Param("id") Integer id,@Param("checkitemId") int checkitemId);

  //  @Select(" select * from t_checkgroup ")
    List<CheckGroup> findCheckGroup(@Param("queryString") String queryString);

    @Select("select CHECKITEM_ID from t_checkgroup_checkitem where CHECKGROUP_ID =#{id}")
    List<Integer> findCheckItemByGroupId(@Param("id") Integer id);

    @Delete("delete from  t_checkgroup_checkitem where CHECKGROUP_ID =#{id} ")
    void releveGroupAndItemConnection(@Param("id") Integer id);

    @SelectProvider(type = sqlProduct.class,method ="updateCheckGroup" )
    void updateCheckGroup(CheckGroup checkGroup);

    @Update("update t_checkgroup set is_delete = 1 where id = #{id} ")
    void deleteCheckGroupById(@Param("id")Integer id);


    @Select("select * from t_checkgroup ")
    List<CheckGroup> findAllGroup();

}
