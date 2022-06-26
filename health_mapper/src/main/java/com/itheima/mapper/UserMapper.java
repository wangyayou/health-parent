package com.itheima.mapper;

import com.itheima.pojo.Menu;
import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;
import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

public interface UserMapper {

    @Select(" select * from t_user where username = #{username} ")
    User findUserByUserName(@Param("username") String username);

    @Select(" select t_role.* from t_user_role ,t_role where t_user_role.USER_ID = #{uid} and  t_user_role.ROLE_ID = t_role.ID  ")
    Set<Role> findRoleByUId(@Param("uid")Integer uid);

    @Select("select t_permission.* from t_role_permission , t_permission where t_role_permission.ROLE_ID = #{rid} and t_role_permission.PERMISSION_ID = t_permission.ID")
    Set<Permission> findPermissionByRoleId(@Param("rid")Integer rid);

    List<Role> findRolesByUsername(@Param("username")String username);

    List<Menu> findUserParentMenuByRoleId(@Param("id")Integer id);

    List<Menu> findMenuChildrenByPath(@Param("id")Integer id);
}
