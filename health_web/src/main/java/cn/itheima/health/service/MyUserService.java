package cn.itheima.health.service;

import com.itcast.service.UserService;
import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;
import com.itheima.pojo.User;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Component("userService")
public class MyUserService implements UserDetailsService {

    @Reference
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user =  userService.findTotalUserInformationByUsername(username);
        System.out.println(user.getPassword()+"****************************");
        if (user==null){
            return null;
        }
        ArrayList<GrantedAuthority> GAList = new ArrayList<>();
        Set<Role> roles = user.getRoles();
        if (roles.size()>0&&roles!= null){
            for (Role role : roles) {
                SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.getKeyword());
                GAList.add(simpleGrantedAuthority);
                Set<Permission> permissions = role.getPermissions();
                if (permissions.size()>0&& permissions!=null){
                    for (Permission permission : permissions) {
                        SimpleGrantedAuthority simpleGrantedAuthority1 = new SimpleGrantedAuthority(permission.getKeyword());
                        GAList.add(simpleGrantedAuthority1);

                }
                }
            }
            System.out.println(GAList+"****************************************");
            return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),GAList);
        }else {
            return null;
        }

    }
}
