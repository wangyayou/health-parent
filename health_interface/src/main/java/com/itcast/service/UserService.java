package com.itcast.service;

import com.itheima.pojo.Menu;
import com.itheima.pojo.User;

import java.util.List;

public interface UserService {

    User findTotalUserInformationByUsername(String username);


    List<Menu> findUserMenuByUserName(String username);


}
