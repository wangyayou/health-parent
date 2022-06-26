package com.mobile.health.controller;


import com.itcast.entity.Result;
import com.itcast.resources.MessageConstant;
import com.itcast.service.MemberService;
import com.itcast.service.UserService;
import com.itcast.service.orderInfoService;
import com.itcast.utils.CheckCodeUtils;
import com.itheima.pojo.Member;
import com.itheima.pojo.Order;
import com.itheima.pojo.Setmeal;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/orderInfo")
public class orderInfoController {

    @Reference
    private orderInfoService orderInfoService;

    @Autowired
    private JedisPool jedisPool;

    @Reference
    private MemberService MembeService;

    @RequestMapping("/findSetMealById.do")
    public Result  findSetMealById(@RequestParam("id") Integer id){
        try {
            Setmeal setmeal =  orderInfoService.findSetMealById(id);
            return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS,setmeal);
        } catch (Exception e) {
            return new Result(false, MessageConstant.QUERY_SETMEAL_FAIL);
        }

    }

    // checkCodeCurrent

    @RequestMapping("/checkCodeCurrent.do")
    public Result  checkCodeCurrent(@RequestParam("code") String code,@RequestParam("phone") String phone){
        try {
            Jedis jedis = jedisPool.getResource();
            if (!StringUtils.isEmpty(code)){
                String scode = jedis.get(phone);
                jedis.close();
                if (code.equals(scode)){
                    return new Result(true,"验证码输入正确");
                }else {
                    return new Result(true,"验证码输入错误");
                }
            }
            return new Result(true, MessageConstant.VALIDATECODE_ERROR);
        } catch (Exception e) {
            return new Result(false, MessageConstant.QUERY_SETMEAL_FAIL);
        }

    }



    @RequestMapping("/sendCodeByAliYun.do")
    public Result  sendCodeByAliYun(@RequestParam("phone") String pnone){
        try {
            String code = CheckCodeUtils.getCheckCode(4);
            System.out.println("*********************"+code);
            Jedis jedis = jedisPool.getResource();
            jedis.setex(pnone,90,code);
            jedis.close();
            return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
        } catch (Exception e) {
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }

    }

    // AddOrder
    @RequestMapping("/AddOrder.do")
    public Result  AddOrder(@RequestBody Map<String,String> map) throws ParseException {
        String orderType = map.get("orderType");
        String sex = map.get("sex");
        String name = map.get("name");
        String sermealId = map.get("setmealId");
        System.out.println("sermealId = " + sermealId);
        String telephone = map.get("telephone");
        String idCard = map.get("idCard");
        String orderDate = map.get("orderDate");
        int memberId = -1;
        int num =  MembeService.findUserByIdCard(idCard);
        if (num==-1) {
            // 用户没有注册
            System.out.println("用户没有注册");
            Member member = new Member();
            member.setSex(sex);
            member.setIdCard(idCard);
            member.setName(name);
            member.setPhoneNumber(telephone);
            member.setRegTime(new Date());
            MembeService.AddUMember(member);
            System.out.println("member = " + member);
            int ID = MembeService.findUserByIdCard(member.getIdCard());
            memberId = ID;
        }else {
            memberId = num;
        }
        //开始创建订单
        int count=  MembeService.CurrentAndreservedReservationCompareByDate(orderDate);
        if (count>0){
         if (MembeService.findOrderBySetmealIdAndOrderId(sermealId,memberId,orderDate)==null){
            Order order = new Order();
            order.setMemberId(memberId);
            order.setOrderDate(new SimpleDateFormat("yyyy-MM-dd").parse(orderDate));
            order.setOrderType(orderType);
            order.setOrderStatus("0");
            order.setSetmealId(Integer.parseInt(sermealId));
            // 可以预约
            try {
                MembeService.AddOrder(order);
                Integer orderId = order.getId();
                System.out.println("orderId ***********************************= " + orderId);
                MembeService.updateSetMealReservationBySermealId(sermealId);
                Order order1 =  MembeService.findOrderBySetmealIdAndOrderId(sermealId,memberId,orderDate);
                return new Result(true,MessageConstant.ADD_ORDER_SUCCESS,order1);
            } catch (Exception e) {
                e.printStackTrace();
                return new Result(false,MessageConstant.ADD_ORDER_FAIL);
            }
         }else {
           return new Result(false,"你今天已经预约过了");
         }
        }else {
           return new Result(false,MessageConstant.ORDER_FULL);
        }

    }


    // findOrderInfoByOrderId?orderId
    @RequestMapping("/findOrderInfoByOrderId.do")
    public Result  findOrderInfoByOrderId(@RequestParam("orderId") String orderId){
        try {
            Map<String,String> map = orderInfoService.findOrderInfoByOrderId(orderId);
            return new Result(true, MessageConstant.QUERY_ORDERSETTING_SUCCESS,map);
        } catch (Exception e) {
            return new Result(false, MessageConstant.QUERY_ORDERSETTING_FAIL);
        }

    }


}
