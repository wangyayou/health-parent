package com.itheima.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.itcast.service.UserService;
import com.itheima.mapper.UserMapper;
import com.itheima.pojo.Menu;
import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;
import com.itheima.pojo.User;
import com.itheima.utils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JedisPool jedisPool;


    @Override
    public User findTotalUserInformationByUsername(String username) {
        User user = userMapper.findUserByUserName(username);
        Set<Role> roles = userMapper.findRoleByUId(user.getId());
        if (roles.size() > 0 && roles != null) {
            for (Role role : roles) {
                Set<Permission> permissions = userMapper.findPermissionByRoleId(role.getId());
                role.setPermissions(permissions);
            }
        }
        user.setRoles(roles);

        return user;
    }


    @Override
    public List<Menu> findUserMenuByUserName(String username) {
        Jedis jedis = jedisPool.getResource();
        String menus = jedis.get(username);
        List<Menu> JsonTolist = null;
        if (menus==null){
            List<Role> roleList  =  userMapper.findRolesByUsername(username);
            ArrayList<Menu> arrayList = new ArrayList<>();
            if (roleList.size()>0) {
                for (Role role : roleList) {
                    List<Menu> menuList = userMapper.findUserParentMenuByRoleId(role.getId());
                    if (menuList.size() <= 0) {
                        return menuList;
                    }
                    for (Menu menu : menuList) {
                        List<Menu> list = userMapper.findMenuChildrenByPath(menu.getId());
                        menu.setChildren(list);
                    }
                    arrayList.addAll(menuList);;
                    List<Menu> list = utils.QuChong(arrayList);
                    String json = JSON.toJSONString(list);
                    jedis.set(username,json);
                    JsonTolist=arrayList;
                }
            }
        }
        else {
            JsonTolist = JSON.parseObject(menus, new TypeReference<List<Menu>>() {});
            System.out.println("JsonTolist redis= " + JsonTolist);
        }
        return JsonTolist;
    }


    public  String getRedisUtils(String key){
        Jedis jedis = jedisPool.getResource();
        String s = jedis.get(key);
        List<Role> list = JSON.parseObject(s, new TypeReference<List<Role>>() {});
        if (s==null){
            jedis.close();
            return "-1";
        }else {
            jedis.close();
            return s;
        }

    }
    public  void putRedisUtils(String key,Object value){
        Jedis jedis = jedisPool.getResource();
        String toJSON = JSON.toJSONString(value);
        jedis.set(key,toJSON);
        jedis.close();
    }

}
