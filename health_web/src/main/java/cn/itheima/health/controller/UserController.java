package cn.itheima.health.controller;

import com.itcast.entity.Result;
import com.itcast.resources.MessageConstant;
import com.itcast.service.UserService;
import com.itheima.pojo.Menu;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Reference
    private UserService userService;

     @RequestMapping("/getUsername.do")
    public Result getUsername(Authentication authentication){
         try {
             String username = authentication.getName();
             return new Result(true, MessageConstant.GET_USERNAME_SUCCESS,username);
         } catch (Exception e) {
             e.printStackTrace();
             return new Result(true, MessageConstant.GET_USERNAME_FAIL);

         }

     }

    @RequestMapping("/findUserMenuByUserName.do")
    public Result findUserMenuByUserName(@RequestParam("username") String username){
        try {
            List<Menu> menuList = userService.findUserMenuByUserName(username);

            return new Result(true,MessageConstant.GET_MENU_SUCCESS,menuList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.GET_MENU_FAIL);
        }
    }
}
