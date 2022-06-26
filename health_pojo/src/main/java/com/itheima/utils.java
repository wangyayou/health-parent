package com.itheima;


import com.itheima.pojo.Menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class utils {
    public static java.util.List<Menu> QuChong(List<Menu> list){
        HashSet<Menu> meSet = new HashSet<>();
        /*
         将菜单集合中多余的菜单对象去除
         */
        for (Menu menu : list) {
            meSet.add(menu);
        }
        /*
        拿到所有菜单的ID并排序封装到数组中
         */
        int lenght = meSet.size();
        Integer[] arr = new Integer[lenght];
        int i =0;
        for (Menu menu : meSet) {
            arr[i]=menu.getId();
            i++;
        }
        Arrays.sort(arr);
        /*
        遍历数组，寻找菜单添加到list中
         */
        ArrayList<Menu> meList2 = new ArrayList<>();
        for (int j = 0; j < arr.length; j++) {
            for (Menu menu : meSet) {
                if (menu.getId()==arr[j]){
                    meList2.add(menu);
                }
            }
        }
        return meList2;
    }
}
