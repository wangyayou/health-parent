package com.itheima.mapper;

import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;
import com.itheima.pojo.Setmeal;
import com.itheima.sqlMapper.sqlProduct;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface setMealMapper {


          /*    @Select("<script>SELECT t_checkgroup.* from \n" +
            "(select ts.*,tsmcp.CHECKGROUP_ID from t_setmeal_checkgroup tsmcp ,(SELECT * from t_setmeal tsml where tsml.id = #{id})  ts where ts.id = tsmcp.SETMEAL_ID)\n" +
            " groupid \n" +
            "INNER JOIN t_checkgroup on groupid.checkgroup_id = t_checkgroup.ID</script>")*/
    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    @Insert("insert into t_setmeal values (null,#{name},#{code},#{helpCode},#{sex},#{age},#{price},#{remark},#{attention},#{img})")
    void addSetMealNoGroupIds(Setmeal setmeal);

    @Insert("insert into t_setmeal_checkgroup values(#{id},#{checkgroupId})")
    void AddMealAndGroupIdsConnection(@Param("id") Integer id,@Param("checkgroupId")  int checkgroupId);

    List<Setmeal> findAllMeals(@Param("queryString")String queryString);

    @Select("select CHECKGROUP_ID from t_setmeal_checkgroup where SETMEAL_ID=#{id}")
    List<Integer> findCheckGroupBySid(@Param("id") Integer id);

    @Update("update t_setmeal set is_delete = 1 where id =#{id} ")
    void delSetMealsByID(@Param("id") Integer id);

    @SelectProvider(type = sqlProduct.class,method ="UpdateSetMealsProperty" )
    void UpdateSetMealsProperty(Setmeal setmeal);

    @Delete("delete from  t_setmeal_checkgroup where SETMEAL_ID =#{id} ")
    void delMealAndCheckGroupBySid(@Param("id")Integer id);

    @Select("select * from t_setmeal where id = #{id} ")
    Setmeal findSetMealDetail(@Param("id")Integer id);

 @Select("<script>SELECT t_checkgroup.* from \n" +
            "(select ts.*,tsmcp.CHECKGROUP_ID from t_setmeal_checkgroup tsmcp ,(SELECT * from t_setmeal tsml where tsml.id = #{id} and tsml.is_delete =0)  ts where ts.id = tsmcp.SETMEAL_ID)\n" +
            " groupid \n" +
            "INNER JOIN t_checkgroup on groupid.checkgroup_id = t_checkgroup.ID</script>")
    List<CheckGroup> findCheckGroupsBySid(@Param("id")Integer id);
 /*@Select("<script>SELECT t_checkitem.* from\n" +
            " (SELECT t_checkgroup_checkitem.CHECKITEM_ID from \n" +
            "(SELECT t_checkgroup.* from \n" +
            "(select ts.*,tsmcp.CHECKGROUP_ID from t_setmeal_checkgroup tsmcp ,(SELECT * from t_setmeal tsml where tsml.id = #{id} and tsml.is_delete =0)  ts where ts.id = tsmcp.SETMEAL_ID)\n" +
            " groupid \n" +
            "INNER JOIN t_checkgroup on groupid.checkgroup_id = t_checkgroup.ID)  \n" +
            "groupandsermeal\n" +
            "INNER JOIN t_checkgroup_checkitem on groupandsermeal.id = t_checkgroup_checkitem.CHECKGROUP_ID)\n" +
            " checkitem INNER JOIN t_checkitem on checkitem.checkitem_id = t_checkitem.ID<script>")
    */

 // @Select("<script>SELECT * from  (SELECT t_checkgroup_checkitem.CHECKITEM_ID from t_checkgroup_checkitem where t_checkgroup_checkitem.CHECKGROUP_ID = #{id})  \n" +
   //         "cidtable INNER JOIN t_checkitem where cidtable.checkitem_id = t_checkitem.ID\n</script>")

 @Select("<script>SELECT t_checkitem.* from  (SELECT t_checkgroup_checkitem.CHECKITEM_ID from t_checkgroup_checkitem where t_checkgroup_checkitem.CHECKGROUP_ID = #{id})  \n" +
            "cidtable INNER JOIN t_checkitem where cidtable.checkitem_id = t_checkitem.ID</script>")
    List<CheckItem> findCheckItemByGid(@Param("id")Integer id);


    @Select("select * from t_setmeal where id = #{id}")
    Setmeal findSetMealDetailBySid(@Param("id")Integer id);

    @Select("select checkgroup_id from t_setmeal_checkgroup where setmeal_id = #{id} ")
    List<Integer> findCheckGroupIdsBySid(@Param("id")Integer id);


    List<CheckGroup> findCheckGroupByGids(@Param("groupIdList")List<Integer> groupIdList);

    @Select("select checkitem_id from t_checkgroup_checkitem where checkgroup_id = #{id}")
    List<Integer> findCheckitemIdsByGId(@Param("id")Integer id);

    List<CheckItem> findCheckItemsByItemIds(@Param("itemIdList")List<Integer> itemIdList);

    @Select("select count(*) from t_order where SETMEAL_ID = #{id}")
    int findSailedMealCountBySid(@Param("id")Integer id);

    @Select(" select  count(1) value,ts.NAME name from t_order tor ,t_setmeal ts where tor.SETMEAL_ID = ts.ID group by tor.SETMEAL_ID")
    ArrayList<Map> findSailedMealNameAndCount();
}
