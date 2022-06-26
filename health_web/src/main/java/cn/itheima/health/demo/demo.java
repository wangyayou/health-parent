package cn.itheima.health.demo;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class demo {
    public static void main(String[] args) {
        BCryptPasswordEncoder ec = new BCryptPasswordEncoder();

        String encode = ec.encode("111");
        String encode1 = ec.encode("123");
        System.out.println("encode = " + encode);
        System.out.println("encode = " + encode1);
        System.out.println(ec.matches("111", encode));
        System.out.println(ec.matches("123", encode1));
    }
}
