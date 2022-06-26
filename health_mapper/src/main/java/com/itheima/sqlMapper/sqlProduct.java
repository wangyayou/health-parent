package com.itheima.sqlMapper;

import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;
import com.itheima.pojo.Setmeal;
import org.apache.commons.lang3.StringUtils;

public class sqlProduct {

    public String updateItem(CheckItem checkItem) {
        String sql = "  update  t_checkitem  set  ";
        if (!StringUtils.isEmpty(checkItem.getSex())){
            sql += " sex = #{sex}, ";
        }
        if (!StringUtils.isEmpty(checkItem.getAge())){
            sql += " age = #{age}, ";
        }
        if (!StringUtils.isEmpty(checkItem.getPrice()+"")){
            sql += " price = #{price}, ";
        }
        if (!StringUtils.isEmpty(checkItem.getType())){
        sql += " type = #{type}, ";
        }
        if (!StringUtils.isEmpty(checkItem.getAttention())){
            sql += " attention = #{attention}, ";
        }
        if (!StringUtils.isEmpty(checkItem.getRemark())){
            sql += " remark = #{remark}, ";
        }
        sql += " name = #{name} where id = #{id} ";

        return sql;
    }

    public String updateCheckGroup(CheckGroup checkGroup){
        String sql = "  update  t_checkgroup  set  ";
        if (!StringUtils.isEmpty(checkGroup.getSex())){
            sql += " sex = #{sex}, ";
        }
        if (!StringUtils.isEmpty(checkGroup.getCode())){
            sql += " code = #{code}, ";
        }
        if (!StringUtils.isEmpty(checkGroup.getHelpCode())){
            sql += " HelpCode = #{helpCode}, ";
        }
        if (!StringUtils.isEmpty(checkGroup.getAttention())){
            sql += " attention = #{attention}, ";
        }
        if (!StringUtils.isEmpty(checkGroup.getRemark())){
            sql += " remark = #{remark}, ";
        }
        sql += " name = #{name} where id = #{id} ";

        return sql;
    }

    public String UpdateSetMealsProperty(Setmeal setmeal){
        String sql = "  update  t_setmeal  set  ";

        if (!StringUtils.isEmpty(setmeal.getSex())){
            sql += " sex = #{sex}, ";
        }
        if (!StringUtils.isEmpty(setmeal.getPrice()+"")){
            sql += " price = #{price}, ";
        }
        if (!StringUtils.isEmpty(setmeal.getAge())){
            sql += " age = #{age}, ";
        }
        if (!StringUtils.isEmpty(setmeal.getCode())){
            sql += " code = #{code}, ";
        }
        if (!StringUtils.isEmpty(setmeal.getImg())){
            sql += " img = #{img}, ";
        }
        if (!StringUtils.isEmpty(setmeal.getHelpCode())){
            sql += " HelpCode = #{helpCode}, ";
        }
        if (!StringUtils.isEmpty(setmeal.getAttention())){
            sql += " attention = #{attention}, ";
        }
        if (!StringUtils.isEmpty(setmeal.getRemark())){
            sql += " remark = #{remark}, ";
        }
        sql += " name = #{name} where id = #{id} ";

        return sql;
    }
}
